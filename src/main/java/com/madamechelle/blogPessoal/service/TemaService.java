package com.madamechelle.blogPessoal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madamechelle.blogPessoal.model.Tema;
import com.madamechelle.blogPessoal.repository.TemaRepository;

@Service
public class TemaService {

	@Autowired
	private TemaRepository repository;
	
	public Optional <Object> cadastrarTema (String novoTema){
		Optional<Tema> temaExistente = repository.findByNome(novoTema);
		
		if (temaExistente.isPresent()) {
			return Optional.empty();
		} else {
			return Optional.ofNullable(novoTema);
		}
	}
	
}
