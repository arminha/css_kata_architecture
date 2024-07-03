package ch.kata.architecture.application.usecase

import ch.kata.architecture.application.entity.Planet
import ch.kata.architecture.application.port.left.PlanetManagementPort
import ch.kata.architecture.application.port.left.exception.PlanetAlreadyExistsException
import ch.kata.architecture.application.port.right.PlanetRepository

class PlanetManagement(val planetRepository: PlanetRepository): PlanetManagementPort {

    override fun registerPlanet(name: String, x: Int, y: Int) {
        if (planetRepository.findByName(name) != null) {
            throw PlanetAlreadyExistsException();
        }

        if (planetRepository.findByCoordinate(x, y) != null) {
            throw PlanetAlreadyExistsException();
        }

        planetRepository.upsert(Planet(name, x, y))
    }

    override fun findPlanet(name: String): Planet? = planetRepository.findByName(name)
}