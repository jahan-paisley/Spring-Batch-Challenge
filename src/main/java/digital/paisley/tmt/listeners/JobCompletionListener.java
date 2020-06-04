package digital.paisley.tmt.listeners;

import digital.paisley.tmt.entities.StoreOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@Slf4j
public class JobCompletionListener extends JobExecutionListenerSupport {

    private final JdbcTemplate jdbcTemplate;

    public JobCompletionListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Executing job id " + jobExecution.getId());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("============ JOB FINISHED ============ Verifying the results....\n");

            List<StoreOrder> results = jdbcTemplate.query("SELECT * FROM PUBLIC.STORE_ORDER", new RowMapper<StoreOrder>() {
                @Override
                public StoreOrder mapRow(ResultSet rs, int row) throws SQLException {
                    StoreOrder storeOrderBuilder = StoreOrder.builder().id(rs.getLong(1)).customerId(rs.getString(3)).build();
                    return storeOrderBuilder;
                }
            });

            for (StoreOrder item : results) {
                log.info("Discovered Customer Id<" + item.customerId + "> in the database.");
            }

        }
    }
}
