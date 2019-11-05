package com.scaffold.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.scaffold.exception.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ApiWrapper {

    private final ObjectMapper mapper;
    private final ObjectWriter writer;

    private final Logger log = LoggerFactory.getLogger(ApiWrapper.class);


    public ApiWrapper() {
        this.mapper = new ObjectMapper();
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        this.mapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));

        this.writer = mapper.writerWithDefaultPrettyPrinter();
    }

    public <Q, R> ResponseEntity<String> execute(TypeReference<Q> requestType, String requestBody,
                                                 BusinessLogic<Q, R> businessLogic) throws Exception {
        Q request;
        try {
            request = mapper.readValue(requestBody, requestType);
        } catch (IOException ioex) {
            log.error("Exception occured while reading input", ioex);
            return new ResponseEntity<>(formatResponse(new ApiResponse<>(ioex.getMessage() == null
                    ? "Please check your request" : ioex.getMessage())),
                    HttpStatus.BAD_REQUEST);
        }

        try {
            R response = null;
            response = businessLogic.runLogic(request);
            return new ResponseEntity<>(formatResponse(new ApiResponse<>(response)), HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Exception occured", ex);
            return new ResponseEntity<>(
                    formatResponse(new ApiResponse<>(ex.getMessage() == null ?
                            "Something went wrong while processing your request"
                            : ex.getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public <R> ResponseEntity<String> executeWithoutInputs(BusinessLogicWithoutInputs<R> businessLogic)
            throws Exception {
        R response = null;
        try {
            response = businessLogic.runLogic();
            return new ResponseEntity<>(formatResponse(new ApiResponse<>(response)), HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Exception occured", ex);
            return new ResponseEntity<>(
                    formatResponse(new ApiResponse<>(ex.getMessage() == null ?
                            "Something went wrong while processing your request" : ex.getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private <R> String formatResponse(ApiResponse<R> response) throws Exception {
        try {
            return writer.writeValueAsString(response);
        } catch (JsonProcessingException jex) {
            log.error("Exception occured", jex);
            throw new Exception("Error while sending the response.");
        }
    }

    @FunctionalInterface
    public interface BusinessLogic<Q, R> {
        R runLogic(Q request) throws ApplicationException;
    }

    @FunctionalInterface
    public interface BusinessLogicWithoutInputs<R> {
        R runLogic() throws ApplicationException;
    }


}
