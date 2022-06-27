package test;

import pt.iscte.guitoo.Point;

public class Veneno extends ObjectosSokoban {

	public Veneno(Point position) {
		super(position);
		this.camada = 2;
		this.imagem = "images/Veneno.png";
		this.transponivel = true;
		this.movivel = false;
		}

}
