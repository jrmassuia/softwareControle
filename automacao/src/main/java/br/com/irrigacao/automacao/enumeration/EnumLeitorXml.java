package br.com.irrigacao.automacao.enumeration;

public enum EnumLeitorXml {

	WEB_SERVICE("Web Service"), LOCAL("Local");

	private String tipo;

	private EnumLeitorXml(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
