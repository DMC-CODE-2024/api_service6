package app.apiservice.repository.app;

import app.apiservice.entity.app.TRCTypeApprovedDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TRCApprovedDataRepo extends JpaRepository<TRCTypeApprovedDataEntity, Long>, JpaSpecificationExecutor<TRCTypeApprovedDataEntity> {
}
