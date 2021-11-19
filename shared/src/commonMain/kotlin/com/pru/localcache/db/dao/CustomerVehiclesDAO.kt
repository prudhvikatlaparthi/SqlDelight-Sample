package com.pru.localcache.db.dao

import com.pru.localcache.CRM_CustomerVehicles
import com.pru.localcache.CRM_CustomerVehiclesQueries

class CustomerVehiclesDAO(private val cmQuery: CRM_CustomerVehiclesQueries) {

    @Throws(Exception::class)
    fun insertCrmCustomerVehicles(crmCustomervehicles: CRM_CustomerVehicles) {
        cmQuery.insertCrmCustomerVehicles(crmCustomervehicles)
    }

}