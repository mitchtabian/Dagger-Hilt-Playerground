package com.codingwithmitch.daggerhiltplayground.business.domain.state


/**
 * Not my standard DataState class. I wanted to simplify things for this Hilt example.
 * My standard implementation is much more complex but handles a wide array of use cases.
 * see https://github.com/mitchtabian/Clean-Notes/blob/master/app/src/main/java/com/codingwithmitch/cleannotes/business/domain/state/DataState.kt
 */
sealed class DataState<out R> {

    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val exception: Exception) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}
