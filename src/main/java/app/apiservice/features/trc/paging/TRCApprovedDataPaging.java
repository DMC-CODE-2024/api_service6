package app.apiservice.features.trc.paging;

import app.apiservice.common.db.PropertiesReader;
import app.apiservice.common.enums.Datatype;
import app.apiservice.common.enums.FeaturesEnum;
import app.apiservice.common.enums.SearchOperation;
import app.apiservice.common.exceptions.ResourceServicesException;
import app.apiservice.common.externalproperties.FeatureNameMap;
import app.apiservice.common.features.audit_trail.AuditTrailService;
import app.apiservice.common.specificationsbuilder.GenericSpecificationBuilder;
import app.apiservice.common.specificationsbuilder.SearchCriteria;
import app.apiservice.common.specificationsbuilder.SortDirection;
import app.apiservice.entity.app.TRCTypeApprovedDataEntity;
import app.apiservice.features.trc.model.AuditTrailModel;
import app.apiservice.repository.app.TRCApprovedDataRepo;
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
public class TRCApprovedDataPaging {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    private PropertiesReader propertiesReader;
    private final TRCApprovedDataRepo trcApprovedDataRepo;
    private final AuditTrailService auditTrailService;

    public TRCApprovedDataPaging(TRCApprovedDataRepo trcApprovedDataRepo, AuditTrailService auditTrailService) {
        this.trcApprovedDataRepo = trcApprovedDataRepo;
        this.auditTrailService = auditTrailService;
    }

    @Autowired
    private FeatureNameMap featureNameMap;
    String requestType = "VIEW_TA";
    public Page<TRCTypeApprovedDataEntity> findAll(TRCTypeApprovedDataEntity trcTypeApprovedDataEntity, int pageNo, int pageSize) {
        Page<TRCTypeApprovedDataEntity> page;
        try {
            logger.info("request received : " + trcTypeApprovedDataEntity + " [pageNo] ...." + pageNo + " [pageSize]...." + pageSize);
            String sort;
            String orderColumn;
            String columnName;
            if (Objects.nonNull(trcTypeApprovedDataEntity.getAuditTrailModel())) {
                columnName = Objects.nonNull(trcTypeApprovedDataEntity.getAuditTrailModel().getColumnName()) ? trcTypeApprovedDataEntity.getAuditTrailModel().getColumnName() : "Approved Date";
                sort = Objects.nonNull(trcTypeApprovedDataEntity.getAuditTrailModel().getSort()) ? trcTypeApprovedDataEntity.getAuditTrailModel().getSort() : "desc";
                orderColumn = columnName;
            } else {
                sort = "desc";
                orderColumn = "Approved Date";
            }

            orderColumn = sortColumnName(orderColumn);
            Sort.Direction direction = SortDirection.getSortDirection(sort);
            logger.info("orderColumn is : " + orderColumn + " & direction is : " + direction);

            Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction, orderColumn));

            page = trcApprovedDataRepo.findAll(buildSpecification(trcTypeApprovedDataEntity).build(), pageable);
            logger.info("paging API response [" + page + "]");

            auditTrailService.auditTrailOperation(trcTypeApprovedDataEntity.getAuditTrailModel(), featureNameMap.get(requestType),featureNameMap.get("VIEWALL"));

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
        }
        return page;
    }

    public String sortColumnName(String columnName) {
        Map<String, String> map = new HashMap<>();
        if (Objects.nonNull(columnName) && !columnName.isEmpty()) {
            map.put("Created On", "createdOn");
            map.put("Approved Date", "approvalDate");
            map.put("Modified On", "modifiedOn");
            map.put("Company", "company");
            map.put("Trademark", "trademark");
            map.put("Product Name", "productName");
            map.put("Model", "model");
            map.put("Country Of Manufacture", "country");
            map.put("Company ID", "companyId");
            map.put("TRC Identifier", "trcIdentifier");
        }
        return map.get(columnName);
    }


    private GenericSpecificationBuilder<TRCTypeApprovedDataEntity> buildSpecification(TRCTypeApprovedDataEntity trcTypeApprovedDataEntity) {
        logger.info("FilterRequest payload : [" + trcTypeApprovedDataEntity + "]");
        GenericSpecificationBuilder<TRCTypeApprovedDataEntity> cmsb = new GenericSpecificationBuilder<>(propertiesReader.dialect);
        Optional<AuditTrailModel> optional = Optional.ofNullable(trcTypeApprovedDataEntity.getAuditTrailModel());
        if (optional.isPresent()) {
            cmsb.with(new SearchCriteria("approvalDate", optional.get().getStartDate(), SearchOperation.GREATER_THAN, Datatype.DATE));
            cmsb.with(new SearchCriteria("approvalDate", optional.get().getEndDate(), SearchOperation.LESS_THAN, Datatype.DATE));
        }
        cmsb.with(new SearchCriteria("company", trcTypeApprovedDataEntity.getCompany(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("trademark", trcTypeApprovedDataEntity.getTrademark(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("productName", trcTypeApprovedDataEntity.getProductName(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("commercialName", trcTypeApprovedDataEntity.getCommercialName(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("companyId", trcTypeApprovedDataEntity.getCompanyId(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("model", trcTypeApprovedDataEntity.getModel(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("country", trcTypeApprovedDataEntity.getCountry(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("trcIdentifier", trcTypeApprovedDataEntity.getTrcIdentifier(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("approvalDate", trcTypeApprovedDataEntity.getApprovalDate(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("no", trcTypeApprovedDataEntity.getNo(), SearchOperation.EQUALITY, Datatype.LONG));
        cmsb.with(new SearchCriteria("id", trcTypeApprovedDataEntity.getId(), SearchOperation.EQUALITY, Datatype.LONG));

        return cmsb;
    }
}
