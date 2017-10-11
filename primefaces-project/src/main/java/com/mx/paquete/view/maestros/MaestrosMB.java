package com.mx.paquete.view.maestros;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class MaestrosMB {

	private String nombre = "Kukulkan";
	
	
	public void cambiarNombre(){
		this.nombre = "Ramses";
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
}
