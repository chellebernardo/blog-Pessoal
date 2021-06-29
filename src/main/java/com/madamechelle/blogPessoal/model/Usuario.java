package com.madamechelle.blogPessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table (name = "tb_usuario")
public class Usuario {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull (message = "Precisa ter um nome!")
	@Size (min = 3, max = 50)
	private String nome;
	
	@NotNull (message = "Aqui precisa ter um usuário válido!")
	@Size (min = 5, max = 15, message = "User entre 3 e 15")
	private String usuario;
	
	@NotNull (message = "A senha não pode ser nula, please!")
	private String senha;
	
	private String foto;

	private String tipo;

	private String genero;
	
	@OneToMany (mappedBy = "userPosts", cascade = CascadeType.ALL)
	@JsonIgnoreProperties ({"myPosts"})
	private List <Postagem> myPosts;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public List<Postagem> getMyPosts() {
		return myPosts;
	}

	public void setMyPosts(List<Postagem> myPosts) {
		this.myPosts = myPosts;
	}

}
