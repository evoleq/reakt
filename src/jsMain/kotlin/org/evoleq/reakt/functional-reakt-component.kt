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
import org.drx.evoleq.dsl.EvoleqDsl
import org.drx.evoleq.evolving.Evolving
import org.drx.evoleq.evolving.parallel
import react.RComponent
import react.RState


abstract class FunctionalReaktComponent<Data : RState>(properties: FunctionalReaktProps<Data>) : RComponent<FunctionalReaktProps<Data>, Data>(properties) {
    init {
        state = properties.data
    }
    
    /**
     * Update the component.
     * This function
     * 1. applies the update to props.data
     * 2. calls the onUpdate-function (which by default triggers an update of the parent)
     * 3. calls the forceUpdate-function
     * and eventually forces an update of the component.
     */
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
    
    /**
     * Called during the update-process of the component. By default, the updateParent-function is called.
     * Hint: If you override this function and still want to update the parent component you
     * will have to call props.updateParent by yourself
     */
    @EvoleqDsl
    open fun onUpdate(senderId: ID, data: Data): Data {
        props.updateParent(senderId) { data }
        return data
    }
}