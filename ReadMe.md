# Reakt

Go functional with kotlin and react


## The Component Pattern
```kotlin

data class ComponentData(val name: Strng) : RState

class Component : FunctionalReactComponent<ComponentData> {
    /**
     * If necessary, override the onUpdate-function
     */
    override fun onUpdate(senderId : ID, data: ComonentData): ComponentData {
        
        // Example
        // Sender of the update influences update-behavior
        when(senderId) {
            SomeId::class -> someAction(data) /* where someAction:(ComponentData)->ComponentData */
            SomeOtherId::class -> properties.updateParent(Component::class) { data }
        }
    }

    override fun RBuilder.render() {
        
        // ...
        
        // Example
        // navigate to another page of the application
        navlink<RProps>(properties.boundary["browser"]["home"].path){
            +"Home"
        }   
        // Example
        // perform api-request on button-click and update component-data
        button {
            + "Call SuperApi"
            attrs {
                onClickFunction {
                    val url = properties.boundary["SuperApi"]["super-route"].path
                    // perform api-request
                    val result: Response<String> = TODO()
                    update(SomeId::class) {
                        data -> data.copy(name = result.value) // here we assume a successful api-call
                    }
                }
            }
        }

    }

}

fun RBuilder.component(properties: FunctionalReaktProps<ComponentData>.()->Unit) =
  child({props: FunctionalReaktProps<ComponentData> ->
      Component(with(props) {
          properties()
          this
      })
  }){}

```

With this setup you can call the component from render function of another one like so: 

Here, we assume the render function belongs to a class 
```
class ParentComponent : FunctionalReaktComponent<ParentComponentData>
```
and the ParentComponentData has a field 
```
val componentData: ComponentData
```


```kotlin

    //...
    override fun RBuilder.render() {
        /* ... */
        component {
            id = Component::class // or another identifying class
            data = propderties.data.componentData
            forceUpdate { 
                id, data -> someConditionOn(id,data) // : Boolean
            }
            updateParent { 
                id: ID, update /* : (suspend CoroutineScope.(Data) -> Data */ -> with(
                    properties.data
                ) {
                    this@ParentComponent.update(id, copy(componentData = update(componentData)))
                    Unit
                }
            }
            boundary {
                apis { // provide a systematic approach to access routes of external apis and the react application
                    api { // access an external api
                        name = "SuperApi"
                        routes {
                            route {
                                name = "super-route"
                                path = "baseUrl/path/of/the/super/route" 
                            }
                        }
                    }
                    browser { // access (navigate to) routes in the react application
                        routes {
                            route {
                                name = "home"
                                path = "/home"
                            }
                            route {
                                name = "logout"
                                path = "/logout"
                            }
                        }
                    }
                }
            }
        }
    
    }
    

```

## Useful boundary pattern for reusable components
The following is just an idea of how to manage routes of an application systematically.

The benefits of such an approach are:
1. Refactoring of routes becomes easy
2. Re-usability of components with different apis and browser-routes

Keep the names of the api- and browser-routes in a separate object on component-level
```kotlin
object ComponentBoundary {
    object SuperApi {
        const val name = "SuperApi"
        object Routes {
            const val superRoute = "super-route"
        }
    }
    object Browser {
        object Routes {
            const val home = "home"
            const val logout = "logout"
        }
    }
}
```

Keep the paths of the routes of your application in a separate object on application-level

```kotlin
object Routes {
    object Browser {
        const val home = "/home"
        const val logout = "/logout"
    
    } 
    object SuperApi {
        const val baseUrl = "..."
        const val superRoute = "$baseUrl/path/of/the/super/route"
    }

}
```

Now you can configure the boundary of your component like so:
```kotlin
/* ... */
component {
    /* ... */
    boundary {
        apis { // provide a systematic approach to access routes of external apis and the react application
            api { // access an external api
                name = ComponentBoundary.SuperApi.name
                routes {
                    route {
                        name = ComponentBoundary.SuperApi.Routes.superRoute
                        path = Routes.SuperApi.superRoute
                    }
                }
            }
            browser { // access (navigate to) routes in the react application
                routes {
                    route {
                        name = ComponentBoundary.Browser.Routes.home
                        path = Routes.Browser.home
                    }
                    route {
                        name = ComponentBoundary.Browser.Routes.logout
                        path = Routes.Browser.logout
                    }
                }
            }
        }
    }
}

```