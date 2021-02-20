package com.icunoc.biblioteca.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Anotacion jpa para persitencia de datos
@Entity
public class Usuario {
	
	//Atributos de la clase y la tabla
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idUsuario;
	
	@Column(name="usuario", nullable=false, length=50)
	private String usuario;
	@Column(name="password", nullable=false, length=75)
	private String password;
	@Column(name="nombre", nullable=false, length=100)
	private String nombre;
	@Column(name="registroAcademico", nullable=false, length=15)
	private String registroAcademico;
	@Enumerated(EnumType.ORDINAL)
	private Rol rol;
	
	/*metodo get y set de cada atributo*/
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRegistroAcademico() {
		return registroAcademico;
	}
	public void setRegistroAcademico(String registroAcademico) {
		this.registroAcademico = registroAcademico;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	
    
	
}
