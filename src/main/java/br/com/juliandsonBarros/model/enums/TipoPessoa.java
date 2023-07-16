package br.com.juliandsonBarros.model.enums;

public enum TipoPessoa {
	
	PESSOA_FISICA(1,"Pessoa física"),
	PESSOA_JURIDICA(2,"Pessoa jurídica");
	
	private int cod;
	private String nomPessoa;
	private TipoPessoa(int cod, String nomPessoa) {
		this.cod = cod;
		this.nomPessoa = nomPessoa;
	}
	public int getCod() {
		return cod;
	}
	public String getNomPessoa() {
		return nomPessoa;
	}
	
	public static TipoPessoa toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(TipoPessoa p : TipoPessoa.values()) {
			if(cod.equals(p.getCod())){
				return p;
			}
		}
		throw new IllegalArgumentException("Id inválido" + cod);
	}
}	

