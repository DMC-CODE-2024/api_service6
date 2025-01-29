package app.apiservice.repository.app;

import app.apiservice.entity.app.EIRSListManagementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EIRSListManagementRepository extends JpaRepository<EIRSListManagementEntity, Long>, JpaSpecificationExecutor<EIRSListManagementEntity> {

}
