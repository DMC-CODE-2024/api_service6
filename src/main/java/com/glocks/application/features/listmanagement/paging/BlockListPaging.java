package com.glocks.application.features.listmanagement.paging;

import com.glocks.application.common.db.PropertiesReader;
import com.glocks.application.common.enums.Datatype;
import com.glocks.application.common.enums.SearchOperation;
import com.glocks.application.common.exceptions.ResourceServicesException;
import com.glocks.application.common.specificationsbuilder.GenericSpecificationBuilder;
import com.glocks.application.common.specificationsbuilder.SearchCriteria;
import com.glocks.application.common.specificationsbuilder.SortDirection;
import com.glocks.application.entity.app.BlockListEntity;
import com.glocks.application.entity.app.ExceptionListEntity;
import com.glocks.application.features.trc.model.AuditTrailModel;
import com.glocks.application.features.trc.service.HelperUtility;
import com.glocks.application.repository.app.BlockListRepository;
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
public class BlockListPaging {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    private PropertiesReader propertiesReader;
    private BlockListRepository blockListRepository;
    private HelperUtility helperUtility;

    public BlockListPaging(BlockListRepository blockListRepository, HelperUtility helperUtility) {
        this.blockListRepository = blockListRepository;
        this.helperUtility = helperUtility;
    }


    public Page<BlockListEntity> findAll(BlockListEntity blockListEntity, int pageNo, int pageSize) {

        try {
            logger.info("request received : " + blockListEntity + " [pageNo] ...." + pageNo + " [pageSize]...." + pageSize);
            String sort = null, orderColumn = null;
            if (Objects.nonNull(blockListEntity.getAuditTrailModel())) {
                sort = Objects.nonNull(blockListEntity.getAuditTrailModel().getSort()) ? blockListEntity.getAuditTrailModel().getSort() : "desc";
                orderColumn = Objects.nonNull(blockListEntity.getAuditTrailModel().getColumnName()) ? blockListEntity.getAuditTrailModel().getColumnName() : "Modified On";
            } else {
                sort = "desc";
                orderColumn = "Modified On";
            }
            orderColumn = sortColumnName(orderColumn);
            Sort.Direction direction = SortDirection.getSortDirection(sort);
            logger.info("request received : " + blockListEntity + " [pageNo] ...." + pageNo + " [pageSize]...." + pageSize);

            logger.info("orderColumn is : " + orderColumn + " & direction is : " + direction);

            Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction, orderColumn));
            Page<BlockListEntity> page = blockListRepository.findAll(buildSpecification(blockListEntity).build(), pageable);
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
            map.put("IMEI", "actualImei");
            map.put("MSISDN", "msisdn");
            map.put("IMSI", "imsi");
            map.put("Modified On", "modifiedOn");
            map.put("Transaction ID", "txnId");
            map.put("Category", "requestType");
            map.put("Uploaded By", "user.username");
            map.put("Source", "source");
        }
        return map.get(columnName);
    }

    private GenericSpecificationBuilder<BlockListEntity> buildSpecification(BlockListEntity blockListEntity) {
        logger.info("FilterRequest payload : [" + blockListEntity + "]");
        GenericSpecificationBuilder<BlockListEntity> cmsb = new GenericSpecificationBuilder<>(propertiesReader.dialect);

        Optional<AuditTrailModel> optional = Optional.ofNullable(blockListEntity.getAuditTrailModel());
        if (optional.isPresent()) {
            cmsb.with(new SearchCriteria("createdOn", optional.get().getStartDate(), SearchOperation.GREATER_THAN, Datatype.DATE));
            cmsb.with(new SearchCriteria("createdOn", optional.get().getEndDate(), SearchOperation.LESS_THAN, Datatype.DATE));
        }
        cmsb.with(new SearchCriteria("id", blockListEntity.getId(), SearchOperation.EQUALITY, Datatype.LONG));
        cmsb.with(new SearchCriteria("actualImei", blockListEntity.getImei(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("msisdn", blockListEntity.getMsisdn(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("imsi", blockListEntity.getImsi(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("complaintType", blockListEntity.getComplaintType(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("tac", blockListEntity.getTac(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("modeType", blockListEntity.getModeType(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("requestType", blockListEntity.getRequestType(), SearchOperation.EQUALITY, Datatype.STRING));
        cmsb.with(new SearchCriteria("txnId", blockListEntity.getTxnId(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.addSpecification(cmsb.<BlockListEntity>joinWithMultiple(new SearchCriteria("username", blockListEntity.getUploadedBy(), SearchOperation.LIKE, Datatype.STRING)));
        cmsb.with(new SearchCriteria("userType", blockListEntity.getUserType(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("remarks", blockListEntity.getRemarks(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("operatorName", blockListEntity.getOperatorName(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("operatorId", blockListEntity.getOperatorId(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("actualImei", blockListEntity.getActualImei(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("source", blockListEntity.getSource(), SearchOperation.EQUALITY, Datatype.STRING));

        return cmsb;
    }
}
