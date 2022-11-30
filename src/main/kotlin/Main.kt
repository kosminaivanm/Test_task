import entities.Monster
import entities.Player

fun main(args: Array<String>) {
    Fight().fight()
}

class Fight() {

    private val player: Player = Player(13, 10, 40, 4..9, "Игрок")
    private val monsters = arrayListOf<Monster>()

    init {
        monsters.add(Monster(10, 10, 30, 3..7, "Монстр А"))
        monsters.add(Monster(12, 8, 26, 4..8, "Монстр Б"))
        monsters.add(Monster(7, 12, 35, 3..7, "Монстр В"))
    }

    fun fight() {
        while (player.isAlive and (monsters.size != 0)) {
            playerTurn()
            middleTurn()
            monstersTurn()
        }

        if (player.isAlive) {
            println("Вы победили!!!")
            return
        }

        println("Вы проиграли.")
    }

    private fun playerTurn() {

        println("Ваши характеристики:")
        println(player)
        println("Выбери действие:")
        for (m in 1..monsters.size) {
            val i = m - 1
            println("$m - Атаковать ${monsters[i]}")
        }
        if (player.canHeal()) println("${monsters.size + 1} - Восстановить здоровье (${player.healTimes} раза осталось)")

        when (val x = readInt()) {
            in 1..monsters.size -> {
                val dmg = player.attack(monsters[x - 1])
                if (dmg == 0) println("${player.name} промахнулся")
                else println("${player.name} нанёс $dmg урона ${monsters[x - 1].name}")
            }

            monsters.size + 1 -> player.heal()
            else -> {
                println("Введены неверные параметры, попробуйте ещё раз:")
                playerTurn()
            }
        }
    }

    private fun monstersTurn() {
        for (m in monsters) {
            val dmg = m.attack(player)
            if (dmg == 0) println("${m.name} промахнулся")
            else println("${m.name} нанёс $dmg урона ${player.name}")
        }
    }

    private fun middleTurn() {
        for (monster in monsters) if (!monster.isAlive) {
            println("${player.name} убил ${monster.name}!!!")
            monsters.remove(monster)
        }
    }
}

fun readInt(): Int {
    val int = readLine()
    return if (int != null) try {
        int.toInt()
    } catch (e: NumberFormatException) {
        println("Неправильные данные, попробуйте снова")
        readInt()
    }
    else {
        println("Неправильные данные, попробуйте снова")
        readInt()
    }
}