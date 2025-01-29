package app.apiservice.common.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesReader {

    @Value("${spring.jpa.properties.hibernate.dialect}")
    public String dialect;

    @Value("${local-ip}")
    public String localIp;

    @Value("${audit.trail.upload}")
    public String upload;

    @Value("${sidebar.Exception_List_Management}")
    public String exceptionListTitle;

    @Value("${sidebar.Block_List_Management}")
    public String blockListTitle;

    @Value("${sidebar.Blocked_TAC_Management}")
    public String blockedTacTitle;

    @Value("${sidebar.Tracked_List_Management}")
    public String trackedListTitle;

    @Value("${view.title.el}")
    public String viewExceptionListTitle;

    @Value("${view.title.bl}")
    public String viewBlockListTitle;

    @Value("${view.title.btl}")
    public String viewBlockedTacTitle;

    @Value("${sidebar.Type_Approved}")
    public String taTitle;

    @Value("${sidebar.Qualified_Agents}")
    public String qaTitle;

    @Value("${sidebar.Local_Manufacturer_IMEI}")
    public String lmTitle;

    @Value("${sidebar.Approve_Device_TAC}")
    public String approveDeviceTitle;



    @Value("${view.title.lm}")
    public String viewLMTitle;

    @Value("${view.title.qa}")
    public String viewQATitle;

    @Value("${view.title.ta}")
    public String viewTATitle;

    @Value("${view.title.adt}")
    public String viewApproveDeviceTitle;

    @Value("${sidebar.Notification}")
    public String notificationTitle;


    @Value("${audit.trail.viewAll}")
    public String viewAll;

    @Value("${audit.trail.view}")
    public String view;

    @Value("${audit.trail.export}")
    public String export;

    @Value("${audit.trail.update}")
    public String update;
}
