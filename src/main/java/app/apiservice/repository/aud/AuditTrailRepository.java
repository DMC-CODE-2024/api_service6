package app.apiservice.repository.aud;

import app.apiservice.entity.aud.AuditTrail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditTrailRepository extends JpaRepository<AuditTrail, Long>, JpaSpecificationExecutor<AuditTrail> {

    public AuditTrail getById(long id);
}
