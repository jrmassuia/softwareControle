package br.com.irrigacao.automacao.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "TB_SENSOR")
public class Sensor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_SENSOR")
	@SequenceGenerator(name = "SEQ_SENSOR", sequenceName = "SEQ_SENSOR_ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SENSOR")
	private Integer Id;

	@Column(name = "DESCRICAO", length = 255)
	private String descricao;

	@Column(name = "DATA_LEITURA", unique = true)
	@Temporal(TemporalType.DATE)
	private Date dataLeitura;

	@Column(name = "HORA_INICIO")
	@Temporal(TemporalType.TIME)
	private Date horaInicio;

	@Column(name = "HORA_FIM")
	@Temporal(TemporalType.TIME)
	private Date horaFim;

	@OneToOne(cascade = CascadeType.ALL, targetEntity = SensorUmidadeSolo.class, fetch = FetchType.LAZY, mappedBy = "sensor")
	private SensorUmidadeSolo sensorUmidadeSolo;

	@Transient
	private Integer intervaloDia;

	@Transient
	private String proximaChuva;

	@Transient
	private String dataLeituraFormatado;

	@Transient
	private String horaInicioFormatado;

	@Transient
	private String tempoIrrigacaoFormatado;

	@Transient
	private String horaFimFormatado;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
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
		Sensor other = (Sensor) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		return true;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataLeitura() {
		return dataLeitura;
	}

	public void setDataLeitura(Date dataLeitura) {
		this.dataLeitura = dataLeitura;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Date horaFim) {
		this.horaFim = horaFim;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setTempoIrrigacaoFormatado(String tempoIrrigacaoFormatado) {
		this.tempoIrrigacaoFormatado = tempoIrrigacaoFormatado;
	}

	public String getDataLeituraFormatado() {
		if (this.getDataLeitura() != null) {
			this.dataLeituraFormatado = formatarData(this.getDataLeitura());
		}
		return dataLeituraFormatado;
	}

	public void setDataLeituraFormatado(String dataLeituraFormatado) {
		this.dataLeituraFormatado = dataLeituraFormatado;
	}

	public String getHoraInicioFormatado() {
		if (this.getHoraInicio() != null) {
			this.horaInicioFormatado = formatarHora(this.getHoraInicio());
		}
		return horaInicioFormatado;
	}

	public void setHoraInicioFormatado(String horaInicioFormatado) {
		this.horaInicioFormatado = horaInicioFormatado;
	}

	public String getHoraFimFormatado() {
		if (this.getHoraFim() != null) {
			this.horaFimFormatado = formatarHora(this.getHoraFim());
		}
		return horaFimFormatado;
	}

	public void setHoraFimFormatado(String horaFimFormatado) {
		this.horaFimFormatado = horaFimFormatado;
	}

	public String formatarData(Date data) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		return formato.format(data);
	}

	public String formatarHora(Date data) {
		SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
		return formato.format(data);
	}

	public String getTempoIrrigacaoFormatado() {

		if (this.getHoraInicio() != null && this.getHoraFim() != null) {

			long tempo = (this.getHoraFim().getTime() - this.getHoraInicio().getTime());

			tempo = tempo / 60000; // Converte o tempo para minutos
			int minutos = (int) (tempo % 60); // Retira os minutos da hora
			tempo = tempo / 60; // Deixa em tempo apenas as horas

			this.tempoIrrigacaoFormatado = String.format("%02d:%02d", tempo, minutos);

		} else {
			tempoIrrigacaoFormatado = "00:00";
		}

		return tempoIrrigacaoFormatado;
	}

	public SensorUmidadeSolo getSensorUmidadeSolo() {
		if (this.sensorUmidadeSolo == null) {
			this.setSensorUmidadeSolo(new SensorUmidadeSolo());
		}
		return sensorUmidadeSolo;
	}

	public void setSensorUmidadeSolo(SensorUmidadeSolo sensorUmidadeSolo) {
		this.sensorUmidadeSolo = sensorUmidadeSolo;
	}

	public Integer getIntervaloDia() {
		return intervaloDia;
	}

	public void setIntervaloDia(Integer intervaloDia) {
		this.intervaloDia = intervaloDia;
	}

	public String getProximaChuva() {
		return proximaChuva;
	}

	public void setProximaChuva(String proximaChuva) {
		this.proximaChuva = proximaChuva;
	}

}
