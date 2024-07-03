package ch.kata.architecture.application.port.right

import ch.kata.architecture.application.entity.Commodity

interface CommodityRepository {

    fun findByName(name: String): Commodity?
    fun upsert(commodity: Commodity)
}
