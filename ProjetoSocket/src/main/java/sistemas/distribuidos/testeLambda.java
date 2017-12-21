package sistemas.distribuidos;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class testeLambda {

	
	public static void main(String[] args) {
		 int a = 2;
		 
		//classe anonima
		Thread t = new Thread(new Runnable() {
			
			public void run() {
				System.out.println("Hello "+a);

			}
		});
		t.start();
		
		
		Thread ab = new Thread(() -> {
			System.out.println("t2 "+a);
		});
		
//		Thread clienteThread = new Thread( Socket cliente () -> {
//			System.out.println("Nova conexao estabelecida: " + cliente.getPort());
//			while (true) {
//				try {
//					Scanner entrada = new Scanner(cliente.getInputStream());
//					if (entrada.hasNextLine()) {
//						String requisicao = entrada.nextLine();
//						if (requisicao.equals("sair")){
//							System.out.println(cliente.getPort() + ": Desconectou");
//							break;
//						} else if (carros.containsKey(requisicao)) {
//							System.out.println(carros.get(requisicao));
//						} else {
//							System.out.println("Comando invalido");
//						}
//					}
//					entrada.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		});
		
		
	}
}
