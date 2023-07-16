package br.com.juliandsonBarros.serviceImpl;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.juliandsonBarros.model.Endereco;
import br.com.juliandsonBarros.model.Pessoa;
import br.com.juliandsonBarros.model.DTO.PessoaDTO;
import br.com.juliandsonBarros.model.DTO.PessoaNewDTO;
import br.com.juliandsonBarros.model.enums.TipoEndereco;
import br.com.juliandsonBarros.model.enums.TipoPessoa;
import br.com.juliandsonBarros.repositoty.EnderecoRepository;
import br.com.juliandsonBarros.repositoty.PessoaRepository;
import br.com.juliandsonBarros.service.PessoaService;

@Service
public class PessoaServiceImpl implements PessoaService {

	@Autowired
	private PessoaRepository pessoaRepo;

	@Autowired
	private EnderecoRepository endRepo;

	@Transactional
	public Pessoa insert(Pessoa obj) {
		obj.setId(null);
		pessoaRepo.save(obj);
		endRepo.saveAll(obj.getEnderecos());
		return obj;
	}

	public List<Pessoa> findAll() {
		return pessoaRepo.findAll();
	}

	public Pessoa findId(Integer id) {
		return pessoaRepo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Pessoa não encontrada.", id));
	}

	public void delete(Integer id) {
		findId(id);
		try {
			pessoaRepo.deleteById(id);
		} catch (ObjectNotFoundException e) {
			throw new ObjectNotFoundException("Pessoa não encontrada", id);
		}
	}

	public Pessoa update(Pessoa pessoa) {
		Pessoa novaPessoa = findId(pessoa.getId());
		atualizaBanco(novaPessoa, pessoa);
		return pessoaRepo.save(novaPessoa);
	}

	public Pessoa fromDTO(PessoaDTO objDTO) {
		return new Pessoa(objDTO.getId(), objDTO.getNomeOuRazaoSocial(), null, null);
	}

	public Pessoa fromDTO(PessoaNewDTO objDTO) {
		Pessoa pessoa = new Pessoa(objDTO.getId(), objDTO.getNomeOuRazaoSocial(), objDTO.getCpfOuCnpj(),
				TipoPessoa.toEnum(objDTO.getTipo()));
		pessoa.getTelefone().add(objDTO.getTelefone());
		pessoa.getEmail().add(objDTO.getEmail());
		Endereco end = new Endereco(null, TipoEndereco.toEnum(objDTO.getTipo()), objDTO.getEndereco(),
				objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), objDTO.getCidade(),
				objDTO.getUF(), pessoa);
		pessoa.getEnderecos().add(end);

		return pessoa;
	}

	private void atualizaBanco(Pessoa novaPessoa, Pessoa pessoa) {
		novaPessoa.setNomeOuRazaoSocial(pessoa.getNomeOuRazaoSocial());
	}
}
