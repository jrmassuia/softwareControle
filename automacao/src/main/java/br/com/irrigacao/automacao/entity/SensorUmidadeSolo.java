package br.com.irrigacao.automacao.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TB_SENSOR_UMIDADE_SOLO")
public class SensorUmidadeSolo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_SENSOR_UMIDADE_SOLO")
	@SequenceGenerator(name = "SEQ_SENSOR_UMIDADE_SOLO", sequenceName = "SEQ_SENSOR_UMIDADE_SOLO_ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SENSOR_UMIDADE_SOLO")
	private Integer id;

	@OneToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_SENSOR", referencedColumnName = "ID_SENSOR", nullable = true, foreignKey = @ForeignKey(name = "FK_SENSOR") )
	private Sensor sensor;

	@Column(name = "UMIDADE")
	private Double umidade;

	@Column(name = "SITUACAO_SOLO")
	private String situacaoSolo;

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
		SensorUmidadeSolo other = (SensorUmidadeSolo) obj;
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

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public Double getUmidade() {
		return umidade;
	}

	public void setUmidade(Double umidade) {
		this.umidade = umidade;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSituacaoSolo() {
		return situacaoSolo;
	}

	public void setSituacaoSolo(String situacaoSolo) {
		this.situacaoSolo = situacaoSolo;
	}

}
