package com.example.qtpq.repository;

import com.example.qtpq.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    Location findLocationByName(String name);
}
