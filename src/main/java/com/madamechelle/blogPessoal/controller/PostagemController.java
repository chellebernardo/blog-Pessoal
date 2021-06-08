package com.madamechelle.blogPessoal.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madamechelle.blogPessoal.model.Postagem;
import com.madamechelle.blogPessoal.repository.PostagemRepository;
import com.madamechelle.blogPessoal.service.UsuarioServices;

@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")
public class PostagemController {

	// Injeção de dependência
	@Autowired
	private PostagemRepository repository;
	@Autowired
	private UsuarioServices services;

	// Para obter todas as postagens através de uma lista criada no repository
	@GetMapping("/todes")
	public ResponseEntity<List<Postagem>> GetAll() {
		List<Postagem> listaDePostagens = repository.findAll();

		if (listaDePostagens.isEmpty()) {
			return ResponseEntity.status(204).build();

		} else {
			return ResponseEntity.status(200).body(listaDePostagens);
		}
	}

	// Obtendo as postagens por id
	@GetMapping("/{id_postagem}")
	public ResponseEntity<Postagem> buscarPorId(@PathVariable (value = "id_postagem") Long id) {
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());

	}

	// Para obter as postagens por Título
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> buscarPorTitulo(@PathVariable (value = "titulo") String titulo) {
		return ResponseEntity.ok(repository.findByTituloContainingIgnoreCase(titulo));
	}

	@PostMapping("/{id_usuario}/novo")
	public ResponseEntity<Postagem> novoPost(@PathVariable(value = "id_usuario") Long id,
			@RequestBody Postagem novoPost) {
		return ResponseEntity.status(201).body(repository.save(novoPost));
	}

	@PutMapping("/atualizar/{id_postagem}")
	public ResponseEntity<Postagem> atualizarPost(@PathVariable(value = "id_postagem") Long id,
			@Valid @RequestBody Postagem atualizacaoPost) {
		return services.atualizarPost(id, atualizacaoPost)
				.map(attPost -> ResponseEntity.status(201).body(attPost))
				.orElse(ResponseEntity.status(304).build());
	}

	@DeleteMapping("/delete/{id_postagem}")
	public void delete(@PathVariable (value = "id_postagem")Long id) {
		repository.deleteById(id);
	}
}
