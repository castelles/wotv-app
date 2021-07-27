package castelles.com.github.api.model

data class Waterfall(
    val id: Int,
    val image: String,
    val name: String,
    val link: String,
    val longitude: String,
    val latitude: String,
    val localization: String,
    val datetime: String,
    val description: String
)
data class Waterfalls(
    val list: List<Waterfall>
)