package defaultxyz.countries.android.db.type.converters

import androidx.room.TypeConverter
import com.google.gson.Gson

object StringListConverter {
    private val gson = Gson()

    @JvmStatic
    @TypeConverter
    fun fromJsonString(jsonString: String): List<String> =
        gson.fromJson(jsonString, Array<String>::class.java).toList()

    @JvmStatic
    @TypeConverter
    fun toJsonString(data: List<String>): String =
        gson.toJson(data)
}