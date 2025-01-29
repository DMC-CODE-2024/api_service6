package app.apiservice.features.trc.rest_controller;

import app.apiservice.entity.app.MobileDeviceRepository;
import app.apiservice.entity.app.TRCDataManagementEntity;
import app.apiservice.entity.app.TRCTypeApprovedDataEntity;
import app.apiservice.features.trc.service.DistinctParamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @Tag(name = "Type Approval", description = "Type Approval Module API")
    @Operation(
            summary = "Fetch distinct value from the data source",
            description = "Fetch distinct values for the type approval based on the received request")
    @PostMapping
    public ResponseEntity<?> distinct(@RequestBody List<String> param) {
        Class<TRCDataManagementEntity> entity = TRCDataManagementEntity.class;
        return new ResponseEntity<>(distinctParamService.distinct(param, entity), HttpStatus.OK);
    }

    @Tag(name = "Type Approved", description = "Type Approval Module API")
    @Operation(
            summary = "Fetch distinct value from the data source",
            description = "Fetch distinct values for the type approved based on the received request")
    @PostMapping("/ta-data")
    public ResponseEntity<?> distinctTADataDropdown(@RequestBody List<String> param) {
        Class<TRCTypeApprovedDataEntity> entity = TRCTypeApprovedDataEntity.class;
        return new ResponseEntity<>(distinctParamService.distinct(param, entity), HttpStatus.OK);
    }

    @Tag(name = "Approve Device TAC", description = "Type Approval Module API")
    @Operation(
            summary = "Fetch distinct value from the data source",
            description = "Fetch distinct values for the approve device tac based on the received request")
    @PostMapping("/approve-device-tac")
    public ResponseEntity<?> distinctApproveDeviceTACDropdown(@RequestBody List<String> param) {
        Class<MobileDeviceRepository> entity = MobileDeviceRepository.class;
        return new ResponseEntity<>(distinctParamService.distinct(param, entity), HttpStatus.OK);
    }
}
