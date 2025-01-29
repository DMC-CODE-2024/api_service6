package app.apiservice.repository.app;

import app.apiservice.entity.app.TRCLocalManufacturedDevicesDumpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LMDataRepository extends JpaRepository<TRCLocalManufacturedDevicesDumpEntity, Long>, JpaSpecificationExecutor<TRCLocalManufacturedDevicesDumpEntity> {

}
