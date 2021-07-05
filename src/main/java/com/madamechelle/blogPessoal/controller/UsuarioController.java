package com.madamechelle.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.madamechelle.blogPessoal.model.Usuario;
import com.madamechelle.blogPessoal.model.UsuarioLogin;
import com.madamechelle.blogPessoal.repository.UsuarioRepository;
import com.madamechelle.blogPessoal.service.UsuarioServices;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	
	private @Autowired UsuarioRepository repository;
	private @Autowired UsuarioServices usuarioService;
	
	@GetMapping ("/todes")
	public ResponseEntity <List<Usuario>> GetAll (){
		List <Usuario> listaDeUsuarios = repository.findAll();
		if (listaDeUsuarios.isEmpty()) {
			return ResponseEntity.status(204).build();
			
		} else {
			return ResponseEntity.status(200).body(listaDeUsuarios);
		}
	}
	
	@PostMapping ("/cadastrar")
	public ResponseEntity<Usuario> cadastrarUsuario (@Valid @RequestBody Usuario usuario){
		return ResponseEntity.status(201).body(usuarioService.cadastrarUsuario(usuario));
	}
	
	@PostMapping ("/login")
	public ResponseEntity<UsuarioLogin> authentication (@RequestBody Optional <UsuarioLogin> user){
		return usuarioService.login(user)
				.map(usuarioExistente -> ResponseEntity.status(200).body(usuarioExistente))
				.orElse(ResponseEntity.status(401).build());
	}
	
	@GetMapping("/pesquisar/{id}")
	public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable(value = "id") Long id) {
		return repository.findById(id)
				.map(usuarioExistente -> ResponseEntity.status(200).body(usuarioExistente))
				.orElse(ResponseEntity.status(204).build()); 
	}
	
	@GetMapping ("/pesquisar")
	public ResponseEntity<Object> buscarUsuarioPorNome (@RequestParam (defaultValue = "") String nome) {
		List<Usuario> listaDeUsuarios = repository.findAllByNomeContainingIgnoreCase(nome);
		
		if (!listaDeUsuarios.isEmpty()){
			return ResponseEntity.status(200).body(listaDeUsuarios);
		} else {
			return ResponseEntity.status(204).body("Ooops... Parece que esse usuário não existe!");
		}
	}
	
	@PutMapping ("/atualizar/{id}")
	public ResponseEntity <Usuario> atualizarUsuario (@Valid @PathVariable  (value = "id") Long id,
			@Valid @RequestBody Usuario atualizacaoUsuario) {
		return usuarioService.atualizarUsuario(id, atualizacaoUsuario)
				.map(usuarioAtualizado -> ResponseEntity.status(201).body(usuarioAtualizado))
				.orElse(ResponseEntity.status(304).build());
	}
}
