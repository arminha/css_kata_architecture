package ch.kata.architecture.application.port.right

import ch.kata.architecture.application.entity.Planet

interface PlanetRepository {
    fun findByName(s: String): Planet?
    fun findByCoordinate(i: Int, i1: Int): Planet?
    fun upsert(eq: Planet)

}
