package com.example.inhousenavigationsystem.persistence.entity;

import com.example.inhousenavigationsystem.domain.calculation.DistanceCalculation;
import com.example.inhousenavigationsystem.domain.calculation.MobileStationLocationReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MobileStationRepo extends JpaRepository<MobileStationEntity, Long> {

    @Query("SELECT new com.example.inhousenavigationsystem.domain.calculation.DistanceCalculation( " +
            "T.mobileStationId, " +
            "T.distance, " +
            "T.timestamp, " +
            "T.status " +
            " ) FROM ( SELECT " +
            "   row_number() over (partition by ms.id order by msl.createdAt desc) rownumber, " +
            "   ms.id as mobileStationId, " +
            "   sqrt(power((cast(bs.x as double) - cast(msl.x as double)), 2) + power((cast(bs.y as double) - cast(msl.y as double)), 2)) as distance, " +
            "   msl.createdAt as timestamp, " +
            "   ms.status as status " +
            "FROM MobileStationEntity ms " +
            "JOIN ms.locations msl " +
            "JOIN BaseStationEntity bs ON " +
            "    (sqrt(power((cast(bs.x as double) - cast(msl.x as double)), 2) + power((cast(bs.y as double) - cast(msl.y as double)), 2))) <= bs.detectionRadiusInMeters " +
            "WHERE bs.id = :baseStationId AND ms.status = com.example.inhousenavigationsystem.domain.model.MobileStationStatus.ACTIVE ) T " +
            "WHERE T.rownumber = 1 ")
    List<DistanceCalculation> findMobileStationLocationsWithinRadius(@Param("baseStationId") Long baseStationId);

    @Query("SELECT new com.example.inhousenavigationsystem.domain.calculation.MobileStationLocationReport(ms.id, msl.x, msl.y) " +
            "from MobileStationEntity ms " +
            "inner join ms.locations msl " +
            "where ms.id = :mobileStationId " +
            "order by msl.createdAt desc limit 1 ")
    MobileStationLocationReport findLatestMobileStationLocationsById(@Param("mobileStationId") final Long mobileStationId);
}
