package com.madamechelle.blogPessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.madamechelle.blogPessoal.model.Postagem;
import com.madamechelle.blogPessoal.model.Usuario;
import com.madamechelle.blogPessoal.model.UsuarioLogin;
import com.madamechelle.blogPessoal.repository.PostagemRepository;
import com.madamechelle.blogPessoal.repository.UsuarioRepository;

import org.apache.commons.codec.binary.Base64;

@Service
public class UsuarioServices {

	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private PostagemRepository repositoryP;

	/**
	 * Metodo utilizado para cadastrar um usuario no sistema com senha criptografada
	 * @param novoUsuario
	 * @since 2.0
	 * @author Chelle
	 * @return Retorna o cadastro do usuário criando uma senha criptografada.
	 */

	public Usuario cadastrarUsuario(Usuario novoUsuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String senhaEncoder = encoder.encode(novoUsuario.getSenha());
		novoUsuario.setSenha(senhaEncoder);
		
		return repository.save(novoUsuario);
	}
	
	/**
	 * Método para logar o usuário no sistema com autenticação.
	 * @param user - String representando a entidade Usuario
	 * @return Retorna a aute
	 */
	public Optional <UsuarioLogin> login (Optional <UsuarioLogin> user){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional <Usuario> usuario = repository.findByUsuario(user.get().getUsuario());
		
		if (usuario.isPresent()) {
			if (encoder.matches(user.get().getSenha(), user.get().getSenha())) {
				
				String auth = user.get().getUsuario() + ":" + user.get().getSenha();
				byte [] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String (encodedAuth);
				
				user.get().setToken(authHeader);
				user.get().setId(usuario.get().getId());
				user.get().setNome(usuario.get().getNome());
				user.get().setFoto(usuario.get().getFoto());
				user.get().setGenero(usuario.get().getGenero());
				user.get().setTipo(usuario.get().getTipo());
				
				return user;
			}
		}
		return null;
	}
	
	/**
	 * Método utilizado para atualizar os campos de nome e senha do Usuario
	 * 
	 * @param id - Long
	 * @param atualizacaoUsuario - representando a Entidade Usuario
	 * @since 1.0
	 * @author Chelle
	 * @return Retorna um Optional com entidade Usuario caso o mesmo exista, do
	 *         contrário um Optional vazio
	 */

	public Optional<Usuario> atualizarUsuario(Long id, Usuario atualizacaoUsuario) {
		Optional<Usuario> usuarioExistente = repository.findById(id);

		if (usuarioExistente.isPresent()) {
			usuarioExistente.get().setNome(atualizacaoUsuario.getNome());
			usuarioExistente.get().setSenha(atualizacaoUsuario.getSenha());
			usuarioExistente.get().setFoto(atualizacaoUsuario.getFoto());
			usuarioExistente.get().setGenero(atualizacaoUsuario.getGenero());
			usuarioExistente.get().setTipo(atualizacaoUsuario.getTipo());
			return Optional.ofNullable(repository.save(usuarioExistente.get()));
		} else {
			return Optional.empty();
		}
	}

	/**
	 * Método utilizado para atualizar Título e Texto de postagens existentes.
	 * @param  id - Long
	 * @param atualizacaoPost - representando Entidade Postagem
	 * @since 1.0
	 * @author Chelle
	 * @return Retorna um Optional com entidade Postagem caso exista,
	 * se não existir retorna um Optional vazio.
	 */
	public Optional <Postagem> atualizarPost (Long id, Postagem atualizacaoPost){
		Optional <Postagem> postExistente = repositoryP.findById(id);
		
		if (postExistente.isPresent()) {
			postExistente.get().setTitulo(atualizacaoPost.getTitulo());
			postExistente.get().setTexto(atualizacaoPost.getTexto());
			postExistente.get().setTemasRelacionados(atualizacaoPost.getTemasRelacionados());
			return Optional.ofNullable(repositoryP.save(atualizacaoPost));
		} else {
			return Optional.empty();
		}
	}
	
}