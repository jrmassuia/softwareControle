package br.com.irrigacao.automacao.enumeration;

public enum EnumValidaAcoes {

	SIM("Sim"), NAO("Não");

	private String tipo;

	private EnumValidaAcoes(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
