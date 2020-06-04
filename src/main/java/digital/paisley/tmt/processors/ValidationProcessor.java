//package digital.paisley.tmt.processors;
//
//import digital.paisley.tmt.entities.StoreOrder;
//import org.springframework.batch.item.validator.ValidatingItemProcessor;
//import org.springframework.batch.item.validator.ValidationException;
//import org.springframework.batch.item.validator.Validator;
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class ValidationProcessor extends ValidatingItemProcessor<StoreOrder> {
//    @Autowired
//    private Validator<? super StoreOrder> validator;
//
//    @Override
//    public void afterPropertiesSet() {
//        super.setValidator(new CustomerValidator() );
//
//    }
//
//    @Override
//    public void setValidator(Validator<? super StoreOrder> validator) {
//        this.validator = validator;
//    }
//
//    @Override
//    public void setFilter(boolean filter) {
//        super.setFilter(true);
//    }
//
//    @Override
//    public StoreOrder process(StoreOrder item) throws ValidationException {
//
//        //while calling the super.process it throws NullPointerException for the super.validator
//        //even when I set the validator in the afterPropertiesSet using new it also throws a
//        //NullPointerException I also set the validator here to avoid null
//        return super.process(item);
//
//    }
//}
