package studio.codable.nestedrecyclerviewdemo.model

data class ColorItem(
    val id: Int,
    val name: String,
    val hexCode: String
) {
    override fun hashCode(): Int {
        return id
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ColorItem

        if (id != other.id) return false
        if (name != other.name) return false
        if (hexCode != other.hexCode) return false

        return true
    }
}