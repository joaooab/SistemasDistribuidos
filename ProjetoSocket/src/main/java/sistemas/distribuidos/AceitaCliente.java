package sistemas.distribuidos;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class AceitaCliente implements Runnable {
	
	private final Socket cliente;
	private final HashMap<String, String> carros;
	
	AceitaCliente(Socket cliente, HashMap<String, String> carros) {
		this.cliente = cliente;
		this.carros = carros;
	}
	
	public void run() {
		System.out.println("Nova conexao estabelecida: " +
				cliente.getPort());
		try {
			Scanner escutaCliente = new Scanner(cliente.getInputStream());
			PrintStream respondeCliente = new PrintStream(cliente.getOutputStream());
			while (true) {
				if (!escutaCliente.hasNextLine()) continue;
				String comando = escutaCliente.nextLine();
				System.out.println(cliente.getPort() + " envia: " + comando);
				if (comando.equals("sair")) {
					System.out.println(cliente.getPort() + ": Desconectou");
					break;
				}
				respondeCliente.println((carros.getOrDefault(comando, "Comando invalido")));
			}
			escutaCliente.close();
			cliente.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
