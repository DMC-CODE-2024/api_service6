package com.glocks.application.repository.app;

import com.glocks.application.entity.app.DuplicateDeviceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DuplicateDeviceDetailsRepository extends JpaRepository<DuplicateDeviceDetail, Long>, JpaSpecificationExecutor<DuplicateDeviceDetail> {

    List<DuplicateDeviceDetail> findByTransactionId(String requestId);
}
