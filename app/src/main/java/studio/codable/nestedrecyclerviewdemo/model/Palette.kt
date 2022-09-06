package studio.codable.nestedrecyclerviewdemo.model

data class Palette(
    val id: Int,
    val name: String,
    val colors: List<ColorItem>
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Palette

        if (id != other.id) return false
        if (name != other.name) return false

        colors.forEachIndexed { index, colorItem ->
            if(colorItem != other.colors[index])
                return false
        }

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + colors.sumOf { it.hashCode() }
        return result
    }
}