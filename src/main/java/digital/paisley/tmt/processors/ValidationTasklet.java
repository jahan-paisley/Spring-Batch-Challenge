//package digital.paisley.tmt.processors;
//
//import digital.paisley.tmt.entities.StoreOrder;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.FlatFileItemWriter;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.batch.core.*;
//import javax.validation.*;
//import java.util.List;
//
//@Component
//public class ValidationTasklet implements Tasklet {
//
//    private final FlatFileItemReader<StoreOrder> readService;
//    private final FlatFileItemWriter<StoreOrder> writeService;
//    private final Validator validator;
//
//    public ValidationTasklet(FlatFileItemReader<StoreOrder> readService, FlatFileItemWriter<StoreOrder> writeService, Validator validator) {
//        this.readService = readService;
//        this.writeService = writeService;
//        this.validator = validator;
//    }
//
//    @Override
//    public RepeatStatus execute(StepContribution sc, ChunkContext cc) throws Exception {
//
//        // read the input file
//        List<StoreOrder> userList = (List<StoreOrder>) readService.read();
//
//        // validate the input
//        List<User> validUserList = userList.stream()
//                .map( user -> {
//                    Set<ConstraintViolation<User> violations = validator.validate(user);
//                    return violations.isEmpty() ? user : null;
//                })
//                .filter(Objects::nonNull)
//                .collect(Collectors.toList());
//
//        // write the output file
//        writeService.writeFile(validUserList,"/path/to/output/file");
//
//        return RepeatStatus.FINISHED;
//    }
//
//    @Override
//    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//        return null;
//    }
//
//    // Constructor
//}