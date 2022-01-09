import java.util.ArrayList;

// The ElevensBoard class represents the board in a game of Elevens.
// This class will contain all the code that implements the rules of the game
public class ElevensBoard extends Board {

    // The size (number of cards) on the board is fixed at 9
    private static final int BOARD_SIZE = 9;

    // These three arrays will be used in the constructor:
    // The ranks of the cards for this game to be sent to the deck
    private static final String[] RANKS = {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};
    // The suits of the cards for this game to be sent to the deck.
    private static final String[] SUITS = {"spades", "hearts", "diamonds", "clubs"};
    // The values of the cards for this game to be sent to the deck.
    private static final int[] POINT_VALUES = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 0, 0};

    // Creates a new ElevensBoard instance with a standard 52 card deck
    public ElevensBoard() {
        super(BOARD_SIZE, RANKS, SUITS, POINT_VALUES);
    }

    // Given a list of the **indexes** of some cards on the board,
    // returns true if **any** two of the given cards adds up to exactly 11
    // and false otherwise
    public boolean containsPairSum11(ArrayList<Integer> cardIndexes) {
        for (int i = 0; i < cardIndexes.size(); i++) {
            for (int j = i + 1; j < cardIndexes.size(); j++) {

                if (cardAt(cardIndexes.get(i)).getPointValue() + cardAt(cardIndexes.get(j)).getPointValue() == 11) {
                    return true;
                }
            }
        }
        return false;
    }

    // Given a list of the **indexes** of some cards on the board,
    // returns true if there is at least 1 Jack, at least 1 Queen, and at least 1 King
    // amongst the selected cards, and false otherwise
    public boolean containsJQK(ArrayList<Integer> cardIndexes) {
        int J = 0;
        int Q = 0;
        int K = 0;
        for (int i = 0; i < cardIndexes.size(); i++) {
            String rank = (cardAt(cardIndexes.get(i))).getRank();
            if (rank.equals("jack")) {
                J += 1;
            } else if (rank.equals("queen")) {
                Q += 1;
            } else if (rank.equals("king")) {
                K += 1;
            }
        }
        return (J > 0 && Q > 0 && K > 0);
    }

    // Determine if there are any legal plays left on the board.
    // A legal play is either a pair of non-face cards whose values
    // add to 11, or (2) a group of three cards consisting of a jack, a
    // queen, and a king in some order.
    @Override
    public boolean anotherPlayIsPossible() {
        // allCards is a list of the indexes of all cards on the board
        ArrayList<Integer> allCards = getAllCardIndexes();

        // YOUR CODE HERE
        // Just 1-2 lines of code needed

        return containsJQK(allCards) || containsPairSum11(allCards);
    }

    // Determines if the selected cards form a valid group for removal. In Elevens,
    // the legal groups are (1) a pair of non-face cards whose values add to 11, and
    // (2) a group of three cards consisting of a jack, a queen, and a king in some order
    @Override
    public boolean isLegal(ArrayList<Integer> selectedCards) {
//        ArrayList<Integer> allCards = getAllCardIndexes();
//        for (int i = 0; i < selectedCards.size(); i++) {
//            for (int j = 0; j < allCards.size(); j++) {
//                if (allCards.get(j) == selectedCards.get(i)) {
//                    allCards.remove(j);
//                    break;
//                }
//            }
//        }

        for (int x:selectedCards){
            System.out.println(cardAt(x).getRank());
            System.out.println(cardAt(x).getPointValue());
        }

        System.out.println(containsJQK(selectedCards));
        System.out.println(containsPairSum11(selectedCards));
        System.out.println("HHHHH");
        return containsJQK(selectedCards)&&selectedCards.size()==3 || containsPairSum11(selectedCards)&&selectedCards.size()==2;
    }
}

