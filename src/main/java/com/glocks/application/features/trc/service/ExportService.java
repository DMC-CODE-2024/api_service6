package com.glocks.application.features.trc.service;

import com.glocks.application.common.features.sysparam.ConfigTags;
import com.glocks.application.common.features.sysparam.ConfigurationManagementServiceImpl;
import com.glocks.application.entity.app.SystemConfigurationDb;
import com.glocks.application.features.trc.paging.QualifiedAgentsDataPaging;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class ExportService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final ConfigurationManagementServiceImpl configurationManagementServiceImpl;

    public ExportService(ConfigurationManagementServiceImpl configurationManagementServiceImpl) {
        this.configurationManagementServiceImpl = configurationManagementServiceImpl;
    }

    public int pageSize() {
        return Integer.parseInt(configurationManagementServiceImpl.findByTag("file.max-file-record").getValue());
    }

    public DateTimeFormatter forDate() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    public DateTimeFormatter forFile() {
        return DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
    }

    public SystemConfigurationDb filepath() {
        return configurationManagementServiceImpl.findByTag(ConfigTags.file_download_dir);
    }

    public SystemConfigurationDb link() {
        return configurationManagementServiceImpl.findByTag(ConfigTags.file_download_link);
    }
}
