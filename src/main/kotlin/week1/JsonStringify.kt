package week1

import java.text.DecimalFormat

fun stringConcatenation(vararg strings: String): String {
    val result = StringBuilder()
    for (s in strings) {
        result.append(s)
    }
    return result.toString()
}

sealed class JsonElement
data class JsonObject(val fields: Map<String, JsonElement>) : JsonElement() {
    constructor(vararg fields: Pair<String, JsonElement>) : this(fields.toMap())
}

data class JsonArray(val elements: List<JsonElement>) : JsonElement() {
    constructor(vararg elements: JsonElement) : this(elements.toList())
}

data class JsonNumber(val value: Double) : JsonElement()
data class JsonString(val value: String) : JsonElement()
data class JsonBoolean(val value: Boolean) : JsonElement()
object JsonNull : JsonElement()

fun JsonElement.stringify(): String = when (this) {
    is JsonNull -> "null"
    is JsonBoolean -> value.toString()
    is JsonString -> stringConcatenation("\"", value, "\"")
    is JsonNumber -> DecimalFormat().apply { isDecimalSeparatorAlwaysShown = false }.format(value).toString().replace(",", ".")
    is JsonArray -> elements.joinToString (",", "[", "]") { it.stringify() }
    is JsonObject -> fields
        .map { (k, v) -> stringConcatenation("\"", k, "\":", v.stringify()) }
        .joinToString(",", "{", "}")
}
