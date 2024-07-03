package ch.kata.architecture.application.usecase

import ch.kata.architecture.application.entity.Commodity
import ch.kata.architecture.application.entity.Planet
import ch.kata.architecture.application.port.left.exception.CommodityAlreadyExists
import ch.kata.architecture.application.port.right.CommodityRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.assertNull
import kotlin.test.assertSame

@ExtendWith(MockitoExtension::class)
class CommodityManagementTest {

    @Mock
    lateinit var commodityRepository: CommodityRepository

    @InjectMocks
    lateinit var testee: CommodityManagement

    @Test
    fun addToCatalogue_when_newCommodityAdded() {
        // arrange
        val expectedName = "Fluxkompensator"
        val expectedPrice = 1000000UL

        // act
        testee.addToCatalogue(expectedName, expectedPrice)

        // assert
        val expectedCommodity =  Commodity(expectedName, expectedPrice)
        verify(commodityRepository).upsert(expectedCommodity)
    }

    @Test
    fun addToCatalogue_when_nameExists_then_Exception() {
        // arrange
        val expectedName = "Fluxkompensator"
        val expectedPrice = 1000000UL
        val expectedCommodity =  Commodity(expectedName, expectedPrice)

        `when`(commodityRepository.findByName(expectedName)).thenReturn(expectedCommodity)

        // act
        assertThrows<CommodityAlreadyExists> {
            testee.addToCatalogue(expectedName, 10UL)
        }

    }

    @Test
    fun test_findCommodity_when_planetExists_then_returnValue() {
        // arrange
        val expectedCommodity =  Commodity("FluxCompensator", 1000000UL)
        `when`(commodityRepository.findByName(expectedCommodity.name)).thenReturn(expectedCommodity)

        // act
        val actualCommodity = testee.findCommodity(expectedCommodity.name)

        // assert
        assertSame(expectedCommodity, actualCommodity)
    }

    @Test
    fun test_findCommodity_when_commodityNotExists_then_returnNull() {
        // arrange
        val expectedCommodityName = "DoesNotExist"
        `when`(commodityRepository.findByName(expectedCommodityName)).thenReturn(null)

        // act
        val actualCommodity = testee.findCommodity(expectedCommodityName)

        // assert
        assertNull(actualCommodity)
    }
}