package org.evoleq.reakt

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

