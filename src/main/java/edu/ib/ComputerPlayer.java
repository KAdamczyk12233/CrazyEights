package edu.ib;

public class ComputerPlayer extends Player {
    boolean real = false;

    @Override
    public boolean isReal() {
        return super.isReal(real);
    }

    @Override
    public Card turn(String rank, String suit, Table table, String currentSuit) {
        Card card = new Card();
        Boolean match = true;
        Boolean hasCard = false;

        for (int j = 0; j < getN(); j++) {

            if (getCards().get(j).getRank().equals(rank) && getCards().get(j).getSuit().equals(suit)) { //sprawdzenie czy karta jest na ręce
                hasCard = true;
                match = false;

                // modyfikacja: if czy karty komputera = liczba 8 + 1, jeśli tak, to zawsze wyrzuca 8-kę, niezależnie,
                // czy karta (która nie jest 8) pasuje czy nie - obligatoryjne wyrzucenie 8-ki
                if (getCards().get(j).getSuit().equals(currentSuit)
                        || getCards().get(j).getRank().equals(table.getCards().get(table.getCards().size() - 1).getRank())
                        || (getCards().get(j).getRank().equals("8") && getN() != 1)) { // sprawdzenie czy karta pasuje do karty na stole
                    // liczba kart != 1, komputer może wyrzucić 8-kę
                    match = true; // karta pasuje
                    card = getCards().get(j);
                } else if (getCards().get(j).getRank().equals("8") && getN() == 1) {
                    System.out.println("Nie możesz skończyć ósemką");
                }
            }
        }

        // JavaFX - nic nie będzie się dziać
        if (!hasCard) { // gracz nie ma karty, którą zadeklarował
            System.out.println("Nie posiadasz tej karty");
        }

        // JavaFX - komunikat
        if (!match) { // karta, którą zadeklarował gracz, nie pasuje
            System.out.println("Karta nie pasuje");
        }
        return super.turn(card);
    }

    // metoda sprawdzająca, jakich kolorów kart komputer ma najwięcej, żeby wybrać kolor
    public String theMost() {
        String r = "";
        int s = 0;
        int d = 0;
        int h = 0;
        int c = 0;
        int current = 0;
        for (int i = 0; i < getCards().size(); i++) {
            if (getCards().get(i).getSuit().equals("Clubs")) {
                c++;
            } else if (getCards().get(i).getSuit().equals("Hearts")) {
                h++;
            } else if (getCards().get(i).getSuit().equals("Diamonds")) {
                d++;
            } else if (getCards().get(i).getSuit().equals("Spades")) {
                s++;
            }
        }
        int[] tab = {h, d, s, c};
        for (int i = 0; i < tab.length; i++) {
            if (tab[0] > current) {
                current = tab[0];
            }
        }
        if (s == current) {
            r = "Spades";
        } else if (c == current) {
            r = "Clubs";
        } else if (h == current) {
            r = "Hearts";
        } else if (d == current) {
            r = "Diamonds";
        }
        return r;
    }
}
