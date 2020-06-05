package digital.paisley.tmt.listeners;

import digital.paisley.tmt.entities.StoreOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.annotation.OnProcessError;
import org.springframework.batch.core.annotation.OnReadError;
import org.springframework.batch.core.annotation.OnSkipInWrite;

@Slf4j
public class JobExceptionSkipListener {

    @OnReadError
    public void problemOnRead(Exception ex) {
        log.error("Error on Reading CSV is:" + ex.getMessage());
    }

    @OnProcessError
    public void problemOnProcess(StoreOrder storeOrder, Exception ex) {
        log.info("Error on Processing  is:" + ex.getMessage() + " Input is:" + storeOrder.toString());
    }

    @OnSkipInWrite
    public void problemOnWrite(StoreOrder storeOrder, Throwable ex) {
        log.info("Error on Writing  is:" + ex.getMessage());
    }

}
