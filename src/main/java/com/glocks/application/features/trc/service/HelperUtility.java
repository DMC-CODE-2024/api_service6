package com.glocks.application.features.trc.service;

import com.glocks.application.common.db.PropertiesReader;
import com.glocks.application.common.enums.Datatype;
import com.glocks.application.common.enums.SearchOperation;
import com.glocks.application.common.specificationsbuilder.GenericSpecificationBuilder;
import com.glocks.application.common.specificationsbuilder.SearchCriteria;
import com.glocks.application.entity.app.TRCDataManagementEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

@Component
public class HelperUtility {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    private PropertiesReader propertiesReader;

    public <T> boolean isValid(T t) {
        Predicate<T> predicateForString = (x) -> Objects.nonNull(x) && x != "";
        return predicateForString.test(t);
    }
/*
    public <T> GenericSpecificationBuilder<T> buildSpecification(Map<?, ?> map) throws ParseException {
        GenericSpecificationBuilder<T> cmsb = new GenericSpecificationBuilder<>(propertiesReader.dialect);
        SearchOperation so = null;
        Datatype dt = null;
        for (Map.Entry<?, ?> m : map.entrySet()) {

            if (this.isValid(m.getValue())) {
                cmsb.with(new SearchCriteria(m.getKey().toString(), m.getValue(), so, dt));
            }
        }
        logger.info("cmsb [" + cmsb.toString() + "]");
        return cmsb;
    }
*/

}
