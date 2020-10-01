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
    
    private val childUpdates: HashMap<ChildId<*>, (ID, suspend CoroutineScope.(Any) -> Any)->Evolving<Any>> by lazy {
        hashMapOf<ChildId<*>, (ID, suspend CoroutineScope.(Any) -> Any) -> Evolving<Any>>()
    }
    
    /**
     *
     */
    fun  registerChildUpdate(): (id: ChildId<*>, update: (ID, suspend CoroutineScope.(Any) -> Any)->Evolving<Any>)->Unit = {
        id, update -> childUpdates[id] = update
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
     * Update a child-component
     */
    @EvoleqDsl
    fun <ChildData : RState> updateChild(id: ChildId<ChildData>, childData: Data.()->ChildData) = props.scope.parallel{
        with(childUpdates[id]!!) {
            this(ParentId::class){ _ -> props.data.childData()}
        }
    }
    
    /**
     * Called during the update-process of the component. By default, the updateParent-function is called.
     * Hint: If you override this function and still want to update the parent component you
     * will have to call props.updateParent by yourself.
     */
    @EvoleqDsl
    open fun onUpdate(senderId: ID, data: Data): Data {
        if(senderId != ParentId::class) {
            props.updateParent(senderId) { data }
        }
        return data
    }
}

class ParentId

sealed class ChildId<out ChildData> {
    data class IntId<ChildData>(val value: Int) : ChildId<ChildData>()
    data class ClassId<ChildData>(val value: ID): ChildId<ChildData>()
    data class StringId<ChildData>(val value: String): ChildId<ChildData>()
    
    companion object {
        operator fun<ChildData> invoke(value: Int): ChildId<ChildData> = IntId(value)
        operator fun<ChildData> invoke(value: ID): ChildId<ChildData> = ClassId(value)
        operator fun<ChildData> invoke(value: String): ChildId<ChildData> = StringId(value)
    }
}