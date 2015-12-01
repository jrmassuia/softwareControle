package br.com.irrigacao.automacao.enumeration;

public enum EnumProxy {

	COM_PROXY("Com Proxy"), SEM_PROXY("Sem Proxy");

	private String proxy;

	EnumProxy(String proxy) {
		this.proxy = proxy;
	}

	public String getProxy() {
		return proxy;
	}

	public void setProxy(String proxy) {
		this.proxy = proxy;
	}

}
