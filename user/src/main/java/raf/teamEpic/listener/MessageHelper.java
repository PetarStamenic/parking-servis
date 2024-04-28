package raf.teamEpic.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MessageHelper {
    private Validator validator;
    private ObjectMapper objectMapper;

    public <T> T getMessage(Message message, Class<T> tClass) {
        try{
            String json = ((TextMessage) message).getText();
            T data = objectMapper.readValue(json, tClass);

            Set<ConstraintViolation<T>> violations = validator.validate(data);
            if (violations.isEmpty()) {
                return data;
            }
            String violationsString = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", "));
            throw new RuntimeException(violationsString);
        } catch (IOException | JMSException exception) {
            throw new RuntimeException("Message parsing fails.", exception);
        }
    }

    public String createMessage(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Problem with creating text message");
        }
    }
}
