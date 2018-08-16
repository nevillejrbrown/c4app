package org.nevillejrbrown.simplytherest.model


// A resource whose key is of type K
open class Resource<K>(name:String, val key:K) {

    val model: ResourceModel? = null

    fun parseJSON (json:String) {
        // create a model from the JSON
    }

}