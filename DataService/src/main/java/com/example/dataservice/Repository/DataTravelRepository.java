package com.example.dataservice.Repository;

import com.example.dataservice.Model.DataTravel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DataTravelRepository extends JpaRepository<DataTravel, Long> {

    @Query(value = "SELECT * FROM data_travel ORDER BY id DESC LIMIT 1", nativeQuery = true)
    DataTravel findLastDataTravel();
}
