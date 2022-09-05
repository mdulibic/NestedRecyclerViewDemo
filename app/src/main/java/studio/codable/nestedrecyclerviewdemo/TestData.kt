package studio.codable.nestedrecyclerviewdemo

import studio.codable.nestedrecyclerviewdemo.model.ColorItem
import studio.codable.nestedrecyclerviewdemo.model.Palette

fun getTestData(): List<Palette> =
    listOf(Palette(id= 1, name = "Palette 1",
        colors = listOf(ColorItem(id = 1,"Palatinate purple", "#5C2751"),
            ColorItem(id = 2,"Liberty", "#6457A6"),
            ColorItem(id = 3,"Maximum Blue Purple", "#9DACFF"),
            ColorItem(id = 4,"Sky Blue Crayola", "#76E5FC"))),
        Palette(id= 2, name = "Palette 2",
            colors = listOf(ColorItem(id = 5,"Nyanza", "#DCEED1"),
                ColorItem(id = 6,"Cambridge Blue", "#AAC0AA"),
                ColorItem(id = 7,"Old Lavender", "#736372"),
                ColorItem(id = 8,"Beaver", "#A18276"))),
        Palette(id= 3, name = "Palette 3",
            colors = listOf(ColorItem(id = 9,"Eggplant", "#52414C"),
                ColorItem(id = 10,"Ebony", "#596157"),
                ColorItem(id = 11,"Middle Green", "#5B8C5A"),
                ColorItem(id = 12,"Green Yellow Crayola", "#CFD186"),
                ColorItem(id = 13,"Fire Opal", "#E3655B"))),
        Palette(id= 4, name = "Palette 4",
            colors = listOf(ColorItem(id = 14,"Viridian Green", "#0FA3B1"),
                ColorItem(id = 15,"Honeydew", "#D9E5D6"),
                ColorItem(id = 16,"Medium Champagne", "#EDDEA4"),
                ColorItem(id = 17,"Atomic Tangerine", "#F7A072"),
                ColorItem(id = 18,"Deep Saffron", "#FF9B42"))),
        Palette(id= 5, name = "Palette 5",
            colors = listOf(ColorItem(id = 19,"Bittersweet", "#FF6666"),
                ColorItem(id = 20,"Inchworm", "#CCFF66"),
                ColorItem(id = 21,"Tiffany Blue", "#2EC4B6"),
                ColorItem(id = 22,"Lemon Meringue", "#F1E8B8"))),
        Palette(id= 6, name = "Palette 6",
            colors = listOf(ColorItem(id = 23,"Aquamarine", "#7AFDD6"),
                ColorItem(id = 24,"Mint Green", "#77FF94"),
                ColorItem(id = 25,"Inchworm", "#A1E44D"),
                ColorItem(id = 26,"May Green", "#60993E"))),
        Palette(id= 7, name = "Palette 7",
            colors = listOf(ColorItem(id = 27,"Rich Black FOGRA", "#0C090D"),
                ColorItem(id = 28,"UA Red", "#E01A4F"),
                ColorItem(id = 29,"Fire Opal", "#F15946"),
                ColorItem(id = 30,"Orange Yellow ", "#F9C22E"))),
        Palette(id= 8, name = "Palette 8",
            colors = listOf(ColorItem(id = 31,"Popstar", "#B24C63"),
                ColorItem(id = 32,"Han Purple", "#5438DC"),
                ColorItem(id = 33,"Azure", "#357DED"),
                ColorItem(id = 34,"Electric Blue", "#56EEF4"),
                ColorItem(id = 35,"Malachite", "#32E875"))),
        Palette(id= 9, name = "Palette 9",
            colors = listOf(ColorItem(id = 36,"Byzantium", "#6B2D5C"),
                ColorItem(id = 37,"Paradise Pink", "#F0386B"),
                ColorItem(id = 38,"Fiery Rose", "#FF5376"),
                ColorItem(id = 39,"Pink", "#F8C0C8"),
                ColorItem(id = 40,"Gold Crayola ", "#E2C290"))),
        Palette(id= 10, name = "Palette 10",
            colors = listOf(ColorItem(id = 41,"Yellow Crayola", "#F3E37C"),
                ColorItem(id = 42,"Naples Yellow", "#F3D34A"),
                ColorItem(id = 43,"Yellow Orange", "#EEA243"),
                ColorItem(id = 44,"Wheat", "#F1D9A7")))
    )
