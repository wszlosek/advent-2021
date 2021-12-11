package week2


sealed class Tree<T> {
    override fun toString(): String = when (this) {
        is Leaf -> value.toString()
        is Node -> "($left, $right)"
    }
}

data class Leaf<T>(val value: T) : Tree<T>()
data class Node<T>(val left: Tree<T>, val right: Tree<T>) : Tree<T>()

fun Tree<*>.count(): Int = when (this) {
    is Leaf -> 1
    is Node -> left.count() + right.count()
}

fun Tree<*>.countAll(): Int = when (this) {
    is Leaf -> 1
    is Node -> 1 + left.countAll() + right.countAll()
}

fun Tree<*>.height(): Int = when (this) {
    is Leaf -> 1
    is Node -> 1 + left.height().coerceAtLeast(right.height())
}

fun <T : Comparable<T>> Tree<T>.biggest(): T = when (this) {
    is Leaf -> value
    is Node -> maxOf(left.biggest(), right.biggest())
}

fun Tree<*>.sum(): Int = when (this) {
    is Leaf -> value as Int
    is Node -> left.sum() + right.sum()
}

operator fun Tree<*>.contains(s: String?): Boolean = when (this) {
    is Leaf -> value == s
    is Node -> left.contains(s) || right.contains(s)
}

operator fun <T> Tree<T>.plus(second: Tree<T>): Node<T> =
    Node(this, second)

fun <T> Tree<T>.toList(): List<T> = when (this) {
    is Leaf -> listOf(value)
    is Node -> left.toList() + right.toList()
}

fun main() {

}