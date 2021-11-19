package com.pru.localcache.models

import com.pru.localcache.TBL_TODOS


data class TodoItem(
    val id: Long? = null,
    val title: String?,
    val description: String?,
    val status : String?,
    val testColumn : String?,
)
