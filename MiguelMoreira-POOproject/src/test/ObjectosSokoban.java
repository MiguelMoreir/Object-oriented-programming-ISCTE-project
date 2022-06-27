package test;

import pt.iscte.guitoo.Point;

public class ObjectosSokoban {
	private Point position;
	protected String imagem;
	protected boolean transponivel;
	protected boolean movivel;
	protected int camada;
	

	public ObjectosSokoban(Point position) {
		this.position = position;
	}


	public String getName() {
		return getClass().getSimpleName().toLowerCase();
	}
	
		
	public int getCamada() {
		return camada;
	}


	public Point getPosition() {
		return position;
	}


	public void setPosition(Point position) {
		this.position = position;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public boolean isTransponivel() {
		return transponivel;
	}

	public boolean isMovivel() {
		return movivel;
	}


	
}
