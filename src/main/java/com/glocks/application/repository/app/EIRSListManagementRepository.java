package com.glocks.application.repository.app;

import com.glocks.application.entity.app.EIRSListManagementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EIRSListManagementRepository extends JpaRepository<EIRSListManagementEntity, Long>, JpaSpecificationExecutor<EIRSListManagementEntity> {

}
