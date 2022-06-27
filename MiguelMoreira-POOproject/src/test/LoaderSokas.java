package test;

import java.io.File;
import java.util.Scanner;

public class LoaderSokas implements FileLoader {


	@Override
	public String getExtension() {//extensao referente a ficheiros "sokas"
		return "sokas";
	}

	@Override
	public Sokoban load(File f) {
		Sokoban sokoban = new Sokoban();
		Scanner scanner = null;
		try {
			scanner = new Scanner(f);
			sokoban.setEnergia(scanner.nextInt());//primeira linha referente á energia
			sokoban.setAltura(scanner.nextInt());//segunda linha referente á altura
			sokoban.setLargura(scanner.nextInt());//terceira linha referente á largura
			scanner.nextLine();
			char elementoTabuleiro = 0;
			int posicaoX = 0;
			int posicaoY = 0;
			while(scanner.hasNext()) {
				String linha = scanner.nextLine();//na linha
				elementoTabuleiro = linha.charAt(0);//index(0) é referente ao caracter
				posicaoX = Character.getNumericValue(linha.charAt(2));//index(2) é referente á posicao X
				posicaoY = Character.getNumericValue(linha.charAt(4));//index(4) é referente á posicao y
				ObjectosSokoban obj = FabricaObjectos.criarObj(elementoTabuleiro, posicaoX, posicaoY);
				sokoban.addObjTabuleiro(obj);//add a lista correspondente ao objecto com o tipo de camada
			}

		}
		catch(Exception e) {
			e.printStackTrace();
		}


		sokoban.addLista();//add tudo á lista principal

		scanner.close();
		return sokoban;
	}

}


