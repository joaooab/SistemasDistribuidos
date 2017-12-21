package sistemas.distribuidos;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) throws IOException {
		Socket cliente = new Socket("127.0.0.1", 5000);
		System.out.println("O cliente se conectou ao servidor!");

		Scanner entrada;
		PrintStream enviaRequisicao = new PrintStream(cliente.getOutputStream());
		Scanner recebeResposta = new Scanner(cliente.getInputStream());
		while (true) {
			entrada = new Scanner(System.in);
			String comando = entrada.nextLine();
			enviaRequisicao.println(comando);
			if (comando.equals("sair"))
				break;
			String escutaServidor = recebeResposta.nextLine();
			System.out.println(escutaServidor);
		}
		System.out.println("Desconectado do servidor");
		entrada.close();
		enviaRequisicao.close();
	}
}
