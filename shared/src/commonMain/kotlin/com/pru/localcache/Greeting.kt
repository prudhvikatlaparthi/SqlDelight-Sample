package com.pru.localcache

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}