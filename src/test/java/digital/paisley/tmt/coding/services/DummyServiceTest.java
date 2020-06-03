package digital.paisley.tmt.coding.services;

import digital.paisley.tmt.coding.clients.DummyClient;
import digital.paisley.tmt.coding.entities.StoreOrder;
import digital.paisley.tmt.coding.repositories.DummyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class DummyServiceTest {

    @Mock
    private DummyClient mockDummyClient;
    @Mock
    private DummyRepository mockDummyRepository;

    private DummyService dummyServiceUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        dummyServiceUnderTest = new DummyService(mockDummyClient, mockDummyRepository);
    }

    @Test
    void testGetDummyById() {
        // Setup

        // Configure DummyRepository.getOne(...).
        final StoreOrder dummy = new StoreOrder();
        dummy.setId(0L);
        dummy.setCategory("dummy");
        when(mockDummyRepository.getOne(0L)).thenReturn(dummy);

        // Run the test
        final String result = dummyServiceUnderTest.getDummyById(0L);

        // Verify the results
        assertEquals("dummy", result);
    }

    @Test
    void testGetDummyByIdFromClient() {
        // Setup
        when(mockDummyClient.getById(0L)).thenReturn("result");

        // Run the test
        final String result = dummyServiceUnderTest.getDummyByIdFromClient(0L);

        // Verify the results
        assertEquals("result", result);
    }
}
