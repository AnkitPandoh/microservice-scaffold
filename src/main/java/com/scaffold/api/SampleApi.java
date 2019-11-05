package com.scaffold.api;

import com.scaffold.exception.ApplicationException;
import com.scaffold.response.ApiWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customer")
public class SampleApi {

    private final ApiWrapper wrapper;

    public SampleApi(final ApiWrapper wrapper){
        this.wrapper = new ApiWrapper();
    }

    @GetMapping(value="/v1/{id}", produces="application/json")
    public ResponseEntity<String> getResponse(@PathVariable String id) throws Exception {
        return wrapper.executeWithoutInputs(()->{
            throw new ApplicationException("failed");
        });
    }
}
