package app.apiservice.common.externalproperties;

import app.apiservice.common.db.PropertiesReader;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FeatureNameMap {
    @Autowired
    PropertiesReader propertiesReader;

    private final Logger log = LoggerFactory.getLogger(FeatureNameMap.class);
    public Map<String, String> globalMap = new HashMap<>();

    public String get(String key) {
        return globalMap.get(key);
    }

    public void remove(String key) {
        globalMap.remove(key);
    }

    public boolean containsKey(String key) {
        return globalMap.containsKey(key);
    }

    public void forEach() {
        globalMap.forEach((key, value) -> log.info("globalMap [" + key + ": " + value + "]"));
    }

    @PostConstruct
    public void init() {
        globalMap.put("UPLOAD", propertiesReader.upload);
        globalMap.put("VIEWALL", propertiesReader.viewAll);
        globalMap.put("VIEW", propertiesReader.view);
        globalMap.put("EXPORT", propertiesReader.export);
        globalMap.put("UPDATE", propertiesReader.update);

        globalMap.put("EXCEPTION_LIST", propertiesReader.exceptionListTitle);
        globalMap.put("BLOCK_LIST", propertiesReader.blockListTitle);
        globalMap.put("BLOCK_TAC", propertiesReader.blockedTacTitle);
        globalMap.put("GRAY_LIST", propertiesReader.trackedListTitle);

        globalMap.put("VIEW_EXCEPTION_LIST", propertiesReader.viewExceptionListTitle);
        globalMap.put("VIEW_BLOCK_LIST", propertiesReader.viewBlockListTitle);
        globalMap.put("VIEW_BLOCK_TAC", propertiesReader.viewBlockedTacTitle);

        globalMap.put("TA", propertiesReader.taTitle);
        globalMap.put("QA", propertiesReader.qaTitle);
        globalMap.put("LM", propertiesReader.lmTitle);
        globalMap.put("APPROVE_DEVICE_TAC", propertiesReader.approveDeviceTitle);
        globalMap.put("APPROVED_DEVICE_TAC", propertiesReader.viewApproveDeviceTitle);

        globalMap.put("VIEW_QA", propertiesReader.viewQATitle);
        globalMap.put("VIEW_TA", propertiesReader.viewTATitle);
        globalMap.put("VIEW_LM", propertiesReader.viewLMTitle);

        globalMap.put("NOTIFICATION", propertiesReader.notificationTitle);



    }
}
