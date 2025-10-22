import java.security.MessageDigest
import kotlin.collections.joinToString
import kotlin.text.format
import kotlin.text.toByteArray

object Utils {
    fun sha256(input: String): String {
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(input.toByteArray(Charsets.UTF_8))
        return digest.joinToString("") { "%02x".format(it) }
    }
}
