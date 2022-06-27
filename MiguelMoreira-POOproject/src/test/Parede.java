package test;

import pt.iscte.guitoo.Point;

public class Parede extends ObjectosSokoban {

	public Parede(Point position) {
		super(position);
		this.imagem = "images/Parede.png";
		this.movivel = false;
		this.transponivel = false;
		this.camada = 1;
	}

}
