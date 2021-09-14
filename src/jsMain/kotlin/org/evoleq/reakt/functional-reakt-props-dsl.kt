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
import org.evoleq.reakt.texts.Locale
import org.evoleq.reakt.texts.Texts
import react.RState

@DslMarker
annotation class FunctionalReaktPropsDsl


@FunctionalReaktPropsDsl
fun <Data : RState> FunctionalReaktProps<Data>.id(
    id: ()->ID
): FunctionalReaktProps<Data> = FunctionalReaktProps(
    id(),
    data,
    scope,
    forceUpdate,
    updateParent,
    registerChildUpdate,
    boundary,
    texts
)
/*{
    //this@id.id = id()
}

 */

@FunctionalReaktPropsDsl
fun <Data : RState> FunctionalReaktProps<Data>.scope(
    scope: ()->CoroutineScope
): FunctionalReaktProps<Data> = FunctionalReaktProps(
    id,
    data,
    scope(),
    forceUpdate,
    updateParent,
    registerChildUpdate,
    boundary,
    texts
)

@FunctionalReaktPropsDsl
fun <Data : RState> FunctionalReaktProps<Data>.data(
    data: ()->Data
): FunctionalReaktProps<Data> = FunctionalReaktProps(
    id,
    data(),
    scope,
    forceUpdate,
    updateParent,
    registerChildUpdate,
    boundary,
    texts
)

@FunctionalReaktPropsDsl
fun <Data : RState> FunctionalReaktProps<Data>.updateParent(
    update: (ID, suspend CoroutineScope.(Data) -> Data) -> Unit
): FunctionalReaktProps<Data> = FunctionalReaktProps(
    id,
    data,
    scope,
    forceUpdate,
    update,
    registerChildUpdate,
    boundary,
    texts
)

@FunctionalReaktPropsDsl
fun <Data: RState> FunctionalReaktProps<Data>.registerChildUpdate(
    register: (ChildId<*>, (ID, suspend CoroutineScope.(Any) -> Any)-> Evolving<Any>)->Unit
): FunctionalReaktProps<Data> = FunctionalReaktProps(
    id,
    data,
    scope,
    forceUpdate,
    updateParent,
    register,
    boundary,
    texts
)
@FunctionalReaktPropsDsl
fun <Data : RState> FunctionalReaktProps<Data>.forceUpdate(
    force: (ID, Data) -> Boolean
): FunctionalReaktProps<Data> = FunctionalReaktProps(
    id,
    data,
    scope,
    force,
    updateParent,
    registerChildUpdate,
    boundary,
    texts
)

@FunctionalReaktPropsDsl
fun <Data : RState, T : Texts> FunctionalReaktProps<Data>.texts(
    texts: () -> T
): FunctionalReaktProps<Data> = FunctionalReaktProps(
    id,
    data,
    scope,
    forceUpdate,
    updateParent,
    registerChildUpdate,
    boundary,
    texts
)



