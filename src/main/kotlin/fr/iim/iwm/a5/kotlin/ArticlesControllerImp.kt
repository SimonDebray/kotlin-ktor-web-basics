package fr.iim.iwm.a5.kotlin

import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode

class ArticlesControllerImp(private val model: Model) : ArticlesController {

    override fun list(): FreeMarkerContent{
        val indexData = IndexArticle(model.getArticles())

        return FreeMarkerContent("index.ftl", indexData, "e")
    }

    override fun show(id: Int): Any{
        val article = model.getArticle(id)
        val comments = model.getArticleComments(id)

        if (article !== null)
            return FreeMarkerContent("article.ftl", mapOf("article" to article, "comments" to comments), "e")

        return HttpStatusCode.NotFound
    }

    override fun createComment(id: Int, textComment: String): Any? {
        return  model.addComments(id, textComment)
    }

    override fun delete(id: Int): Any? {
        return model.deleteArticle(id)
    }

    override fun deleteComment(id: Int): Any? {
        return model.deleteComment(id)
    }

    override fun create(): FreeMarkerContent {
        return FreeMarkerContent("article_create.ftl", null, "e")
    }

    override fun store(title: String, textContent: String): Any? {
        return model.addArticle(title, textContent)
    }

    override fun showAdmin(id: Int): Any {
        val article = model.getArticle(id)
        val comments = model.getArticleComments(id)

        if (article !== null)
            return FreeMarkerContent("admin_article.ftl", mapOf("article" to article, "comments" to comments), "e")

        return HttpStatusCode.NotFound
    }
}