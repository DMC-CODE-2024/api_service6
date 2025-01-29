package app.apiservice.repository.app;

import app.apiservice.entity.app.MobileDeviceRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MobileDeviceRepoRepository extends JpaRepository<MobileDeviceRepository, Integer>, JpaSpecificationExecutor<MobileDeviceRepository> {
    @Modifying
    @Query("update MobileDeviceRepository m set m.trcApprovedStatus =:#{#mdr.action},m.approvedBy =:#{#mdr.approvedBy},m.trcApprovalDate=CURRENT_TIMESTAMP,  m.remark =:#{#mdr.remark}, m.isTypeApproved=1 WHERE m.id =:#{#mdr.id}")
    // public int update(@Param("id") Integer id, @Param("action") String action,@Param("remark") String remark);
    public int update(@Param("mdr") MobileDeviceRepository mdr);

}
