package fr.iim.iwm.a5.kotlin

interface Model {
    fun getArticles (): List<Article>
    fun getArticle (id: Int): Article?
    fun getArticleComments (id: Int): List<Comment>
    fun addComments (id: Int, textComment: String): Any?
    fun authenticate (username: String, user_password: String): String?
    fun deleteArticle(id: Int): Any?
    fun deleteComment(id: Int): Any?
    fun addArticle(title: String, textContent: String): Any?
}