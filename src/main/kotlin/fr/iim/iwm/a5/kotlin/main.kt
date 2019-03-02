package fr.iim.iwm.a5.kotlin

import io.ktor.application.call
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.response.respond
import io.ktor.freemarker.*
import freemarker.cache.*
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.auth.*
import io.ktor.http.Parameters
import io.ktor.request.receiveParameters
import io.ktor.response.respondRedirect
import io.ktor.routing.post
import io.ktor.sessions.*

fun Application.cmsApp(
    articlesController: ArticlesController,
    usersController: UsersController
) {
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }

    install(Sessions) {
        cookie<Session>("logged")
    }

    install(Authentication) {
        form("myauth1") {
            userParamName = "username"
            passwordParamName = "password"
            challenge = FormAuthChallenge.Redirect{ "/login" }
            validate { credentials ->

                val username = usersController.login(credentials.name, credentials.password)

                if (username != null) {

                    UserIdPrincipal(credentials.name)
                } else {
                    null
                }
            }
            skipWhen { call -> call.sessions.get<Session>() != null }
        }
    }

    routing {
        get("/") {
            val template = articlesController.list()

            call.respond(template)
        }
        get("/article/{id}") {
            val id = call.parameters["id"]!!.toInt()

            val template = articlesController.show(id)

            call.respond(template)
        }
        post("/article/{id}") {
            val id = call.parameters["id"]!!.toInt()

            val postParameters: Parameters = call.receiveParameters()

            val textComment = postParameters["comment"]

            if (textComment !== null) {
                articlesController.createComment(id, textComment)
            }
            call.respondRedirect("/article/$id")
        }

        get("/login") {
            val template = usersController.loginForm()

            call.respond(template)
        }

        authenticate("myauth1") {
            post("/login") {
                val principal = call.authentication.principal<UserIdPrincipal>()

                call.sessions.set(Session(principal!!.name))

                call.respondRedirect("/admin")
            }
            get("/admin") {
                val template = usersController.dashboard()

                call.respond(template)
            }
            get("/admin/article/{id}") {
                val id = call.parameters["id"]!!.toInt()

                val template = articlesController.showAdmin(id)

                call.respond(template)
            }
            get("/admin/article/create") {
                val template = articlesController.create()

                call.respond(template)
            }
            post("/admin/article/create") {
                val postParameters: Parameters = call.receiveParameters()

                val textContent = postParameters["textContent"]
                val title = postParameters["title"]

                if (textContent !== null && title != null) {
                    articlesController.store(title, textContent)
                }

                call.respondRedirect("/admin/article/create")
            }
            post("/article/delete/{id}") {
                val id = call.parameters["id"]!!.toInt()

                articlesController.delete(id)

                call.respondRedirect("/admin")
            }
            post("/comment/delete/{id}") {
                val id = call.parameters["id"]!!.toInt()

                val postParameters: Parameters = call.receiveParameters()

                val articleId = postParameters["article_id"]

                articlesController.deleteComment(id)

                call.respondRedirect("/admin/article/$articleId")
            }
            // POST:/logout clears the session and redirects to the '/' page.
            get("/logout") {
                call.sessions.clear<Session>()

                call.respondRedirect("/")
            }
        }
    }
}

fun main() {

    val model = MysqlModel("jdbc:mysql://localhost:8889/cms", "root", "root")

    val articlesController = ArticlesControllerImp(model)

    val usersController = UsersControllerImp(model)

    embeddedServer(Netty,8080) { cmsApp(articlesController, usersController) }.start(true)
}