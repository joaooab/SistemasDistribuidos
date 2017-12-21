package sistemas.distribuidos;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class Servidor {

	private static final HashMap<String, String> carros = new HashMap<>();

	public static void main(String[] args){
		try {
			ServerSocket servidor = new ServerSocket(5000);
			inicializaMapaDeCarros();
			System.out.println("Servidor Iniciado");
			while(true){
				
				Socket cliente = servidor.accept();
				new Thread(new AceitaCliente(cliente, carros)).start();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void inicializaMapaDeCarros() {
		carros.put("audi", "Audi � As quatro argolas unidas representam as marcas alem�s que formaram a Auto Union, fundada em 1947. S�o elas: Horch, Audi, Wanderer e DKW. No dia 1� de janeiro de 1985, a Auto Union passou a se chamar Audi AG, com sede empresarial em Nekarsulm, na Alemanha");
		carros.put("alfa", "Alfa Romeo � O s�mbolo � composto pela bandeira com a cruz vermelha (bras�o da cidade de Mil�o) e pela serpente devorando um homem (s�mbolo da fam�lia real milanesa). O nome do fabricante italiano, fundado em 1910, � a combina��o da sigla A.L.F.A (Anonima Lombarda Fabbrica Automobili) com o sobrenome do engenheiro Nicola Romeo, fundador da marca");
		carros.put("bmw", "Bayerische Motoren Werke -> BMW � Representa uma h�lice de avi�o, nas cores azuis e pretas. Foi criada depois que os irm�os Karl Rath e Gustav Otto conseguiram permiss�o do governo alem�o para produzir motores de avi�o, em 1917. O primeiro carro a ter o s�mbolo da marca alem� foi o modelo Dixi 3/15, de 1928. BMW � a abreviatura de �F�brica de Motores da Bav�ria� (Bayerische Motoren Werk).");
		carros.put("cadillac", "Cadillac � Marca famosa da General Motors, o seu emblema � derivado do bras�o da fam�lia de Sir Antoine de la Mothe Cadillac, o fundador da empresa. Desperta muita admira��o no mundo todo, com sua grinalda de plumas � um verdadeiro cl�ssico!");
		carros.put("chevrolet", "Chevrolet � Diz a lenda que o logotipo em forma de gravata borboleta foi baseado na ilustra��o do papel de parede de um hotel em Paris onde um dos fundadores da marca, William Durant, teria se hospedado, em 1908. Durant guardou a amostra na carteira para us�-la como s�mbolo da marca de autom�vel que fundou em parceria com o piloto Louis Chevrolet");
		carros.put("chrysler", "Chrysler � A antiga estrela de cinco pontas, formada a partir de um pent�gono com cinco tri�ngulos, representa a precis�o da engenharia desta montadora norte-americana. A logo atual � um escudo com asas, que j� havia sido foi adotado entre as d�cadas de 30 e 50");
		carros.put("citroen", "Citro�n � Os dois �V� invertidos, conhecidos na Fran�a como �Deux Chevron�, simbolizam a engrenagem bi-helicoidal criada pelo engenheiro Andre Citro�n, fundador desta tradicional marca francesa");
		carros.put("ferrari", "Ferrari � O cavalo preto empinado sobre o fundo amarelo era usado no avi�o de Francesco Barraca, piloto de ca�a italiano morto na Primeira Guerra Mundial. A pedido da m�e de Barraca, o comendador Enzo Ferrari passou a adotar o emblema em seus carros a partir de 1923.");
		carros.put("fiat", "F�brica Italiana de Autom�veis de Turim -> Fiat � A sigla em letras brancas sobre fundo azul significa F�brica Italiana de Autom�veis de Turim. Por algum tempo as 4 letras foram substitu�das por 4 barras inclinadas (brancas ou cromadas) mas, atualmente, o s�mbolo remonta aos primeiros ve�culos fabricados pela Fiat.");
		carros.put("ford", "Ford � O s�mbolo oval com a assinatura de Henry Ford permanece quase inalterado desde a funda��o da empresa, em 1903. Hoje ele inspira o desenho das grades dos carros da marca");
		carros.put("lamborghini", "Lamborghini � O touro que aparece no s�mbolo dos esportivos italianos � uma homenagem do fundador da marca, Ferruccio Lamborghini, �s lutas de touro, pelas quais era fan�tico. Tanto que alguns carros da marca (Diablo e Murci�lago) t�m nomes de touros famosos");
		carros.put("maserati", "Maserati � O logotipo da marca italiana representa o tridente de Netuno, s�mbolo da cidade de Bolonha. A f�brica foi fundada em 1919 pelos irm�os Carlo, Bindo, Alfieri, Ettore e Ernesto Maserati. Hoje, simboliza uma das mais cultuadas linhas de autom�veis esportivos de todo o mundo.");
		carros.put("mb", "Mercedes-Benz � A estrela de tr�s pontas representa a fabrica��o de motores para uso na terra, �gua e ar. Surgiu depois que Gottlieb Daimler enviou cart�o postal para sua mulher, dizendo que a estrela impressa no cart�o iria brilhar sobre sua obra.");
		carros.put("mitsubishi", "Mitsubishi � Um diamante de tr�s pontas que remete � resist�ncia e preciosidade. O s�mbolo veio do nome da marca: �Mitsu� significa tr�s em japon�s; �Bishi�, diamante.");
		carros.put("nissan", "Nissan � Originalmente possuia uma moldura azul (cor do c�u e do sucesso na cultura japonesa) e um c�rculo vermelho ao fundo (que representa a luz do sol e a sinceridade), uma refer�ncia ao prov�rbio �sinceridade leva ao sucesso�. Hoje utiliza uma vers�o estilizada, somente em tons de cinza. Nissan significa �irm�o mais velho�");
		carros.put("peugeot", "Peugeot � O le�o estilizado, que representa a �qualidade superior da marca� e homenageia a cidade de Lion (Fran�a), � usado desde 1919. Desde ent�o, o logotipo sofreu sete modifica��es.");
		carros.put("porsche", "Porsche � S�o dois bras�es sobrepostos � o da regi�o de Baden-W�rttemberg e o da cidade de Stutgartt (o cavalo empinado), sede da marca alem�. A marca adotou o s�mbolo a partir de 1949");
		carros.put("renault", "Renault - O losango parecido com um diamante foi adotado em 1925, para sugerir sofistica��o e prest�gio. Desde ent�o, teve quatro mudan�as de visual. O primeiro s�mbolo, de 1898, era dois �R�, em homenagem aos irm�os Louis e Marcel Renault, fundadores da marca francesa");
		carros.put("rr", "Rolls Royce - Os dois �R� do logotipo eram originalmente estampados em vermelho. Com a morte de seus dois fundadores, Charles Rolls (1910) e Frederick Royce (1933), as letras passaram a ser grafadas em preto, em sinal de luto.");
		carros.put("saab", "A sueca Saab come�ou a fabricar avi�es em 1938. O nome vem de Svenska Aeroplan Akteebolaget. A produ��o de autom�veis come�ou em 1959. O logotipo circular tem um animal mitol�gico com cabe�a de �guia e garras de le�o, s�mbolo da vigil�ncia. O azul de fundo � a cor da marinha.");
		carros.put("subaru", "Subaru - � a divis�o automotiva da Fuji Heavy Industries Ltd. Na l�ngua japonesa, Subaru tem o significado de �pl�iade� (conjunto de estrelas). Isso explica a constela��o adotada como logotipo da marca.");
		carros.put("vw", "Volkswagen - Um dos mais familiares s�mbolos entre as marcas de ve�culos, este c�rculo envolve um �V� e um �W�, iniciais de volks (em alem�o: povo) e wagen (vag�o, ve�culo), ou seja: carro do povo, ou popular, j� naquela �poca!");
		carros.put("volvo", "O pol�mico logotipo da marca sueca (que hoje � controlada pela Ford) � o s�mbolo da masculinidade e por esse motivo j� foi muito contestado por movimentos feministas na Europa. Esse s�mbolo era usado pelos alquimistas para representar o metal, uma alus�o que a Volvo fez � durabilidade dos seus ve�culos.");
	}
}
