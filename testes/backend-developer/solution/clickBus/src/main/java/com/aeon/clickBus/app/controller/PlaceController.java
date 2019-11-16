package com.aeon.clickBus.app.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.aeon.clickBus.app.model.Place;
import com.aeon.clickBus.app.service.PlaceService;

@RestController
@RequestMapping("/places")
public class PlaceController {

	@Autowired
	PlaceService placeRepo;
	
	@PostMapping
	public ResponseEntity<?> createOrUpdatePlace(@Valid @RequestBody Place place){
		Place newPlace = placeRepo.save(place);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/places").
				path("/{id}").buildAndExpand(newPlace.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping
	public ResponseEntity<List<Place>> listPlace(){
		return ResponseEntity.ok().body(placeRepo.findAll());
	}
	
	@GetMapping("/searchId/{id}")
	public ResponseEntity<Place> findById(@PathVariable Long id){
		return ResponseEntity.ok().body(placeRepo.findById(id).get());
	}
	
	@GetMapping("/searchName/{name}")
	public ResponseEntity<List<Place>> findByName(@PathVariable String name){
		return ResponseEntity.ok().body(placeRepo.findByName(name));
	}
	
	@PostMapping("/delete/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		placeRepo.delete(id);
		return ResponseEntity.noContent().build();
	}
}
