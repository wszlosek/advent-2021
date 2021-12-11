package week1

fun generateParenthesisCombinations(num: Int): List<String> {
    val result = mutableListOf<String>()
    generate(0, 0, num, "", result)
    return result
}

fun generate(numOfStart: Int, numOfClose: Int, num: Int, h: String, result: MutableList<String>) {
    if (numOfStart == numOfClose && numOfClose == num) {
        result.add(h)
    } else {
        if (numOfStart < num) {
            generate(numOfStart + 1, numOfClose, num, "$h(", result)
        }
        if (numOfClose < numOfStart){
            generate(numOfStart, numOfClose + 1, num, "$h)", result)
        }
    }
}