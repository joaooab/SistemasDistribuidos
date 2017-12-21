package sistemas.distribuidos;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServidorChat {

	private static List<Socket> listaDeCliente = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		ServerSocket servidor = new ServerSocket(1000);
		System.out.println("Servidor inciado!");

		while (true) {
			Socket cliente = servidor.accept();
			System.out.println(cliente.getPort() + " conectou-se");
			synchronized (listaDeCliente){
				listaDeCliente.add(cliente);
				new Thread(new AtenderCliente(cliente)).start();
			}
		}
	}

	public static void distribuiMensagens(String mensagem, Socket clienteDaMensagem) throws IOException {
		for (Socket cliente : listaDeCliente) {
			if (cliente != clienteDaMensagem) {
				PrintStream envidaDadosCliente = new PrintStream(cliente.getOutputStream());
				envidaDadosCliente.println(clienteDaMensagem.getPort() + ": " + mensagem);
			}
		}
	}
	
	public static void removeDaLista(Socket cliente){
		listaDeCliente.remove(cliente);
	}
}
