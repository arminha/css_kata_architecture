package ch.kata.architecture.application.port.left

import ch.kata.architecture.application.entity.Commodity

interface CommodityManagementPort {
    fun addToCatalogue(name: String, priceCents: ULong)
    fun findCommodity(name: String): Commodity?
}
