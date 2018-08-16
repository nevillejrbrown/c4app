package org.nevillejrbrown.simplytherest.model

// This an HTTP collection of resources, each of which is of type T.
//
class Collection<T: Resource<K>, K> (val name:String, val parent:String) {

    // Add a resource to this collection
    fun add():T? {
        return null
    }

    // delete a resource from this collection
    fun delete(key:K) {

    }
    
}