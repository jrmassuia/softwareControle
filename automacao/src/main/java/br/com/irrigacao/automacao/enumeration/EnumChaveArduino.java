package br.com.irrigacao.automacao.enumeration;

public enum EnumChaveArduino {

	LIGAR("ARDUINO-LIGAR"), DESLIGAR("ARDUINO-DELISGAR"), CONSULTAR_SENSOR("ARDUINO-CONSULTAR-SENSOR"), LIGAR_BOMBA(
			"ARDUINO-LIGAR-BOMBA"), DESLIGAR_BOMBA("ARDUINO-DESLIGAR-BOMBA");

	private String descricao;

	private EnumChaveArduino(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
