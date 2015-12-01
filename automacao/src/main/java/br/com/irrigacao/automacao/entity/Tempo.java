package br.com.irrigacao.automacao.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TB_TEMPO")
public class Tempo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_TEMPO")
	@SequenceGenerator(name = "SEQ_TEMPO", sequenceName = "SEQ_TEMPO_ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TEMPO")
	private Integer id;

	@Column(name = "DIA_SEMANA", length = 10)
	private String diaSemana;

	@Column(name = "DATA_DIA")
	private Calendar dataDia;

	@Column(name = "DATA_ULTIMA_ATUALIZACAO")
	private Calendar dataUltimaAtual;

	@Column(name = "TEMPERATURA_MAXIMA", length = 10)
	private String temperaturaMaxima;

	@Column(name = "TEMPERATURA_MINIMA", length = 10)
	private String temperaturaMinima;

	@Column(name = "PROBABILIDADE_CHUVA")
	private Double probChuva;

	@Column(name = "PRECISAO_CHUVA")
	private Double precChuva;

	@Column(name = "SITUACAO", length = 255)
	private String situacao;

	@Column(name = "ULTRA_VIOLETA", length = 10)
	private String ultraVioleta;

	@Column(name = "REGIAO", length = 150)
	private String regiao;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tempo other = (Tempo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public Calendar getDataDia() {
		return dataDia;
	}

	public void setDataDia(Calendar dataDia) {
		this.dataDia = dataDia;
	}

	public Calendar getDataUltimaAtual() {
		return dataUltimaAtual;
	}

	public void setDataUltimaAtual(Calendar dataUltimaAtual) {
		this.dataUltimaAtual = dataUltimaAtual;
	}

	public String getTemperaturaMaxima() {
		return temperaturaMaxima;
	}

	public void setTemperaturaMaxima(String temperaturaMaxima) {
		this.temperaturaMaxima = temperaturaMaxima;
	}

	public String getTemperaturaMinima() {
		return temperaturaMinima;
	}

	public void setTemperaturaMinima(String temperaturaMinima) {
		this.temperaturaMinima = temperaturaMinima;
	}

	public Double getProbChuva() {
		return probChuva;
	}

	public void setProbChuva(Double probChuva) {
		this.probChuva = probChuva;
	}

	public Double getPrecChuva() {
		return precChuva;
	}

	public void setPrecChuva(Double precChuva) {
		this.precChuva = precChuva;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getUltraVioleta() {
		return ultraVioleta;
	}

	public void setUltraVioleta(String ultraVioleta) {
		this.ultraVioleta = ultraVioleta;
	}

	public String getRegiao() {
		return regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
