package com.tcc.enums;


public enum UnidadeEnum {
	
	LM (1, "Lúmen"),
	CD (2, "Candela"),
	LX (3, "Lux");
	
	private int cod;
	private String descricao;
	
	private UnidadeEnum(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static UnidadeEnum toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for (UnidadeEnum u: UnidadeEnum.values()) {
			if (cod.equals(u.getCod())) {
				return u;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
	
}