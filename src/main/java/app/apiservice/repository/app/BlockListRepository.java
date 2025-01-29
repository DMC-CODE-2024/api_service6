package app.apiservice.repository.app;

import app.apiservice.entity.app.BlockListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockListRepository extends JpaRepository<BlockListEntity, Long>, JpaSpecificationExecutor<BlockListEntity> {

}
