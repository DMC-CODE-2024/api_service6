package com.glocks.application.repository.app;

import com.glocks.application.entity.app.TRCLocalManufacturedDevicesDumpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LMDataRepository extends JpaRepository<TRCLocalManufacturedDevicesDumpEntity, Long>, JpaSpecificationExecutor<TRCLocalManufacturedDevicesDumpEntity> {

}
