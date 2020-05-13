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
import kotlinx.coroutines.Job
import org.drx.dynamics.ID
import org.drx.evoleq.dsl.EvoleqDsl
import org.drx.evoleq.evolving.Evolving
import org.drx.evoleq.evolving.parallel
import react.RComponent
import react.RProps
import react.RState


open class FunctionalReaktProps<Data : RState>(
    open var id: ID,
    open var data: Data,
    open var scope: CoroutineScope = CoroutineScope(Job()),
    open var forceUpdate: (ID, Data) -> Boolean = { _, _ -> false },
    open var updateParent: (ID, suspend CoroutineScope.(Data) -> Data) -> Unit = { _, _ -> Unit }
) : RProps


abstract class FunctionalReaktComponent<Data : RState>(properties: FunctionalReaktProps<Data>) : RComponent<FunctionalReaktProps<Data>, Data>(properties) {
    init {
        state = properties.data
    }
    
    @EvoleqDsl
    fun update(senderId: ID, update: suspend CoroutineScope.(Data) -> Data): Evolving<Data> = props.scope.parallel {
        with(onUpdate(senderId, update(props.data))) data@{
            if (props.forceUpdate(senderId, this@data)) {
                props.data = this@data
                forceUpdate()
            }
            this
        }
    }
    
    @EvoleqDsl
    open fun onUpdate(senderId: ID, data: Data): Data {
        props.updateParent(senderId) { data }
        return data
    }
}