package com.glocks.application.features.listmanagement.paging;

import com.glocks.application.common.db.PropertiesReader;
import com.glocks.application.common.enums.Datatype;
import com.glocks.application.common.enums.SearchOperation;
import com.glocks.application.common.exceptions.ResourceServicesException;
import com.glocks.application.common.specificationsbuilder.GenericSpecificationBuilder;
import com.glocks.application.common.specificationsbuilder.SearchCriteria;
import com.glocks.application.common.specificationsbuilder.SortDirection;
import com.glocks.application.entity.app.BlockListEntity;
import com.glocks.application.entity.app.GrayListEntity;
import com.glocks.application.features.trc.model.AuditTrailModel;
import com.glocks.application.features.trc.service.HelperUtility;
import com.glocks.application.repository.app.BlockListRepository;
import com.glocks.application.repository.app.GrayListRepository;
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
public class GrayListPaging {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    private PropertiesReader propertiesReader;
    private GrayListRepository grayListRepository;
    private HelperUtility helperUtility;

    public GrayListPaging(GrayListRepository grayListRepository, HelperUtility helperUtility) {
        this.grayListRepository = grayListRepository;
        this.helperUtility = helperUtility;
    }


    public Page<GrayListEntity> findAll(GrayListEntity grayListEntity, int pageNo, int pageSize) {

        try {
            logger.info("request received : " + grayListEntity + " [pageNo] ...." + pageNo + " [pageSize]...." + pageSize);
            String sort = null, orderColumn = null;
            if (Objects.nonNull(grayListEntity.getAuditTrailModel())) {
                sort = Objects.nonNull(grayListEntity.getAuditTrailModel().getSort()) ? grayListEntity.getAuditTrailModel().getSort() : "desc";
                orderColumn = Objects.nonNull(grayListEntity.getAuditTrailModel().getColumnName()) ? grayListEntity.getAuditTrailModel().getColumnName() : "Modified On";
            } else {
                sort = "desc";
                orderColumn = "Modified On";
            }
            orderColumn = sortColumnName(orderColumn);
            Sort.Direction direction = SortDirection.getSortDirection(sort);
            logger.info("request received : " + grayListEntity + " [pageNo] ...." + pageNo + " [pageSize]...." + pageSize);

            logger.info("orderColumn is : " + orderColumn + " & direction is : " + direction);

            Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction, orderColumn));
            Page<GrayListEntity> page = grayListRepository.findAll(buildSpecification(grayListEntity).build(), pageable);
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

    private GenericSpecificationBuilder<GrayListEntity> buildSpecification(GrayListEntity grayListEntity) {
        logger.info("FilterRequest payload : [" + grayListEntity + "]");
        GenericSpecificationBuilder<GrayListEntity> cmsb = new GenericSpecificationBuilder<>(propertiesReader.dialect);

        Optional<AuditTrailModel> optional = Optional.ofNullable(grayListEntity.getAuditTrailModel());
        if (optional.isPresent()) {
            cmsb.with(new SearchCriteria("createdOn", optional.get().getStartDate(), SearchOperation.GREATER_THAN, Datatype.DATE));
            cmsb.with(new SearchCriteria("createdOn", optional.get().getEndDate(), SearchOperation.LESS_THAN, Datatype.DATE));
        }
        cmsb.with(new SearchCriteria("id", grayListEntity.getId(), SearchOperation.EQUALITY, Datatype.LONG));
        cmsb.with(new SearchCriteria("actualImei", grayListEntity.getImei(), SearchOperation.LIKE, Datatype.STRING));
        //cmsb.with(new SearchCriteria("imei", grayListEntity.getImei(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("msisdn", grayListEntity.getMsisdn(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("imsi", grayListEntity.getImsi(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("complaintType", grayListEntity.getComplaintType(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("tac", grayListEntity.getTac(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("modeType", grayListEntity.getModeType(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("requestType", grayListEntity.getRequestType(), SearchOperation.EQUALITY, Datatype.STRING));
        cmsb.with(new SearchCriteria("txnId", grayListEntity.getTxnId(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.addSpecification(cmsb.<GrayListEntity>joinWithMultiple(new SearchCriteria("username", grayListEntity.getUploadedBy(), SearchOperation.LIKE, Datatype.STRING)));
        cmsb.with(new SearchCriteria("userType", grayListEntity.getUserType(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("remarks", grayListEntity.getRemarks(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("operatorName", grayListEntity.getOperatorName(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("operatorId", grayListEntity.getOperatorId(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("actualImei", grayListEntity.getActualImei(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("source", grayListEntity.getSource(), SearchOperation.EQUALITY, Datatype.STRING));

        return cmsb;
    }
}
