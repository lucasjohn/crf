package crf.crf.helper;

public class Commons {

	/*
	 * User Help
	 */
	public static void printHelp(){
		System.out.println("#Accepted Commands");
		System.out.println("#'count *' - escreve no console a contagem total de registros importados");
		System.out.println("#'count distinct [property]' - escreve no console o total de valores distintos da propriedade (coluna)");
		System.out.println("#'filter [property] [value]' -  escreve no console a linha de cabeçalho e todas as linhas em que a propriedade enviada possua o valor enviado");
		System.out.println("#'quit' - sai do programa");
		System.out.println("#'-h' - lista comandos");
	}
}
