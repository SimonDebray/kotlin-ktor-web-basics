package fr.iim.iwm.a5.kotlin

import io.ktor.freemarker.FreeMarkerContent

interface ArticlesController {
    fun list(): FreeMarkerContent
    fun create(): FreeMarkerContent
    fun store(title: String, textContent: String): Any?
    fun show(id: Int): Any
    fun delete(id: Int): Any?
    fun createComment(id: Int, textComment: String): Any?
    fun deleteComment(id: Int): Any?
    fun showAdmin(id: Int): Any
}