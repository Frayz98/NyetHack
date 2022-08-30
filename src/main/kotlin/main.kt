fun main(args: Array<String>) {
    val name = "Madrigal"
    val healthPoints = 89
    val isBlessed = false
    val condition = formatHealthStatus(name, healthPoints, isBlessed)

    println(castFireball(4))

    println(condition)
}

private fun formatHealthStatus(name: String, healthPoints: Int, isBlessed: Boolean): String {
    return when (healthPoints) {
        100 -> "$name is in excellent condition!"
        in 90..99 -> "$name has a few scratches"
        in 75..89 -> if (isBlessed) "$name has some minor wounds, but is healing quite quickly!"
            else "$name has some minor wounds"
        in 15..74 -> "$name looks pretty hurt"
        else -> "$name is in awful condition!"
    }
}

private fun castFireball(numFireballs: Int = 2): String = when(numFireballs) {
    in 1..10 -> "Tipsy"
    in 11..20 -> "Sloshed"
    in 21..30 -> "Soused"
    in 31..40 -> "Stewed"
    in 41..50 -> "..t0aSt3d"
    else -> "..."
}


