package digital.paisley.tmt.coding.repositories;

import digital.paisley.tmt.coding.entities.StoreOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DummyRepository  extends JpaRepository<StoreOrder, Long>, JpaSpecificationExecutor<StoreOrder> {

	String getByDummy(String dummy);

}
