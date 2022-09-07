package studio.codable.nestedrecyclerviewdemo.model

data class Palette(
    val id: Int,
    val name: String,
    val colors: List<ColorItem>
)