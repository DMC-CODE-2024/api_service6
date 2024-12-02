package com.glocks.application.repository.app;

import com.glocks.application.entity.app.MSISDNSeriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperatorSeriesRepository extends JpaRepository<MSISDNSeriesEntity, Long>, JpaSpecificationExecutor<MSISDNSeriesEntity> {

    public List<MSISDNSeriesEntity> findBySeriesStart(Integer seriesStart);
}
