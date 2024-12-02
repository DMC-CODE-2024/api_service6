package com.glocks.application.repository.app;

import com.glocks.application.entity.app.BlockTACListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockTACRepository extends JpaRepository<BlockTACListEntity, Long>, JpaSpecificationExecutor<BlockTACListEntity> {

}
