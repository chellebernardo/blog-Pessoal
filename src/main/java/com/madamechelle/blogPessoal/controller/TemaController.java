package com.madamechelle.blogPessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madamechelle.blogPessoal.model.Tema;
import com.madamechelle.blogPessoal.repository.TemaRepository;

@RestController
@RequestMapping ("/tema")
@CrossOrigin (origins = "*", allowedHeaders = "*")
public class TemaController {
	
	@Autowired
	private TemaRepository repository;
	
	@GetMapping ("/todes")
	public ResponseEntity<List<Tema>> buscarTodes (){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<Tema> buscarPorId (@PathVariable Long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(404).build());
	}
	
	@GetMapping ("/{nome}")
	public ResponseEntity<List<Tema>> buscarPorNome (@PathVariable String nome) {
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping ("/novo")
	public ResponseEntity<Tema> cadastrarTema (@RequestBody Tema novoTema) {
		return ResponseEntity.status(201).body(novoTema);
	}
				
	
	/*@PutMapping ("/atualizar/{id_tema}")
	public ResponseEntity<Tema> atualizarTema (@PathVariable  (value = "id_tema") Long id,
			@Valid @RequestBody Tema atualizacaoTema) {
		return services.atualizarTema(id, atualizacaoTema)
				.map(attTema -> ResponseEntity.status(201).body(attTema))
				.orElse(ResponseEntity.status(304).build());
	}*/
	
	@DeleteMapping ("/deletar/{id}")
	public void delete (@PathVariable Long id) {
		repository.deleteById(id);
	}
}
