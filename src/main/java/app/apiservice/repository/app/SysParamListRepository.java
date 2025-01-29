package app.apiservice.repository.app;

import app.apiservice.entity.app.SysParamListValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SysParamListRepository extends JpaRepository<SysParamListValueEntity, Long>, JpaSpecificationExecutor<SysParamListValueEntity> {

    public SysParamListValueEntity findByTagAndValue(String tag, String value);

}
