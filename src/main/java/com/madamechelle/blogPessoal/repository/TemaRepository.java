package com.madamechelle.blogPessoal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madamechelle.blogPessoal.model.Tema;

public interface TemaRepository extends JpaRepository<Tema, Long>{
	
	public List<Tema> findAllByDescricaoContainingIgnoreCase(String descricao);
	
	public Optional <Object> findByDescricaoContainingIgnoreCase (String descricao);
	
	public Optional <Tema> findByDescricao (String descricao);

}
