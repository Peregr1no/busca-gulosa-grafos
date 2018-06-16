package pacotinho;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class PilhaDahora {
	public class gambiDaPilha{//NUNCA FAÇAM ISSO CRIANÇAS
			public gambiDaPilha(int number) {
			this.numero = number;
		}		
		public int numero;
	}

	public Stack<gambiDaPilha> empilha = new Stack<>(); //declarando uma variavel do tipo pilha 
	
	int[][] mapa = {{0,1,1,1,0,0,0},
            		{1,0,0,0,1,0,0},
            		{1,0,0,1,1,1,0},
            		{1,0,1,0,0,1,0},
            		{0,1,1,0,0,0,1},
            		{0,0,1,1,0,0,1},
            		{0,0,0,0,1,1,0}};

	String[] cidades = { "A", "B", "C", "D", "E", "F", "G" };
	
	ArrayList<Integer> lista_exclusao = new ArrayList<>();
	
	int inicio;
    int fim;

	private Scanner scan;
    
    public void menorCusto(){
        do{
            int menorAresta = 0;   
            // For para pegar a menor aresta
            for(int i = 0; i < 7; i++){
                if (mapa[empilha.lastElement().numero][i] != 0){
                    if(menorAresta == 0 || mapa[empilha.lastElement().numero][menorAresta] >= mapa[empilha.lastElement().numero][i]){
                        if (verificaJaAdicionado(i) == 0){
                            menorAresta = i;
                        }
                    }
                }
            }
            // If para verificar se o nodo atual tem caminho para o fim
            if(mapa[empilha.lastElement().numero][fim] != 0){
                menorAresta = fim;
            }
            
            gambiDaPilha menorArestaObject = new gambiDaPilha(menorAresta);

            empilha.push(menorArestaObject);
            lista_exclusao.add(menorAresta);

        } while (empilha.lastElement().numero != fim);
    }
    
    public int verificaJaAdicionado(int vlr){
         for (int i = 0; i < lista_exclusao.size(); i++){
        	 if(vlr == lista_exclusao.get(i)){
        		 return 1;
            }
        }
        return 0;
    }
    
    public void imprimirMapa(){
        System.out.println("   A   B   C   D   E   F   G");
        for (int i = 0; i < 7; i++){
        	System.out.print(cidades[i] + "  ");
            for (int j = 0; j < 7; j++){
                if (mapa[i][j] >= 10){
                	System.out.print(mapa[i][j] + "  ");
                }else{
                	System.out.print(mapa[i][j] + "   ");
                }
            }
            System.out.println("\n");
        }
    }// Fecha imprimir mapa
    
    public int converter(String opcao)
    {
        boolean doInf = true;

        do
        {

            switch (opcao)
            {

                case "A":
                case "a":

                    return 0;
                    
                case "B":
                case "b":

                    return 1;

                case "C":
                case "c":

                    return 2;

                case "D":
                case "d":

                    return 3;
                    
                case "E":
                case "e":

                    return 4;

                case "F":
                case "f":

                    return 5;

                case "G":
                case "g":

                    return 6;

                default:

                	System.out.println("Caminho invalido, digite um valido");
                	Scanner scaning = new Scanner(System.in);
                	opcao = scaning.next();
                	break;

            }
        } while (doInf == true);
        	return -1;
    }
    
    public void imprimirResultado(){
        for(int i = 0; i < empilha.size(); i++){
            System.out.print(cidades[empilha.elementAt(i).numero]);
        }
    }
    
    public void inicia(){

        System.out.println("MAPA:");
        imprimirMapa();
        System.out.println("A partir de que nodo gostaria de sair?");
        scan = new Scanner(System.in);
        inicio = converter(scan.next());
        gambiDaPilha gambi = new gambiDaPilha(inicio);
        empilha.push(gambi);
        System.out.println("Em que nodo gostaria de chegar?");
        fim = converter(scan.next());
        lista_exclusao.add(inicio);
        menorCusto();
        imprimirResultado();
    }
}
