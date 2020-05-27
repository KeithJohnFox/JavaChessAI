import javax.swing.*;
import java.awt.*;
import java.util.*;

public class AIAgent{
  Random rand;

  public AIAgent(){
    rand = new Random();
  }

/*
  The method randomMove takes as input a stack of potential moves that the AI agent
  can make. The agent uses a random number generator to randomly select a move from
  the inputted Stack and returns this to the calling agent.
*/

  public Move randomMove(Stack possibilities){

    int moveID = rand.nextInt(possibilities.size());
    System.out.println("Agent randomly selected move : "+moveID);
    for(int i=1;i < (possibilities.size()-(moveID));i++){
      possibilities.pop();
    }
    Move selectedMove = (Move)possibilities.pop();
    return selectedMove;
  }

  //This function takes the possible moves available and apply's a utility function that works out the best move to take

  /* NextBestMove
  -> Get all possible movements from white
  -> Create Utility function based on current move, example if we take a piece we score points
  -> Loop through stack of movements and check if we are taking a piece if so make this move
   */

  public Move nextBestMove(Stack whiteMoveStack, Stack blackPieceStack){
    Stack whiteMoves = (Stack) whiteMoveStack.clone();
    Stack blackStackPosition = (Stack) blackPieceStack.clone();

    Move whiteMove, currentMove, optimumMove;
    optimumMove = null;
    Square blackPosition;

    double score = 0.0;
    double currentScore = 0.0;

    //While there is white moves
    while(!whiteMoveStack.empty()){
      //pop white move from the stack of possible moves
      whiteMove = (Move) whiteMoveStack.pop();
      // store pop move into move var
      currentMove = whiteMove;

      //Declare center squares of hill, if no valid move, move piece to center if possibilities
      if((currentMove.getStart().getYC() < currentMove.getLanding().getYC())
      && (currentMove.getLanding().getXC() == 3) && (currentMove.getLanding().getYC() == 3)
           || (currentMove.getLanding().getXC() == 4) && (currentMove.getLanding().getYC() == 3)
           || (currentMove.getLanding().getXC() == 3) && (currentMove.getLanding().getYC() == 4)
           || (currentMove.getLanding().getXC() == 4) && (currentMove.getLanding().getYC() == 4)) {
        //give center movement half a score
        score = 0.5;

        //If score for move is greater then current score select as move
        if(score > currentScore) {
          //update current score
          currentScore = score;
          //update best current move
          optimumMove = currentMove;

        }
      }
      //If there are moves that takes a black piece
      while(!blackStackPosition.isEmpty()){
        score = 0;
        blackPosition = (Square) blackStackPosition.pop();

        if((currentMove.getLanding().getXC() == blackPosition.getXC()) && (currentMove.getLanding().getYC() == blackPosition.getYC())) {
            //Check which move takes the highest value piece
            switch (blackPosition.getName()) {
                case "BlackPawn":
                    score = 1;
                    break;
                case "BlackKnight":
                case "BlackBishop":
                    score = 3;
                    break;
                case "BlackRook":
                    score = 5;
                    break;
                case "BlackQueen":
                    score = 9;
                    break;
                case "BlackKing":
                    score = 1000;
                    break;
            }
        }
        //Update the optimum move with the highest score move
        if(score > currentScore){
          currentScore = score;
          optimumMove = currentMove;
        }
      }
      //reset black stack squares for next move
      blackStackPosition = (Stack) blackPieceStack.clone();
    }
    //Output score of best move
    if(currentScore > 0){
      System.out.println("Best move by AI: " + currentScore);
      return optimumMove;
    }
    //Make a random move if there is no optimum move
    System.out.println("random move");
    return randomMove(whiteMoves);
  }





  //This Function determines what the other player is going to do next
  //Min max routine

  /* TwoLevelsDeep
   -> Gets all possible moves for white (stack), then we find best possible movements for the player
   -> Then we get all possible movements for Black (stack) after the board changes for each of the movements for white
   -> rank these according to a utility function
   -> The agent makes the best possible move that it can make with least best response from the player
   */

  public Move twoLevelsDeep(Stack possibilities){



    Move selectedMove = new Move();
    return selectedMove;
  }
}
