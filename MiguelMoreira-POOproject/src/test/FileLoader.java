package test;

import java.io.File;

public interface FileLoader {
	// devolve a extens�o de ficheiro associada
	String getExtension();
	// constr�i o objeto de jogo com a informa��o do ficheiro
	Sokoban load(File f);

}
