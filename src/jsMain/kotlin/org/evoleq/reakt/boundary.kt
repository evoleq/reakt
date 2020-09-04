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
package org.evoleq.reakt

@DslMarker
annotation class BoundaryDsl

data class Boundary(
    val apis: Map<String, Api> = mapOf()
) {
    operator fun get(api: String): Api = apis[api] ?: error("No such api: name = $api")
    operator fun get(api:String, route: String): Route = this[api][route]
}

data class Api(
    val name: String = "api",
    private val routes: HashMap<String,Route>
) {
    operator fun get(key: String): Route = routes[key]!!
}

fun apis(vararg apis: Api) = with(apis.map{Pair(it.name, it)}){
    hashMapOf(*this.toTypedArray())
}

@Suppress("FunctionName")
fun Browser(routes: HashMap<String,Route>): Api = Api("browser", routes)


fun routes(vararg routes: Pair<String,Route>) = hashMapOf(*routes)
fun routes(vararg routes: Route) = with(routes.map{Pair(it.name,it)}) {
    routes(*this.toTypedArray())
}
data class Route(
    val name: String,
    val path: String
)

fun Route.path(params: HashMap<String, String>): String {
    var tmp = path
    
    params.forEach {
        tmp.replace("{${it.key}}",it.value)
    }
    
    return tmp
}

