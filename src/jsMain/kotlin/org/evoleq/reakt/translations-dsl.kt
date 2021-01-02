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

import org.drx.dynamics.ID


@BoundaryDsl
inline fun <reified Type> Boundary.translations(noinline configuration: Translations.()->Unit) {
    if(translations[Type::class] == null) {
        translations[Type::class] = Translations()
    }
    translations[Type::class]!!.configuration()
}

/**
 * Register a translation (Source)->Target
 * Hint: Here, the type of the source-parameter wil be used as a key for the translation
 */
@BoundaryDsl
inline fun <reified Source, Target> Translations.translate(noinline translate: (Source)-> Target) {
    this[Source::class] = translate.cast()
}

/**
 * Register a translation (Source)->Target under an ID
 */
@BoundaryDsl
fun <Source, Target> Translations.translate(key: ID, translate: (Source)-> Target) {
    this[key] = translate.cast()
}

/**
 * Register a translation (Source)->Target under a String
 */
@BoundaryDsl
fun <Source, Target> Translations.translate(key: String, translate: (Source)-> Target) {
    this[key] = translate.cast()
}

/**
 * Register a translation (Source)->Target under an Int
 */
@BoundaryDsl
fun <Source, Target> Translations.translate(key: Int, translate: (Source)-> Target) {
    this[key] = translate.cast()
}

/**
 * Access a translation-function
 */
@BoundaryDsl
inline fun <reified Key> Translations.translate(): (Any)->Any = this[Key::class]

/**
 * Access a translation-function
 */
@BoundaryDsl
fun Translations.translate(key: ID): (Any)->Any = this[key]

/**
 * Access a translation-function
 */
@BoundaryDsl
fun Translations.translate(key: String): (Any)->Any = this[key]

/**
 * Access a translation-function
 */
@BoundaryDsl
fun Translations.translate(key: Int): (Any)->Any = this[key]

/**
 * Translate some data using the type of the data as id
 */
@BoundaryDsl
fun Translations.translate(data: Any): Any =
    this[data::class](data)

/**
 * Translate some data using an [ID] as a key
 */
@BoundaryDsl
fun Translations.translate(key: ID, data: Any): Any =
    this[key](data)

/**
 * Translate some data using a String as a key
 */
@BoundaryDsl
fun Translations.translate(key: String, data: Any): Any =
    this[key](data)

/**
 * Translate some data using an [Int] as a key
 */
@BoundaryDsl
fun Translations.translate(key: Int, data: Any): Any =
    this[key](data)

/**
 * Translate some piece of data registered under [Type][data:class]
 */
@BoundaryDsl
inline fun <reified Type> HashMap<ID, Translations>.translate(data: Any): Any =
    when(val translations = this[Type::class]) {
        null -> throw TranslationsException.UnknownType(Type::class)
        else -> translations.translate (data)
    }

/**
 * Translate some piece of data registered under [key][data:class]
 */
@BoundaryDsl
inline fun <reified Type>  HashMap<ID, Translations>.translate(key: ID, data: Any): Any =
    when(val translations = this[Type::class]) {
        null -> throw TranslationsException.UnknownType(Type::class)
        else -> translations.translate (key, data)
    }

/**
 * Translate some piece of data registered under [key][data:class]
 */
@BoundaryDsl
inline fun <reified Type>  HashMap<ID, Translations>.translate(key: String, data: Any): Any =
    when(val translations = this[Type::class]) {
        null -> throw TranslationsException.UnknownType(Type::class)
        else -> translations.translate (key, data)
    }

/**
 * Translate some piece of data registered under [key][data:class]
 */
@BoundaryDsl
inline fun <reified Type>  HashMap<ID, Translations>.translate(key: Int, data: Any): Any =
    when(val translations = this[Type::class]) {
        null -> throw TranslationsException.UnknownType(Type::class)
        else -> translations.translate (key, data)
    }

/**
 * Access a translation function of type 'Type' and key: ID
 */
@BoundaryDsl
inline fun <reified Type>  HashMap<ID, Translations>.translate(key: ID): (Any)->Any =
    when(val translations = this[Type::class]) {
        null -> throw TranslationsException.UnknownType(Type::class)
        else -> translations.translate (key)
    }
/**
 * Access a translation function of type 'Type' and key: String
 */
@BoundaryDsl
inline fun <reified Type>  HashMap<ID, Translations>.translate(key: String): (Any)->Any =
    when(val translations = this[Type::class]) {
        null -> throw TranslationsException.UnknownType(Type::class)
        else -> translations.translate (key)
    }

/**
 * Access a translation function of type 'Type' and key: Int
 */
@BoundaryDsl
inline fun <reified Type>  HashMap<ID, Translations>.translate(key: Int): (Any)->Any =
    when(val translations = this[Type::class]) {
        null -> throw TranslationsException.UnknownType(Type::class)
        else -> translations.translate (key)
    }






@BoundaryDsl
inline fun <reified Type> Boundary.translate(data: Any): Any =
    translations.translate<Type>(data)

@BoundaryDsl
inline fun <reified Type> Boundary.translate(key: String,data: Any): Any =
    translations.translate<Type>(key, data)

@BoundaryDsl
inline fun <reified Type> Boundary.translate(key: Int,data: Any): Any =
    translations.translate<Type>(key, data)

@BoundaryDsl
inline fun <reified Type> Boundary.translate(key: ID,data: Any): Any =
    translations.translate<Type>(key, data)


@BoundaryDsl
inline fun <reified Type> Boundary.translate(key: ID): (Any)->Any =
    translations.translate<Type>(key)

@BoundaryDsl
inline fun <reified Type> Boundary.translate(key: String): (Any)->Any =
    translations.translate<Type>(key)

@BoundaryDsl
inline fun <reified Type> Boundary.translate(key: Int): (Any)->Any =
    translations.translate<Type>(key)