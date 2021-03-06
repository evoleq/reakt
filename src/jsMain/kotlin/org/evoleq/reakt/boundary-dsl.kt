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

import org.drx.configuration.Configuration
import org.drx.dynamics.ID
import react.RState

class BoundaryConfiguration : Configuration<Boundary> {

    private val apis: HashMap<String, Api> by lazy { hashMapOf<String,Api>() }

    val translations: HashMap<ID,Translations> by lazy{ hashMapOf<ID,Translations>() }
    
    override fun configure(): Boundary = Boundary(apis,translations)

    @BoundaryDsl
    fun apis(configuration: HashMap<String,Api>.()->Unit) {
        apis.configuration()
    }

    @BoundaryDsl
    fun HashMap<String,Api>.api(configuration: ApiConfiguration.()->Unit) = with(ApiConfiguration()) {
        configuration()
        val api = configure()
        this@api[api.name] = api
    }

    @BoundaryDsl
    fun HashMap<String,Api>.browser(configuration: ApiConfiguration.()->Unit) = with(ApiConfiguration()) {
        configuration()
        name = "browser"
        this@browser[name] = configure()
    }
    
    @BoundaryDsl
    inline fun <reified Type> translations(noinline configuration: Translations.()->Unit) {
        if(translations[Type::class] == null) {
            translations[Type::class] = Translations()
        }
        translations[Type::class]!!.configuration()
    }
}

class ApiConfiguration : Configuration<Api> {
    
    @BoundaryDsl
    lateinit var name: String

    private val routes: HashMap<String, Route> by lazy{ hashMapOf<String,Route>()}

    override fun configure(): Api = Api(name, routes)

    @BoundaryDsl
    fun routes(configuration: HashMap<String, Route>.()->Unit) {
        routes.configuration()
    }

    @BoundaryDsl
    fun HashMap<String, Route>.route(configuration: RouteConfiguration.()->Unit) = with(RouteConfiguration()) {
        configuration()
        val route = configure()
        this@route[route.name] = route
    }
}

class RouteConfiguration : Configuration<Route> {
    
    @BoundaryDsl
    lateinit var name: String
    
    @BoundaryDsl
    lateinit var path: String

    override fun configure(): Route = Route(name, path)
}

@BoundaryDsl
fun <Data : RState> FunctionalReaktProps<Data>.boundary(
    configuration: BoundaryConfiguration.()->Unit
):FunctionalReaktProps<Data> = with(BoundaryConfiguration()) {
    configuration()
    //boundary = configure()
    FunctionalReaktProps(
        this@boundary.id,
        this@boundary.data,
        this@boundary.scope,
        this@boundary.forceUpdate,
        this@boundary.updateParent,
        this@boundary.registerChildUpdate,
        configure()
    )
}

@BoundaryDsl
fun boundary(configuration: BoundaryConfiguration.()->Unit): Boundary = with(BoundaryConfiguration()) {
    configuration()
    configure()
}
