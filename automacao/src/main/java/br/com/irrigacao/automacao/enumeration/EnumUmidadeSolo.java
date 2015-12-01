package br.com.irrigacao.automacao.enumeration;

public enum EnumUmidadeSolo {

	SECO(700, "Solo Seco"), UMIDO(701, "Solo Úmido");

	private String situacaoSolo;
	private double umidadeSolo;

	private EnumUmidadeSolo(double umidadeSolo, String situacaoSolo) {
		this.situacaoSolo = situacaoSolo;
		this.umidadeSolo = umidadeSolo;
	}

	public String getSituacaoSolo() {
		return situacaoSolo;
	}

	public void setSituacaoSolo(String situacaoSolo) {
		this.situacaoSolo = situacaoSolo;
	}

	public double getUmidadeSolo() {
		return umidadeSolo;
	}

	public void setUmidadeSolo(double umidadeSolo) {
		this.umidadeSolo = umidadeSolo;
	}

}
