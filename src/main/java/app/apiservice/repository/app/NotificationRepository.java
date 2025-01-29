package app.apiservice.repository.app;

import app.apiservice.entity.app.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long>, JpaSpecificationExecutor<NotificationEntity> {

    List<NotificationEntity> findByFeatureTxnIdAndFeatureName(String requestId, String featureName);
}
