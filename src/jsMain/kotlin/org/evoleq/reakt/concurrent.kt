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
import react.RState

open class ConcurrentReaktProps<Data: RState>(
    override var id: ID,
    override var data: Data,
    override var scope: CoroutineScope = CoroutineScope(Job()),
    override var forceUpdate: (ID,Data)->Boolean = {_,_ ->false},
    override var updateParent:  (ID, suspend CoroutineScope.(Data)->Data)->Unit = { _, _->Unit}
) : FunctionalReaktProps<Data>(
    id, data, scope, forceUpdate, updateParent
)