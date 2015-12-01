package br.com.irrigacao.automacao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.irrigacao.automacao.enumeration.EnumLeitorXml;

@Entity
@Table(name = "TB_CONFIGURACAO")
public class Configuracao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_CONFIGURACAO")
	@SequenceGenerator(name = "SEQ_CONFIGURACAO", sequenceName = "SEQ_CONFIGURACAO_ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONFIGURACAO")
	private Integer id;

	@Column(name = "HOST", length = 50)
	private String host;

	@Column(name = "PORTA", length = 50)
	private String porta;

	@Column(name = "USUARIO", length = 50)
	private String usuario;

	@Column(name = "SENHA", length = 50)
	private String senha;

	@Column(name = "POSSUI_PROXY", length = 50)
	private String possuiProxy;

	@Column(name = "QUANTIDADE_DIA")
	private Integer quantidadeDia;

	@Column(name = "LEITURA_XML", length = 20)
	private String leituraXml;

	// Configuração de ip de conexão com arduino
	@Column(name = "IP_ARDUINO", length = 20)
	private String ipArduino;

	// Configuração de porta de conexão com arduino
	@Column(name = "PORTA_ARDUINO")
	private Integer portaArudino;

	// Configuração para definir se o sistema deve validar a previsão do tempo e
	// intervalo de dias
	@Column(name = "VALIDA_ACOES", length = 5)
	private String validaAcoes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPorta() {
		return porta;
	}

	public void setPorta(String porta) {
		this.porta = porta;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getQuantidadeDia() {
		return quantidadeDia;
	}

	public void setQuantidadeDia(Integer quantidadeDia) {
		this.quantidadeDia = quantidadeDia;
	}

	public String getLeituraXml() {
		if (this.leituraXml == null) {
			this.setLeituraXml(EnumLeitorXml.LOCAL.getTipo());
		}
		return leituraXml;
	}

	public void setLeituraXml(String leituraXml) {
		this.leituraXml = leituraXml;
	}

	public Integer getPortaArudino() {
		return portaArudino;
	}

	public void setPortaArudino(Integer portaArudino) {
		this.portaArudino = portaArudino;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getIpArduino() {
		return ipArduino;
	}

	public void setIpArduino(String ipArduino) {
		this.ipArduino = ipArduino;
	}

	public String getPossuiProxy() {
		return possuiProxy;
	}

	public void setPossuiProxy(String possuiProxy) {
		this.possuiProxy = possuiProxy;
	}

	public String getValidaAcoes() {
		return validaAcoes;
	}

	public void setValidaAcoes(String validaAcoes) {
		this.validaAcoes = validaAcoes;
	}

}
