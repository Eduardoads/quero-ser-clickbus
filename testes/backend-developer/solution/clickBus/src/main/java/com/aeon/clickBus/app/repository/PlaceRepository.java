package com.aeon.clickBus.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aeon.clickBus.app.model.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long>{
	List<Place> findByNameStartingWith(String name);
}
