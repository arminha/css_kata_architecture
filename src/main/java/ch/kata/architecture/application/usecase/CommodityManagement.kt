package ch.kata.architecture.application.usecase

import ch.kata.architecture.application.entity.Commodity
import ch.kata.architecture.application.port.left.CommodityManagementPort
import ch.kata.architecture.application.port.left.exception.CommodityAlreadyExists
import ch.kata.architecture.application.port.right.CommodityRepository

class CommodityManagement(val commodityRepository: CommodityRepository) : CommodityManagementPort {

    override fun addToCatalogue(name: String, priceCents: ULong) {
        if (commodityRepository.findByName(name) != null) {
            throw CommodityAlreadyExists();
        }

        commodityRepository.upsert(Commodity(name, priceCents))
    }

    override fun findCommodity(name: String): Commodity? {
        return commodityRepository.findByName(name)
    }

}