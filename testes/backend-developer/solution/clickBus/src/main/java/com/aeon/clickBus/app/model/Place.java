package com.aeon.clickBus.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "places")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Place implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6182253679432755916L;
	
	private Long id;
	private String name;
	private String slug;
	private String city;
	private State state;
	private Countries countries;
	private Date createdAt;
	private Date updatedAt;
	
	public Place() {
		
	}
	
	public Place(Long id, String name, String slug, String city, 
			State state, Countries countries, Date createdAt,
			Date updatedAt) {
		super();
		this.id = id;
		this.name = name;
		this.slug = slug;
		this.city = city;
		this.state = state;
		this.countries = countries;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public Place(Long id, String name, String slug, String city, 
			State state, Countries countries) {
		super();
		this.id = id;
		this.name = name;
		this.slug = slug;
		this.city = city;
		this.state = state;
		this.countries = countries;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotBlank(message = "Name is required information.")
	@Column(name = "name", length = 50, nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank(message = "Slug is required information.")
	@Column(name = "slug", length = 20, nullable = false)
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	
	@NotBlank(message = "City is required information.")
	@Column(name = "city", length = 20, nullable = false)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name = "state", nullable = false)
	@Enumerated(EnumType.STRING)
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	
	@Column(name = "countries", nullable = false)
	@Enumerated(EnumType.STRING)
	public Countries getCountries() {
		return countries;
	}
	public void setCountries(Countries countries) {
		this.countries = countries;
	}
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
    @Column(name = "createdAt",nullable = false)
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
    @Column(name = "updatedAt",nullable = true)
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
