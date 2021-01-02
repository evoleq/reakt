/**
 * Copyright (c) 2020 Dr. Florian Schmidt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
