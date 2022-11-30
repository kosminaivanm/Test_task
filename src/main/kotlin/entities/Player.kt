package entities

class Player(_attack: Int, _defense: Int, _maxHealth: Int, _damage: IntRange, name: String) : Entity(
    _attack, _defense, _maxHealth, _damage, name
) {
    var healTimes = 3
        private set

    fun heal() {
        if (!isAlive) return
        if (!canHeal()) return

        healTimes--
        healthPoints += maxHealth / 2
    }

    fun canHeal() = healTimes > 0
}
