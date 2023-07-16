package br.com.juliandsonBarros.model.enums;

public enum TipoEndereco {
	
	ENDERECO_RESISENCIAL(1,"Enderço residencial"),
	ENDERECO_COMERCIAL(2,"Endereco comercial");
	
	private int codEndereco;
	private String nomEndereco;
	
	private TipoEndereco(int codEndereco, String nomEndereco) {
		this.codEndereco = codEndereco;
		this.nomEndereco = nomEndereco;
	}

	public int getCodEndereco() {
		return codEndereco;
	}

	public String getNomEndereco() {
		return nomEndereco;
	}
	
	public static TipoEndereco toEnum (Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(TipoEndereco x : TipoEndereco.values()) {
			if(cod.equals(x.getCodEndereco())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido" + cod);
	}
}
