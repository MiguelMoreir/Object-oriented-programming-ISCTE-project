package test;

import pt.iscte.guitoo.Point;

public class Bateria extends ObjectosSokoban {

	public Bateria(Point position) {
		super(position);
		this.camada = 2;
		this.imagem = "images/Bateria.png";
		this.movivel = false;
		this.transponivel = true;
	}

}
