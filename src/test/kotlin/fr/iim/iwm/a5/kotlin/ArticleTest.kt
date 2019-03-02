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
}