package org.evoleq.reakt.route



fun String.matchesAsRoute(other: String): Boolean {
    val split = prepareRoute().split("/")
    val otherSplit = other.prepareRoute().split("/")
    
    if(split.size != otherSplit.size){
        return false
    }
    
    return Pair(split,otherSplit)
        .toListOfPairs()
        .map { pair -> with(pair){
           when{
               first.startsWith(":") -> true
               second.startsWith(":") -> true
               else -> first == second
           }
        } }
        .reduce{a, b -> a && b}
}

fun String.prepareRoute() = removeLeadingSlash().removeTailSlash()

fun String.removeLeadingSlash() = when(startsWith("/")){
    true -> substring(1)
    false -> this
}
fun String.removeTailSlash() = when(endsWith("/")) {
    true -> dropLast(1)
    false -> this
}

fun <S,T> Pair<List<S>, List<T>>.toListOfPairs(): List<Pair<S,T>> {
    if(first.size != second.size){
        throw Exception()
    }
    return first.mapIndexed{index, s -> Pair(s,second[index]) }
}
