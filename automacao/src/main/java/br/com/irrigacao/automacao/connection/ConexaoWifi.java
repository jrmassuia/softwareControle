package br.com.irrigacao.automacao.connection;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import br.com.irrigacao.automacao.controller.SessionController;

public class ConexaoWifi {

	private Socket clientSocket;

	public String enviaDados(String enviarParametro) {

		String recebeParametro = "";
		String ip = SessionController.configuracao.getIpArduino();
		Integer porta = SessionController.configuracao.getPortaArudino();

		try {
			clientSocket = new Socket(ip, porta);
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			outToServer.writeBytes("" + enviarParametro);
			System.out.println("Dados enviado para o arduino: " + enviarParametro);

			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			recebeParametro = inFromServer.readLine();
			System.out.println("Dados recebido do arduino: " + recebeParametro);

			outToServer.close();
			inFromServer.close();

			clientSocket.close();

		} catch (Exception e) {
			System.out.println("Erro de conexão com arduino");
			return null;
		}

		return recebeParametro;
	}

}
