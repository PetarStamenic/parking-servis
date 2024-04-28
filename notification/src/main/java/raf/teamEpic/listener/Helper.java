package raf.teamEpic.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Helper {
    private Validator validator;
    private ObjectMapper objectMapper;

    public <T> T getMessage(Message message,Class<T> tClass)throws RuntimeException, JMSException{
        try {
            String json = ((TextMessage) message).getText();
            T data = objectMapper.readValue(json,tClass);
            Set<ConstraintViolation<T>> violations = validator.validate(data);
            if(violations.isEmpty())
                return data;
            printViolations(violations);
            return null;


        }catch (Exception e){
            throw new RuntimeException("Parsing failed",e);
        }
    }

    private <T> void printViolations(Set<ConstraintViolation<T>> violations ){
        String concatenatedViolations = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(" , "));
        throw new RuntimeException(concatenatedViolations);
    }
}
