package entities

class Monster(_attack: Int, _defense: Int, _maxHealth: Int, _damage: IntRange, name: String) : Entity(
    _attack, _defense, _maxHealth,
    _damage, name
) {
}