package com.glocks.application.repository.app;

import com.glocks.application.entity.app.NotificationEntity;
import com.glocks.application.entity.app.SysParamListValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SysParamListRepository extends JpaRepository<SysParamListValueEntity, Long>, JpaSpecificationExecutor<SysParamListValueEntity> {

    public SysParamListValueEntity findByTagAndValue(String tag, String value);

}
