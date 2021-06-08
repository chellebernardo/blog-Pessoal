package com.madamechelle.blogPessoal.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_postagem")
public class Postagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Size (min = 5, max = 100)
	private String titulo;

	@NotNull
	/*@Size(min = 10, max = 500)*/
	@Column(name = "CONTENT", length = 1000)
	// anotação para aumentar o tamanho do texto no corpo da postagem no MySQL
	private String texto;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date = new java.sql.Date(System.currentTimeMillis());
	
	@ManyToMany
	@JoinTable(name = "relaçãoPT", joinColumns = @JoinColumn(name = "fk_post"), inverseJoinColumns = @JoinColumn(name = "fk_tag"))
	//@JsonIgnoreProperties({ "" })
	private Set<Tema> temasRelacionados = new HashSet<>();
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "usuarioPostagem")
	//@JsonIgnoreProperties({ "" })
	private Usuario userPosts;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Set<Tema> getTemasRelacionados() {
		return temasRelacionados;
	}

	public void setTemasRelacionados(Set<Tema> temasRelacionados) {
		this.temasRelacionados = temasRelacionados;
	}

	public Usuario getUserPost() {
		return userPosts;
	}

	public void setUserPost(Usuario userPost) {
		this.userPosts = userPost;
	}

}
