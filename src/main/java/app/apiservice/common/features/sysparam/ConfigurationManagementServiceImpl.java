package app.apiservice.common.features.sysparam;

import app.apiservice.common.db.PropertiesReader;
import app.apiservice.common.exceptions.ResourceServicesException;
import app.apiservice.entity.app.SystemConfigurationDb;
import app.apiservice.repository.app.SystemConfigurationDbRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationManagementServiceImpl {
    private static final Logger logger = LogManager.getLogger(
            ConfigurationManagementServiceImpl.class);

    @Autowired
    SystemConfigurationDbRepository systemConfigurationDbRepository;

    @Autowired
    PropertiesReader propertiesReader;


    public SystemConfigurationDb findByTag(String tag) {
        try {
            return systemConfigurationDbRepository.getByTag(tag);
        } catch (Exception e) {
            logger.info("Exception found=" + e.getMessage());
            throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
        }
    }

}
