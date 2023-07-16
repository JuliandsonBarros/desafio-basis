package br.com.juliandsonBarros.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.juliandsonBarros.model.Pessoa;
import br.com.juliandsonBarros.model.DTO.PessoaDTO;
import br.com.juliandsonBarros.model.DTO.PessoaNewDTO;


@Service
public interface PessoaService{
	
	public Pessoa insert(Pessoa obj);
	
	public List<Pessoa> findAll();
	
	public Pessoa findId(Integer id);
	
	public void delete(Integer id);
	
	public Pessoa update(Pessoa pessoa);
	
	public Pessoa fromDTO(PessoaDTO objDTO);
	
	public Pessoa fromDTO(PessoaNewDTO objDTO);
}
