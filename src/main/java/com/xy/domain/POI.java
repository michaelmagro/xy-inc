package com.xy.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;

@Entity
public class POI {
	
	@Id
	String name;
	
	@Min(value = 1, message = "A coordenada X deve ser um inteiro positivo")
	int coordinateX;
	
	@Min(value = 1, message = "A coordenada Y deve ser um inteiro positivo")
	int coordinateY;
	
	public POI() {
	}
	
	public POI(String name, int coordinateX, int coordinateY) {
		super();
		this.name = name;
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getCoordinateX() {
		return coordinateX;
	}
	
	public void setCoordinateX(int coordinateX) {
		this.coordinateX = coordinateX;
	}
	
	public Integer getCoordinateY() {
		return coordinateY;
	}
	
	public void setCoordinateY(int coordinateY) {
		this.coordinateY = coordinateY;
	}
	
}
