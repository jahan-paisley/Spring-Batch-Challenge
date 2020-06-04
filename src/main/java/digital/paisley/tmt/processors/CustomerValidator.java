//package digital.paisley.tmt.processors;
//
//import digital.paisley.tmt.entities.StoreOrder;
//import org.springframework.batch.item.validator.ValidationException;
//import org.springframework.batch.item.validator.Validator;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.validation.ConstraintViolation;
//import javax.validation.Validation;
//import javax.validation.ValidatorFactory;
//import java.util.Set;
//
//@Component
//public class CustomerValidator implements Validator<StoreOrder> {
//
//    private final Validator validator;
//
//    @Override
//    public void afterPropertiesSet() {
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        validator = factory.getValidator();
//    }
//
//    @Override
//    public void validate(StoreOrder record) throws ValidationException {
//
//        Set<ConstraintViolation<StoreOrder>> violations = validator.validate(record);
//        if (violations.size() > 0) {
//            generateValidationException(violations);
//        }
//
//    }
//
//    public void generateValidationException(Set<ConstraintViolation<StoreOrder>> violations)
//            throws ValidationException {
//        StringBuilder message = new StringBuilder();
//        for (ConstraintViolation<StoreOrder> violation : violations) {
//            message.append(violation.getMessage());
//        }
//        throw new ValidationException(message.toString());
//    }
//}
