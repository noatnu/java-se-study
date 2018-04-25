package Interview.a3;

public class Chess  extends BoardGame{
    Chess(){
        super(11);
    }

    public static void main(String[] args) {
        Chess chess = new Chess();
    }
}

class Game{
    Game(int i){
        System.out.println("i = [" + i + "]");
    }
}

class BoardGame extends Game{
    BoardGame(int i){
        super(i);
        System.out.println("BoardGame.BoardGame");
    }
}
