package pro.edvard.blackbox.model

data class Level(
    val number: Int,
    val answer: Int,
) {
    companion object {
        fun gelAllLevels(): MutableList<Level> {
            val levels: MutableList<Level> = mutableListOf()
            levels.add(Level(1, 3))
            levels.add(Level(2, 3))
            levels.add(Level(3, 6))
//            levels.add(Level(4, 12))
//            levels.add(Level(5, 26))
//            levels.add(Level(6, 25))
//            levels.add(Level(7, 26))
//            levels.add(Level(8, 24))
            return levels
        }
    }
}