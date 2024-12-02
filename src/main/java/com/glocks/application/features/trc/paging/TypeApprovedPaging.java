package com.glocks.application.features.trc.paging;

import com.glocks.application.common.db.PropertiesReader;
import com.glocks.application.common.enums.Datatype;
import com.glocks.application.common.enums.SearchOperation;
import com.glocks.application.common.exceptions.ResourceServicesException;
import com.glocks.application.common.specificationsbuilder.GenericSpecificationBuilder;
import com.glocks.application.common.specificationsbuilder.SearchCriteria;
import com.glocks.application.common.specificationsbuilder.SortDirection;
import com.glocks.application.entity.app.ExceptionListEntity;
import com.glocks.application.entity.app.TRCDataManagementEntity;
import com.glocks.application.features.trc.model.AuditTrailModel;
import com.glocks.application.features.trc.service.HelperUtility;
import com.glocks.application.repository.app.TypeApprovedRepo;
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
public class TypeApprovedPaging {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    private PropertiesReader propertiesReader;
    private TypeApprovedRepo typeApprovedRepo;
    private HelperUtility helperUtility;

    public TypeApprovedPaging(TypeApprovedRepo typeApprovedRepo, HelperUtility helperUtility) {
        this.typeApprovedRepo = typeApprovedRepo;
        this.helperUtility = helperUtility;
    }


    public Page<TRCDataManagementEntity> findAll(TRCDataManagementEntity trcDataManagementEntity, int pageNo, int pageSize) {

        try {
            logger.info("request received : " + trcDataManagementEntity + " [pageNo] ...." + pageNo + " [pageSize]...." + pageSize);
            String sort = null, orderColumn = null;
            if (Objects.nonNull(trcDataManagementEntity.getAuditTrailModel())) {
                sort = Objects.nonNull(trcDataManagementEntity.getAuditTrailModel().getSort()) ? trcDataManagementEntity.getAuditTrailModel().getSort() : "desc";
                orderColumn = Objects.nonNull(trcDataManagementEntity.getAuditTrailModel().getColumnName()) ? trcDataManagementEntity.getAuditTrailModel().getColumnName() : "Modified On";
            } else {
                sort = "desc";
                orderColumn = "Modified On";
            }
            orderColumn = sortColumnName(orderColumn);
            Sort.Direction direction = SortDirection.getSortDirection(sort);
            logger.info("request received : " + trcDataManagementEntity + " [pageNo] ...." + pageNo + " [pageSize]...." + pageSize);

            logger.info("orderColumn is : " + orderColumn + " & direction is : " + direction);

            Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction, orderColumn));
            Page<TRCDataManagementEntity> page = typeApprovedRepo.findAll(buildSpecification(trcDataManagementEntity).build(), pageable);
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
            map.put("Updated Date", "createdOn");
            map.put("Modified On", "modifiedOn");
            map.put("File Name", "fileName");
            map.put("Transaction ID", "transactionId");
            map.put("Status", "status");
            map.put("Request Type", "requestType");
            map.put("Uploaded By", "user.username");
        }
        return map.get(columnName);
    }

    private GenericSpecificationBuilder<TRCDataManagementEntity> buildSpecification(TRCDataManagementEntity trcDataManagementEntity) {
        logger.info("FilterRequest payload : [" + trcDataManagementEntity + "]");
        GenericSpecificationBuilder<TRCDataManagementEntity> cmsb = new GenericSpecificationBuilder<>(propertiesReader.dialect);

        Optional<AuditTrailModel> optional = Optional.ofNullable(trcDataManagementEntity.getAuditTrailModel());
        if (optional.isPresent()) {
            cmsb.with(new SearchCriteria("createdOn", optional.get().getStartDate(), SearchOperation.GREATER_THAN, Datatype.DATE));
            cmsb.with(new SearchCriteria("createdOn", optional.get().getEndDate(), SearchOperation.LESS_THAN, Datatype.DATE));
        }
        cmsb.addSpecification(cmsb.<ExceptionListEntity>joinWithMultiple(new SearchCriteria("username", trcDataManagementEntity.getUploadedBy(), SearchOperation.LIKE, Datatype.STRING)));
        cmsb.with(new SearchCriteria("transactionId", trcDataManagementEntity.getTransactionId(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("fileName", trcDataManagementEntity.getFileName(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("status", trcDataManagementEntity.getStatus(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("requestType", trcDataManagementEntity.getRequestType(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("id", trcDataManagementEntity.getId(), SearchOperation.EQUALITY, Datatype.LONG));

        return cmsb;
    }

    public Optional<TRCDataManagementEntity> find(Long id) {
        return typeApprovedRepo.findById(id);
    }
}
