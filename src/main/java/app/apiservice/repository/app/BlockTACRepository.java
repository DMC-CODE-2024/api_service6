package app.apiservice.repository.app;

import app.apiservice.entity.app.BlockTACListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockTACRepository extends JpaRepository<BlockTACListEntity, Long>, JpaSpecificationExecutor<BlockTACListEntity> {

}
