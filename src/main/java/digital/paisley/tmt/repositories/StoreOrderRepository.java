package digital.paisley.tmt.repositories;

import digital.paisley.tmt.entities.StoreOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StoreOrderRepository extends JpaRepository<StoreOrder, Long>, JpaSpecificationExecutor<StoreOrder> {

	String getByDummy(String dummy);

}
