package ch.kata.architecture.application.usecase

import ch.kata.architecture.application.entity.Planet
import ch.kata.architecture.application.port.left.exception.PlanetAlreadyExistsException
import ch.kata.architecture.application.port.right.PlanetRepository
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.stubbing.OngoingStubbing
import kotlin.test.Test
import kotlin.test.assertNull
import kotlin.test.assertSame


@ExtendWith(MockitoExtension::class)
class PlanetManagementTest {

    @Mock
    lateinit var planetRepository: PlanetRepository

    @InjectMocks
    lateinit var testee: PlanetManagement

    @Test
    fun test_registerPlanet_when_planetNotExists_then_upsert() {
        // act
        testee.registerPlanet("Erde", 20, 20);

        // assert
        val expectedPlanet = Planet("Erde", 20, 20)
        verify(planetRepository).upsert(expectedPlanet)
    }

    @Test
    fun test_registerPlanet_when_nameExists_then_error() {
        // arrange
        whenever(planetRepository.findByName("Erde")).thenReturn(Planet("Erde", 20, 20))

        // act & assert
        assertThrows<PlanetAlreadyExistsException> {
            testee.registerPlanet("Erde", 0, 0)
        }
    }

    @Test
    fun test_registerPlanet_when_coordinateExistes_then_error() {
        // arrange
        whenever(planetRepository.findByCoordinate(20, 20)).thenReturn(Planet("Erde", 20, 20))

        // act & assert
        assertThrows<PlanetAlreadyExistsException> {
            testee.registerPlanet("Mars", 20, 20)
        }
    }

    @Test
    fun test_findPlanet_when_planetExists_then_returnValue() {
        // arrange
        val expectedPlanet =  Planet("Erde", 20, 20)
        whenever(planetRepository.findByName(expectedPlanet.name)).thenReturn(expectedPlanet)

        // act
        val actualPlanet = testee.findPlanet(expectedPlanet.name)

        // assert
        assertSame(expectedPlanet, actualPlanet)
    }

    @Test
    fun test_findPlanet_when_planetNotExists_then_returnNull() {
        // arrange
        val expectedPlanetName = "DoesNotExist"
        whenever(planetRepository.findByName(expectedPlanetName)).thenReturn(null)

        // act
        val actualPlanet = testee.findPlanet(expectedPlanetName)

        // assert
        assertNull(actualPlanet)
    }

    private fun <T> whenever(methodCall: T): OngoingStubbing<T> = Mockito.`when`(methodCall)
}