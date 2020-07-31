package tictactoe;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        char[] symbols = new char[9];
        int[][] solutions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        int[][] coordinates = {{1, 3},{2, 3},{3, 3},
        						{1, 2},{2, 2},{3, 2},
        						{1, 1},{2, 1},{3, 1}};
        char player = 'X';
       
        //String input = sc.next();
        //fillArray(input, symbols);
        printBoard(symbols);
        //stateGame(symbols, solutions);
        
        boolean flag = false;
        //System.out.println("iniciando bandera "+flag);
        while(!stateGame(symbols, solutions)) {
        	while(!flag) {
        		flag = selectCoordinate(sc,coordinates,symbols,player);
        		if(flag) {
            		printBoard(symbols);
            		player = 'X' == player ? 'O':'X';
            	}
        	}
        	flag = false;
        }
    }
    private static void fillArray(String input, char[] symbols){
        for(int i=0; i < 9; i++){
            char temp = input.charAt(i); 
            if(temp == 'X' || temp == 'O' || temp == '_'){
                symbols[i] = temp;
            }
            
        }
    }
    
    private static void printBoard(char[] symbols){
        int index = 0;
        System.out.println("---------");
        for(int i= 0; i <3; i++){
            System.out.print("| ");
             for(int j=0; j < 3; j++){
                 System.out.print(symbols[index]+" ");
                 ++index;
             }
             System.out.println("|");
        }
        System.out.println("---------");
    }
    
    private static boolean stateGame(char[] symbols, int[][] solutions){
        boolean xWins = false;
        boolean oWins = false;
        int countO = 0;
        int countX = 0;
        boolean emptyCells = false;
        for(int i= 0; i < solutions.length; i++){
            if(symbols[solutions[i][0]] == symbols[solutions[i][1]] && symbols[solutions[i][0]] == symbols[solutions[i][2]] ) {
                if(symbols[solutions[i][0]] == 'X'){
                    xWins = true;
                }
                else if(symbols[solutions[i][0]] == 'O'){
                    oWins = true;
                }
            }
        }
        for(int i : symbols){
            if(i == 'X'){
                ++countX;
            }
            else if(i == 'O'){
                ++countO;
            }
            else {
            	emptyCells = true;
            }
            
        }
        /*if((xWins && oWins) || (Math.abs(countX-countO) == 2)){
            System.out.println("Impossible");
        }*/
        if(xWins){
            System.out.println("X wins");
            return true;
        }
        else if(oWins){
            System.out.println("O wins");
            return true;
        }
        /*else if(emptyCells){
            System.out.println("Game not finished");
        }*/
        else if(!emptyCells){
            System.out.println("Draw");
            return true;
        }
        
        return false;
    }
    
    private static boolean selectCoordinate(Scanner sc, int[][] coordinates, char[] symbols, char player) {
    	System.out.print("Enter the coordinates: ");
        String input1 = sc.next();
        String input2 = sc.next();
    	int[] coordinate = new int[2];
        try {
        	coordinate[0] = Integer.parseInt(input1);
        	coordinate[1] = Integer.parseInt(input2);
        }
        catch(Exception ex) {
        	System.out.println("You should enter numbers!");
        }
        if((coordinate[0] < 1 || coordinate [0] > 3) || (coordinate[1] < 1 || coordinate[1] > 3)) {
        	System.out.println("Coordinates should be from 1 to 3!");
        }
        for(int i=0; i < coordinates.length; i++) {
        	if(coordinates[i][0] == coordinate[0] && coordinates[i][1] == coordinate[1]) {
        		if(symbols[i] != 'X' && symbols[i] != 'O') {
        			symbols[i] = player;
        			return true;
        		}
        		else{
        			System.out.println("This cell is occupied! Choose another one!");
        		}
        		
        	}
        }
        return false;
    }
}