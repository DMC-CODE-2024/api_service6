package com.glocks.application.repository.app;

import com.glocks.application.entity.app.AddressEntity;
import com.glocks.application.entity.app.CommuneEntity;
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
public interface CommuneRepository extends JpaRepository<CommuneEntity, Long>, JpaSpecificationExecutor<CommuneEntity> {

    void deleteByCommune(String commune);
    Optional<CommuneEntity> findByCommuneAndDistrictId(String commune,int districtId);

    @Modifying
    @Query("UPDATE CommuneEntity SET commune =:#{#a.commune} ,modifiedOn=CURRENT_TIMESTAMP WHERE id =:id")
    public int updateEnColumns(@Param("a") AddressEntity addressEntity, @Param("id") Long id);

    @Modifying
    @Query("UPDATE CommuneEntity SET communeKm =:#{#a.communeKm}, modifiedOn=CURRENT_TIMESTAMP WHERE id =:id")
    public int updateKmColumns(@Param("a") AddressEntity addressEntity, @Param("id") Long id);

    List<CommuneEntity> findByDistrictId(int id);
}
