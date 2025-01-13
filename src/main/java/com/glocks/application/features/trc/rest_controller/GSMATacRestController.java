package com.glocks.application.features.trc.rest_controller;

import com.glocks.application.common.model.ResponseModel;
import com.glocks.application.entity.app.MobileDeviceRepository;
import com.glocks.application.entity.app.TRCDataManagementEntity;
import com.glocks.application.features.trc.service.GSMATacService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/trc/gsma-tac")
public class GSMATacRestController {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private GSMATacService gsmaTacService;

    public GSMATacRestController(GSMATacService gsmaTacService) {
        this.gsmaTacService = gsmaTacService;
    }

    @Tag(name = "Approve Device TAC", description = "Type Approval Module API")
    @Operation(
            summary = "Fetch all record",
            description = "Fetches all mdr entities from data source  based on trcApprovedStatus")
    @PostMapping("/paging")
    public MappingJacksonValue paging(@RequestBody MobileDeviceRepository mobileDeviceRepository, @RequestParam(value = "pageNo", defaultValue = "0") int pageNo, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        logger.info("MobileDeviceRepository payload for paging [" + mobileDeviceRepository + "]");
        return gsmaTacService.paging(mobileDeviceRepository, pageNo, pageSize);
    }

    @Tag(name = "Approve Device TAC", description = "Type Approval Module API")
    @Operation(
            summary = "Export csv file",
            description = "Fetches all mdr entities and their associated data from the data source, with the number of records limited to a configurable parameter, up to a maximum of 50,000. Subsequently, generate a .csv file containing the retrieved data.")

    @PostMapping("/export")
    public MappingJacksonValue export(@RequestBody MobileDeviceRepository mobileDeviceRepository) {
        logger.info("Approve Device payload for export [" + mobileDeviceRepository + "]");
        return gsmaTacService.export(mobileDeviceRepository);
    }

    @Tag(name = "Approve Device TAC", description = "Type Approval Module API")
    @Operation(
            summary = "Update approve device TAC record",
            description = "Update trcApprovedStatus,approvedBy,trcApprovalDate and remark field in mdr table where isTypeApproved is equal to 1")

    @PostMapping
    public ResponseModel update(@RequestBody MobileDeviceRepository mobileDeviceRepository) {
        return gsmaTacService.update(mobileDeviceRepository);

    }
}
