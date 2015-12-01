package br.com.irrigacao.automacao.enumeration;

public enum EnumSituacaoSolo {

	SECO("Solo Seco"), UMIDO("Solo Úmido"), IRRIGADO("Solo Irrigado");

	private String descricao;

	private EnumSituacaoSolo(String descricao) {
		this.descricao = descricao;

	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
