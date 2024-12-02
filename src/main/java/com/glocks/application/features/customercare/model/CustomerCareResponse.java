package com.glocks.application.features.customercare.model;

import com.glocks.application.entity.app.IMEIManualPair;
import com.glocks.application.entity.app.NotificationEntity;

import java.util.List;

public class CustomerCareResponse<T> {
    private List<T> requestIdResponse;
    private List<NotificationEntity> notificationEntity;
    public CustomerCareResponse() {

    }

    public List<T> getRequestIdResponse() {
        return requestIdResponse;
    }

    public CustomerCareResponse<T> setRequestIdResponse(List<T> requestIdResponse) {
        this.requestIdResponse = requestIdResponse;
        return this;
    }

    public List<NotificationEntity> getNotificationEntity() {
        return notificationEntity;
    }

    public CustomerCareResponse<T> setNotificationEntity(List<NotificationEntity> notificationEntity) {
        this.notificationEntity = notificationEntity;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CustomerCareResponse{");
        sb.append("requestIdResponse=").append(requestIdResponse);
        sb.append(", notificationEntity=").append(notificationEntity);
        sb.append('}');
        return sb.toString();
    }
}
