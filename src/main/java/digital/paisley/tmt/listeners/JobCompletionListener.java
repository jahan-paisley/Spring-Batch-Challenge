package digital.paisley.tmt.listeners;

import digital.paisley.tmt.entities.StoreOrder;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobCompletionListener implements JobExecutionListener {
    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Executing job id " + jobExecution.getId());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            List<StoreOrder> result = jdbcTemplate.query("SELECT * FROM STORE_ORDER",
                    (rs, row) -> {
                        StoreOrder storeOrder = StoreOrder.builder().id(rs.getLong(0)).build();
                        return storeOrder;
                    });
            System.out.println("Number of Records:" + result.size());
        }
    }
}
