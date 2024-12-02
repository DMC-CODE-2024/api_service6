package com.glocks.application.features.trc.rest_controller;

import com.glocks.application.entity.app.MobileDeviceRepository;
import com.glocks.application.entity.app.TRCDataManagementEntity;
import com.glocks.application.entity.app.TRCTypeApprovedDataEntity;
import com.glocks.application.features.trc.service.DistinctParamService;
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
@RequestMapping("/trc/distinct")
public class DropdownRestController {
    private final Logger logger = LogManager.getLogger(this.getClass());

    private DistinctParamService distinctParamService;

    public DropdownRestController(DistinctParamService distinctParamService) {
        this.distinctParamService = distinctParamService;
    }

    @PostMapping
    public ResponseEntity<?> distinct(@RequestBody List<String> param) {
        Class<TRCDataManagementEntity> entity = TRCDataManagementEntity.class;
        return new ResponseEntity<>(distinctParamService.distinct(param, entity), HttpStatus.OK);
    }

    @PostMapping("/ta-data")
    public ResponseEntity<?> distinctTADataDropdown(@RequestBody List<String> param) {
        Class<TRCTypeApprovedDataEntity> entity = TRCTypeApprovedDataEntity.class;
        return new ResponseEntity<>(distinctParamService.distinct(param, entity), HttpStatus.OK);
    }

    @PostMapping("/approve-device-tac")
    public ResponseEntity<?> distinctApproveDeviceTACDropdown(@RequestBody List<String> param) {
        Class<MobileDeviceRepository> entity = MobileDeviceRepository.class;
        return new ResponseEntity<>(distinctParamService.distinct(param, entity), HttpStatus.OK);
    }
}
