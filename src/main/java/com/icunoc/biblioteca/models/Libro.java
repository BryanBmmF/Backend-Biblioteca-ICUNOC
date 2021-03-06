package com.icunoc.biblioteca.models;

import com.icunoc.biblioteca.enums.Idioma;
import javax.persistence.*;
import java.util.Calendar;

@Entity
public class Libro {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idLibro;
	@Column(nullable=false, length=45)
	private String codigo;
	@Column(nullable=false, length=150)
	private String nombre;
	@Column(nullable=true, length=100)
	private String autor;
	@Column(nullable=false)
	private int stock;
	@Column(nullable=false)
	private  int edicion;
	@Temporal(TemporalType.DATE)
	private Calendar fechaPublicacion;
	@Enumerated(EnumType.ORDINAL)
	private Idioma rol;
	@Column( nullable=true, length=150)
	private String pathImagen;

	//freign key categoria
	@ManyToOne
	@JoinColumn(name="idCategoria")
	private Categoria categoria;

	public int getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getEdicion() {
		return edicion;
	}

	public void setEdicion(int edicion) {
		this.edicion = edicion;
	}

	public Calendar getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Calendar fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public Idioma getRol() {
		return rol;
	}

	public void setRol(Idioma rol) {
		this.rol = rol;
	}

	public String getPathImagen() {
		return pathImagen;
	}

	public void setPathImagen(String pathImagen) {
		this.pathImagen = pathImagen;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
