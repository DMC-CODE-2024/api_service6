package com.glocks.application.features.trc.interfaces;

import com.glocks.application.entity.app.MobileDeviceRepository;

@FunctionalInterface
public interface ApprovedDeviceTacI {
    public int update(MobileDeviceRepository mobileDeviceRepository);
}
