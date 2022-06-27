package test;

import pt.iscte.guitoo.Point;

public class FabricaObjectos {
	//Sokoban sokoban;
	public static ObjectosSokoban criarObj(char c, int posX, int posY) {//recebe um carater c com pos(x,y) e criar um OBJ referente ao simbolo

		Point posicao = new Point(posX,posY);
		switch(c) {
		case '#':
			return new Parede(posicao);
		case ' ':
			return new Chao(posicao);
		case 'X':
			return new Alvo(posicao);
		case 'C':
			return new Caixote(posicao);		
		case 'E':
			return new Empilhadora(posicao);
		case 'P':
			return new Bateria(posicao);
		case 'B':
			return new Buraco(posicao);
		case 'V':
			return new Veneno(posicao);
		}
		return null;
	}

}
