package com.glocks.application.features.trc.rest_controller;

import com.glocks.application.common.model.ResponseModel;
import com.glocks.application.entity.app.MobileDeviceRepository;
import com.glocks.application.entity.app.TRCDataManagementEntity;
import com.glocks.application.features.trc.service.GSMATacService;
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


    @PostMapping("/paging")
    public MappingJacksonValue paging(@RequestBody MobileDeviceRepository mobileDeviceRepository, @RequestParam(value = "pageNo", defaultValue = "0") int pageNo, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        logger.info("MobileDeviceRepository payload for paging [" + mobileDeviceRepository + "]");
        return gsmaTacService.paging(mobileDeviceRepository, pageNo, pageSize);
    }

    @PostMapping("/export")
    public MappingJacksonValue export(@RequestBody MobileDeviceRepository mobileDeviceRepository) {
        logger.info("Approve Device payload for export [" + mobileDeviceRepository + "]");
        return gsmaTacService.export(mobileDeviceRepository);
    }

    @PostMapping
    public ResponseModel update(@RequestBody MobileDeviceRepository mobileDeviceRepository) {
        return gsmaTacService.update(mobileDeviceRepository);

    }
}
