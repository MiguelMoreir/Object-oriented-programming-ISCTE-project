package test;

import pt.iscte.guitoo.Point;

public class Buraco extends ObjectosSokoban {

	public Buraco(Point position) {
		super(position);
		this.camada = 2;
		this.imagem = "images/Buraco.png";
		this.movivel = false;
		this.transponivel = true;
		
	}

}
