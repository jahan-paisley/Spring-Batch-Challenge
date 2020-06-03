package digital.paisley.tmt.services;

import digital.paisley.tmt.entities.StoreOrder;
import digital.paisley.tmt.repositories.StoreOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class StoreOrderServiceTest {

    @Mock
    private StoreOrderRepository mockStoreOrderRepository;

    private StoreOrderService storeOrderServiceUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        storeOrderServiceUnderTest = new StoreOrderService( mockStoreOrderRepository);
    }

    @Test
    void testGetStoreOrderById() {
        // Setup

        // Configure DummyRepository.getOne(...).
        final StoreOrder storeOrder = new StoreOrder();
        storeOrder.setId(0L);
        storeOrder.setCategory("dummy");
        when(mockStoreOrderRepository.getOne(0L)).thenReturn(storeOrder);

        // Run the test
        final String result = storeOrderServiceUnderTest.getDummyById(0L);

        // Verify the results
        assertEquals("dummy", result);
    }

}
