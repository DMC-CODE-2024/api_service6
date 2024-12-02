package com.glocks.application.repository.app;

import com.glocks.application.entity.app.IMEIManualPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMEIManualPairRepository extends JpaRepository<IMEIManualPair, Long>, JpaSpecificationExecutor<IMEIManualPair> {

    List<IMEIManualPair> findByRequestId(String requestID);
}
