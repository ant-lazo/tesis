package com.bolsadeideas.springboot.app.models.entity;

import lombok.Data;

@Data
public class ProductError {
	
	public Long id;
	
	public String nombre;
	
	public Double precio;
	
	public Integer cantidad;
	
	public Double total; 

}
