package org.nevillejrbrown.simplytherest.model


// A resource whose key is of type K
abstract class Resource<K>(name:String) {

    var model: ResourceModel? = null

    var key: K? = null

    abstract fun parseJSON (json:String)



}