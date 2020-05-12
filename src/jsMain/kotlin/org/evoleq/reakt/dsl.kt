package org.evoleq.reakt

import react.RBuilder
import react.dom.render
import kotlin.browser.document


fun root(elementId: String = "root", block: RBuilder.()->Unit) = render(document.getElementById(elementId)){
    block()
}