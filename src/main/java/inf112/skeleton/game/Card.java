package inf112.skeleton.game;

public class Card {
    public int priorityNr;
    public CardType type;

    public Card(CardType type, int priorityNr) {
        this.priorityNr = priorityNr;
        this.type = type;
    }

    @Override
    public String toString() {
        return type.name();
    }
}
