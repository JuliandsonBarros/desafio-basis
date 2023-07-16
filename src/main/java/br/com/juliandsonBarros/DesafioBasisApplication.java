package br.com.juliandsonBarros;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.juliandsonBarros.model.Endereco;
import br.com.juliandsonBarros.model.Pessoa;
import br.com.juliandsonBarros.model.enums.TipoEndereco;
import br.com.juliandsonBarros.model.enums.TipoPessoa;
import br.com.juliandsonBarros.repositoty.EnderecoRepository;
import br.com.juliandsonBarros.repositoty.PessoaRepository;

@SpringBootApplication
public class DesafioBasisApplication implements CommandLineRunner {
	
	@Autowired
	private PessoaRepository pessoaRepo;
	@Autowired
	private EnderecoRepository endRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(DesafioBasisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Pessoa p1 =  new Pessoa(null, "Juliandson Barros","0138935100",  TipoPessoa.PESSOA_FISICA);
		p1.getTelefone().addAll(Arrays.asList("61992850840"));
		p1.getEmail().addAll(Arrays.asList("Juliandsonbs@gmail.com"));
		
		Endereco e1 = new Endereco(null,TipoEndereco.ENDERECO_RESISENCIAL,"Quadra 06","27","MR 02","Oeste","73750-060","Planaltina","GO",p1);
		
		Pessoa p2 =  new Pessoa(null, "JBS CONSTRUTORA","014890001/256", TipoPessoa.PESSOA_JURIDICA);
		p2.getTelefone().addAll(Arrays.asList("6132650868"));
		p2.getEmail().addAll(Arrays.asList("jbsconstrutora@gmail.com"));
		
		Endereco e2 = new Endereco(null,TipoEndereco.ENDERECO_COMERCIAL,"Comercial","2708","MR 02","Norte","73710-060","Planaltina","GO",p2);
		pessoaRepo.saveAll(Arrays.asList(p1,p2));
		endRepo.saveAll(Arrays.asList(e1,e2));
	}

}
