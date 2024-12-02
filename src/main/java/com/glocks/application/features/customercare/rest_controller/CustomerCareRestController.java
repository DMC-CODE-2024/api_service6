package com.glocks.application.features.customercare.rest_controller;

import com.glocks.application.features.customercare.model.CustomerCareRequest;
import com.glocks.application.features.customercare.model.CustomerCareResponse;
import com.glocks.application.features.customercare.service.CustomerCareService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/customer-care")
public class CustomerCareRestController {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private CustomerCareService customerCareService;

    public CustomerCareRestController(CustomerCareService customerCareService) {
        this.customerCareService = customerCareService;
    }

    @PostMapping("/ids")
    public ResponseEntity<?> get(@RequestBody CustomerCareRequest customerCareRequest) {
        logger.info("[ requestID " + customerCareRequest.getRequestId() + "]");
        return new ResponseEntity<>(customerCareService.get(customerCareRequest), HttpStatus.OK);
    }
}
