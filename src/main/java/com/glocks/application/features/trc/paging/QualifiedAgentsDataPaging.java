package com.glocks.application.features.trc.paging;

import com.glocks.application.common.db.PropertiesReader;
import com.glocks.application.common.enums.Datatype;
import com.glocks.application.common.enums.SearchOperation;
import com.glocks.application.common.exceptions.ResourceServicesException;
import com.glocks.application.common.specificationsbuilder.GenericSpecificationBuilder;
import com.glocks.application.common.specificationsbuilder.SearchCriteria;
import com.glocks.application.common.specificationsbuilder.SortDirection;
import com.glocks.application.entity.app.TRCQualifiedAgentsDataEntity;
import com.glocks.application.features.trc.model.AuditTrailModel;
import com.glocks.application.repository.app.QualifiedAgentsDataRepository;
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
public class QualifiedAgentsDataPaging {

    private final Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    private PropertiesReader propertiesReader;
    private final QualifiedAgentsDataRepository qualifiedAgentsDataRepository;

    public QualifiedAgentsDataPaging(QualifiedAgentsDataRepository qualifiedAgentsDataRepository) {
        this.qualifiedAgentsDataRepository = qualifiedAgentsDataRepository;
    }

    public Page<TRCQualifiedAgentsDataEntity> findAll(TRCQualifiedAgentsDataEntity trcQualifiedAgentsDataEntity, int pageNo, int pageSize) {
        {
            String sort;
            String orderColumn;
            Page<TRCQualifiedAgentsDataEntity> page;
            try {
                logger.info("request received : " + trcQualifiedAgentsDataEntity + " [pageNo] ...." + pageNo + " [pageSize]...." + pageSize);
                if (Objects.nonNull(trcQualifiedAgentsDataEntity.getAuditTrailModel())) {
                    sort = Objects.nonNull(trcQualifiedAgentsDataEntity.getAuditTrailModel().getSort()) ? trcQualifiedAgentsDataEntity.getAuditTrailModel().getSort() : "desc";
                    orderColumn = Objects.nonNull(trcQualifiedAgentsDataEntity.getAuditTrailModel().getColumnName()) ? trcQualifiedAgentsDataEntity.getAuditTrailModel().getColumnName() : "Modified On";
                } else {
                    sort = "desc";
                    orderColumn = "Modified On";
                }
                orderColumn = sortColumnName(orderColumn);
                Sort.Direction direction = SortDirection.getSortDirection(sort);

                logger.info("orderColumn is : " + orderColumn + " & direction is : " + direction);

                Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction, orderColumn));

                page = qualifiedAgentsDataRepository.findAll(buildSpecification(trcQualifiedAgentsDataEntity).build(), pageable);
                logger.info("paging API response [" + page + "]");
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
            }
            return page;
        }
    }

    public String sortColumnName(String columnName) {
        Map<String, String> map = new HashMap<>();
        if (Objects.nonNull(columnName) && !columnName.isEmpty()) {
            map.put("S.No", "no");
            map.put("Company Name", "companyName");
            map.put("Company ID", "companyId");
            map.put("Modified On", "modifiedOn");
            map.put("Phone Number", "phoneNumber");
            map.put("Email", "email");
            map.put("Expiry", "expiryDate");
        }
        return map.get(columnName);
    }

    private GenericSpecificationBuilder<TRCQualifiedAgentsDataEntity> buildSpecification(TRCQualifiedAgentsDataEntity trcQualifiedAgentsDataEntity) {
        logger.info("FilterRequest payload : [" + trcQualifiedAgentsDataEntity + "]");
        GenericSpecificationBuilder<TRCQualifiedAgentsDataEntity> cmsb = new GenericSpecificationBuilder<>(propertiesReader.dialect);
        Optional<AuditTrailModel> optional = Optional.ofNullable(trcQualifiedAgentsDataEntity.getAuditTrailModel());
        if (optional.isPresent()) {
            String startDate = optional.get().getStartDate();
            String endDate = optional.get().getEndDate();
            cmsb.with(new SearchCriteria("createdOn", startDate, SearchOperation.GREATER_THAN, Datatype.DATE));
            cmsb.with(new SearchCriteria("createdOn", endDate, SearchOperation.LESS_THAN, Datatype.DATE));
        }
        cmsb.with(new SearchCriteria("no", trcQualifiedAgentsDataEntity.getNo(), SearchOperation.EQUALITY, Datatype.INT));
        cmsb.with(new SearchCriteria("companyName", trcQualifiedAgentsDataEntity.getCompanyName(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("companyId", trcQualifiedAgentsDataEntity.getCompanyId(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("expiryDate", trcQualifiedAgentsDataEntity.getExpiryDate(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("phoneNumber", trcQualifiedAgentsDataEntity.getPhoneNumber(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("email", trcQualifiedAgentsDataEntity.getEmail(), SearchOperation.LIKE, Datatype.STRING));
        return cmsb;
    }
}
