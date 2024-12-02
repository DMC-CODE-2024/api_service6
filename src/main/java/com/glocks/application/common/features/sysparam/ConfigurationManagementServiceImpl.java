package com.glocks.application.common.features.sysparam;

import com.glocks.application.common.db.PropertiesReader;
import com.glocks.application.common.exceptions.ResourceServicesException;
import com.glocks.application.entity.app.SystemConfigurationDb;
import com.glocks.application.repository.app.SystemConfigurationDbRepository;
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
