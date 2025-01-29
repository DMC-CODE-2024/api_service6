package app.apiservice.features.trc.service;

import app.apiservice.common.features.sysparam.ConfigTags;
import app.apiservice.common.features.sysparam.ConfigurationManagementServiceImpl;
import app.apiservice.entity.app.SystemConfigurationDb;
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
        return DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
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
