package fr.iim.iwm.a5.kotlin

import io.ktor.freemarker.FreeMarkerContent

interface UsersController {
    fun loginForm(): FreeMarkerContent
    fun login(username: String, user_password: String): String?
    fun dashboard(): FreeMarkerContent
}