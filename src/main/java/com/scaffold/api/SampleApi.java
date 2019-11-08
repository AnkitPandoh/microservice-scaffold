package com.scaffold.api;

import com.scaffold.response.ApiWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customer")
public class SampleApi {

    private static final Logger logger = LogManager.getLogger(SampleApi.class);
    private final ApiWrapper wrapper;

    public SampleApi(final ApiWrapper wrapper){
        this.wrapper = new ApiWrapper();
    }

    @GetMapping(value="/v1/{id}", produces="application/json")
    public ResponseEntity<String> getResponse(@PathVariable String id) throws Exception {
        return wrapper.executeWithoutInputs(()->{
            logger.debug("Debugging log");
            logger.info("Info log");
            logger.warn("Hey, This is a warning!");
            logger.error("Oops! We have an Error. OK");
            logger.fatal("Damn! Fatal error. Please fix me.");
            return "hi";
        });
    }
}
