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

import kotlinx.coroutines.CoroutineScope
import org.drx.dynamics.ID
import org.drx.evoleq.evolving.Evolving
import react.RState

@DslMarker
annotation class FunctionalReaktPropsDsl


@FunctionalReaktPropsDsl
fun <Data : RState> FunctionalReaktProps<Data>.id(
    id: ()->ID
) {
    this@id.id = id()
}

@FunctionalReaktPropsDsl
fun <Data : RState> FunctionalReaktProps<Data>.scope(
    scope: ()->CoroutineScope
) {
    this@scope.scope = scope()
}

@FunctionalReaktPropsDsl
fun <Data : RState> FunctionalReaktProps<Data>.data(
    data: ()->Data
) {
    this@data.data = data()
}

@FunctionalReaktPropsDsl
fun <Data : RState> FunctionalReaktProps<Data>.updateParent(
    update: (ID, suspend CoroutineScope.(Data) -> Data) -> Unit
) {
    updateParent = update
}

@FunctionalReaktPropsDsl
fun <Data: RState> FunctionalReaktProps<Data>.registerChildUpdate(
    register: (ChildId<*>, (ID, suspend CoroutineScope.(Any) -> Any)-> Evolving<Any>)->Unit
){
    registerChildUpdate = register
}
@FunctionalReaktPropsDsl
fun <Data : RState> FunctionalReaktProps<Data>.forceUpdate(
    force: (ID, Data) -> Boolean
) {
    forceUpdate = force
}



