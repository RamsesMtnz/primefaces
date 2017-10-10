package com.mx.paquete.accesodatos.pu;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="facultad_odontologia",name="maestro")
public class MaestroEntity implements Serializable{

	private static final long serialVersionUID = 4275700533205358736L;
	
	@Id
	@Column(name = "id_empleado")
	private Integer idEmpleado;
	
	@Column(name = "nombre")
	private String nombre;

	@Column(name = "apellido_paterno")
	private String apellido_paterno;
	
	@Column(name = "apellido_materno")
	private String apellido_materno;

	@Column(name = "correo")
	private String correo;

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido_paterno() {
		return apellido_paterno;
	}

	public void setApellido_paterno(String apellido_paterno) {
		this.apellido_paterno = apellido_paterno;
	}

	public String getApellido_materno() {
		return apellido_materno;
	}

	public void setApellido_materno(String apellido_materno) {
		this.apellido_materno = apellido_materno;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	

	


}
