package fr.iim.iwm.a5.kotlin

import io.ktor.auth.UserIdPrincipal
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode

class UsersControllerImp(private val model: Model) : UsersController {

    override fun loginForm(): FreeMarkerContent {
        return FreeMarkerContent("login_form.ftl", null, "e")
    }

    override fun login(username: String, user_password: String): String? {
        return model.authenticate(username, user_password)
    }

    override fun dashboard(): FreeMarkerContent {
        val indexData = IndexArticle(model.getArticles())

        return FreeMarkerContent("admin.ftl", indexData, "e")
    }
}