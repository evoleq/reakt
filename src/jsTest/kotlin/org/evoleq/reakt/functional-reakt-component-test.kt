package org.evoleq.reakt



import kotlinx.browser.document
import kotlinx.coroutines.*
import kotlinx.html.id
import org.drx.dynamics.Dynamic
import org.drx.dynamics.exec.blockUntil
import react.RBuilder
import react.dom.h1
import kotlin.test.Test
import kotlin.test.assertNotNull


class FunctionalReaktComponentTest {
    /*
    @BeforeTest
    fun setup() {
        println("creating root ...")
        val div = document.createElement("div")
        document.body!!.appendChild(div)
        div.setAttribute("id", "root")
    }
    @AfterTest
    fun tearDown() {
        val div = document.getElementById("root")
        if(div != null) {
            document.body?.removeChild(div)
        }
    }
    
     */
    @Test
    fun testExample() =runTest{
        //val div = document.createElement("div")
        //document.body!!.appendChild(div)
        //div.setAttribute("id", "root")
         root{
             h1{
                 attrs {
                     id="headline"
                 }
                 +"hello"
             }
         }
        
        val h = document.getElementById("headline")
        assertNotNull(h)
    }
    
    fun testComponent(component: RBuilder.()->Unit) {
    
    }
    
    
    @Test
    fun onUpdate () = runTest{
        val doneDynamic by Dynamic(false)
        val job = GlobalScope.launch {
            root {
                testComponent {
                    id { TestComponent::class }.
                    scope { CoroutineScope(Job()) }.
                    data { Data(11) }.
                    updateParent{
                        x,update -> println("update parent called")
                    }.
                    registerChildUpdate{
                        i,update -> Unit
                    }.
                    forceUpdate{ _,_ -> true}
                }
            }
            doneDynamic.value = true
        }
        blockUntil(doneDynamic){v -> v}
        
        val h = document.getElementById("headline")
        assertNotNull(h)
       // assertTrue(false)
        delay(1000)
        job.cancel()
       
    }
}
    
