package test;


import java.awt.Frame;
import java.awt.Window;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import pt.iscte.guitoo.board.Board;

public class SokobanGUI {

	final Board board;
	Sokoban sokoban;
	FileLoader loader;
	int nivel;
	SokobanGUI(String title, int nivel) throws FileNotFoundException {
		this.nivel = nivel;
		sokoban = loadGame(getFicheiroNome());
		// 50 corresponde ah largura (pixels)das imagens fornecidas
		board  = new Board(title, sokoban.getAltura(), sokoban.getLargura(), 50);
		// dada a coordenada (linha, coluna) devolve nome de imagem a mostrar
		board.setIconProvider((r,c) -> lerListaObjectos(r, c)); 
		sokoban.numeroTotalObjectivos();
		board.addMouseListener((r,c) -> mover(r,c));
	}





	private Sokoban loadGame(String string) {
		String extensaoFile = string.split("\\.")[1];//retorna a segunda parte da string apartir "."
		if(extensaoFile.equals("sokas")) {//se for sokas
			 loader = new LoaderSokas();//FileLoader referente á extensao sokas
			return loader.load(new File(string));//retorna o sokaban referent ao ficheiro
		}
		if(extensaoFile.equals("sok")) {//se for sok
			 loader = new LoaderSok();//FileLoader referente á extensao sok
			return loader.load(new File(string));//retorna o sokaban referent ao ficheiro
		}
		return null;
	}





	private void mover(int r, int c) {
		File folder = new File("../MiguelMoreira-POOproject/niveis");
		if(sokoban.getEnergia() > 0) {//se tiver energia maior que 0
			sokoban.readClick(r, c);//pode se mexer 
			if(nivel != todosNiveis(folder).size()-1) {
				System.out.println("nivel aceite");
				if(sokoban.isNivelTerminado()) {//se for true
					nivel++;
					passarNivel();
				}
			}else {
				System.out.println("ultimo nivel");
				if(sokoban.isNivelTerminado()) {
					board.showMessage("Ganhaste o jogo");
				}
			}
		}
		else {
			board.showMessage("NAO EXISTE MAIS ENERGIA \n" + "PERDESTE O JOGO");
		}
		if(sokoban.isCaiNumBuraco() == true) {//se a empelhadora cair num buraco
			board.showMessage("PERDESTE O JOGO");
		}

	}

	private void passarNivel() {
		try {
			SokobanGUI gui = new SokobanGUI("novo nivel",nivel);//novo sokoban GUI 
			gui.open();//abrir gui
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<String> todosNiveis(final File folder) {// colocar todos os ficheiros(niveis) da pasta niveis numa lista de strings
		List<String> fileNiveis = new ArrayList<String>();

		for (File file : folder.listFiles()) {
			fileNiveis.add(file.getName());
		}
		return fileNiveis;
	}

	public String getFicheiroNome() {//retorna o nome do ficheiro referente ao nivel
		File folder = new File("../MiguelMoreira-POOproject/niveis");
		todosNiveis(folder);
		return "niveis/" + todosNiveis(folder).get(nivel);
	}



	private String lerListaObjectos(int r, int c) {//retorna uma string para a pos(r,c), neste caso a img do objecto na pos
		for (ObjectosSokoban obj : sokoban.getListaObjectosSokoban()) {
			if(obj.getPosition().getX() == r && obj.getPosition().getY() == c) {
				return obj.getImagem();
			}
		}
		return "images/Chao.png";
	}

	void open() {
		// abre a janela
		board.open();
	}

	public static void main(String[] args) throws FileNotFoundException {
		SokobanGUI gui = new SokobanGUI("Project Sokoban",0);
		gui.open(); 
		
	}
}
