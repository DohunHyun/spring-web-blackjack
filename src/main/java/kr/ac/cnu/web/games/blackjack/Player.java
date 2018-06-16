package kr.ac.cnu.web.games.blackjack;

import kr.ac.cnu.web.exceptions.NotEnoughBalanceException;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by rokim on 2018. 5. 26..
 */
public class Player {
    @Getter
    private long balance;
    @Getter
    private long currentBet = 0;
    @Getter
    private long min_Bet = 1000;
    @Getter
    private boolean isPlaying;
    @Getter
    private Hand hand;
    @Getter
    private boolean checkDoubleDown;


    public Player(long seedMoney, Hand hand) {
        this.balance = seedMoney;
        this.hand = hand;
        checkDoubleDown = false;
        isPlaying = false;
    }

    public void reset() {
        checkDoubleDown = false;
        hand.reset();
        isPlaying = false;
    }

    public void placeBet(long bet) {
        if (balance < min_Bet) {
            bet = balance;
        }
        if (balance < bet) {
            throw new NotEnoughBalanceException();
        }

        // 이전 경기에 베팅한 금액을 경기 종료 후 balance에서 차감했기에,
        // 현재 베팅한 금액과 이전 경기의 베팅 금액의 차이 만큼만 차감한다.
        bet -= currentBet;
        balance -= bet;
        currentBet = bet;

        isPlaying = true;
    }

    public void deal() {
        hand.drawCard();
        hand.drawCard();
    }

    public void blackjackwin() { //blackjack으로 이기는 경우 1.5배
        // 이전 금액을 default bet 금액으로 설정, 고로 승리 시 bet 금액 만큼 만 받는다.
        // blackjackwin은 1.5배
        balance += currentBet * 1.5;

    }

    public void win() {
        // 이전 금액을 default bet 금액으로 설정, 고로 승리 시 bet 금액 만큼 만 받는다.
        // 그냥 승리는 1배
        balance += currentBet;
    }

    public void tie() {
        // 이전 금액을 default bet 금액으로 설정, 비긴경기는 아무거도 하지않아도 된다.
    }

    public void lost() {
        // 이전 금액을 default bet 금액으로 설정, 패배는 현재 남은 금액과 비교하여, 가능한지 알아본다.
        if(balance < currentBet){
            currentBet = balance;
        }
        balance -= currentBet;
    }

    public Card hitCard() {
        return hand.drawCard();
    }

    public void stand() {
        this.isPlaying = false;
    }

    public void doubleDown() {
        if(balance < 2 * currentBet) {
            throw new NotEnoughBalanceException();
        }
        balance -= currentBet;
        currentBet = currentBet * 2;
        checkDoubleDown = true;
    }


}
