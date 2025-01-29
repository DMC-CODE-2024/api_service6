package app.apiservice.repository.app;

import app.apiservice.entity.app.GrayListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GrayListRepository extends JpaRepository<GrayListEntity, Long>, JpaSpecificationExecutor<GrayListEntity> {

}
