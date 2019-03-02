import java.sql.Connection
import java.sql.DriverManager
import java.util.concurrent.ConcurrentLinkedQueue

class ConnectionPool(private val url: String, private val user: String?, private val password: String?) {

    private val list = ConcurrentLinkedQueue<Connection>()

    fun getConnection() : Connection =
        list.poll()
            ?: DriverManager.getConnection(url, user, password)

    fun makeAvailable(connection: Connection) {
        println("make available")
        list.add(connection)
    }

    inline fun use(block: (Connection) -> Unit) {
        val connection = getConnection()

        try {
            block(connection)
        } finally {
            makeAvailable(connection)
        }
    }
}