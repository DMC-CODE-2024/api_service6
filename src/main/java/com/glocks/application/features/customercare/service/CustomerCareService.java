package com.glocks.application.features.customercare.service;

import com.glocks.application.entity.app.DuplicateDeviceDetail;
import com.glocks.application.entity.app.IMEIManualPair;
import com.glocks.application.entity.app.MSISDNSeriesEntity;
import com.glocks.application.entity.app.NotificationEntity;
import com.glocks.application.features.customercare.model.CustomerCareRequest;
import com.glocks.application.features.customercare.model.CustomerCareResponse;
import com.glocks.application.repository.app.DuplicateDeviceDetailsRepository;
import com.glocks.application.repository.app.IMEIManualPairRepository;
import com.glocks.application.repository.app.NotificationRepository;
import com.glocks.application.repository.app.OperatorSeriesRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerCareService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private IMEIManualPairRepository imeiManualPairRepository;
    private NotificationRepository notificationRepository;
    private DuplicateDeviceDetailsRepository duplicateDeviceDetailsRepository;

    public CustomerCareService(IMEIManualPairRepository imeiManualPairRepository, NotificationRepository notificationRepository, DuplicateDeviceDetailsRepository duplicateDeviceDetailsRepository) {
        this.imeiManualPairRepository = imeiManualPairRepository;
        this.notificationRepository = notificationRepository;
        this.duplicateDeviceDetailsRepository = duplicateDeviceDetailsRepository;
    }


    public CustomerCareResponse get(CustomerCareRequest customerCareRequest) {
        String requestId = customerCareRequest.getRequestId();
        CustomerCareResponse customerCareResponse = new CustomerCareResponse();

        Optional<List<IMEIManualPair>> manualPairList = Optional.ofNullable(imeiManualPairRepository.findByRequestId(requestId));

        logger.info("[ manualPairList " + manualPairList + "]");
        List<IMEIManualPair> list = manualPairList.isPresent() ? manualPairList.get() : null;
        IMEIManualPair imeiManualPair, imeiManualPair2, imeiManualPair3, imeiManualPair4;
        List<IMEIManualPair> imeiManualPairList = new ArrayList<>();
        for (IMEIManualPair i : list) {
            if (Objects.nonNull(i.getImei1())) {
                imeiManualPair = i;
                imeiManualPair.setImei1(i.getImei1());
                imeiManualPair.setMsisdn1(i.getMsisdn1());
                imeiManualPairList.add(imeiManualPair);
            }
            if (Objects.nonNull(i.getImei2())) {
                imeiManualPair2 = i;
                imeiManualPair2.setImei1(i.getImei2());
                imeiManualPair2.setMsisdn1(i.getMsisdn2());
                imeiManualPairList.add(imeiManualPair2);
            }
            if (Objects.nonNull(i.getImei3())) {
                imeiManualPair3 = i;
                imeiManualPair3.setImei1(i.getImei3());
                imeiManualPair3.setMsisdn1(i.getMsisdn3());
                imeiManualPairList.add(imeiManualPair3);
            }
            if (Objects.nonNull(i.getImei4())) {
                imeiManualPair4 = i;
                imeiManualPair4.setImei1(i.getImei4());
                imeiManualPair4.setMsisdn1(i.getMsisdn4());
                imeiManualPairList.add(imeiManualPair4);
            }
        }
        logger.info("imeiManualPairList {}", imeiManualPairList);
        customerCareResponse.setRequestIdResponse(imeiManualPairList);

        return customerCareResponse;
    }


}
