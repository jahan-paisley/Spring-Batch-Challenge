package digital.paisley.tmt.services;

import digital.paisley.tmt.repositories.StoreOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StoreOrderService {


	private final StoreOrderRepository storeOrderRepository;

	private static Logger log = LoggerFactory.getLogger(StoreOrderService.class);

	public StoreOrderService(StoreOrderRepository storeOrderRepository) {
		this.storeOrderRepository = storeOrderRepository;
	}

	public String getDummyById(Long id) {
		return "dummy";
	}

}
