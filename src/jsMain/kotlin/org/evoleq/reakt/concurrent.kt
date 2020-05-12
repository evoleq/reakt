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