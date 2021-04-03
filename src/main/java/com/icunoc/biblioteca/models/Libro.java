package com.icunoc.biblioteca.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.icunoc.biblioteca.enums.Idioma;
import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "Libro")
public class Libro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int idLibro;
	@Column(name = "codigo")
	private String codigo;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "autor")
	private String autor;
	@Column(name = "stock")
	private int stock;
	@Column(name = "edicion")
	private  int edicion;
	@Column(name = "fechaPublicacion")
	@Temporal(TemporalType.DATE)
	private Calendar fechaPublicacion;
	@Column(name = "idioma")
	@Enumerated(EnumType.ORDINAL)
	private Idioma idioma;
	@Column(name = "pathImagen")
	private byte[] pathImagen;
	@Column(name = "idCategoria")
	private int idCategoria;

	public Libro(){}

	public Libro(String autor, String codigo, int edicion, Calendar fechaPublicacion, Idioma idioma, String nombre, byte[] pathImagen, int stock,  int categoria) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.autor = autor;
		this.stock = stock;
		this.edicion = edicion;
		this.fechaPublicacion = fechaPublicacion;
		this.idioma = idioma;
		this.pathImagen = pathImagen;
		this.idCategoria = categoria;
	}

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

	public Idioma getIdioma() {
		return idioma;
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}

	public byte[] getPathImagen() {
		return pathImagen;
	}

	public void setPathImagen(byte[] pathImagen) {
		this.pathImagen = pathImagen;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
}
