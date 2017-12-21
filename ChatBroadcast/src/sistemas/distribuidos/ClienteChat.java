package sistemas.distribuidos;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteChat {

	public static void main(String[] args) throws UnknownHostException, IOException {

		
		Socket servidor = new Socket("127.0.0.1", 1000);
		System.out.println("Conectou-se ao servidor");

		Scanner leitor = new Scanner(System.in);
		PrintStream envia = new PrintStream(servidor.getOutputStream());
		Scanner ouvirServidor = new Scanner(servidor.getInputStream());
		
		//Cliente envia Mensagem
		new Thread(() -> {
			while (true) {
				String mensagem = leitor.nextLine();
				envia.println(mensagem);
			}
		}).start();

		//Cliente escuta Mensagem
		new Thread(() -> {
			while (true) {
				String mensagem = ouvirServidor.nextLine();
				System.out.println(mensagem);
			}
		}).start();
	}
}
