package com.madamechelle.blogPessoal.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.sun.istack.NotNull;

@Entity
@Table (name = "tb_tema")
public class Tema {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)	
	private Long id;
	
	@NotNull
	private String descricao;
	
	@ManyToMany(mappedBy = "temasRelacionados")
	//@JsonIgnoreProperties({ "" })
	private List<Postagem> meusPosts = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Postagem> getMeusPosts() {
		return meusPosts;
	}

	public void setMeusPosts(List<Postagem> meusPosts) {
		this.meusPosts = meusPosts;
	}
	
}
