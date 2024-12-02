package com.glocks.application.features.addressmanagement.paging;

import com.glocks.application.common.db.PropertiesReader;
import com.glocks.application.common.enums.Datatype;
import com.glocks.application.common.enums.SearchOperation;
import com.glocks.application.common.exceptions.ResourceServicesException;
import com.glocks.application.common.specificationsbuilder.GenericSpecificationBuilder;
import com.glocks.application.common.specificationsbuilder.SearchCriteria;
import com.glocks.application.common.specificationsbuilder.SortDirection;
import com.glocks.application.entity.app.AddressEntity;
import com.glocks.application.features.trc.model.AuditTrailModel;
import com.glocks.application.features.trc.service.HelperUtility;
import com.glocks.application.repository.app.AddressListMgmtRepository;
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
public class AddressListManagementPaging {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    private PropertiesReader propertiesReader;
    private AddressListMgmtRepository addressListMgmtRepository;
    private HelperUtility helperUtility;

    public AddressListManagementPaging(AddressListMgmtRepository addressListMgmtRepository, HelperUtility helperUtility) {
        this.addressListMgmtRepository = addressListMgmtRepository;
        this.helperUtility = helperUtility;
    }


    public Page<AddressEntity> findAll(AddressEntity addressEntity, int pageNo, int pageSize) {

        try {
            logger.info("request received : " + addressEntity + " [pageNo] ...." + pageNo + " [pageSize]...." + pageSize);
            String sort = null, orderColumn = null;
            if (Objects.nonNull(addressEntity.getAuditTrailModel())) {
                sort = Objects.nonNull(addressEntity.getAuditTrailModel().getSort()) ? addressEntity.getAuditTrailModel().getSort() : "desc";
                orderColumn = Objects.nonNull(addressEntity.getAuditTrailModel().getColumnName()) ? addressEntity.getAuditTrailModel().getColumnName() : "Modified On";
            } else {
                sort = "desc";
                orderColumn = "Modified On";
            }
            orderColumn = sortColumnName(orderColumn);
            Sort.Direction direction = SortDirection.getSortDirection(sort);
            logger.info("request received : " + addressEntity + " [pageNo] ...." + pageNo + " [pageSize]...." + pageSize);

            logger.info("orderColumn is : " + orderColumn + " & direction is : " + direction);

            Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction, orderColumn));
            Page<AddressEntity> page = addressListMgmtRepository.findAll(buildSpecification(addressEntity).build(), pageable);
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
            map.put("Province", "province");
            map.put("Modified On", "modifiedOn");
            map.put("District", "district");
            map.put("Commune", "commune");
        }
        return map.get(columnName);
    }

    private GenericSpecificationBuilder<AddressEntity> buildSpecification(AddressEntity addressEntity) {
        logger.info("FilterRequest payload : [" + addressEntity + "]");
        GenericSpecificationBuilder<AddressEntity> cmsb = new GenericSpecificationBuilder<>(propertiesReader.dialect);

        Optional<AuditTrailModel> optional = Optional.ofNullable(addressEntity.getAuditTrailModel());
        if (optional.isPresent()) {
            cmsb.with(new SearchCriteria("createdOn", optional.get().getStartDate(), SearchOperation.GREATER_THAN, Datatype.DATE));
            cmsb.with(new SearchCriteria("createdOn", optional.get().getEndDate(), SearchOperation.LESS_THAN, Datatype.DATE));
        }
        cmsb.with(new SearchCriteria("province", addressEntity.getProvince(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("district", addressEntity.getDistrict(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("commune", addressEntity.getCommune(), SearchOperation.LIKE, Datatype.STRING));
        return cmsb;
    }

/*    public Optional<EIRSListManagementEntity> find(Long id) {
        return eirsListManagementRepository.findById(id);
    }*/
}
