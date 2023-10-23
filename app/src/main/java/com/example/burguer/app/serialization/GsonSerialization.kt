import com.example.burguer.app.serialization.JsonSerialization
import com.google.gson.Gson

class GsonSerialization : JsonSerialization {

    private val gson = Gson()

    override fun <T> toJson(obj: T): String {
        return gson.toJson(obj)
    }

    override fun <T> fromJson(json: String, type: Class<T>): T {
        return gson.fromJson(json, type)
    }
} 