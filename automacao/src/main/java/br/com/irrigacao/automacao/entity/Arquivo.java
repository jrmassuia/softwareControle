package br.com.irrigacao.automacao.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "TB_ARQUIVO")
public class Arquivo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_ARQUIVO")
	@SequenceGenerator(name = "SEQ_ARQUIVO", sequenceName = "SEQ_ARQUIVO_ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ARQUIVO")
	private Integer id;

	@Column(name = "ARQUIVO", nullable = true)
	private byte[] arquivo;

	@Column(name = "DESCRIÇÃO")
	private String descricao;

	@Column(name = "DATA_CONSULTA")
	@Temporal(TemporalType.DATE)
	private Date dataConsulta;

	@Column(name = "FORMATO", length = 10)
	private String formato;

	@Column(name = "NOME", length = 100)
	private String nome;

	@Transient
	private String dataConsultaFormatada;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDataConsultaFormatada() {
		if (this.getDataConsulta() != null) {
			this.dataConsultaFormatada = formatarData(this.getDataConsulta());
		}
		return dataConsultaFormatada;
	}

	public void setDataConsultaFormatada(String dataConsultaFormatada) {
		this.dataConsultaFormatada = dataConsultaFormatada;
	}

	public String formatarData(Date data) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		return formato.format(data);
	}
}
