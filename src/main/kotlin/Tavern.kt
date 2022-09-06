import java.io.File
import kotlin.math.roundToInt

const val TAVERN_NAME = "Taernyl's Folly"

var playerGold = 10
var playerSilver = 10

val patronList = listOf("Eli", "Mordoc", "Sophie")
val lastname = listOf("Ironfoot", "Fernsworth, Baggins")
val uniquePatrons = mutableSetOf<String>()
val menuData = File("data/menu.txt").readText().split('\n')

fun main(args: Array<String>) {
    if (patronList.contains("Eli")) {
        println("The tavern master says: Eli's in the back playing cards")
    } else {
        println("The tavern master says: Eli isn't here")
    }

    if (patronList.containsAll(listOf("Sophie", "Mordoc"))){
        println("The tavern master says: Yea, they're seated by the stew kettle")
    } else {
        println("The tavern master says: Nay, they departed hours ago")
    }

//    placeOrder("shandy,Dragon's Breath,5.91")

//    for (parton in patronList){
//        println("Good evening, $parton")
//    }

//    patronList.forEachIndexed { index, patron ->
//        println("Good evening, $patron - you're #${index + 1} in line")
//        placeOrder(patron, menuData.shuffled().first())
//    }
//
//    menuData.forEachIndexed { index, menuItem ->
//        println("$index : $menuItem")
//    }

    (0..9).forEach { _ ->
        val first = patronList.shuffled().first()
        val last = lastname.shuffled().first()
        val name = "$first $last"
        uniquePatrons += name
    }
}

fun performPurchase(price: Double) {
    displayBalance()
    val totalPurse = playerGold + (playerSilver / 100.0)
    println("Total purse: $totalPurse")
    println("Purchasing item for $price ")

    val remainingBalance = totalPurse - price
    println("%.2f".format(remainingBalance))


    val remainingGold = remainingBalance.toInt()
    val remainingSilver = (remainingBalance % 1 * 100).roundToInt()

    playerGold = remainingGold
    playerSilver = remainingSilver
    displayBalance()
}

private fun displayBalance() {
    println("Player's purse balance: Gold: $playerGold , Silver: $playerSilver")
}

private fun toDragonSpeak(phrase: String) =
    phrase.replace(Regex("[aeiouAEIOU]")) {
        when (it.value) {
            "a" -> "4"
            "e" -> "3"
            "i" -> "1"
            "o" -> "0"
            "u" -> "|_|"
            "A" -> "4"
            "E" -> "3"
            "I" -> "1"
            "O" -> "0"
            "U" -> "|_|"
            else -> it.value
        }
    }

private fun placeOrder(patronName: String ,menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf("\'")
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
//    println("Madrigal speaks with $tavernMaster about their order")
    println("$patronName speaks with $tavernMaster about their order")

    val (type, name, price) = menuData.split(",")

    val message = "$patronName buys a $name ($type) for $price"
    println(message)

    performPurchase(price.toDouble())

//    val phrase = "Ah, delicious $name!"
//    println("Madrigal exclaims: ${toDragonSpeak(phrase)}")

    val phrase = if (name == "Dragon's Breath") {
        "$patronName exclaims: ${toDragonSpeak("AH, DELICIOUS $name!")}"
    } else {
        "$patronName says: Thanks for the $name"
    }

    println(phrase)
}