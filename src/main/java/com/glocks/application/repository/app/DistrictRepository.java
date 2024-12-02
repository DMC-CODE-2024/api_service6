package com.glocks.application.repository.app;

import com.glocks.application.entity.app.AddressEntity;
import com.glocks.application.entity.app.DistrictEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(rollbackOn = {SQLException.class})
public interface DistrictRepository extends JpaRepository<DistrictEntity, Long>, JpaSpecificationExecutor<DistrictEntity> {
    void deleteByDistrict(String district);

    @Modifying
    @Query("UPDATE DistrictEntity SET district =:#{#a.district},modifiedOn=CURRENT_TIMESTAMP WHERE id =:id")
    public int updateEnColumns(@Param("a") AddressEntity addressEntity, @Param("id") Long id);

    @Modifying
    @Query("UPDATE DistrictEntity SET modifiedOn=CURRENT_TIMESTAMP, districtKm =:#{#a.districtKm} WHERE id =:id")
    public int updateKmColumns(@Param("a") AddressEntity addressEntity, @Param("id") Long id);

    Optional<DistrictEntity> findByDistrict( String district);

    List<DistrictEntity> findByProvinceId(long provinceId);

    Optional<DistrictEntity> findByDistrictOrDistrictKm(String district,String districtKm);
}
