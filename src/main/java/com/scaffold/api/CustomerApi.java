package com.scaffold.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.scaffold.model.Customer;
import com.scaffold.response.ApiWrapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("customer")
public class CustomerApi {

    private static final Logger logger = LogManager.getLogger(CustomerApi.class);
    private final ApiWrapper wrapper;

    private Map<String, Customer> customerStore = new ConcurrentHashMap<>();

    public CustomerApi(final ApiWrapper wrapper) {
        this.wrapper = new ApiWrapper();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ApiOperation(value="Find Customer By Id", notes="Provide an Id to look up a customer",
        response=Customer.class)
    public ResponseEntity<String> getCustomer(@ApiParam(value="Id value for the customer you need to retrieve", required=true)
                                                  @PathVariable String id) throws Exception {
        return wrapper.executeWithoutInputs(() -> {
            logger.debug("Get Customer By Id");
            return customerStore.get(id);
        });
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<String> getAllCustomers() throws Exception {
        return wrapper.executeWithoutInputs(() -> {
            logger.debug("Get All Customers");
            return new ArrayList(customerStore.values());
        });
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> saveCustomer(@RequestBody String request) throws Exception {
        return wrapper.execute(new TypeReference<Customer>() {
        }, request, (customer) -> {
            logger.debug("Save Customer");
            customerStore.put(customer.getId(), customer);
            return "saved";
        });
    }
}
