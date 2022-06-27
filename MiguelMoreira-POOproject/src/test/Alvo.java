package test;

import pt.iscte.guitoo.Point;

public class Alvo extends ObjectosSokoban{

	public Alvo(Point position) {
		super(position);
		this.camada = 1;
		this.imagem = "images/Alvo.png";
		this.transponivel = true;
		this.movivel = false;
	}

}
