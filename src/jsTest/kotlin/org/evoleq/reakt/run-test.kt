package org.evoleq.reakt

import kotlinx.browser.document
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise

/*
    fun runTest(test: suspend ()->Unit) = GlobalScope.promise {
        test()
        
    }
    
     */

fun runTest(mountPoint: String = "root" ,test: suspend ()->Unit) = GlobalScope.promise {
    val div = document.createElement("div")
    document.body!!.appendChild(div)
    div.setAttribute("id", mountPoint)
    test()
    
}