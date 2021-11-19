package com.pru.localcache.db.dao

import com.pru.localcache.QR_Codes
import com.pru.localcache.TBL_QRCODESQueries

class QR_DAO(private val qrQuery: TBL_QRCODESQueries) {

    @Throws(Exception::class)
    fun insertQRCODES(qrCodes: List<QR_Codes>) {
        qrQuery.transaction {
            qrCodes.forEach {
                qrQuery.insertQRCODE(it)
            }
        }
    }
}