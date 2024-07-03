package ch.kata.architecture.adapter.controller

import ch.kata.architecture.adapter.controller.dto.CommodityDetails
import ch.kata.architecture.adapter.controller.dto.PlanetDetails
import ch.kata.architecture.application.port.left.CommodityManagementPort
import ch.kata.architecture.application.port.left.PlanetManagementPort


class GalacticTradingPost(val planetManagementPort: PlanetManagementPort, val commodityManagementPort: CommodityManagementPort) {

    fun registerPlanet(name: String, x: Int, y: Int) {
        planetManagementPort.registerPlanet(name, x, y)
    }

    fun retrievePlanetDetails(name: String): PlanetDetails? {
        val planet = planetManagementPort.findPlanet(name) ?: return null

        return PlanetDetails(
            name = planet.name,
            xCoordinate = planet.xCoordinate,
            yCoordinate = planet.yCoordinate
        )
    }

    fun addCommodityToCatalaogue(name: String, priceCents: ULong) {
        commodityManagementPort.addToCatalogue(name, priceCents)
    }

    fun retrieveCommodityDetails(name: String): CommodityDetails? {
        val commodity = commodityManagementPort.findCommodity(name) ?: return null

        return CommodityDetails(
            name = commodity.name,
            priceCents = commodity.priceCents
        )
    }

}




