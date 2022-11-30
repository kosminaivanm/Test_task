package entities

open class Entity(
    _attack: Int, _defense: Int, _maxHealth: Int, _damage: IntRange, _name: String
) {
    var name: String = _name
    var attack: Int = 1
        set(value) {
            field = value.coerceIn(1..20)
        }
    var defense: Int = 1
        set(value) {
            field = value.coerceIn(1..20)
        }
    var maxHealth: Int = 1
        set(value) {
            field = maxOf(0, value)
        }
    var damage: IntRange
    val isAlive get() = (healthPoints > 0)
    var healthPoints: Int = 0
        set(value) {
            field = value.coerceIn(0..maxHealth)
        }

    init {
        attack = _attack.coerceIn(1..20)
        defense = _defense.coerceIn(1..20)
        maxHealth = _maxHealth
        healthPoints = _maxHealth
        damage = _damage
    }

    fun attack(defender: Entity) : Int{
        if (!isAlive) return 0

        val attackModifier: Int = maxOf(attack - defender.defense, 1)
        val diceRange = 1..6

        for (i in 1..attackModifier) {
            if (4 < diceRange.random()) {
                val oldDefenderHealthPoints =  defender.healthPoints
                defender.healthPoints -= damage.random()
                return oldDefenderHealthPoints - defender.healthPoints
            }
        }
        return 0
    }

    override fun toString(): String {
        return "$name с $attack атаки, $defense защиты, $healthPoints здоровья, $maxHealth максимального здоровья, $damage урона"
    }

}