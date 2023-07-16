package br.com.juliandsonBarros.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.juliandsonBarros.model.enums.TipoPessoa;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L; 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Integer id;
	private String nomeOuRazaoSocial;
	private String cpfOuCnpj;
	
	private Integer tp;
	
	@ElementCollection
	@CollectionTable(name="TELEFONE")
	private Set<String> telefone = new HashSet<>();
	
	@ElementCollection
	@CollectionTable(name="EMAIL")
	private Set<String> email = new HashSet<>();
	
	@OneToMany(mappedBy = "pessoa")
	private List<Endereco> enderecos = new ArrayList<>();
	
	public Pessoa() {
	}

	public Pessoa(Integer id, String nome, String cpfOuCnpj, TipoPessoa tipo ) {
		this.id = id;
		this.nomeOuRazaoSocial = nome;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tp = (tipo == null) ? null :tipo.getCod();//Tipo pessoa não pode ser nulo. trata objeto caso chegue nulo ou atribui valor ao codigo enum
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


	public void setNomeOuRazaoSocial(String nome) {
		this.nomeOuRazaoSocial = nome;
	}


	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}


	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public TipoPessoa getTp() {
		return TipoPessoa.toEnum(tp);
	}

	public void setTp(TipoPessoa tp) {
		this.tp = tp.getCod();
	}

	public Set<String> getTelefone() {
		return telefone;
	}


	public void setTelefone(Set<String> telefone) {
		this.telefone = telefone;
	}


	public Set<String> getEmail() {
		return email;
	}


	public void setEmail(Set<String> email) {
		this.email = email;
	}
	
	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(id, other.id);
	}
}
