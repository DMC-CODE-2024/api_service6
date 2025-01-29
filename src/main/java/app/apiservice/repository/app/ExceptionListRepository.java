package app.apiservice.repository.app;

import app.apiservice.entity.app.ExceptionListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ExceptionListRepository extends JpaRepository<ExceptionListEntity, Long>, JpaSpecificationExecutor<ExceptionListEntity> {

}
