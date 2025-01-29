package app.apiservice.features.trc.paging;

import app.apiservice.common.db.PropertiesReader;
import app.apiservice.common.enums.Datatype;
import app.apiservice.common.enums.SearchOperation;
import app.apiservice.common.exceptions.ResourceServicesException;
import app.apiservice.common.specificationsbuilder.GenericSpecificationBuilder;
import app.apiservice.common.specificationsbuilder.SearchCriteria;
import app.apiservice.common.specificationsbuilder.SortDirection;
import app.apiservice.entity.app.TRCLocalManufacturedDevicesDumpEntity;
import app.apiservice.features.trc.model.AuditTrailModel;
import app.apiservice.repository.app.LMDataRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Component
public class LMDataPaging {

    private final Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    private PropertiesReader propertiesReader;
    private final LMDataRepository lmDataRepository;

    public LMDataPaging(LMDataRepository lmDataRepository) {
        this.lmDataRepository = lmDataRepository;
    }

    public Page<TRCLocalManufacturedDevicesDumpEntity> findAll(TRCLocalManufacturedDevicesDumpEntity trcLocalManufacturedDevicesDumpEntity, int pageNo, int pageSize) {

        try {
            logger.info("request received : " + trcLocalManufacturedDevicesDumpEntity + " [pageNo] " + pageNo + " [pageSize]" + pageSize);
            String sort = null, orderColumn = null;
            if (Objects.nonNull(trcLocalManufacturedDevicesDumpEntity.getAuditTrailModel())) {
                sort = Objects.nonNull(trcLocalManufacturedDevicesDumpEntity.getAuditTrailModel().getSort()) ? trcLocalManufacturedDevicesDumpEntity.getAuditTrailModel().getSort() : "desc";
                orderColumn = Objects.nonNull(trcLocalManufacturedDevicesDumpEntity.getAuditTrailModel().getColumnName()) ? trcLocalManufacturedDevicesDumpEntity.getAuditTrailModel().getColumnName() : "Uploaded Date";
            } else {
                sort = "desc";
                orderColumn = "Uploaded Date";
            }
            orderColumn = sortColumnName(orderColumn);
            Sort.Direction direction = SortDirection.getSortDirection(sort);
            logger.info("request received : " + trcLocalManufacturedDevicesDumpEntity + " [pageNo] ...." + pageNo + " [pageSize]...." + pageSize);

            logger.info("orderColumn is : " + orderColumn + " & direction is : " + direction);

            Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction, orderColumn));
            Page<TRCLocalManufacturedDevicesDumpEntity> page = lmDataRepository.findAll(buildSpecification(trcLocalManufacturedDevicesDumpEntity).build(), pageable);
            logger.info("paging API response [" + page + "]");
            return page;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
        }
    }

    public String sortColumnName(String columnName) {
        Map<String, String> map = new HashMap<>();
        if (Objects.nonNull(columnName) && !columnName.isEmpty()) {
            map.put("Created On", "createdOn");
            map.put("Uploaded Date", "createdOn");
            map.put("Modified On", "modifiedOn");
            map.put("Serial Number", "serialNumber");
            map.put("IMEI", "actualImei");
            map.put("Manufacturer ID", "manufacturerId");
            map.put("Manufacturer Name", "manufacturerName");
        }
        return map.get(columnName);
    }


    private GenericSpecificationBuilder<TRCLocalManufacturedDevicesDumpEntity> buildSpecification(TRCLocalManufacturedDevicesDumpEntity trcLocalManufacturedDevicesDumpEntity) {
        logger.info("FilterRequest payload : [" + trcLocalManufacturedDevicesDumpEntity + "]");
        GenericSpecificationBuilder<TRCLocalManufacturedDevicesDumpEntity> cmsb = new GenericSpecificationBuilder<>(propertiesReader.dialect);
        Optional<AuditTrailModel> optional = Optional.ofNullable(trcLocalManufacturedDevicesDumpEntity.getAuditTrailModel());
        if (optional.isPresent()) {
            cmsb.with(new SearchCriteria("createdOn", optional.get().getStartDate(), SearchOperation.GREATER_THAN, Datatype.DATE));
            cmsb.with(new SearchCriteria("createdOn", optional.get().getEndDate(), SearchOperation.LESS_THAN, Datatype.DATE));
        }
      //  cmsb.with(new SearchCriteria("actualImei", trcLocalManufacturedDevicesDumpEntity.getActualImei(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("actualImei", trcLocalManufacturedDevicesDumpEntity.getImei(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("serialNumber", trcLocalManufacturedDevicesDumpEntity.getSerialNumber(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("manufacturerName", trcLocalManufacturedDevicesDumpEntity.getManufacturerName(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("manufacturerId", trcLocalManufacturedDevicesDumpEntity.getManufacturerId(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("manufactureringDate", trcLocalManufacturedDevicesDumpEntity.getManufactureringDate(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("serialNumber", trcLocalManufacturedDevicesDumpEntity.getSerialNumber(), SearchOperation.LIKE, Datatype.STRING));

        return cmsb;
    }
}
