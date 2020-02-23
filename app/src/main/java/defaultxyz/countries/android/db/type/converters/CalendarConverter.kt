package defaultxyz.countries.android.db.type.converters

import androidx.room.TypeConverter
import java.util.*

object CalendarConverter {

    @JvmStatic
    @TypeConverter
    fun fromLong(timestamp: Long): Calendar =
        Calendar.getInstance().apply {
            timeInMillis = timeInMillis
        }

    @JvmStatic
    @TypeConverter
    fun toLong(date: Calendar): Long =
        date.timeInMillis
}