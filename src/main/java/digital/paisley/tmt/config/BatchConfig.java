package digital.paisley.tmt.config;

import digital.paisley.tmt.entities.StoreOrder;
import digital.paisley.tmt.processors.DBLogProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    @Value("classPath:/input/sales.csv")
    private Resource inputResource;

    @Bean
    public Job readCSVFileJob() {
        return jobBuilderFactory
                .get("readCSVFileJob")
                .incrementer(new RunIdIncrementer())
                .start(step())
                .build();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory
                .get("step")
                .<StoreOrder, StoreOrder>chunk(5)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public ItemProcessor<StoreOrder, StoreOrder> processor() {
        return new DBLogProcessor();
    }

    @Bean
    public FlatFileItemReader<StoreOrder> reader() {
        FlatFileItemReader<StoreOrder> itemReader = new FlatFileItemReader<StoreOrder>();
        itemReader.setLineMapper(lineMapper());
        itemReader.setLinesToSkip(1);
        itemReader.setResource(inputResource);
        return itemReader;
    }

    @Bean
    public LineMapper<StoreOrder> lineMapper() {
        DefaultLineMapper<StoreOrder> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames(new String[]{"orderId","orderDate","shipDate","shipMode","customerId","customerName","productId","category","productName","quantity","discount","profit"});
        lineTokenizer.setIncludedFields(new int[]{ 1,2,3,4,5,6,13,14,16,18,19,20});
        BeanWrapperFieldSetMapper<StoreOrder> mapper = new BeanWrapperFieldSetMapperCustom<>();
        mapper.setTargetType(StoreOrder.class);
        mapper.setDistanceLimit(0);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(mapper);
        return lineMapper;
    }

    @Bean
    public JdbcBatchItemWriter<StoreOrder> writer() {
        JdbcBatchItemWriter<StoreOrder> itemWriter = new JdbcBatchItemWriter<StoreOrder>();
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<StoreOrder>());
        itemWriter.setSql("INSERT INTO STORE_ORDER (ORDER_ID, ORDER_DATE, SHIP_DATE, SHIP_MODE, CUSTOMER_ID, CUSTOMER_NAME,PRODUCT_ID,CATEGORY,PRODUCT_NAME,QUANTITY, DISCOUNT, PROFIT)  VALUES ( :orderId, :orderDate, :shipDate, :shipMode, :customerId, :customerName, :productId, :category, :productName, :quantity, :discount, :profit)");
        itemWriter.setDataSource(dataSource);
        return itemWriter;
    }
}
