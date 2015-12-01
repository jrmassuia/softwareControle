package br.com.irrigacao.automacao.connection;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TesteConexaoWifi {

	private static final String serverIP = "192.168.40.111";
	private static final int serverPort = 80;
	private Socket clientSocket;

	public boolean testeConexao() {
		boolean retorno = false;
		try {
			clientSocket = new Socket(serverIP, serverPort);

			if (clientSocket.isClosed()) {
				retorno = true;
			} else {
				retorno = false;
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return retorno;
	}
}
