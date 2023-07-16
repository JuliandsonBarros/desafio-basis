package br.com.juliandsonBarros.model.DTO;

import java.io.Serializable;

import br.com.juliandsonBarros.model.Pessoa;
import br.com.juliandsonBarros.model.enums.TipoPessoa;

public class PessoaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nomeOuRazaoSocial;
	
	public PessoaDTO() {
	}

	public PessoaDTO(Pessoa pessoa) {
		id = pessoa.getId();
		nomeOuRazaoSocial = pessoa.getNomeOuRazaoSocial();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeOuRazaoSocial() {
		return nomeOuRazaoSocial;
	}

	public void setNomeOuRazaoSocial(String nomeOuRazaoSocial) {
		this.nomeOuRazaoSocial = nomeOuRazaoSocial;
	}
}
