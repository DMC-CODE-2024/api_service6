package app.apiservice.features.trc.interfaces;

import app.apiservice.entity.app.MobileDeviceRepository;

@FunctionalInterface
public interface ApprovedDeviceTacI {
    public int update(MobileDeviceRepository mobileDeviceRepository);
}
