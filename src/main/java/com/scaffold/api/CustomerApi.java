package com.scaffold.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.scaffold.model.Customer;
import com.scaffold.model.Response;
import com.scaffold.response.ApiWrapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("customer")
@Log4j2
public class CustomerApi {

    private final ApiWrapper wrapper;

    private Map<String, Customer> customerStore = new ConcurrentHashMap<>();

    public CustomerApi(final ApiWrapper wrapper) {
        this.wrapper = new ApiWrapper();
    }

    @GetMapping(value = "/v1.0/{id}", produces = "application/json")
    @ApiOperation(value = "Find Customer By Id", notes = "Provide an Id to look up a customer",
            response = Customer.class)
    public ResponseEntity<?> getCustomer(@ApiParam(value = "Id value for the customer you need to retrieve", required = true)
                                                          @PathVariable String id) throws Exception {
        log.debug("Get Customer By Id");
        return ResponseEntity.ok(new Response(customerStore.get(id), HttpStatus.OK.value()));
    }

    @GetMapping(value = "/v1.0", produces = "application/json")
    public ResponseEntity<Response<List<Customer>>> getAllCustomers() throws Exception {
        log.debug("Get All Customers");
        return ResponseEntity.ok(new Response(new ArrayList(customerStore.values()), HttpStatus.OK.value()));
    }

    @PostMapping(value = "/v1.0", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> saveCustomer(@RequestBody Customer customer) throws Exception {
        log.debug("Save Customer");
        customerStore.put(customer.getId(), customer);
        return ResponseEntity.ok(new Response("SAVED", HttpStatus.OK.value()));
    }
}
