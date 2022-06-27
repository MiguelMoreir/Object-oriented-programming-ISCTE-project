package test;


import java.util.ArrayList;
import java.util.List;


import pt.iscte.guitoo.Point;


public class Sokoban{
	private List<ObjectosSokoban> listaObjectosSokobanSecundarios = new ArrayList<ObjectosSokoban>();//objectos secundarios(paredes,chao)
	private List<ObjectosSokoban> listaObjectosSokobanPrimarios = new ArrayList<ObjectosSokoban>();//objectos primarios(empilhadora,caixao,veneno,buraco,bateria)
	private List<ObjectosSokoban> listaObjectosSokoban = new ArrayList<ObjectosSokoban>();
	Empilhadora empilhadora;
	int energia;
	int altura;
	int largura;
	int numeroObjectivos;
	int numeroObjectivosCumpridos;
	boolean caiNumBuraco = false;

	public Sokoban(){

	}



	public List<ObjectosSokoban> getListaObjectosSokoban() {
		return listaObjectosSokoban;
	}




	public ObjectosSokoban imprimirLista() {
		ObjectosSokoban obj = null;
		for (int i = 0; i < listaObjectosSokoban.size(); i++) {
			obj = listaObjectosSokoban.get(i);
			System.out.println("O obj "+ i + " é: "+ obj.getName() +" na posicao:  "+ obj.getPosition());
		}	
		return obj;
	}		

	public Empilhadora getEmpilhadora() {//empilhadora na lista de objectos
		ObjectosSokoban obj = null;
		for (int i = 0; i < listaObjectosSokoban.size(); i++) {
			obj = listaObjectosSokoban.get(i);
			if(obj.getName().equals("empilhadora")){
				System.out.println("existe empilhadora na posição:  "  + obj.getPosition());
				empilhadora = (Empilhadora) obj; //o objsokoban do tipo Empilhadora = empilhadora
				return empilhadora;
			}
		}
		System.out.println("não existe empilhadora");
		return null;
	}

	public void readClick(int r, int c) {
		ObjectosSokoban proxProximObjecto = null;
		ObjectosSokoban proximObjecto = objNaNextPos(r, c);//objecto na pos(r,c)
		empilhadora = getEmpilhadora();
		if(Math.abs(empilhadora.getPosition().getX() - r) + Math.abs(empilhadora.getPosition().getY() - c) > 1) {//se o valor absoluto for maior que 1 quer dizer que o movimento não corresponde á mudança de uma casa(quadrado)
			System.out.println("Posicao Invalida");
			return;
		}

		if(empilhadora.getPosition().getX() !=  r) {//se empilhadora não estiver na mesma linha
			if (empilhadora.getPosition().getX() > r) {//se a empilhadora se mover de uma linha superior para uma inferior(r) a emp sobe
				empilhadora.setImagem("images/Empilhadora_U.png");
				proxProximObjecto = objNaNextPos(r - 1, c);//objecto na segunda posicao aseguir á empilhadora
			} else {
				empilhadora.setImagem("images/Empilhadora_D.png");//condicao contraria, logo empilhadora numa linha inferior para uma linha superior(r) a emp desce
				proxProximObjecto = objNaNextPos(r + 1, c);
			}
		}

		if(empilhadora.getPosition().getY() !=  c) {//se empilhadora não estiver na mesma coluna
			if (empilhadora.getPosition().getY() > c) {//se a empilhadora estiver numa coluna superior a (r) , a empilhadora esta a ir para a esquerda
				empilhadora.setImagem("images/Empilhadora_L.png");
				proxProximObjecto = objNaNextPos(r, c - 1);
			} else {//caso contrario esta a ir para a direita
				empilhadora.setImagem("images/Empilhadora_R.png");
				proxProximObjecto = objNaNextPos(r, c + 1);
			}
		}
		proximoNivel(proxProximObjecto, proximObjecto);
		verificaColisoes(r,c,proxProximObjecto,proximObjecto);
		buracoCaixoteInterecao(proxProximObjecto, proximObjecto);
		empilhadoraBuraco(proximObjecto);
		energiaGanha(proximObjecto);
		energiaVeneno(proximObjecto);
		System.out.println(empilhadora.getPosition() + "pos atual");
		if(proximObjecto != null) {
			System.out.println("nextpos é:  " + proximObjecto.getName());
		}
		if(proxProximObjecto != null) {
			System.out.println("nextNextpos é:  " + proxProximObjecto.getName());
		}
	}


