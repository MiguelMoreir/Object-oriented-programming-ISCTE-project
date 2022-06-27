package test;

import pt.iscte.guitoo.Point;

public class Caixote extends ObjectosSokoban{

	public Caixote(Point position) {
		super(position);
		this.camada = 2;
		this.imagem = "images/Caixote.png";
		this.movivel = true;
		this.transponivel = false;
	}

}
