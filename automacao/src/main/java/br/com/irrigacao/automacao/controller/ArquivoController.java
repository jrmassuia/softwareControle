package br.com.irrigacao.automacao.controller;

import java.io.InputStream;
import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;

import br.com.irrigacao.automacao.entity.Arquivo;
import br.com.irrigacao.automacao.service.GenericService;

@Named
@ConversationScoped
public class ArquivoController extends GenericController<Arquivo> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private GenericService<Arquivo> service;

	private UploadedFile arquivo;

	@Override
	public void setService(GenericService<Arquivo> service) {
		super.setService(this.service);
	}

	@Override
	public GenericService<Arquivo> getService() {
		return this.service;
	}

	@Override
	protected void antesSalvar() {
		try {

			InputStream is = arquivo.getInputstream();
			byte[] bytes = IOUtils.toByteArray(is);

			this.objeto.setArquivo(bytes);
			this.objeto.setNome(arquivo.getFileName());
			String formato = arquivo.getFileName();
			this.objeto.setFormato(formato.substring(formato.lastIndexOf('.'), formato.length()));
			this.objeto.setNome(arquivo.getFileName());
			this.objeto.setArquivo(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public UploadedFile getArquivo() {
		return arquivo;
	}

	public void setArquivo(UploadedFile arquivo) {
		this.arquivo = arquivo;
	}

}
