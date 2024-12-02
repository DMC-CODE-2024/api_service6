package com.glocks.application.features.listmanagement.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

@Service
public class ExceptionListFeatureDistinctParamService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @PersistenceContext
    private EntityManager em;

    public Map<String, List<?>> distinct(List<String> param, Class<?> entity) {
        Map<String, List<?>> map = new HashMap<>();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<?> query = criteriaBuilder.createQuery(entity);
        Root<?> c = query.from(entity);
        Consumer<String> consumer = (s) -> {
            logger.info("Parameter : [" + s + "]");
            List<?> resultList = null;
            CriteriaQuery<?> criteriaQuery;
            try {
                criteriaQuery = query.select(c.get(s)).distinct(true)
                        // .where(criteriaBuilder.notEqual(c.get(s), ""))
                        .orderBy(criteriaBuilder.asc(c.get(s)));
                resultList = em.createQuery(criteriaQuery).getResultList();
                resultList.removeIf(Objects::isNull);
                map.put(s, resultList);
            } catch (Exception e) {
                map.put(s, resultList);
            }
        };
        param.forEach(consumer);
        return map;
    }
}
