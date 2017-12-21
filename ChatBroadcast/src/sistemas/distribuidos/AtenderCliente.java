package sistemas.distribuidos;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class AtenderCliente implements Runnable {

	private Socket cliente;
	
	public AtenderCliente(Socket cliente) {
		this.cliente = cliente;
	}

	@Override
	public void run() {
		try {
			Scanner ouvirMenssagem = new Scanner(this.cliente.getInputStream());
			
			while (true) {
				String mensagem =  ouvirMenssagem.nextLine();
				if(mensagem.equals("sair")){
					break;
				}
				ServidorChat.distribuiMensagens(mensagem, cliente);
			}
			ServidorChat.removeDaLista(cliente);
			cliente.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
