package com.glocks.application.features.trc.rest_controller;

import com.glocks.application.entity.app.TRCLocalManufacturedDevicesDumpEntity;
import com.glocks.application.features.trc.service.LMDataPagingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/trc/lm-data")
public class LMDataRestController {
    private final Logger logger = LogManager.getLogger(this.getClass());

    private final LMDataPagingService lmDataPagingService;

    public LMDataRestController(LMDataPagingService lmDataPagingService) {
        this.lmDataPagingService = lmDataPagingService;
    }

    @PostMapping("/paging")
    public MappingJacksonValue paging(@RequestBody TRCLocalManufacturedDevicesDumpEntity trcLocalManufacturedDevicesDumpEntity, @RequestParam(value = "pageNo", defaultValue = "0") int pageNo, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        logger.info("TRCLocalManufacturedDevicesDumpEntity payload for paging [" + trcLocalManufacturedDevicesDumpEntity + "]");
        return lmDataPagingService.paging(trcLocalManufacturedDevicesDumpEntity, pageNo, pageSize);
    }

    @PostMapping("/export")
    public MappingJacksonValue export(@RequestBody TRCLocalManufacturedDevicesDumpEntity trcLocalManufacturedDevicesDumpEntity) {
        logger.info("TRCLocalManufacuredDevicesDumpEntity payload for export [" + trcLocalManufacturedDevicesDumpEntity + "]");
        return lmDataPagingService.export(trcLocalManufacturedDevicesDumpEntity);
    }

}
