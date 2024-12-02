package com.glocks.application.repository.app;

import com.glocks.application.entity.app.AddressEntity;
import com.glocks.application.entity.app.CommuneEntity;
import com.glocks.application.entity.app.DistrictEntity;
import com.glocks.application.entity.app.ProvinceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.sql.SQLException;
import java.util.Optional;

@Repository
@Transactional(rollbackOn = {SQLException.class})
public interface ProvinceRepository extends JpaRepository<ProvinceEntity, Long>, JpaSpecificationExecutor<ProvinceEntity> {

    @Modifying
    @Query("UPDATE ProvinceEntity SET province =:newValue,modifiedOn=CURRENT_TIMESTAMP WHERE province =:oldValue")
    public int updateEnColumns(@Param("oldValue") String oldValue,@Param("newValue") String newValue);

    @Modifying
    @Query("UPDATE ProvinceEntity SET provinceKm =:newValue,modifiedOn=CURRENT_TIMESTAMP WHERE province =:oldValue")
    public int updateKmColumns(@Param("oldValue") String oldValue,@Param("newValue") String newValue);

    Optional<ProvinceEntity> findByProvince(String province);
}
