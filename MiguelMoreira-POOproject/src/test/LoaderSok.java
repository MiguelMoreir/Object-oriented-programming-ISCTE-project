package test;

import java.io.File;
import java.util.Scanner;

public class LoaderSok implements FileLoader {

	@Override
	public String getExtension() {//extensao do ficheiro referente a ficheiros .sok 
		return "sok";
	}

	@Override
	public Sokoban load(File f) {
		Sokoban sokoban = new Sokoban();
		Scanner scanner = null;
		try {
			scanner = new Scanner(f);
			sokoban.setEnergia(scanner.nextInt());//primeira linha do ficheiro nº energia
			sokoban.setAltura(scanner.nextInt());//segunda linha do ficheiro nº altura
			sokoban.setLargura(scanner.nextInt());//terceira linha do ficheiro nº largura
			scanner.nextLine();
			int posicaoX = 0;
			while(scanner.hasNext()) {
				String linha = scanner.nextLine();
				for(int posicaoY = 0; posicaoY != linha.length(); posicaoY++) {//iterar por cada coluna de cada linha
					ObjectosSokoban obj = FabricaObjectos.criarObj(linha.charAt(posicaoY), posicaoX, posicaoY);//char no index da linha(posy) com a pos(x,y)
					sokoban.addObjTabuleiro(obj);
				}
				posicaoX ++;// depois do loop terminar de ler todas as colunas da linha, passa para a proxima
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}


		sokoban.addLista();

		scanner.close();
		return sokoban;
	}

}
