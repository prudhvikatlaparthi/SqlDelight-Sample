package com.pru.localcache.db.repository

import com.pru.localcache.LocalCacheDB
import com.pru.localcache.db.DatabaseDriverFactory
import com.pru.localcache.db.dao.CustomerVehiclesDAO
import com.pru.localcache.db.dao.QR_DAO
import com.pru.localcache.db.dao.TodoDAO

class DataBaseSDK(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = LocalCacheDB(databaseDriverFactory.createDriver())
    private val customerVehiclesDAO = CustomerVehiclesDAO(database.cRM_CustomerVehiclesQueries)
    private val todoDAO = TodoDAO(database.todosQueries)
    private val qrDAO = QR_DAO(database.tBL_QRCODESQueries)

    fun getQRDAO(): QR_DAO = qrDAO
    fun getTodoDAO(): TodoDAO = todoDAO
    fun getCustomerVehiclesDAO(): CustomerVehiclesDAO = customerVehiclesDAO

}