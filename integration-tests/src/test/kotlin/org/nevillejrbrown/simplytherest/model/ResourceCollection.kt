package org.nevillejrbrown.simplytherest.model

// This an HTTP collection of resources, each of which is of type T.
//
class ResourceCollection<T: Resource<K>, K> (val name:String) {

    // Add a resource to this collection
    open fun add():T? {

        // create the thing - default is to call POST on end point

        // populate the model of the subtype

        // return the object

        return null

    }

    // delete a resource from this collection
    fun delete(key:K) {

    }



}