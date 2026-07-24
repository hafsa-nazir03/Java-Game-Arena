import java.util.*;
import java.io.*;

public class GameArena {
    static String countryOriginal = "";
     static String fruitOriginal = "";
     static String cityOriginal = "";
     static String mixedOriginal = " ";
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
       System.out.println("                 ------Welcome to Gaming Arena-------");
        boolean loopVar = false;
        int userChoice = 0;
        String tenWords[] = {"Hello","World","Runway","Computer","Science","Programming","Cookie","Finland","Extraordinary","Semester"};
        int playerScore = 0;
        String[] words = new String[10];
        String playerName = " ";
        boolean playAgain;
         int size = 0;
        while(true){
        System.out.println("Enter 1 to play hangman, 2 to play memory match, 3 to play words scramble, 4 to play tic-tac-toe, and 0 to exit");
    do{
        try{
     userChoice = inp.nextInt();
     inp.nextLine();
     loopVar = false;
     }catch(InputMismatchException e){
           System.out.println(e);
           System.out.println("Enter only integers");
           inp.nextLine();
       }
        }while(loopVar);
        
        if(userChoice==0){
            System.out.println("Byee");
            break;
            }else if(userChoice<0 || userChoice>4){
                System.out.println("kindly select from given menu");
                continue;
           }
        
            if(userChoice==1){
           System.out.println("                -----Welcome to Hangman Game-----");
             try{
       System.out.println("Enter your name: ");
       playerName = inp.nextLine();
        
        playerScore = 0;
        FileOutputStream fos = new FileOutputStream("input.txt");
        PrintWriter pw = new PrintWriter(fos);
        for(int i=0; i<tenWords.length; i++){
        pw.println(tenWords[i]);
        }
        pw.close();
        FileInputStream fis = new FileInputStream("input.txt");
        Scanner sc = new Scanner(fis);
        int count = 0;
        while(sc.hasNext()){
            String word = sc.nextLine();
            words[count] = word;
            count++;
            
        } 
         sc.close();

    }catch(FileNotFoundException e){
        System.out.println(e);
        System.out.println("File not found");
    }catch(Exception e){
        System.out.println(e);
        System.out.println("An error occured");
    }
     do{
    int random = (int) (Math.random() * words.length);
    String selected = words[random].toLowerCase();

    System.out.print("Mystery Word: ");
    for(int i=0; i<selected.length(); i++){
    System.out.print("_ ");
    }
    System.out.println();
    System.out.println("Letters guessed: "+ "[]");
    System.out.println("Guesses Remaining: " + "6");

    char currentWord[] = new char[selected.length()];
    for(int i=0; i<selected.length(); i++){
     currentWord[i] = '_';
    }

   int guessedCount = 0;
    char guessedLetters[] = new char[26];
    int totalGuesses = 6;

    boolean done = true;
    boolean correctWordGuessed = false;

    while(totalGuesses > 0){
     done = true;

     boolean correct = false;
     boolean alreadyGuessed = false;

               
     System.out.print("Guess a letter: ");
    char guess = inp.next().toLowerCase().charAt(0);
               
                 
     if(guess>='a' && guess<='z'){

     }else{
     System.out.println("Enter a valid choice");
      guess = inp.next().toLowerCase().charAt(0);
      }
                
     System.out.println();
                

    for(int i=0; i<guessedCount; i++){
    if(guessedLetters[i] == guess){
     alreadyGuessed = true;
     break;
     }
      }

     if(alreadyGuessed){
      System.out.println("You already guessed this letter!\n");
      continue;
      }

     guessedLetters[guessedCount] = guess;
      guessedCount++;

                
    for(int i=0; i<selected.length(); i++){
      if(guess == selected.charAt(i)){
       currentWord[i] = guess;
       correct = true;
         }
       }

               
      if(correct){
      playerScore += 20; 
      }

      if(!correct){
      totalGuesses--;
      }

      System.out.print("Mystery Word: ");
      for(char c : currentWord){
      System.out.print(c + "");
      }
     System.out.println();

     System.out.print("Letters Guessed: [");
     for(int i=0; i<guessedCount; i++){
     System.out.print("'" + guessedLetters[i] + "'");
     if(i<guessedCount-1)
     System.out.print(",");
      }
     System.out.println("]");

     System.out.println("Guesses Remaining: " + totalGuesses);
      printHangman(totalGuesses);

     for(char c : currentWord){
      if(c == '_'){
     done = false;
     break;
     }
    }

    if(done){
    System.out.println("You won! The word was " + selected);
    correctWordGuessed = true;
    break;
     }
    }

           
   if(!correctWordGuessed){
   System.out.println("You lose! The word was " + selected);
   playerScore = 0;  
   }

      System.out.println("Do you want to try again or quit(enter 1 for try again and 2 to quit)");
      int choice = inp.nextInt();

       if(choice == 1){
       playAgain = true;
       }else if(choice == 2){
       playAgain = false;
      System.out.println("Name  " + playerName);
      System.out.println("Score " + playerScore);
      try{
      FileOutputStream cin = new FileOutputStream("record.txt",true);
      PrintWriter wp = new PrintWriter(cin);
      wp.println(playerName + " : " + playerScore);
      wp.close();
                    
     }catch(Exception e){
     System.out.println("Error while writing file");
    }
    }else{
    System.out.println("Enter a valid choice");
    choice = inp.nextInt();
    playAgain = false;
    }

    } while(playAgain);
   }
       
       if(userChoice==2){
           System.out.println("                -----Welcome to Memory Match Game!!!-----");
           boolean loopVar1 = true;
           do{
     try{
     System.out.println("Enter size(even) for grid"); 
     size = inp.nextInt();
     if(size % 2!=0){      
     System.out.println("Enter even grids plz");
     
     continue;
     }
     loopVar1 = false;
     }catch(InputMismatchException inpMis){
     System.out.println(inpMis);
     System.out.println("You should enter integer");
     inp.nextLine();
     }

   }while(loopVar1);
   
    String [][] array = new String[size][size]; 
     int totalCells = size * size;  
     int numberPairs = totalCells/2; 

     String []shapes = generateEmojis(numberPairs); 
     String [] temp = new String[totalCells];  
 
    int Index = 0;   
    for(int i=0; i<numberPairs; i++){
    temp[Index] = shapes[i];
    temp[Index + 1] = shapes[i];
    Index = Index + 2;
}  
       
  
      for(int i=0; i<temp.length;i++){ 
      int rand = (int) (Math.random() * temp.length);
      String shuffledShapes = temp[i];
      temp[i] = temp[rand];
      temp[rand] = shuffledShapes;
      }

    int index = 0; 
    for(int i=0; i<array.length; i++){
       for(int j=0; j<array[i].length; j++){
         
         array[i][j] = temp[index];
         index++;
       }
   }
  int number = 1;  
     for(int i=0; i<array.length; i++){
       for(int j=0; j<array[i].length; j++){
         if(number<=totalCells){
         System.out.printf("%3s",number);
          number++; 
        }
      
       }
      
        System.out.println();

    }
     
      boolean [][] revealed = new boolean[array.length][array[0].length]; 
      boolean allTilesRevealed = false;  
      int score = 0;  
      

      while(!allTilesRevealed){ 
      int number1= 0;  
      int row1 = 0;     
      int column1 = 0;
      int number2 = 0;  
      int row2 = 0;
      int column2 = 0; 
     boolean loopVar2 = true;  
     String actualValue1 = " "; 
    String actualValue2 = " "; 

     do{   
        try{
      System.out.println("Enter a number to flip one tile");  
      number1 = inp.nextInt(); 

      row1 = (number1-1)/size;       
      column1 = (number1-1)%size;

      System.out.println("Enter other number to flip tile "); 
      number2 = inp.nextInt();
      row2 = (number2-1)/size;
      column2 = (number2-1)%size; 

     actualValue1 = array[row1][column1]; 
     actualValue2 = array[row2][column2]; 
    temporaryPrintGrid(row1,column1,row2,column2,array,revealed);  
    

     loopVar2 = false;  

   }catch(InputMismatchException e){
     System.out.println(e);
     System.out.println("You should enter an integer value");
     inp.nextLine();
    
     }catch(IndexOutOfBoundsException e){
      System.out.println(e);
     System.out.println("You should enter value within range of grid");
    
   }
  
  }while(loopVar2);
  if(row1==row2 && column1==column2){  
     System.out.println("You cannot select the same tile twice");
     continue;
     }

     if(revealed[row1][column1] || revealed [row2][column2]){  
     System.out.println("You have already guesssed this tile choose another one");
     continue;
     } 

     if(actualValue1.equals(actualValue2)){  
     revealed[row1][column1]=true;  
     revealed[row2][column2]=true;
     score = score + 20;
     System.out.println("You guessed it correctly the score is " + score);  
    
     }else{
     score = score - 5;  
        if(score<0){
         score=0;
         }
     System.out.println("Oops! wrong guess your score is " + score);
      
     }
      
    printGrid(array,revealed); 
      allTilesRevealed = true;  
      for(int i =0; i<revealed.length; i++){
         for(int j=0; j<revealed[i].length; j++){
          if(!revealed[i][j]){
           allTilesRevealed=false;
           break;
         }

      }

    }
   
   }
    
   System.out.println("Congratulations! All tiles revealed, Final score is " + score);
   
    }
       
    if(userChoice==3){
     boolean loopVariable = true;
     String guess = "";
        int totalScore = 0;
        System.out.println("                -----WELCOME TO WORDS SCRAMBLE GAME-----");
       do{ 
        try {
        System.out.println("Press 1 for country names, 2 for fruit names, 3 for city names, 4 for mixed names or 0 to exit");
        int choice = inp.nextInt();
        inp.nextLine();

       if(choice==0){
        break;

       }if(choice!=1 && choice!=2 && choice!=3 && choice!=4 && choice!=0){
        System.out.println("Enter valid number");
        
        continue;
        
        }
        System.out.println("Enter e for easier mode, m for medium mode, and h for hard mode"); 
        char difficultyLevel = inp.next().charAt(0);
        inp.nextLine();
        while(true){
        if(difficultyLevel!='e' && difficultyLevel!='m' && difficultyLevel!='h'){
        System.out.println("Enter valid input");
        difficultyLevel = inp.next().charAt(0);
        continue;
        }
       break;
       }

       if(choice==1){

        for(int i=0; i<5; i++){
        int count = i+1;
        System.out.println("Round "+count);
        long startTime = System.currentTimeMillis();
        String word = countryWord("country.txt");
        System.out.println(" Scrambled Word: " + word);
        guess = time(difficultyLevel,startTime);
        if(!guess.equals("TimeOut")){
        int roundScore = score(countryOriginal,guess);
        totalScore = totalScore + roundScore;
        }
        }
        System.out.println("The total score is "+ totalScore);
        System.out.println("Thanks for playing");

        }else if(choice==2){

        for(int i=0; i<5; i++){
        int count = i+1;
        System.out.println("Round "+count);
        long startTime = System.currentTimeMillis();
        String word = fruitWord("fruit.txt");
        System.out.println(" Scrambled Word: " + word);
        guess = time(difficultyLevel,startTime);
        if(!guess.equals("TimeOut")){
        int roundScore = score(fruitOriginal,guess);
        totalScore = totalScore + roundScore;
        }
        }
        System.out.println("The total score is "+ totalScore);
        System.out.println("Thanks for playing");

        }else if(choice==3){

        for(int i=0; i<5; i++){
        int count = i+1;
        System.out.println("Round "+count);
        long startTime = System.currentTimeMillis();
        String word = cityWord("city.txt");
        System.out.println(" Scrambled Word: " + word);
        guess = time(difficultyLevel,startTime);
        if(!guess.equals("TimeOut")){

        int roundScore = score(cityOriginal,guess);
        totalScore = totalScore + roundScore;
        }
        }
        System.out.println("The total score is "+ totalScore);
        System.out.println("Thanks for playing");

        }else if(choice==4){

        for(int i=0; i<5; i++){
        int count = i+1;
        System.out.println("Round "+count);
        long startTime = System.currentTimeMillis();
        String word = mixedWord("country.txt","fruit.txt","city.txt");
        System.out.println(" Scrambled Word: " + word);
        guess = time(difficultyLevel,startTime);
        if(!guess.equals("TimeOut")){

        int roundScore = score(mixedOriginal,guess);
        totalScore = totalScore + roundScore;
        }
        }
        System.out.println("The total score is "+ totalScore);
        System.out.println("Thanks for playing");
        
        }
        FileOutputStream fos = new FileOutputStream("country.txt");
        PrintWriter pw = new PrintWriter(fos);
String countryNames[]={"PAKISTAN","BANGLADESH","CHINA","SRILANKA","SUDAN","ARABIA","NEPAL","INDONESIA","BAHRAIN","PALESTINE","MALAYSIA","JORDAN","IRAQ","TUNISIA","YEMEN","LIBYA"};
        
       for(int i=0; i<countryNames.length; i++){
       pw.println(countryNames[i]);
       }
       pw.close();

       FileOutputStream write = new FileOutputStream("fruit.txt");
        PrintWriter writer = new PrintWriter(write);
String fruitNames[]={"APPLE","BANANA","ORANGE","STRAWBERRY","PEAR","CHERRY","MANGO","PAPAYA","WATERMELON","LOQUAT","GRAPES","PLUM","PEACH","BLUEBERRY","GUAVA","KIWI"};
        
       for(int i=0; i<fruitNames.length; i++){
       writer.println(fruitNames[i]);
    
       }
       writer.close();
       FileOutputStream cin = new FileOutputStream("city.txt");
        PrintWriter wp = new PrintWriter(cin);
        String cityNames[] = {"RAWALPINDI","ISLAMABAD","KARACHI","SARGODHA","BAHAWALPUR","PESHAWAR","QUETTA","MULTAN","SUKKHUR","LAHORE"};
        
       for(int i=0; i<cityNames.length; i++){
       wp.println(cityNames[i]);
    
       }
       wp.close();
       loopVariable = false;

      }catch(InputMismatchException e){
      System.out.println("Exception Input Mismatch");
      System.out.println("Enter an integer");
      inp.nextLine();
      
      }catch(FileNotFoundException e){
      System.out.println("Exception file not found");
      inp.nextLine();

    }

      }while(loopVariable);

  } 
  
  if(userChoice==4){
     System.out.println("                -----Welcome to Tic-Tack-Toe Game!!!-----");
      boolean loopVariable = true;
       String[][] array = new String[3][3];
       int num = 1;
       boolean mainMenu = true;
      do{
      try{
       while(mainMenu){
       System.out.println("Press 1 for multiplayer mode, press 2 for single player mode, press anyother key to exit");
       char choice = inp.next().charAt(0);
       inp.nextLine();
      
       if(choice!='1' && choice!='2'){
       System.out.println("Bye");
        mainMenu = false;
       loopVariable = false;
       break;
       }

       
       else if(choice=='1'){
       mainMenu = false;
       System.out.println("Player 01 kindly enter your name");
       String player1Name = inp.nextLine();
       String symbol1;
       String symbol2;

       while(true){
    
       System.out.println(player1Name + ",kindly choose a symbol from following: X or O ");
       symbol1 = inp.nextLine().toUpperCase();
         
       if(symbol1.equals("X") || symbol1.equals("O")){
             break;
       }else{
       System.out.println("Enter a valid input");
       
       }
      
     }    
         
       
       System.out.println("Player 02 kindly enter your name");
       String player2Name = inp.nextLine();
     
       if(symbol1.equals("X")){
       System.out.println("The symbol assigned to " + player2Name + " is O");
       symbol2 = "O";

       }else{
       System.out.println("The symbol assigned to "  + player2Name + " is X");
       symbol2 = "X";
       }
           
  
   String currentPlayer = " ";
    boolean restart = true;
    int tossChoice = 0; 
    while(restart){
    int toss = (int) (Math.random() * 2);
 while(true){
  try{  
    
    System.out.println(player2Name + " kindly enter 0 for heads or 1 for tails");
    tossChoice = inp.nextInt();
    inp.nextLine();

    if(tossChoice==0 || tossChoice==1){
    break;
    
    }else{
    System.out.println("Enter valid input");
    }
  
    } catch (InputMismatchException e) {
    System.out.println("Enter integer 0 or 1");
    inp.nextLine();
       }
     }

    if(tossChoice==toss){
    System.out.println(player2Name + " wins the toss, so the first move will be done by " + player2Name);
    currentPlayer = player2Name;
  
    

    }else{
    System.out.println(player1Name + " wins the toss,so the first move will be done by " + player1Name);
    currentPlayer = player1Name;
    }
    

    
   
     loopVariable = false;

     
     
   int number = 1;
   int totalCells = 3 * 3;
   for(int i=0; i<array.length; i++){
      for(int j=0; j<array[i].length; j++){
       if(number<=totalCells){
       System.out.printf("%3s",number);
       number++;
      }
    }
   System.out.println();
  }

   
   boolean isGameOver = false;
   boolean isPlayer1Turn = true;
   String currentSymbol = " ";
   
   while(!isGameOver){

 try{
   
   int row1 = 0; 
   int row2 = 0;
   int column1 = 0;
   int column2 = 0;


while(true){
  try{ 
   if(isPlayer1Turn){
   System.out.println("winner of toss,kindly tell a number where you want to place your symbol");
   int num1 = inp.nextInt();
  
   row1 = (num1-1)/3; 
   column1 = (num1-1)%3; 

   if(array[row1][column1]!=null){ 
  System.out.println("This cell is already taken, so select another one");
  continue;
  }
   if(currentPlayer.equals(player1Name)){
   currentSymbol = symbol1; 
   }else{
   currentSymbol = symbol2;
   }

   array[row1][column1] = currentSymbol;
   printGrid(array);
   if(winCheck(symbol1,symbol2,array,player1Name,player2Name)){
   isGameOver = true;

   
   }
    
   
   isPlayer1Turn = false;

  }else{
   System.out.println("other player,kindly select a number where you want to place your symbol"); 
   int num2 = inp.nextInt();
  
   row2 = (num2-1)/3; 
   column2 = (num2-1)%3; 

   if(array[row2][column2]!=null){ 
  System.out.println("This cell is already taken, so select another one");
  continue;
  }

   if(currentPlayer.equals(player1Name)){
   currentSymbol = symbol2;
   }else{
   currentSymbol = symbol1;
   
   }

   array[row2][column2] = currentSymbol;
   printGrid(array);
   if(winCheck(symbol1,symbol2,array,player1Name,player2Name)){
   isGameOver = true; 

   }

   isPlayer1Turn = true;
   

  }

    break; 
    }catch(InputMismatchException e){
   System.out.println("enter integer only");
   inp.nextLine();

   }

 }

   
 

   if(!isGameOver && isDraw(array,symbol1,symbol2,player1Name,player2Name)){
  
   System.out.println("Its a draw");
   isGameOver = true;
   }
  
   }catch(ArrayIndexOutOfBoundsException e){
      System.out.println("Exception Index out of bounds");
      System.out.println("Enter an integers between 1-9 only");
     

   }

  }  
  System.out.println("Game Over!!!");
    
    System.out.println("Press Y to restart the game, or anyother key to go to main menu");
    inp.nextLine();
    String gameChoice = inp.nextLine().toUpperCase();
    for(int i=0; i<array.length; i++){ 
       for(int j=0; j<array[i].length; j++){
       array[i][j]= null;
       }
     }

    isGameOver = false; 
    isPlayer1Turn = true; 
    currentSymbol = " "; 
    

    if(!gameChoice.equals("Y")){
    restart = false;
    mainMenu = true;
       }

    }

  }

if(choice=='2'){         
   mainMenu = false;
   System.out.println("Player kindly enter your name");
   String name = inp.nextLine();
   String comp = "computer";
   String playerSymbol;
   String computerSymbol;
   String currPlayer;
   boolean loopVariable1 = true; 
  
   while(true){ 
       System.out.println(name + ",kindly choose a symbol from following: X or O ");
       playerSymbol = inp.nextLine().toUpperCase();
         
       if(playerSymbol.equals("X") || playerSymbol.equals("O")){
             break;
       }else{
       System.out.println("Enter a valid input");
       
       }
      
     }   

      if(playerSymbol.equals("X")){
       System.out.println("The symbol assigned to computer is O");
       computerSymbol = "O";

       }else{
       System.out.println("The symbol assigned to computer is X");
       computerSymbol = "X";
       }

    boolean restart2 = true;
    int tossMain = -1;
    while(restart2){ 
  while(true){
   try{
    tossMain = (int) (Math.random() * 2);
    System.out.println(name + ",kindly enter 0 for heads or 1 for tails");
    int playerChoiceToss = inp.nextInt();
    inp.nextLine();

    if(playerChoiceToss!=0 && playerChoiceToss!=1){
    System.out.println("Enter valid input");
    continue;
    

    }if(playerChoiceToss==tossMain){
    System.out.println(name + " wins the toss, so the first move will be done by player");
    currPlayer = name;
  
    

    }else if(playerChoiceToss!=tossMain){
    System.out.println("computer wins the toss,so the first move will be done by computer");
    currPlayer = comp;
  
    

   }else{
    System.out.println("It's a draw so let us again see who wins");
   
    continue;
 
      }

   break;

   }catch(InputMismatchException e){
   System.out.println("Enter integers only");
   inp.nextLine();
   }

  } 

    int integer = 1;
   int totalboxes = 3 * 3;
   for(int i=0; i<array.length; i++){
      for(int j=0; j<array[i].length; j++){
       if(integer<=totalboxes){
       System.out.printf("%3s",integer);
       integer++;
      }
    }
   System.out.println();
  }

 boolean GameOver = false;
   boolean isPlayerTurn = true;
   String currSymbol = " "; 
    boolean isplayerTurn = true;
   while(!GameOver){
 
 try{ 
   
   int row1 = 0; 
   int row2 = 0;
   int column1 = 0;
   int column2 = 0;
   int num1;
   if(currPlayer.equals(comp)){ 
   System.out.println("Its computer turn, so wait until he selects a tile");
   
   int[]move = computerMove(array,playerSymbol,computerSymbol);
   row1 = move[0];
   column1 = move[1];

   if(currPlayer.equals(name)){
   currSymbol = playerSymbol;
   currPlayer = comp;
   }else{
   currSymbol = computerSymbol;
   currPlayer = name;
   }

   array[row1][column1] = currSymbol;
   printGrid(array);
   

   if(winCheck2(playerSymbol,computerSymbol,array,name,comp)){ 
   GameOver = true; 

   
   }else{
   isPlayerTurn = false;
   }

 

  }else{
  boolean validInput = false;
 while(!validInput){
  try{
   if(currPlayer.equals(name)){ 
   System.out.println("Now " + name + " plz select a number where you want to place your symbol");
   int num2 = inp.nextInt();

   if(num2<1 || num2>9){
   System.out.println("Enter number between 1-9 only");
   continue;
   }
  
   row2 = (num2-1)/3; 
   column2 = (num2-1)%3; 

  if(array[row2][column2]!=null){ 
  System.out.println("This cell is already taken, so select another one");
  continue;
  }

  }
  validInput = true;
  break;
  }catch(InputMismatchException e){
  System.out.println("Enter integers only");
  inp.nextLine();
  }
 }
    if(currPlayer.equals(name)){ 
   currSymbol = playerSymbol;
   currPlayer = comp;
   }else{
   currSymbol = computerSymbol;
   currPlayer = name;
   }

   array[row2][column2] = currSymbol;
   printGrid(array);
   if(winCheck2(playerSymbol,computerSymbol,array,name,comp)){ 
   GameOver = true; 

   }else{
   isPlayerTurn = true;
   }

 }
 
 
   if(!GameOver && isDraw2(array,playerSymbol,computerSymbol,name,comp)){ 
  
   System.out.println("Its a draw");
   GameOver = true;
   }
  
   }catch(ArrayIndexOutOfBoundsException e){
      System.out.println("Exception Index out of bounds");
      System.out.println("Enter an integers between 1-9 only");
     

   }

  } 
  System.out.println("Game Over!!!");

  System.out.println("Press Y to restart the game, or anyother key to go to main menu");
     inp.nextLine();
    String gameChoice2 = inp.nextLine().toUpperCase();
    for(int i=0; i<array.length; i++){ 
       for(int j=0; j<array[i].length; j++){
       array[i][j]= null;
       }
     }

    GameOver = false; 
    isPlayerTurn = true; 
    currSymbol = " "; 
    

    if(!gameChoice2.equals("Y")){
    restart2 = false;
    mainMenu = true;
    }

    }

   }

   

   }

   }catch(InputMismatchException e){  
      System.out.println("Exception");
      System.out.println("Enter an integer");
      inp.nextLine();

   }

      } while(loopVariable);  
       
    }
       
      }
   
 }
 public static void printHangman(int guessesLeft){
        switch(guessesLeft){

            case 6:
                System.out.println(" -------");
                System.out.println("  |/      |");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("___|____");
                break;

            case 5:
                System.out.println(" -------");
                System.out.println("  |/      |");
                System.out.println("  |      (_)");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("___|____");
                break;

            case 4:
                System.out.println(" -------------");
                System.out.println("  |/      |");
                System.out.println("  |      (_)");
                System.out.println("  |       | ");
                System.out.println("  |       | ");
                System.out.println("  |");
                System.out.println("___|____");
                break;

            case 3:
                System.out.println(" --------------");
                System.out.println("  |/      |");
                System.out.println("  |      (_)");
                System.out.println("  |     \\|  ");
                System.out.println("  |       | ");
                System.out.println("  |");
                System.out.println("___|____");
                break;

            case 2:
                System.out.println(" --------------");
                System.out.println("  |/      |");
                System.out.println("  |      (_)");
                System.out.println("  |     \\|/   ");
                System.out.println("  |       | ");
                System.out.println("  |");
                System.out.println("___|____");
                break;

            case 1:
                System.out.println(" -------------");
                System.out.println("  |/      |");
                System.out.println("  |      (_)");
                System.out.println("  |     \\|/   ");
                System.out.println("  |       | ");
                System.out.println("  |      / ");
                System.out.println("___|____");
                break;

            case 0:
                System.out.println(" --------------");
                System.out.println("  |/      |");
                System.out.println("  |      (_)");
                System.out.println("  |     \\|/   ");
                System.out.println("  |       | ");
                System.out.println("  |      / \\ ");
                System.out.println("___|____");
                break;
        }
    }
   public static String[] generateEmojis(int numberPairs){   
          String [] result = new String[numberPairs];  
          int startOfEmojis = 0x1F600; 
          int endOfEmojis = 0x1F64F;
          int code = startOfEmojis;  
   
        for(int i=0; i<numberPairs; i++){   

         if(code>endOfEmojis){  
           code = startOfEmojis;
           }
         
          char[] conversion = Character.toChars(code); 
          result [i] = String.valueOf(conversion);     
          code++; 
          }

          return result;  
   }

   public static void temporaryPrintGrid(int row1,int column1, int row2, int column2,String array[][],boolean revealed[][]){  

       int number = 1; 
    for(int i=0; i<array.length;i++){
        for(int j=0; j<array[i].length;j++){   
        
     if(revealed[i][j] || (i==row1 && j==column1) || (i==row2 && j==column2)){  
       System.out.printf("%3s",array[i][j]);
    
   }else{
      System.out.printf("%3s",number);   
      
    } 
    number++; 


   }
   System.out.println();  
  }
    
 }
   public static void printGrid(String [][] array, boolean [][] revealed){
   int number = 1;
 for(int i=0; i<array.length;i++){
        for(int j=0; j<array[i].length;j++){ 
         if(revealed[i][j]==true){ 
        System.out.printf("%3s",array[i][j]);  

        }else{
      System.out.printf("%3s",number);   
      
    } 
    number++; 


   }
   System.out.println();  




    }

  }   
  public static String countryWord(String fileName){
    int count = 0; 
    String[] countryNames = new String[16];
    String selected = " ";
    String scrambled = " ";
 try{

    FileInputStream fis = new FileInputStream(fileName);
    Scanner inp = new Scanner(fis);
    while(inp.hasNext()){
    String word = inp.nextLine();
    countryNames[count] = word;
    count++;
    }
    
    int random = (int) (Math.random() * countryNames.length);
    
    selected = countryNames[random];

    countryOriginal = selected; 
    char[] chars = new char[selected.length()];
   for(int i=0; i<selected.length(); i++){
      chars[i] = selected.charAt(i);
      }

    
    for(int i=0; i<chars.length; i++){
    int j = (int) (Math.random() * chars.length);
    char temp = chars[i];
    chars[i] = chars[j];
    chars[j] = temp;
    }
  
    for(char words : chars){
    scrambled = scrambled + words;
    }

    inp.close();
    }catch(FileNotFoundException e){
  System.out.println("exception of file not found");
  }

   
  return scrambled;

   }
  public static String fruitWord(String fileName){
    int count = 0; 
    String[] fruitNames = new String[16];
    String selected = " ";
    String scrambled = " ";
 try{

    FileInputStream fis = new FileInputStream(fileName);
    Scanner inp = new Scanner(fis);
    while(inp.hasNext()){
    String word = inp.nextLine();
    fruitNames[count] = word;
    count++;
    }
    
    int random = (int) (Math.random() * fruitNames.length);
    
    selected = fruitNames[random];
    
    fruitOriginal = selected; 
    char[] chars = new char[selected.length()];

    for(int i=0; i<selected.length(); i++){
      chars[i] = selected.charAt(i);
      }
    
    for(int i=0; i<chars.length; i++){
    int j = (int) (Math.random() * chars.length);
    char temp = chars[i];
    chars[i] = chars[j];
    chars[j] = temp;
    }
  
    for(char words : chars){
    scrambled = scrambled + words;
    }

    inp.close();
    }catch(FileNotFoundException e){
  System.out.println("exception of file not found");
  }

   
  return scrambled;


 }

 public static String cityWord(String fileName){
    int count = 0; 
    String[] cityNames = new String[10];
    String selected = " ";
    String scrambled = " ";
 try{

    FileInputStream fis = new FileInputStream(fileName);
    Scanner inp = new Scanner(fis);
    while(inp.hasNext()){
    String word = inp.nextLine();
    cityNames[count] = word;
    count++;
    }
   
   
    int random = (int) (Math.random() * cityNames.length);
    
    selected = cityNames[random];
    
   
    cityOriginal = selected; 

    char[] chars = new char[selected.length()];

    for(int i=0; i<selected.length(); i++){
      chars[i] = selected.charAt(i);
      }
    
    for(int i=0; i<chars.length; i++){
    int j = (int) (Math.random() * chars.length);
    char temp = chars[i];
    chars[i] = chars[j];
    chars[j] = temp;
    }
  
    for(char words : chars){
    scrambled = scrambled + words;
    }

    inp.close();
    }catch(FileNotFoundException e){
  System.out.println("exception of file not found");
  }

   
  return scrambled;


 }

  public static String mixedWord(String fileName1,String fileName2,String fileName3){
  String[] countryNames = new String[16];
  String[] fruitNames = new String[16];
  String[] cityNames = new String[10];
  int count1 = 0;
  int count2 = 0;
  int count3 = 0;
  String selected1 = " ";
  String selected2 = " ";
  String selected3 = " ";
  String[] finalArray = new String[3];
  String finalWord = " ";
  String scrambled = " ";

  try{

    FileInputStream fis = new FileInputStream(fileName1);
    Scanner inp = new Scanner(fis);
    while(inp.hasNext()){
    String word1 = inp.nextLine();
    countryNames[count1] = word1;
    count1++;
    }
   
    FileInputStream read = new FileInputStream(fileName2);
    Scanner in = new Scanner(read);
    while(in.hasNext()){
    String word2 = in.nextLine();
    fruitNames[count2] = word2;
    count2++;
    }
 
    FileInputStream din = new FileInputStream(fileName3);
    Scanner cin = new Scanner(din);
    while(cin.hasNext()){
    String word3 = cin.nextLine();
    cityNames[count3] = word3;
    count3++;
    }
   
    int rand1 = (int) (Math.random() * countryNames.length);
    selected1 = countryNames[rand1];
    int rand2 = (int) (Math.random() * fruitNames.length);
    selected2 = fruitNames[rand2];
    int rand3 = (int) (Math.random() * cityNames.length);
    selected3 = cityNames[rand3];
    finalArray[0] = selected1;
    finalArray[1] = selected2;
    finalArray[2] = selected3;

    int random = (int) (Math.random() * finalArray.length);
    
    finalWord = finalArray[random];
    mixedOriginal = finalWord; 

   char[] chars = new char[finalWord.length()];

    for(int i=0; i<finalWord.length(); i++){
      chars[i] = finalWord.charAt(i);
      }
    
    for(int i=0; i<chars.length; i++){
    int j = (int) (Math.random() * chars.length);
    char temp = chars[i];
    chars[i] = chars[j];
    chars[j] = temp;
    }
  
    for(char words : chars){
    scrambled = scrambled + words;
    }

    inp.close();
    in.close();
    cin.close();
  
    }catch(FileNotFoundException e){
  System.out.println("exception of file not found");
  }

   
  return scrambled;


  }

 public static int score(String word,String guess){
         int score = 0;
       if(guess.equals(word)){
        score = score + 1;
      System.out.println("Correct! +1 point");
       
      }else{
      score = score+0;
      System.out.println("Incorrect!The correct word was: " + word );
      }
         
      return score;
     

   }

  public static boolean isValid(String guess){
      boolean valid = true;
      for(int i=0; i<guess.length(); i++){
      char check = guess.charAt(i);
      if(!Character.isLetter(check)){
      valid = false;
      break;
      }

     if(!valid){  
     System.out.println("Invalid, letters are allowed only");
     return false;

   }

     

   }

    return valid;

  }
  public static String time(char difficultyLevel,long startTime){
          
          Scanner inp = new Scanner(System.in);
          String guess = " ";
      while(true){
          guess = inp.nextLine().toUpperCase();
          int timeLimit = 0;
          if(difficultyLevel=='e'){
          timeLimit = 9;
         }else if(difficultyLevel=='m'){
          timeLimit = 6;
         }else if(difficultyLevel=='h'){
          timeLimit = 3;
         }else{
         System.out.println("Enter valid choice");
         }

       long endTime = System.currentTimeMillis();
        long extraTime = endTime-startTime;
        int extraSeconds = (int) (extraTime/1000);

        if(extraSeconds>=timeLimit){
        System.out.println("Time over. You didn't answered in " + timeLimit + " seconds");
        guess = "TimeOut";
        return guess;

        }if(!isValid(guess)){
        System.out.println("Enter valid input");
        guess = " ";
        continue;
      }
     
        break;
     }

       return guess;
 }
  public static void printGrid(String[][] array){
    String symbol = "X";
    int number = 1;
    for(int i=0; i<array.length; i++){
       for(int j=0; j<array[i].length; j++){

       if(array[i][j]!=null){
       System.out.printf("%3s",array[i][j]);
       
       }else{
       System.out.printf("%3s",number);
       }
       number++;
  
    }

    System.out.println();
   }

  }
  public static boolean winCheck(String symbol1,String symbol2,String[][] array,String player1Name,String player2Name){
  
  
  if(symbol1.equals(array[0][0]) && symbol1.equals(array[0][1]) && symbol1.equals(array[0][2])){
 System.out.println("The winner is " + player1Name);
 return true;
 
 }
  
 else if(symbol1.equals(array[1][0]) && symbol1.equals(array[1][1]) && symbol1.equals(array[1][2])){
 System.out.println("The winner is " + player1Name);
  return true;
 }
  
  else if(symbol1.equals(array[2][0]) && symbol1.equals(array[2][1]) && symbol1.equals(array[2][2])){
 System.out.println("The winner is " + player1Name);
  return true;
 }
  
 else if(symbol1.equals(array[0][0]) && symbol1.equals(array[1][0]) && symbol1.equals(array[2][0])){
 System.out.println("The winner is " + player1Name);
  return true;
 }

 else if(symbol1.equals(array[0][1]) && symbol1.equals(array[1][1]) && symbol1.equals(array[2][1])){
 System.out.println("The winner is " + player1Name);
  return true;
 }

  else if(symbol1.equals(array[0][2]) && symbol1.equals( array[1][2]) && symbol1.equals(array[2][2])){
 System.out.println("The winner is " + player1Name);
  return true;

 }else if(symbol1.equals(array[0][0]) && symbol1.equals(array[1][1]) && symbol1.equals(array[2][2])){
 System.out.println("The winner is " + player1Name);
  return true;

  }else if(symbol1.equals(array[0][2]) && symbol1.equals(array[1][1]) && symbol1.equals(array[2][0])){
 System.out.println("The winner is " + player1Name);
  return true;

 }
 

  
   if(symbol2.equals(array[0][0]) && symbol2.equals(array[0][1]) && symbol2.equals(array[0][2])){
 System.out.println("The winner is " + player2Name);
  return true;

 }else if(symbol2.equals(array[1][0]) && symbol2.equals(array[1][1]) && symbol2.equals(array[1][2])){
 System.out.println("The winner is " + player2Name);
  return true;

 }
  else if(symbol2.equals(array[2][0]) && symbol2.equals(array[2][1]) && symbol2.equals(array[2][2])){
 System.out.println("The winner is " + player2Name);
  return true;

 }
  else if(symbol2.equals(array[0][0]) && symbol2.equals(array[1][0]) && symbol2.equals(array[2][0])){
 System.out.println("The winner is " + player2Name);
  return true;
 }

  else if(symbol2.equals(array[0][1]) && symbol2.equals(array[1][1]) && symbol2.equals(array[2][1])){
 System.out.println("The winner is " + player2Name);
  return true;
 }

 else if(symbol2.equals(array[0][2]) && symbol2.equals(array[1][2]) && symbol2.equals(array[2][2])){
 System.out.println("The winner is " + player2Name);
  return true;

 }else if(symbol2.equals(array[0][0]) && symbol2.equals(array[1][1]) && symbol2.equals(array[2][2])){
 System.out.println("The winner is " + player2Name);
  return true;

  }else if(symbol2.equals(array[0][2]) && symbol2.equals(array[1][1]) && symbol2.equals(array[2][0])){
 System.out.println("The winner is " + player2Name);
  return true;

  }
   
  

   return false;

 }


 public static boolean isDraw(String[][]array,String symbol1,String symbol2,String player1Name,String player2Name){
        
  
  for(int i=0; i<array.length;i++){
     for(int j=0; j<array[i].length; j++){
   if(!symbol1.equals(array[i][j]) && !symbol2.equals(array[i][j])){
   return false;
   }
 
  }

 }
 
  if(!winCheck(symbol1,symbol2,array,player1Name,player2Name)){
  return true;
   }

  return false;
 
  }



 public static boolean winCheck2(String playerSymbol,String computerSymbol,String[][] array,String name,String player2Name){
  
  
  if(playerSymbol.equals(array[0][0]) && playerSymbol.equals(array[0][1]) && playerSymbol.equals(array[0][2])){
 System.out.println("The winner is " + name);
 return true;
 
 }
  
 else if(playerSymbol.equals(array[1][0]) && playerSymbol.equals(array[1][1]) && playerSymbol.equals(array[1][2])){
 System.out.println("The winner is " + name);
  return true;
 }
  
  else if(playerSymbol.equals(array[2][0]) && playerSymbol.equals(array[2][1]) && playerSymbol.equals(array[2][2])){
 System.out.println("The winner is " + name);
  return true;
 }
  
 else if(playerSymbol.equals(array[0][0]) && playerSymbol.equals(array[1][0]) && playerSymbol.equals(array[2][0])){
 System.out.println("The winner is " + name);
  return true;
 }

 else if(playerSymbol.equals(array[0][1]) && playerSymbol.equals(array[1][1]) && playerSymbol.equals(array[2][1])){
 System.out.println("The winner is " + name);
  return true;
 }

  else if(playerSymbol.equals(array[0][2]) && playerSymbol.equals( array[1][2]) && playerSymbol.equals(array[2][2])){
 System.out.println("The winner is " + name);
  return true;

 }else if(playerSymbol.equals(array[0][0]) && playerSymbol.equals(array[1][1]) && playerSymbol.equals(array[2][2])){
 System.out.println("The winner is " + name);
  return true;

  }else if(playerSymbol.equals(array[0][2]) && playerSymbol.equals(array[1][1]) && playerSymbol.equals(array[2][0])){
 System.out.println("The winner is " + name);
  return true;

 }
 

  
   if(computerSymbol.equals(array[0][0]) && computerSymbol.equals(array[0][1]) && computerSymbol.equals(array[0][2])){
 System.out.println("The winner is " + player2Name);
  return true;

 }else if(computerSymbol.equals(array[1][0]) && computerSymbol.equals(array[1][1]) && computerSymbol.equals(array[1][2])){
 System.out.println("The winner is " + player2Name);
  return true;

 }
  else if(computerSymbol.equals(array[2][0]) && computerSymbol.equals(array[2][1]) && computerSymbol.equals(array[2][2])){
 System.out.println("The winner is " + player2Name);
  return true;

 }
  else if(computerSymbol.equals(array[0][0]) && computerSymbol.equals(array[1][0]) && computerSymbol.equals(array[2][0])){
 System.out.println("The winner is " + player2Name);
  return true;
 }

  else if(computerSymbol.equals(array[0][1]) && computerSymbol.equals(array[1][1]) && computerSymbol.equals(array[2][1])){
 System.out.println("The winner is " + player2Name);
  return true;
 }

 else if(computerSymbol.equals(array[0][2]) && computerSymbol.equals(array[1][2]) && computerSymbol.equals(array[2][2])){
 System.out.println("The winner is " + player2Name);
  return true;

 }else if(computerSymbol.equals(array[0][0]) && computerSymbol.equals(array[1][1]) && computerSymbol.equals(array[2][2])){
 System.out.println("The winner is " + player2Name);
  return true;

  }else if(computerSymbol.equals(array[0][2]) && computerSymbol.equals(array[1][1]) && computerSymbol.equals(array[2][0])){
 System.out.println("The winner is " + player2Name);
  return true;

  }
   
  

   return false;

 }

 public static boolean isDraw2(String[][]array,String playerSymbol,String computerSymbol,String name,String comp){
        
  
  for(int i=0; i<array.length;i++){
     for(int j=0; j<array[i].length; j++){
   if(!playerSymbol.equals(array[i][j]) && !computerSymbol.equals(array[i][j])){
   return false;
   }
 
  }

 }
 
  if(!winCheck(playerSymbol,computerSymbol,array,name,comp)){ 
  return true;
   }

  return false;
 
  }


 public static int [] computerMove(String[][]array,String playerSymbol,String computerSymbol){
  

    if(computerSymbol.equals(array[0][0]) && computerSymbol.equals(array[0][1]) && array[0][2]==null){ 
   int move[] = {0,2};
     return move;

    }else if(computerSymbol.equals(array[0][2]) && computerSymbol.equals(array[0][1]) && array[0][0]==null){
    int move[] = {0,0};
     return move;

   }else if(computerSymbol.equals(array[0][0]) && computerSymbol.equals(array[0][2]) && array[0][1]==null){
  int move[] = {0,1};
    return move;

   }else if(computerSymbol.equals(array[1][0]) && computerSymbol.equals(array[1][1]) && array[1][2]==null){
  int move[] = {1,2};
    return move;

   }else if(computerSymbol.equals(array[1][1]) && computerSymbol.equals(array[1][2]) && array[1][0]==null){
  int move[] = {1,0};
    return move;

   }else if(computerSymbol.equals(array[1][0]) && computerSymbol.equals(array[1][2]) && array[1][1]==null){
 int move[] = {1,1};
   return move;

  }else if(computerSymbol.equals(array[2][0]) && computerSymbol.equals(array[2][1]) && array[2][2]==null){
 int move[] = {2,2};
   return move;

 }else if(computerSymbol.equals(array[2][1]) && computerSymbol.equals(array[2][2]) && array[2][0]==null){
 int move[] = {2,0};
   return move;

  }else if(computerSymbol.equals(array[2][0]) && computerSymbol.equals(array[2][2]) && array[2][1]==null){
 int move[] = {2,1};
   return move;

 }else if(computerSymbol.equals(array[0][0]) && computerSymbol.equals(array[1][0]) && array[2][0]==null){
 int move[] = {2,0};
   return move;

}else if(computerSymbol.equals(array[1][0]) && computerSymbol.equals(array[2][0]) && array[0][0]==null){
 int move[] = {0,0};
   return move;

 }else if(computerSymbol.equals(array[0][0]) && computerSymbol.equals(array[2][0]) && array[1][0]==null){
  int move[] = {1,0};  
   return move;

 }else if(computerSymbol.equals(array[0][1]) && computerSymbol.equals(array[1][1]) && array[2][1]==null){
 int move[] = {2,1};  
   return move;

  }else if(computerSymbol.equals(array[1][1]) && computerSymbol.equals(array[2][1]) && array[0][1]==null){
 int move[] = {0,1};  
   return move;

 }else if(computerSymbol.equals(array[0][1]) && computerSymbol.equals(array[2][1]) && array[1][1]==null){
 int move[] = {1,1};  
   return move;

 }else if(computerSymbol.equals(array[0][2]) && computerSymbol.equals(array[1][2]) && array[2][2]==null){
 int move[] = {2,2};  
   return move;

 }else if(computerSymbol.equals(array[1][2]) && computerSymbol.equals(array[2][2]) && array[0][2]==null){
 int move[] = {0,2};  
   return move;

 }else if(computerSymbol.equals(array[0][2]) && computerSymbol.equals(array[2][2]) && array[1][2]==null){
 int move[] = {1,2};  
   return move;

 }else if(computerSymbol.equals(array[0][0]) && computerSymbol.equals(array[1][1]) && array[2][2]==null){
 int move[] = {2,2}; 
   return move;

 }else if(computerSymbol.equals(array[1][1]) && computerSymbol.equals(array[2][2]) && array[0][0]==null){
 int move[] = {0,0}; 
   return move;

  }else if(computerSymbol.equals(array[0][0]) && computerSymbol.equals(array[2][2]) && array[1][1]==null){
 int move[] = {1,1}; 
   return move;

 }
  if(playerSymbol.equals(array[0][0]) && playerSymbol.equals(array[2][0]) && array[1][0]==null){
   int move[] = {1,0};
   return move;
  }else if(playerSymbol.equals(array[0][0]) && playerSymbol.equals(array[0][1]) && array[0][2]==null){ 
  int move[] = {0,2};
     return move;

    }else if(playerSymbol.equals(array[0][2]) && playerSymbol.equals(array[0][1]) && array[0][0]==null){
  int move[] = {0,0};
     return move;

   }else if(playerSymbol.equals(array[0][0]) && playerSymbol.equals(array[0][2]) && array[0][1]==null){
  int move[] = {0,1};
    return move;

  }else if(playerSymbol.equals(array[1][0]) && playerSymbol.equals(array[1][1]) && array[1][2]==null){
  int move[] = {1,2};
    return move;

   }else if(playerSymbol.equals(array[1][1]) && playerSymbol.equals(array[1][2]) && array[1][0]==null){
  int move[] = {1,0};
    return move;

   }else if(playerSymbol.equals(array[1][0]) && playerSymbol.equals(array[1][2]) && array[1][1]==null){
 int move[] = {1,1};
  return move;

  }else if(playerSymbol.equals(array[2][0]) && playerSymbol.equals(array[2][1]) && array[2][2]==null){
 int move[] = {2,2};
   return move;

 }else if(playerSymbol.equals(array[2][1]) && playerSymbol.equals(array[2][2]) && array[2][0]==null){
 int move[] = {2,0};
   return move;

  }else if(playerSymbol.equals(array[2][0]) && playerSymbol.equals(array[2][2]) && array[2][1]==null){
 int move[] = {2,1};
   return move;

 }else if(playerSymbol.equals(array[0][0]) && playerSymbol.equals(array[1][0]) && array[2][0]==null){
 int move[] = {2,0};
   return move;

}else if(playerSymbol.equals(array[1][0]) && playerSymbol.equals(array[2][0]) && array[0][0]==null){
 int move[] = {0,0};
   return move;

 }else if(playerSymbol.equals(array[0][0]) && playerSymbol.equals(array[2][0]) && array[1][0]==null){
 int move[] = {1,0};  
   return move;

 }else if(playerSymbol.equals(array[0][1]) && playerSymbol.equals(array[1][1]) && array[2][1]==null){
 int move[] = {2,1};  
   return move;

  }else if(playerSymbol.equals(array[1][1]) && playerSymbol.equals(array[2][1]) && array[0][1]==null){
 int move[] = {0,1};  
   return move;

 }else if(playerSymbol.equals(array[0][1]) && playerSymbol.equals(array[2][1]) && array[1][1]==null){
 int move[] = {1,1};  
   return move;

 }else if(playerSymbol.equals(array[0][2]) && playerSymbol.equals(array[1][2]) && array[2][2]==null){
 int move[] = {2,2};  
   return move;

 }else if(playerSymbol.equals(array[1][2]) && playerSymbol.equals(array[2][2]) && array[0][2]==null){
 int move[] = {0,2};  
   return move;

 }else if(playerSymbol.equals(array[0][2]) && playerSymbol.equals(array[2][2]) && array[1][2]==null){
  int move[] = {1,2};  
   return move;

 }else if(playerSymbol.equals(array[0][0]) && playerSymbol.equals(array[1][1]) && array[2][2]==null){
 int move[] = {2,2}; 
   return move;

 }else if(playerSymbol.equals(array[1][1]) && playerSymbol.equals(array[2][2]) && array[0][0]==null){
  int move[] = {0,0}; 
  return move;

  }else if(playerSymbol.equals(array[0][0]) && playerSymbol.equals(array[2][2]) && array[1][1]==null){
  int move[] = {1,1}; 
  return move;

 }
 if(playerSymbol.equals(array[1][2]) && playerSymbol.equals(array[2][0]) && array[2][1]==null){
   int move[] = {2,1};
   return move;
  
   }else if(playerSymbol.equals(array[2][2]) && playerSymbol.equals(array[0][0]) && array[1][1]==null){
   int move[] = {1,1};
   return move;
   
   }else if(playerSymbol.equals(array[2][0]) && playerSymbol.equals(array[1][0]) && array[0][0]==null){
   int move[] = {0,0};
   return move;

   }else if(playerSymbol.equals(array[2][1]) && playerSymbol.equals(array[1][2]) && array[2][2]==null){
   int move[] = {2,2};
   return move;

   }else if(playerSymbol.equals(array[1][2]) && playerSymbol.equals(array[2][0]) && playerSymbol.equals(array[2][2]) && array[1][1]==null){
   int move[] = {1,1};
   return move;
   

   }else if(playerSymbol.equals(array[1][2]) && playerSymbol.equals(array[2][0]) && playerSymbol.equals(array[2][2]) && playerSymbol.equals(array[2][1])){

    if(array[1][1]==null){
    int move[] = {1,1};
    return move;

    }else{
     if(array[0][0]==null){
    int move[] = {0,0};
    return move;

    }else{
    int move[] = {0,2};
    return move;

    }

   }
  
  }  

  if(array[1][1]==null){
   int move[] = {1,1};
    return move;

 }else if(array[0][0]==null){
 int move[] = {0,0};
  return move;

 }else if(array[0][2]==null){
 int move[] = {0,2};
  return move;

 }else if(array[2][0]==null){
 int move[] = {2,0};
  return move;

 }else if(array[2][2]==null){
 int move[] = {2,2};
  return move;

 }else if(array[0][1]==null){
 int move[] = {0,1};
  return move;

 }else if(array[1][0]==null){
int move[] = {1,0};
  return move;

 }else if(array[1][2]==null){
int move[] = {1,2};
  return move;

 }else if(array[2][1]==null){
 int move[] = {2,1};
 return move;

 }
    int [] move = new int[2];
   for(int i=0; i<array.length; i++){
      for(int j=0; j<array[i].length; j++){
      
       if(array[i][j]==null){
         move[0] = i;
         move[1] = j;
         return move;
         }

     }

  }

   return move;


   }

  }











































