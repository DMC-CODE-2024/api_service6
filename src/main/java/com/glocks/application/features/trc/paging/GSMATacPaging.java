package com.glocks.application.features.trc.paging;

import com.glocks.application.common.db.PropertiesReader;
import com.glocks.application.common.enums.Datatype;
import com.glocks.application.common.enums.SearchOperation;
import com.glocks.application.common.exceptions.ResourceServicesException;
import com.glocks.application.common.specificationsbuilder.GenericSpecificationBuilder;
import com.glocks.application.common.specificationsbuilder.SearchCriteria;
import com.glocks.application.common.specificationsbuilder.SortDirection;
import com.glocks.application.entity.app.MobileDeviceRepository;
import com.glocks.application.repository.app.MobileDeviceRepoRepository;
import com.glocks.application.features.trc.model.AuditTrailModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class GSMATacPaging {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private MobileDeviceRepoRepository mobileDeviceRepoRepository;
    @Autowired
    private PropertiesReader propertiesReader;

    public GSMATacPaging(MobileDeviceRepoRepository mobileDeviceRepoRepository) {
        this.mobileDeviceRepoRepository = mobileDeviceRepoRepository;
    }

    public Page<MobileDeviceRepository> findAll(MobileDeviceRepository mobileDeviceRepository, int pageNo, int pageSize) {

        try {
            logger.info("request received : " + mobileDeviceRepository + " [pageNo] ...." + pageNo + " [pageSize]...." + pageSize);
            String sort = null, orderColumn = null;
            if (Objects.nonNull(mobileDeviceRepository.getAuditTrailModel())) {
                sort = Objects.nonNull(mobileDeviceRepository.getAuditTrailModel().getSort()) ? mobileDeviceRepository.getAuditTrailModel().getSort() : "desc";
                orderColumn = Objects.nonNull(mobileDeviceRepository.getAuditTrailModel().getColumnName()) ? mobileDeviceRepository.getAuditTrailModel().getColumnName() : "Modified On";
            } else {
                sort = "desc";
                orderColumn = "Modified On";
            }

            orderColumn = sortColumnName(orderColumn);
            Sort.Direction direction = SortDirection.getSortDirection(sort);
            logger.info("request received : " + mobileDeviceRepository + " [pageNo] ...." + pageNo + " [pageSize]...." + pageSize);

            logger.info("orderColumn is : " + orderColumn + " & direction is : " + direction);

            Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction, orderColumn));
            Page<MobileDeviceRepository> page;
            page = mobileDeviceRepoRepository.findAll(buildSpecification(mobileDeviceRepository).build(), pageable);
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
            map.put("Model Name", "modelName");
            map.put("Manufacturer", "manufacturer");
            map.put("Manufacturing Country", "manufacturingLocation");
            map.put("OS", "os");
            map.put("Device Type", "deviceType");
            map.put("Sim Slot", "simSlot");
            map.put("TAC", "deviceId");
            map.put("Modified On", "modifiedOn");
        }
        return map.get(columnName);
    }

    private GenericSpecificationBuilder<MobileDeviceRepository> buildSpecification(MobileDeviceRepository mobileDeviceRepository) {
        logger.info("FilterRequest payload : [" + mobileDeviceRepository + "]");
        GenericSpecificationBuilder<MobileDeviceRepository> cmsb = new GenericSpecificationBuilder<>(propertiesReader.dialect);
        Optional<AuditTrailModel> optional = Optional.ofNullable(mobileDeviceRepository.getAuditTrailModel());
        if (optional.isPresent()) {
            String startDate = optional.get().getStartDate();
            String endDate = optional.get().getEndDate();
            cmsb.with(new SearchCriteria("launchDate", startDate, SearchOperation.EQUALITY, Datatype.DATE));
            cmsb.with(new SearchCriteria("createdOn", endDate, SearchOperation.LESS_THAN, Datatype.DATE));

        }
        cmsb.with(new SearchCriteria("modelName", mobileDeviceRepository.getModelName(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("manufacturer", mobileDeviceRepository.getManufacturer(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("manufacturingLocation", mobileDeviceRepository.getManufacturingLocation(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("os", mobileDeviceRepository.getOs(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("deviceType", mobileDeviceRepository.getDeviceType(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("deviceId", mobileDeviceRepository.getDeviceId(), SearchOperation.LIKE, Datatype.STRING));

        Optional<String> approvedStatus = Optional.ofNullable(mobileDeviceRepository.getTrcApprovedStatus());
        if (approvedStatus.isPresent()) {
            cmsb.with(new SearchCriteria("trcApprovedStatus", mobileDeviceRepository.getTrcApprovedStatus(), SearchOperation.LIKE, Datatype.STRING));
        } else {
            cmsb.addSpecification(cmsb.notApproved(new SearchCriteria("trcApprovedStatus", "Null", SearchOperation.EQUALITY_CASE_INSENSITIVE, Datatype.STRING)));
        }

        cmsb.with(new SearchCriteria("simSlot", mobileDeviceRepository.getSimSlot(), SearchOperation.EQUALITY, Datatype.INT));

        return cmsb;
    }


    public <T> T optional(T t) {
        Optional<T> opt = Optional.ofNullable(t);
        if (opt.isPresent()) {
            return t;
        }
        return null;
    }


}
