package br.com.juliandsonBarros.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.juliandsonBarros.model.Pessoa;
import br.com.juliandsonBarros.model.DTO.PessoaDTO;
import br.com.juliandsonBarros.model.DTO.PessoaNewDTO;
import br.com.juliandsonBarros.service.PessoaService;

@RestController
@RequestMapping(value="/pessoa")
public class PessoaController {
	
	@Autowired 
	private PessoaService pessoaServce;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PessoaDTO>> findAll() {
		List<Pessoa> listPessoa = pessoaServce.findAll();
		List<PessoaDTO> listDTO = listPessoa.stream().map(e -> new PessoaDTO(e)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pessoa> find(@PathVariable Integer id){
		Pessoa pessoa = pessoaServce.findId(id);
		return ResponseEntity.ok().body(pessoa);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody PessoaNewDTO objDTO){
		Pessoa pessoaSalva = pessoaServce.fromDTO(objDTO);
		pessoaSalva = pessoaServce.insert(pessoaSalva);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(pessoaSalva.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody PessoaDTO objDTO, @PathVariable Integer id){
		Pessoa pessoaSalva = pessoaServce.fromDTO(objDTO);
		pessoaSalva.setId(id);
		pessoaSalva = pessoaServce.update(pessoaSalva);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		pessoaServce.delete(id);
		return ResponseEntity.noContent().build();
	}
}
