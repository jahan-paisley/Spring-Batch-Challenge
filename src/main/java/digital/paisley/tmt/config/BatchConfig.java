package digital.paisley.tmt.config;

import digital.paisley.tmt.entities.StoreOrder;
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
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Value("classPath:/input/inputData.csv")
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
        DefaultLineMapper<StoreOrder> lineMapper = new DefaultLineMapper<StoreOrder>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames(new String[] { "id", "firstName", "lastName" });
        lineTokenizer.setIncludedFields(new int[] { 0, 1, 2 });
        BeanWrapperFieldSetMapper<StoreOrder> fieldSetMapper = new BeanWrapperFieldSetMapper<StoreOrder>();
        fieldSetMapper.setTargetType(StoreOrder.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

    @Bean
    public JdbcBatchItemWriter<StoreOrder> writer() {
        JdbcBatchItemWriter<StoreOrder> itemWriter = new JdbcBatchItemWriter<StoreOrder>();
        itemWriter.setDataSource(dataSource());
        //todo:This query should be change
        itemWriter.setSql("INSERT INTO STORE_ORDER (ID) VALUES (:id, :firstName, :lastName)");
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<StoreOrder>());
        return itemWriter;
    }

    @Bean
    public DataSource dataSource(){
        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
        return embeddedDatabaseBuilder.addScript("classpath:org/springframework/batch/core/schema-drop-h2.sql")
                .addScript("classpath:org/springframework/batch/core/schema-h2.sql")
                .addScript("classpath:tmt.sql")
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }
}
