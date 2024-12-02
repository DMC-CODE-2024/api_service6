package com.glocks.application.features.addressmanagement.service;

import com.glocks.application.entity.app.CommuneEntity;
import com.glocks.application.entity.app.DistrictEntity;
import com.glocks.application.features.trc.service.DistinctParamService;
import com.glocks.application.repository.app.CommuneRepository;
import com.glocks.application.repository.app.DistrictRepository;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class AddressListManagementDistinct {

    private final DistinctParamService distinctParamService;
    private final DistrictRepository districtRepository;
    private final CommuneRepository communeRepository;

    public AddressListManagementDistinct(CommuneRepository communeRepository, DistinctParamService distinctParamService, DistrictRepository districtRepository) {
        this.communeRepository = communeRepository;
        this.distinctParamService = distinctParamService;
        this.districtRepository = districtRepository;
    }


    public Map<String, List<?>> distinct(List<String> param, Class<?> entity) {
        return distinctParamService.distinct(param, entity);
    }

    public Optional<List<DistrictEntity>> findByProvinceId(Long provinceId) {
        List<DistrictEntity> sortList = districtRepository.findByProvinceId(provinceId);
        sortList.sort(Comparator.comparing(DistrictEntity::getDistrict));
        return Optional.ofNullable(sortList);
    }

    public Optional<List<CommuneEntity>> findByDistrict(String district) {
        Optional<DistrictEntity> byDistrict = districtRepository.findByDistrictOrDistrictKm(district,district);
        if (byDistrict.isPresent()) {
            List<CommuneEntity> sortList = communeRepository.findByDistrictId(Integer.parseInt(byDistrict.get().getId().toString()));
            sortList.sort(Comparator.comparing(CommuneEntity::getCommune));
            return Optional.ofNullable(sortList);
        }
        return Optional.empty();
    }
}
