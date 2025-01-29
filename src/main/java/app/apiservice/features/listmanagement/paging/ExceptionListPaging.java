package app.apiservice.features.listmanagement.paging;

import app.apiservice.common.db.PropertiesReader;
import app.apiservice.common.enums.Datatype;
import app.apiservice.common.enums.SearchOperation;
import app.apiservice.common.exceptions.ResourceServicesException;
import app.apiservice.common.specificationsbuilder.GenericSpecificationBuilder;
import app.apiservice.common.specificationsbuilder.SearchCriteria;
import app.apiservice.common.specificationsbuilder.SortDirection;
import app.apiservice.entity.app.ExceptionListEntity;
import app.apiservice.features.trc.model.AuditTrailModel;
import app.apiservice.features.trc.service.HelperUtility;
import app.apiservice.repository.app.ExceptionListRepository;
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
public class ExceptionListPaging {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    private PropertiesReader propertiesReader;
    private ExceptionListRepository exceptionListRepository;
    private HelperUtility helperUtility;

    public ExceptionListPaging(ExceptionListRepository exceptionListRepository, HelperUtility helperUtility) {
        this.exceptionListRepository = exceptionListRepository;
        this.helperUtility = helperUtility;
    }


    public Page<ExceptionListEntity> findAll(ExceptionListEntity exceptionListEntity, int pageNo, int pageSize) {

        try {
            logger.info("request received : " + exceptionListEntity + " [pageNo] ...." + pageNo + " [pageSize]...." + pageSize);
            String sort = null, orderColumn = null;
            if (Objects.nonNull(exceptionListEntity.getAuditTrailModel())) {
                sort = Objects.nonNull(exceptionListEntity.getAuditTrailModel().getSort()) ? exceptionListEntity.getAuditTrailModel().getSort() : "desc";
                orderColumn = Objects.nonNull(exceptionListEntity.getAuditTrailModel().getColumnName()) ? exceptionListEntity.getAuditTrailModel().getColumnName() : "Modified On";
            } else {
                sort = "desc";
                orderColumn = "Modified On";
            }
            orderColumn = sortColumnName(orderColumn);
            Sort.Direction direction = SortDirection.getSortDirection(sort);
            logger.info("request received : " + exceptionListEntity + " [pageNo] ...." + pageNo + " [pageSize]...." + pageSize);

            logger.info("orderColumn is : " + orderColumn + " & direction is : " + direction);

            Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction, orderColumn));
            Page<ExceptionListEntity> page = exceptionListRepository.findAll(buildSpecification(exceptionListEntity).build(), pageable);
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

    private GenericSpecificationBuilder<ExceptionListEntity> buildSpecification(ExceptionListEntity exceptionListEntity) {
        logger.info("FilterRequest payload : [" + exceptionListEntity + "]");
        GenericSpecificationBuilder<ExceptionListEntity> cmsb = new GenericSpecificationBuilder<>(propertiesReader.dialect);

        Optional<AuditTrailModel> optional = Optional.ofNullable(exceptionListEntity.getAuditTrailModel());
        if (optional.isPresent()) {
            cmsb.with(new SearchCriteria("createdOn", optional.get().getStartDate(), SearchOperation.GREATER_THAN, Datatype.DATE));
            cmsb.with(new SearchCriteria("createdOn", optional.get().getEndDate(), SearchOperation.LESS_THAN, Datatype.DATE));
        }
        cmsb.with(new SearchCriteria("id", exceptionListEntity.getId(), SearchOperation.EQUALITY, Datatype.LONG));
        cmsb.with(new SearchCriteria("actualImei", exceptionListEntity.getImei(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("msisdn", exceptionListEntity.getMsisdn(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("imsi", exceptionListEntity.getImsi(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("complaintType", exceptionListEntity.getComplaintType(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("tac", exceptionListEntity.getTac(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("modeType", exceptionListEntity.getModeType(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("requestType", exceptionListEntity.getRequestType(), SearchOperation.EQUALITY, Datatype.STRING));
        cmsb.with(new SearchCriteria("txnId", exceptionListEntity.getTxnId(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.addSpecification(cmsb.<ExceptionListEntity>joinWithMultiple(new SearchCriteria("username", exceptionListEntity.getUploadedBy(), SearchOperation.LIKE, Datatype.STRING)));
        cmsb.with(new SearchCriteria("userType", exceptionListEntity.getUserType(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("remarks", exceptionListEntity.getRemarks(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("operatorName", exceptionListEntity.getOperatorName(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("operatorId", exceptionListEntity.getOperatorId(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("actualImei", exceptionListEntity.getActualImei(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("source", exceptionListEntity.getSource(), SearchOperation.EQUALITY, Datatype.STRING));

        return cmsb;
    }
}
