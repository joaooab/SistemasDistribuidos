package sistemas.distribuidos;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class RecebeMensagem {

	private static final int TAM = 512;
	
	public static void main(String[] args) throws IOException {
		int porta = Integer.parseInt("4001");
		DatagramSocket ds = new DatagramSocket(porta);
		System.out.println("Ouvindo a porta: " + porta);
		
		byte dados[] = new byte[TAM];
		DatagramPacket pacoteRecebido = new DatagramPacket(dados, dados.length);
		ds.setSoTimeout(10000);
		ds.receive(pacoteRecebido);
		
		String mensagem = new String(pacoteRecebido.getData()).trim();
		System.out.println("Mensagem recebida: " + mensagem);
		
		Scanner leitor = new Scanner(new File("frases.txt"));
		String mensagemEnviada = "";
		boolean contemMensagem = false;
		while(leitor.hasNext()){
			String linha = leitor.nextLine();
			if(linha.contains(mensagem)){
				mensagemEnviada = linha;
				System.out.println("Mensagem envida: " + mensagemEnviada);
				contemMensagem = true;
			}
		}
		if(!contemMensagem){
			mensagemEnviada = "Servidor não contém essa frase";
		}
		
		InetAddress destino;
		destino = InetAddress.getByName("localhost");
		int portaDeEnvio = Integer.parseInt("4002");
		byte dadosDeEnvio[] = mensagemEnviada.getBytes();

		DatagramPacket pacoteDeEnvio = new DatagramPacket(dadosDeEnvio, dadosDeEnvio.length, destino, portaDeEnvio);
		DatagramSocket dsDeEnvio = new DatagramSocket();
		dsDeEnvio.send(pacoteDeEnvio);
		System.out.println("Mensagem envida para: " + destino.getHostAddress() + "\n "
				+ " Porta: " + porta + " Mensagem: " + mensagem);
		ds.close();
		dsDeEnvio.close();
 	}
}
