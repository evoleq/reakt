package org.evoleq.reakt

import kotlinx.html.id
import org.drx.dynamics.ID
import react.RBuilder
import react.RState
import react.child
import react.dom.div
import react.dom.h1
import react.dom.p

typealias TestProps = FunctionalReaktProps<Data>

data class Data(
    val value: Int
) : RState
class TestComponent(override val properties: TestProps) : FunctionalReaktComponent<Data>(properties) {
    
    override fun onUpdate(senderId: ID, data: Data): Data {
        println(data)
        return data
    }
    
    override fun RBuilder.render() {
        div{
            h1{
                attrs {
                    id="headline"
                }
                +"headline"
            }
            p{
                +"value = ${properties.data.value}"
            }
            update(Int::class){data: Data -> data.copy(value = data.value +1)}
        }
    }
}
@ReaktDsl
fun RBuilder.testComponent(properties: TestProps.()->TestProps) =
    child({props: TestProps ->
        TestComponent(
            props.properties().
            forceUpdate{ _, _ -> true }
        )
    }){}
    