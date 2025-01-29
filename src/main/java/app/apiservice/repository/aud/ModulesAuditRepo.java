package app.apiservice.repository.aud;

import app.apiservice.entity.aud.ModulesAuditModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModulesAuditRepo extends JpaRepository<ModulesAuditModel, Long>, JpaSpecificationExecutor<ModulesAuditModel> {


    @Query("SELECT DISTINCT s.featureName FROM ModulesAuditModel s")
    public List<String> findDistinctFeatureName();

    @Query("SELECT DISTINCT s.status FROM ModulesAuditModel s  where s.status is NOT NULL and s.status != ''")
    public List<String> findDistinctStatus();

    @Query("SELECT DISTINCT s.moduleName FROM ModulesAuditModel s")
    public List<String> findDistinctModuleName();

    @Query("SELECT DISTINCT s.action FROM ModulesAuditModel s")
    public List<String> findDistinctAction();
}
