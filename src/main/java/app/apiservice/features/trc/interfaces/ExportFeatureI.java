package app.apiservice.features.trc.interfaces;

import app.apiservice.common.model.FileDetails;

@FunctionalInterface
public interface ExportFeatureI<T> {
    public FileDetails export(T t);
}
