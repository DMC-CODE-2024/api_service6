package app.apiservice.repository.app;

import app.apiservice.entity.app.TRCQualifiedAgentsDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface QualifiedAgentsDataRepository extends JpaRepository<TRCQualifiedAgentsDataEntity, Long>, JpaSpecificationExecutor<TRCQualifiedAgentsDataEntity> {

}
