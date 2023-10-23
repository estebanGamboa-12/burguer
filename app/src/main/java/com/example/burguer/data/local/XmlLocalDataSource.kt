import android.content.Context
import android.util.Log
import com.example.burguer.app.ErrorApp
import com.example.burguer.domain.Burguer
import com.example.burguer.app.Either
import com.example.burguer.app.left
import com.example.burguer.app.right
import com.example.burguer.app.serialization.JsonSerialization

class XmlLocalDataSource(
    private val context: Context,
    private val serialization: JsonSerialization
) {
    private val sharedPref = context.getSharedPreferences("burguer", Context.MODE_PRIVATE)
    private val burguerId = "burguerId1"

    fun getBurguers(): Either<ErrorApp, Burguer> {
        val jsonBurguer = sharedPref.getString(burguerId, null)

        jsonBurguer?.let {
            return serialization.fromJson(it, Burguer::class.java).right()
        }
        return ErrorApp.DataError.left()
    }

    fun saveBurguers(burguer: Burguer) {
        sharedPref.edit().apply() {
            putString(burguerId, serialization.toJson(burguer, Burguer::class.java))
            apply()
        }

        }
    }
