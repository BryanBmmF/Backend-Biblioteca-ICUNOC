package com.icunoc.biblioteca.security.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//Anotacion jpa para persitencia de datos
@Entity
public class Usuario {
	
	//Atributos de la clase y la tabla
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUsuario;
	@Column(unique = true, nullable=false, length=50)
	private String usuario;
	@Column(nullable=false, length=100)
	private String password;
	@Column(nullable=false, length=100)
	private String nombre;
	@Column(nullable=false, length=15)
	private String registroAcademico;
	@ManyToMany
	@JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"),
	inverseJoinColumns = @JoinColumn(name = "rol_id"))
	private Set<Rol> roles = new HashSet<>();

	public Usuario() {
	}

	public Usuario(String usuario, String password, String nombre, String registroAcademico) {
		this.usuario = usuario;
		this.password = password;
		this.nombre = nombre;
		this.registroAcademico = registroAcademico;
	}

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

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
}
