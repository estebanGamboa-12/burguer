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
    private val sharedPref = context.getSharedPreferences("Burguers", Context.MODE_PRIVATE)
    private val burguerId = "burguer_list"

    fun getBurguers(): Either<ErrorApp, List<Burguer>> {
        val jsonBurguerList = sharedPref.getString(burguerId, null)

        return if (jsonBurguerList != null) {
            val burguerList = serialization.fromJson(jsonBurguerList, Array<Burguer>::class.java).toList()

            Log.d("@dev", "Burguer list obtenida de SharedPreferences: $burguerList")
            burguerList.right()
        } else {
            ErrorApp.DataError.left()
        }
    }

    fun saveBurguers(burguerList: List<Burguer>) {
        val jsonBurguerList = serialization.toJson(burguerList)
        Log.d("@dev", "Burguer list a guardar en SharedPreferences: $jsonBurguerList")
        sharedPref.edit().putString(burguerId, jsonBurguerList).apply()
    }
}