package test;

import pt.iscte.guitoo.Point;

public class Chao extends ObjectosSokoban {

	public Chao(Point position) {
		super(position);
		this.transponivel = true;
		this.movivel = false;
		this.imagem = "images/Chao.png";
		this.camada = 0;
	}

	
}
