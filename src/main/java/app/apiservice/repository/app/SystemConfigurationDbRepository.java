package app.apiservice.repository.app;

import app.apiservice.entity.app.SystemConfigurationDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemConfigurationDbRepository extends JpaRepository<SystemConfigurationDb, Long>, JpaSpecificationExecutor<SystemConfigurationDb> {

    public SystemConfigurationDb getByTag(String tag);

    public SystemConfigurationDb getById(Long id);

    public List<SystemConfigurationDb> getByType(Integer type);

}
