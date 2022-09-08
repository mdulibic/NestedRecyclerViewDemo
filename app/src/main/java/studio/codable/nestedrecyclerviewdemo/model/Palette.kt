package studio.codable.nestedrecyclerviewdemo.model

data class Palette(
    val id: Long,
    val name: String,
    val colors: List<ColorItem>
)