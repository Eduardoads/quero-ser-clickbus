package com.aeon.clickBus.app.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aeon.clickBus.app.model.Place;
import com.aeon.clickBus.app.repository.PlaceRepository;

@Service
public class PlaceService {

	@Autowired
	private PlaceRepository repository;
	
	public List<Place> findAll(){
		List<Place> lista = repository.findAll();
		Collections.sort(lista, Comparator.comparingLong(Place::getId));
		return lista;
	}
	
	public Optional<Place> findById(Long id) {
		return repository.findById(id);
	}
	
	public List<Place> findByName(String param){
		return repository.findByNameStartingWith(param);
	}
	
	public Place save(Place place) {
		
		Optional<Place> oldPlace = findById(place.getId());
		
		if(oldPlace.isPresent()) {
			Place newPlace = oldPlace.get();
			
			newPlace.setName(place.getName());
			newPlace.setSlug(place.getSlug());
			newPlace.setCity(place.getCity());
			newPlace.setState(place.getState());
			newPlace.setCountries(place.getCountries());
			newPlace.setCreatedAt(place.getCreatedAt());
			newPlace.setUpdatedAt(new Date());
			
			newPlace = repository.saveAndFlush(newPlace);
			
			return newPlace;
		}else {
			place.setCreatedAt(new Date());
			
			place = repository.saveAndFlush(place);
			
			return place;
		}
	}
	
	public void delete(Long id){
		Optional<Place> obj = findById(id);
		Place place = obj.get();
		repository.delete(place);
	}
}
