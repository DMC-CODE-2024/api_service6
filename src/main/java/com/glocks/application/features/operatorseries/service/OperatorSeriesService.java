package com.glocks.application.features.operatorseries.service;

import com.glocks.application.common.enums.FeaturesEnum;
import com.glocks.application.common.features.audit_trail.AuditTrailService;
import com.glocks.application.entity.app.GrayListEntity;
import com.glocks.application.entity.app.MSISDNSeriesEntity;
import com.glocks.application.features.listmanagement.paging.GrayListPaging;
import com.glocks.application.features.operatorseries.model.GenricResponse;
import com.glocks.application.repository.app.OperatorSeriesRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import  jakarta.persistence.EntityManager;
import  jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OperatorSeriesService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private OperatorSeriesRepository operatorSeriesRepository;
    private final int LENGTH = 5;

    public OperatorSeriesService(OperatorSeriesRepository operatorSeriesRepository) {
        this.operatorSeriesRepository = operatorSeriesRepository;
    }


    public GenricResponse seriesValidation(String msisdn) {
        Optional<String> optional = getLength(msisdn);
        GenricResponse response = new GenricResponse();
        String len;
        if (optional.isPresent()) {
            List<MSISDNSeriesEntity> bySeriesStart = operatorSeriesRepository.findBySeriesStart(Integer.valueOf(optional.get()));
            logger.info("record fetched [" + bySeriesStart + "]");

            if (bySeriesStart.isEmpty()) {
                len = null;
                return response
                        .setMessage("no data found")
                        .setStatusCode(HttpStatus.OK.value())
                        .setData(bySeriesStart)
                        .setSeriesLength(len)
                        .setValid(false);
            } else {
                len = bySeriesStart.get(0).getLength();
                return response
                        .setMessage("data found")
                        .setStatusCode(HttpStatus.OK.value())
                        .setData(bySeriesStart)
                        .setSeriesLength(len)
                        .setValid(true);
            }

        }
        return response
                .setMessage("pass a valid msisdn length")
                .setStatusCode(HttpStatus.OK.value())
                .setData(null)
                .setSeriesLength(null)
                .setValid(false);
    }

    public Optional<String> getLength(String val) {
        if (val.length() >= LENGTH) {
            logger.info("extract 5 alpha from string " + val.substring(0, LENGTH));
            return Optional.of(val.substring(0, LENGTH));
        } else {
            logger.info("length is less than defined length" + LENGTH);
            return Optional.empty();
        }
    }
}
