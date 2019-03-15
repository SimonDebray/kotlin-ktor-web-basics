package fr.iim.iwm.a5.kotlin

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ArticleTest {

    @Test
    fun testArticle() {

        val model = mock<Model> {
            on { getArticle(43) } doReturn Article(43, "Titre", "Text")

            val comments = ArrayList<Comment>()

            comments.add(Comment(1, 43, "Un commentaire"))
            comments.add(Comment(2, 43, "Un autre commentaire"))

            on { getArticleComments(43) } doReturn comments
        }

        val articlesControllerImp = ArticlesControllerImp(model)

        val article = articlesControllerImp.show(43)

        assertTrue(article is FreeMarkerContent)
    }

    @Test
    fun testNoArticle() {
        val model = mock<Model> {}

        val articlesControllerImp = ArticlesControllerImp(model)

        val article = articlesControllerImp.show(42)

        assertEquals(HttpStatusCode.NotFound, article)
    }

    @Test
    fun testArticleList() {
        val model = mock<Model> {
            val articles = ArrayList<Article>()

            articles.add(Article(1, "Titre", "Un contenu"))
            articles.add(Article(2, "Titre bis", "Un autre contenu"))
            on { getArticles() } doReturn articles
        }

        val articlesControllerImp = ArticlesControllerImp(model)

        val list = articlesControllerImp.list()

        assertTrue(list is FreeMarkerContent)
    }

    @Test
    fun testArticleCreate() {
        val model = mock<Model> {}

        val articlesControllerImp = ArticlesControllerImp(model)

        val form = articlesControllerImp.create()

        assertTrue(form is FreeMarkerContent)
    }
}