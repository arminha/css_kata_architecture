package ch.kata.architecture.application.port.left

import ch.kata.architecture.application.entity.Planet

interface PlanetManagementPort {
    fun registerPlanet(name: String, x: Int, y: Int)

    fun findPlanet(name: String): Planet?
}
