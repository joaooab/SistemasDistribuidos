package sistemas.distribuidos;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class AtenderCliente implements Runnable {

	private Socket cliente;
	private String nome;
	
	public AtenderCliente(Socket cliente, String nome) {
		this.cliente = cliente;
		this.nome = nome;
	}

	@Override
	public void run() {
		try {
			Scanner recebe = new Scanner(this.cliente.getInputStream());
			PrintStream envia = new PrintStream(this.cliente.getOutputStream());
			
			while (true) {
				String mensagem =  recebe.nextLine();
				String vetor[] = mensagem.split(":");
				if(mensagem.equals("sair")){
					envia.println(this.nome + " desconectou-se");
					break;
				}
				processaMensagem(envia, mensagem, vetor);
			}
			System.out.println(this.nome + " sdesconectou-se");
			ServidorChat.removeCliente(this.nome);
			cliente.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void processaMensagem(PrintStream envia, String mensagem, String[] vetor) throws IOException {
		if (mensagem.equals("*")){
			ServidorChat.distribuiMensagens(this.nome, "*", "");
		} else if (vetor.length == 2) {
			ServidorChat.distribuiMensagens(vetor[0], vetor[1], this.nome);
		}
		else envia.println("Erro: Siga as regras");
	}
}
