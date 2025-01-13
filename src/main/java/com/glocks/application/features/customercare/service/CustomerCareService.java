package com.glocks.application.features.customercare.service;

import com.glocks.application.entity.app.IMEIManualPair;
import com.glocks.application.features.customercare.model.CustomerCareRequest;
import com.glocks.application.features.customercare.model.CustomerCareResponse;
import com.glocks.application.features.customercare.model.IMEIManualPairDTO;
import com.glocks.application.repository.app.IMEIManualPairRepository;
import com.glocks.application.repository.app.NotificationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    public CustomerCareService(IMEIManualPairRepository imeiManualPairRepository, NotificationRepository notificationRepository) {
        this.imeiManualPairRepository = imeiManualPairRepository;
        this.notificationRepository = notificationRepository;
    }


    public CustomerCareResponse get(CustomerCareRequest customerCareRequest) {
        String requestId = customerCareRequest.getRequestId();
        CustomerCareResponse customerCareResponse = new CustomerCareResponse();

        Optional<List<IMEIManualPair>> manualPairList = Optional.ofNullable(imeiManualPairRepository.findByRequestId(requestId));

        logger.info("[ response " + manualPairList + "]");
        // List<IMEIManualPairDTO> list = manualPairList.isPresent() ? manualPairList.get() : null;
        // IMEIManualPair imeiManualPair, imeiManualPair2, imeiManualPair3, imeiManualPair4;

        List<IMEIManualPairDTO> list = new ArrayList<>();
        if (manualPairList.isPresent()) {
            for (IMEIManualPair i : manualPairList.get()) {
                if (Objects.nonNull(i.getImei1())) {
                    IMEIManualPairDTO imeiManualPair1=new IMEIManualPairDTO();
                    imeiManualPair1.setCreatedOn(i.getCreatedOn());
                    imeiManualPair1.setRequestId(i.getRequestId());
                    imeiManualPair1.setRequestType(i.getRequestType());
                    imeiManualPair1.setImei1(i.getImei1());
                    imeiManualPair1.setMsisdn1(i.getMsisdn1());
                    list.add(imeiManualPair1);
                }

                if (Objects.nonNull(i.getImei2())) {
                    IMEIManualPairDTO imeiManualPair2=new IMEIManualPairDTO();
                    imeiManualPair2.setCreatedOn(i.getCreatedOn());
                    imeiManualPair2.setRequestId(i.getRequestId());
                    imeiManualPair2.setRequestType(i.getRequestType());
                    imeiManualPair2.setImei1(i.getImei2());
                    imeiManualPair2.setMsisdn1(i.getMsisdn2());
                    list.add(imeiManualPair2);
                }

                if (Objects.nonNull(i.getImei3())) {
                    IMEIManualPairDTO imeiManualPair3=new IMEIManualPairDTO();
                    imeiManualPair3.setCreatedOn(i.getCreatedOn());
                    imeiManualPair3.setRequestId(i.getRequestId());
                    imeiManualPair3.setRequestType(i.getRequestType());
                    imeiManualPair3.setImei1(i.getImei3());
                    imeiManualPair3.setMsisdn1(i.getMsisdn3());
                    list.add(imeiManualPair3);
                }

                if (Objects.nonNull(i.getImei4())) {
                    IMEIManualPairDTO imeiManualPair4=new IMEIManualPairDTO();
                    imeiManualPair4.setCreatedOn(i.getCreatedOn());
                    imeiManualPair4.setRequestId(i.getRequestId());
                    imeiManualPair4.setRequestType(i.getRequestType());
                    imeiManualPair4.setImei1(i.getImei4());
                    imeiManualPair4.setMsisdn1(i.getMsisdn4());
                    list.add(imeiManualPair4);
                }
            }
        }
        logger.info("imeiManualPairList {}", list);
        customerCareResponse.setRequestIdResponse(list);
        return customerCareResponse;
    }


}
