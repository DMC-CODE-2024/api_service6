package app.apiservice.repository.app;

import app.apiservice.entity.app.WebActionDBEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WebActionDBRepository extends JpaRepository<WebActionDBEntity, Long>, JpaSpecificationExecutor<WebActionDBEntity> {

}
