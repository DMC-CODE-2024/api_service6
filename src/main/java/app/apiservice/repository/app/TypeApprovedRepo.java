package app.apiservice.repository.app;

import app.apiservice.entity.app.TRCDataManagementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeApprovedRepo extends JpaRepository<TRCDataManagementEntity, Long>, JpaSpecificationExecutor<TRCDataManagementEntity> {
}
