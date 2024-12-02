package com.glocks.application.features.listmanagement.rest_controller;

import com.glocks.application.entity.app.*;
import com.glocks.application.features.listmanagement.service.ExceptionListFeatureDistinctParamService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/list-mgmt/distinct")
public class ExceptionListDropdownRestController {
    private final Logger logger = LogManager.getLogger(this.getClass());

    private ExceptionListFeatureDistinctParamService exceptionListFeatureDistinctParamService;

    public ExceptionListDropdownRestController(ExceptionListFeatureDistinctParamService exceptionListFeatureDistinctParamService) {
        this.exceptionListFeatureDistinctParamService = exceptionListFeatureDistinctParamService;
    }

    @PostMapping
    public ResponseEntity<?> distinct(@RequestBody List<String> param) {
        Class<EIRSListManagementEntity> entity = EIRSListManagementEntity.class;
        return new ResponseEntity<>(exceptionListFeatureDistinctParamService.distinct(param, entity), HttpStatus.OK);
    }

    @PostMapping("/exception-list")
    public ResponseEntity<?> distinctTADataDropdown(@RequestBody List<String> param) {
        Class<ExceptionListEntity> entity = ExceptionListEntity.class;
        return new ResponseEntity<>(exceptionListFeatureDistinctParamService.distinct(param, entity), HttpStatus.OK);
    }

    @PostMapping("/block-list")
    public ResponseEntity<?> distinctBlockListDropdown(@RequestBody List<String> param) {
        Class<BlockListEntity> entity = BlockListEntity.class;
        return new ResponseEntity<>(exceptionListFeatureDistinctParamService.distinct(param, entity), HttpStatus.OK);
    }

    @PostMapping("/gray-list")
    public ResponseEntity<?> distinctGrayListDropdown(@RequestBody List<String> param) {
        Class<GrayListEntity> entity = GrayListEntity.class;
        return new ResponseEntity<>(exceptionListFeatureDistinctParamService.distinct(param, entity), HttpStatus.OK);
    }
    @PostMapping("/block-tac")
    public ResponseEntity<?> distinctBlockTACDropdown(@RequestBody List<String> param) {
        Class<BlockTACListEntity> entity = BlockTACListEntity.class;
        return new ResponseEntity<>(exceptionListFeatureDistinctParamService.distinct(param, entity), HttpStatus.OK);
    }
}
