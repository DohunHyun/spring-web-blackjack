package kr.ac.cnu.web.games.blackjack;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rokim on 2018. 5. 26..
 */
public class Hand {
    private Deck deck;
    @Getter
    private List<Card> cardList = new ArrayList<>();

    public Hand(Deck deck) {
        this.deck = deck;
    }

    public Card drawCard() {
        Card card = deck.drawCard();
        cardList.add(card);
        return card;
    }

    public int getCardSum() {
        int sum = 0;    //King,Queen,Jack에 대해서 10으로 계산
        int countAce = 0;

        for (Card card : cardList) {
            if(card.getRank() == 1) { // Ace의 초기값을 계산하기 편하게 하기위해 11로 정해준다.
                countAce++;
                sum += 10;
            }

            if (card.getRank() > 10) {
                sum += 10;
            } else {
                sum += card.getRank();
            }
        }
        for(int i = 0; i < countAce; i++) {
            if(countAce > 0 && sum > 21) { // ace가 있는상황에서 총합이 21이 넘을 경우 ace를 1로 바꿔준다.
                sum -= 10;
            }
        }

        return sum;
    }

    public void reset() {
        cardList.clear();
    }

    public int get_count() {
        return cardList.size();
    }
}
