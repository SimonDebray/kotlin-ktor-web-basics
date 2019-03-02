package fr.iim.iwm.a5.kotlin

import ConnectionPool

class MysqlModel(url: String, user: String?, password: String?) : Model{

    val connectionPool = ConnectionPool(url, user, password)

    override fun getArticles (): List<Article> {

        val connection = connectionPool.getConnection()

        val articles = ArrayList<Article>()

        connection.prepareStatement("SELECT * FROM articles").use { stmt ->
            val results = stmt.executeQuery()

            while (results.next()) {


                val article = Article(
                    results.getInt("id"),
                    results.getString("title"),
                    null)

                articles.add(article)
            }
        }

        return articles
    }

    override fun getArticle (id: Int): Article? {
        connectionPool.use { connection ->
            connection.prepareStatement("SELECT * FROM articles WHERE id = ?").use { stmt ->
                stmt.setInt(1, id)

                val results = stmt.executeQuery()

                val found = results.next()

                if (found) {
                     return Article(
                        results.getInt("id"),
                        results.getString("title"),
                        results.getString("text"))
                }
            }
        }
        return null
    }

    override fun addArticle(title: String, textContent: String): Any? {
        connectionPool.use { connection ->
            connection.prepareStatement("INSERT INTO articles (title, text) VALUE (?, ?);").use { stmt ->
                stmt.setString(1, title)
                stmt.setString(2, textContent)

                return stmt.execute()
            }
        }
        return null
    }

    override fun getArticleComments (id: Int): List<Comment> {

        val comments = ArrayList<Comment>()

        connectionPool.use { connection ->
            connection.prepareStatement("SELECT * FROM comments WHERE article_id = ?").use { stmt ->
                stmt.setInt(1, id)

                val results = stmt.executeQuery()

                while (results.next()) {
                    val comment = Comment(
                        results.getInt("id"),
                        results.getInt("article_id"),
                        results.getString("text")
                    )

                    comments.add(comment)
                }
            }
        }
        return comments
    }

    override fun addComments (id: Int, textComment: String): Any? {
        connectionPool.use { connection ->
            connection.prepareStatement("INSERT INTO comments (article_id, text) VALUE (?, ?);").use { stmt ->
                stmt.setInt(1, id)
                stmt.setString(2, textComment)

                return stmt.execute()
            }
        }
        return null
    }

    override fun authenticate (username: String, user_password: String): String? {
        connectionPool.use { connection ->
            connection.prepareStatement("SELECT username FROM users WHERE username = ? AND user_password = ?").use { stmt ->
                stmt.setString(1, username)
                stmt.setString(2, user_password)

                val results = stmt.executeQuery()

                val found = results.next()

                if (found) {
                    return results.getString("username")
                }
            }
        }
        return null
    }

    override fun deleteArticle(id: Int): Any? {
        connectionPool.use { connection ->
            connection.prepareStatement("DELETE FROM articles WHERE id = ?;").use { stmt ->
                stmt.setInt(1, id)

                return stmt.execute()
            }
        }
        return null
    }

    override fun deleteComment(id: Int): Any? {
        connectionPool.use { connection ->
            connection.prepareStatement("DELETE FROM comments WHERE id = ?;").use { stmt ->
                stmt.setInt(1, id)

                return stmt.execute()
            }
        }
        return null
    }
}