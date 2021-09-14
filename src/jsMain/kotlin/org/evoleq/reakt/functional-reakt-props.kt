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
import org.drx.evoleq.evolving.Evolving
import org.evoleq.reakt.texts.EmptyTexts
import org.evoleq.reakt.texts.Locale
import org.evoleq.reakt.texts.Texts
import react.RProps
import react.RState


data class FunctionalReaktProps<Data : RState>(
    
    /**
     * Id of the Component
     */
    val id: ID,
    
    /**
     * component-specific data
     */
    var data: Data,
    
    /**
     * [CoroutineScope] the component shall use, its default is CoroutineScope(Job())
     */
    val scope: CoroutineScope = CoroutineScope(Job()),
    
    /**
     * Eventually force an update of the [FunctionalReaktComponent],
     * If forceUpdate(id,data) = true, then the function [RComponent.forceUpdate()] is called
     */
    val forceUpdate: (ID, Data) -> Boolean = { _, _ -> false },
    
    /**
     * Update parent component.
     */
    val updateParent: (ID, suspend CoroutineScope.(Data) -> Data) -> Unit = { _, _ -> Unit },
    
    /**
     * Register update-function of a child-component
     */
    val registerChildUpdate: (ChildId<*>, (ID, suspend CoroutineScope.(Any) -> Any)-> Evolving<Any>)->Unit = {_,_ -> Unit},
    
    /**
     * [Boundary] of the component
     */
    val boundary: Boundary = Boundary(),
    
    val texts: () -> Texts = { EmptyTexts }
) : RProps