	private void verificaColisoes(int r, int c, ObjectosSokoban proxProximObjecto, ObjectosSokoban proximObjecto) {//verificas se a empilhadora se pode mover, e se sim faze lo
		if(proximObjecto != null && proximObjecto.isTransponivel()) {//se o proximo objecto na pos(r,c) aseguir a empilhadora existir e for transponivel 
			empilhadora.setPosition(new Point(r,c));	//a empilhadora move se para (r,c)		
			energiaPerdida();
			return;	//sai-o do metodo
		}
		if(proximObjecto != null && proximObjecto.isMovivel()) {// se o proximo objecto na pos(r,c) aseguir existir e for movivel
			if(proxProximObjecto != null && proxProximObjecto.isTransponivel()) {//se o objecto aseguir ao proxObjecto for transponivel
				empilhadora.setPosition(new Point(r,c));//a empilhadora move se para a pos(r,c)
				energiaPerdida();
				proximObjecto.setPosition(proxProximObjecto.getPosition());//e o objecto na pos(r,c) move se para a pos aseguir ao proxobjecto(proxProximObjecto)
			}
		}
	}

	public ObjectosSokoban objNaNextPos(int r, int c) {// retorna obj na pos(r,c)
		ObjectosSokoban obj = null;
		for (int i = 0; i < listaObjectosSokoban.size(); i++) {
			obj = listaObjectosSokoban.get(i);
			if(obj.getPosition().getX() == r && obj.getPosition().getY() == c) {
				return obj;
			}	
		}
		return null;
	}

	public void buracoCaixoteInterecao(ObjectosSokoban proxProximObjecto, ObjectosSokoban proximObjecto) {
		if(proximObjecto.getName().equals("caixote") && proxProximObjecto.getName().equals("buraco")) {
			listaObjectosSokoban.remove(proximObjecto);
		}
	}

	public void empilhadoraBuraco(ObjectosSokoban objectoNext) {
		if(objectoNext.getName().equals("buraco")) {
			caiNumBuraco = true;
		}
	}


	public void numeroTotalObjectivos() {//numero de alvos
		for (int i = 0; i < listaObjectosSokoban.size(); i++) {
			if(listaObjectosSokoban.get(i).getName().equals("alvo")) {
				numeroObjectivos++;
			}
		}
		System.out.println("o numero total de objectivos:  " + numeroObjectivos);

	}

	public void proximoNivel(ObjectosSokoban proxProximObjecto, ObjectosSokoban proximObjecto) {//sempre que o caixote e o alvo se encontram o numeroObjectivosCumpridos é incrementado em 1

		if(proximObjecto.getName().equals("caixote") && proxProximObjecto.getName().equals("alvo")) {
			numeroObjectivosCumpridos++;
			System.out.println("o contador está a:  " + numeroObjectivosCumpridos);
		}	
	}


	public void addObjTabuleiro(ObjectosSokoban obj) {
		if(obj.getCamada() > 1) {//se um objecto tiver uma cada superiror a 1 
			addListaPrimaria(obj);//é add á lista primaria
			addListaSecundaria(new Chao(obj.getPosition()));//na mesa posicao do objecto add á primeira lista add um chao na segunda
		}
		else {
			addListaSecundaria(obj);//objectos com camada inferior a 1  add á lista secundaria
		}
	}



	public void addListaPrimaria(ObjectosSokoban obj) {
		listaObjectosSokobanPrimarios.add(obj);
	}

	public void addListaSecundaria(ObjectosSokoban obj) {
		listaObjectosSokobanSecundarios.add(obj);
	}
	
	public void energiaVeneno(ObjectosSokoban proximObjecto) {//perder energia com veneno
		if(proximObjecto.getName().equals("veneno")) {
			listaObjectosSokoban.remove(proximObjecto);
			energia = getEnergia() - 10;
			System.out.println("Veneno apanhado perdeste energia\n"+ "A energia é:   " + energia);
		}
	}
	
	
	
	public void energiaPerdida() {//perder energia 
		energia = energia - 1;
		System.out.println("A energia é:   " + energia);
	}

	public void energiaGanha(ObjectosSokoban proximObjecto) {//ganhar energia pilha
		if(proximObjecto.getName().equals("bateria")) {
			listaObjectosSokoban.remove(proximObjecto);		
			energia =getEnergia() + 10;
			System.out.println("A energia é:   " + energia);
			}
		}


	public int getEnergia() {
		return energia;
	}
 
	
	public boolean isCaiNumBuraco() {
		return caiNumBuraco;
	}



	public int getAltura() {
		return altura;
	}



	public int getLargura() {
		return largura;
	}



	public void setEnergia(int energia) {
		this.energia = energia;
	}



	public void setAltura(int altura) {
		this.altura = altura;
	}



	public void setLargura(int largura) {
		this.largura = largura;
	}

	public void addLista() {//add a lista de objectos primeiro os primarios depois os secundarios
		listaObjectosSokoban.addAll(listaObjectosSokobanPrimarios);
		listaObjectosSokoban.addAll(listaObjectosSokobanSecundarios);

	}



	public boolean isNivelTerminado() {//se o numero de objectivos  for igual aos cumpridos o nivelterminado é true
		return numeroObjectivos == numeroObjectivosCumpridos;

	}


}

