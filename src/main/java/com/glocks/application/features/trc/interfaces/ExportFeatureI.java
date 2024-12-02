package com.glocks.application.features.trc.interfaces;

import com.glocks.application.common.model.FileDetails;

@FunctionalInterface
public interface ExportFeatureI<T> {
    public FileDetails export(T t);
}
