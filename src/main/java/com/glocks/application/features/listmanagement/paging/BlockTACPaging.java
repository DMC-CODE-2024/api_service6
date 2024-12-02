package com.glocks.application.features.listmanagement.paging;

import com.glocks.application.common.db.PropertiesReader;
import com.glocks.application.common.enums.Datatype;
import com.glocks.application.common.enums.SearchOperation;
import com.glocks.application.common.exceptions.ResourceServicesException;
import com.glocks.application.common.specificationsbuilder.GenericSpecificationBuilder;
import com.glocks.application.common.specificationsbuilder.SearchCriteria;
import com.glocks.application.common.specificationsbuilder.SortDirection;
import com.glocks.application.entity.app.BlockListEntity;
import com.glocks.application.entity.app.BlockTACListEntity;
import com.glocks.application.features.trc.model.AuditTrailModel;
import com.glocks.application.features.trc.service.HelperUtility;
import com.glocks.application.repository.app.BlockListRepository;
import com.glocks.application.repository.app.BlockTACRepository;
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
public class BlockTACPaging {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    private PropertiesReader propertiesReader;
    private BlockTACRepository blockTACRepository;
    private HelperUtility helperUtility;

    public BlockTACPaging(BlockTACRepository blockTACRepository, HelperUtility helperUtility) {
        this.blockTACRepository = blockTACRepository;
        this.helperUtility = helperUtility;
    }


    public Page<BlockTACListEntity> findAll(BlockTACListEntity blockTACListEntity, int pageNo, int pageSize) {

        try {
            logger.info("request received : " + blockTACListEntity + " [pageNo] ...." + pageNo + " [pageSize]...." + pageSize);
            String sort = null, orderColumn = null;
            if (Objects.nonNull(blockTACListEntity.getAuditTrailModel())) {
                sort = Objects.nonNull(blockTACListEntity.getAuditTrailModel().getSort()) ? blockTACListEntity.getAuditTrailModel().getSort() : "desc";
                orderColumn = Objects.nonNull(blockTACListEntity.getAuditTrailModel().getColumnName()) ? blockTACListEntity.getAuditTrailModel().getColumnName() : "Modified On";
            } else {
                sort = "desc";
                orderColumn = "Modified On";
            }
            orderColumn = sortColumnName(orderColumn);
            Sort.Direction direction = SortDirection.getSortDirection(sort);
            logger.info("request received : " + blockTACListEntity + " [pageNo] ...." + pageNo + " [pageSize]...." + pageSize);

            logger.info("orderColumn is : " + orderColumn + " & direction is : " + direction);

            Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction, orderColumn));
            Page<BlockTACListEntity> page = blockTACRepository.findAll(buildSpecification(blockTACListEntity).build(), pageable);
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
            map.put("IMEI", "imei");
            map.put("MSISDN", "msisdn");
            map.put("IMSI", "imsi");
            map.put("Modified On", "modifiedOn");
            map.put("Transaction ID", "txnId");
            map.put("Category", "requestType");
            map.put("TAC", "tac");
            map.put("Uploaded By", "user.username");
            map.put("Source", "source");
        }
        return map.get(columnName);
    }

    private GenericSpecificationBuilder<BlockTACListEntity> buildSpecification(BlockTACListEntity blockTACListEntity) {
        logger.info("FilterRequest payload : [" + blockTACListEntity + "]");
        GenericSpecificationBuilder<BlockTACListEntity> cmsb = new GenericSpecificationBuilder<>(propertiesReader.dialect);

        Optional<AuditTrailModel> optional = Optional.ofNullable(blockTACListEntity.getAuditTrailModel());
        if (optional.isPresent()) {
            cmsb.with(new SearchCriteria("createdOn", optional.get().getStartDate(), SearchOperation.GREATER_THAN, Datatype.DATE));
            cmsb.with(new SearchCriteria("createdOn", optional.get().getEndDate(), SearchOperation.LESS_THAN, Datatype.DATE));
        }
        cmsb.with(new SearchCriteria("id", blockTACListEntity.getId(), SearchOperation.EQUALITY, Datatype.LONG));
        cmsb.with(new SearchCriteria("requestType", blockTACListEntity.getRequestType(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("tac", blockTACListEntity.getTac(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("txnId", blockTACListEntity.getTxnId(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("source", blockTACListEntity.getSource(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.addSpecification(cmsb.<BlockTACListEntity>joinWithMultiple(new SearchCriteria("username", blockTACListEntity.getUploadedBy(), SearchOperation.LIKE, Datatype.STRING)));
        return cmsb;
    }
}
