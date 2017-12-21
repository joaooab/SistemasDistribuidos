package sistemas.distribuidos;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ServidorChat {

	private static Map<String, Socket> clientes = new HashMap<String, Socket>();

	public static void main(String[] args) throws IOException {

		ServerSocket servidor = new ServerSocket(1000);
		System.out.println("Servidor foi inciado!");

		while (true) {
			Socket cliente = servidor.accept();
			Scanner leitor = new Scanner(cliente.getInputStream());
			String nome = leitor.nextLine();
			System.out.println(nome + " conectou-se");
			synchronized (clientes){
				clientes.put(nome, cliente);
				new Thread(new AtenderCliente(cliente, nome)).start();
			}
		}
	}

	public static void distribuiMensagens(String usuarioDestino, String mensagem, String nome) throws IOException {
		
		if(!clientes.containsKey(usuarioDestino)){
			Socket user = clientes.get(nome);
			PrintStream envia = new PrintStream(user.getOutputStream());
			envia.println("Usuário destino inválido");
			return;
		}
		
		Socket destino = clientes.get(usuarioDestino);
		PrintStream envia = new PrintStream(destino.getOutputStream());
		
		if (mensagem.equals("*")) {
			for (String user : clientes.keySet()) {
				envia.println(user);
			}
		} else {
			envia.println(nome + ":" + mensagem);
		}
	}
	
	public static void removeCliente(String nome){
		clientes.remove(nome);
	}
}
