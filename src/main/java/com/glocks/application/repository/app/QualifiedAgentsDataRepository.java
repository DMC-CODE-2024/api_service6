package com.glocks.application.repository.app;

import com.glocks.application.entity.app.TRCQualifiedAgentsDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface QualifiedAgentsDataRepository extends JpaRepository<TRCQualifiedAgentsDataEntity, Long>, JpaSpecificationExecutor<TRCQualifiedAgentsDataEntity> {

}
