package test;

import java.io.File;

public interface FileLoader {
	// devolve a extensão de ficheiro associada
	String getExtension();
	// constrói o objeto de jogo com a informação do ficheiro
	Sokoban load(File f);

}
