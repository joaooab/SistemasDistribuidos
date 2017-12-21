package sistemas.distribuidos;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class EnviarMensagem {
	
	private static final int TAM = 512;

	public static void main(String[] args) {
		try {
			System.out.println("Insira a mensagem para enviar pro servidor: ");
			Scanner leitor = new Scanner(System.in);
			String mensagemDeEnvio = leitor.nextLine();
			
			InetAddress destino;
			destino = InetAddress.getByName("localhost");
			int portaDeEnvio = Integer.parseInt("4001");
			byte dadosDeEnvio[] = mensagemDeEnvio.getBytes();

			DatagramPacket pacote = new DatagramPacket(dadosDeEnvio, dadosDeEnvio.length, destino, portaDeEnvio);
			DatagramSocket dsDeEnvio = new DatagramSocket();
			
			dsDeEnvio.send(pacote);
			System.out.println("Mensagem envida para: " + destino.getHostAddress() + "\n "
					+ " Porta: " + portaDeEnvio + " Mensagem: " + mensagemDeEnvio);
			
			int porta = Integer.parseInt("4002");
			DatagramSocket ds = new DatagramSocket(porta);
			System.out.println("Ouvindo a porta: " + porta);
			
			byte dados[] = new byte[TAM];
			DatagramPacket pacoteRecebido = new DatagramPacket(dados, dados.length);
			ds.setSoTimeout(10000);
			ds.receive(pacoteRecebido);
			String mensagem = new String(pacoteRecebido.getData()).trim();
			System.out.println("Mensagem recebida: " + mensagem);
			
			dsDeEnvio.close();
			ds.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
