package com.glocks.application.features.listmanagement.paging;

import com.glocks.application.common.db.PropertiesReader;
import com.glocks.application.common.enums.Datatype;
import com.glocks.application.common.enums.SearchOperation;
import com.glocks.application.common.exceptions.ResourceServicesException;
import com.glocks.application.common.specificationsbuilder.GenericSpecificationBuilder;
import com.glocks.application.common.specificationsbuilder.SearchCriteria;
import com.glocks.application.common.specificationsbuilder.SortDirection;
import com.glocks.application.entity.app.EIRSListManagementEntity;
import com.glocks.application.entity.app.GrayListEntity;
import com.glocks.application.features.trc.model.AuditTrailModel;
import com.glocks.application.features.trc.service.HelperUtility;
import com.glocks.application.repository.app.EIRSListManagementRepository;
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
public class EIRSListManagementPaging {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    private PropertiesReader propertiesReader;
    private EIRSListManagementRepository eirsListManagementRepository;
    private HelperUtility helperUtility;

    public EIRSListManagementPaging(EIRSListManagementRepository eirsListManagementRepository, HelperUtility helperUtility) {
        this.eirsListManagementRepository = eirsListManagementRepository;
        this.helperUtility = helperUtility;
    }


    public Page<EIRSListManagementEntity> findAll(EIRSListManagementEntity eirsListManagementEntity, int pageNo, int pageSize) {

        try {
            logger.info("request received : " + eirsListManagementEntity + " [pageNo] ...." + pageNo + " [pageSize]...." + pageSize);
            String sort = null, orderColumn = null;
            if (Objects.nonNull(eirsListManagementEntity.getAuditTrailModel())) {
                sort = Objects.nonNull(eirsListManagementEntity.getAuditTrailModel().getSort()) ? eirsListManagementEntity.getAuditTrailModel().getSort() : "desc";
                orderColumn = Objects.nonNull(eirsListManagementEntity.getAuditTrailModel().getColumnName()) ? eirsListManagementEntity.getAuditTrailModel().getColumnName() : "Modified On";
            } else {
                sort = "desc";
                orderColumn = "Modified On";
            }
            orderColumn = sortColumnName(orderColumn);
            Sort.Direction direction = SortDirection.getSortDirection(sort);
            logger.info("request received : " + eirsListManagementEntity + " [pageNo] ...." + pageNo + " [pageSize]...." + pageSize);

            logger.info("orderColumn is : " + orderColumn + " & direction is : " + direction);

            Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction, orderColumn));
            Page<EIRSListManagementEntity> page = eirsListManagementRepository.findAll(buildSpecification(eirsListManagementEntity).build(), pageable);
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
            map.put("Date", "createdOn");
            map.put("Transaction ID", "transactionId");
            map.put("Modified On", "modifiedOn");
            map.put("Status", "status");
            map.put("Mode", "requestMode");
            map.put("Request", "action");
            map.put("Category", "category");
            map.put("Uploaded By", "user.username");
        }
        return map.get(columnName);
    }

    private GenericSpecificationBuilder<EIRSListManagementEntity> buildSpecification(EIRSListManagementEntity eirsListManagementEntity) {
        logger.info("FilterRequest payload : [" + eirsListManagementEntity + "]");
        GenericSpecificationBuilder<EIRSListManagementEntity> cmsb = new GenericSpecificationBuilder<>(propertiesReader.dialect);

        Optional<AuditTrailModel> optional = Optional.ofNullable(eirsListManagementEntity.getAuditTrailModel());
        if (optional.isPresent()) {
            cmsb.with(new SearchCriteria("createdOn", optional.get().getStartDate(), SearchOperation.GREATER_THAN, Datatype.DATE));
            cmsb.with(new SearchCriteria("createdOn", optional.get().getEndDate(), SearchOperation.LESS_THAN, Datatype.DATE));
        }
        cmsb.with(new SearchCriteria("requestMode", eirsListManagementEntity.getRequestMode(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("action", eirsListManagementEntity.getAction(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("transactionId", eirsListManagementEntity.getTransactionId(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("status", eirsListManagementEntity.getStatus(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("requestType", eirsListManagementEntity.getRequestType(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("fileName", eirsListManagementEntity.getFileName(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("id", eirsListManagementEntity.getId(), SearchOperation.EQUALITY, Datatype.LONG));
        cmsb.addSpecification(cmsb.<GrayListEntity>joinWithMultiple(new SearchCriteria("username", eirsListManagementEntity.getUploadedBy(), SearchOperation.LIKE, Datatype.STRING)));
        cmsb.with(new SearchCriteria("imei", eirsListManagementEntity.getImei(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("msisdn", eirsListManagementEntity.getMsisdn(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("imsi", eirsListManagementEntity.getImsi(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("tac", eirsListManagementEntity.getTac(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("category", eirsListManagementEntity.getCategory(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("remarks", eirsListManagementEntity.getRemarks(), SearchOperation.LIKE, Datatype.STRING));
        // cmsb.with(new SearchCriteria("addedBy", eirsListManagementEntity.getAddedBy(), SearchOperation.LIKE, Datatype.STRING));
        return cmsb;
    }

    public Optional<EIRSListManagementEntity> find(Long id) {
        return eirsListManagementRepository.findById(id);
    }
}
