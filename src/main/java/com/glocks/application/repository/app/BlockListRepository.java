package com.glocks.application.repository.app;

import com.glocks.application.entity.app.BlockListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockListRepository extends JpaRepository<BlockListEntity, Long>, JpaSpecificationExecutor<BlockListEntity> {

}
