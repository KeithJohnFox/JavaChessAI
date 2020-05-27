class Move{
  Square start;
  Square landing;


  /* HINT
    extend the class Move including an evaluation of the move, the output of the utility function
    you wont need to create an additional data structure for keeping track of scores for each movements
   */
  public Move(Square x, Square y){
    start = x;
    landing = y;
  }

  public Move(){

  }

  public Square getStart(){
    return start;
  }

  public Square getLanding(){
    return landing;
  }
}
