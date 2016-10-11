package com.nibado.project.aigames.texas.model;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Match {
    private final Settings settings = new Settings();
    private final CardSet table = new CardSet();
    private final Map<String, Player> players = new HashMap<>();

    private int round;
    private int smallBlind;
    private int bigBlind;
    private int maxWinPot;
    private int amountToCall;
    private Player onButton;

    public Settings settings() {
        return settings;
    }

    public void setOnButton(String name) {
        onButton = getPlayer(name);
    }

    public Player getPlayer(String name) {
        return players.computeIfAbsent(name, Player::new);
    }

    public Collection<Player> getPlayers() {
        return players.values();
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getSmallBlind() {
        return smallBlind;
    }

    public void setSmallBlind(int smallBlind) {
        this.smallBlind = smallBlind;
    }

    public int getBigBlind() {
        return bigBlind;
    }

    public void setBigBlind(int bigBlind) {
        this.bigBlind = bigBlind;
    }

    public Player getOnButton() {
        return onButton;
    }

    public int getMaxWinPot() {
        return maxWinPot;
    }

    public void setMaxWinPot(int maxWinPot) {
        this.maxWinPot = maxWinPot;
    }

    public int getAmountToCall() {
        return amountToCall;
    }

    public void setAmountToCall(int amountToCall) {
        this.amountToCall = amountToCall;
    }

    public CardSet getTable() {
        return table;
    }

    @Override
    public String toString() {
        return "Match{" +
                "table=" + table +
                ", round=" + round +
                ", smallBlind=" + smallBlind +
                ", bigBlind=" + bigBlind +
                ", maxWinPot=" + maxWinPot +
                ", amountToCall=" + amountToCall +
                ", onButton=" + (onButton == null ? null : onButton.getName())+
                ", players=" + getPlayers() +
                '}';
    }

    public static class Settings {
        private int handsPerLevel;
        private int startingStack;
        private String botName;
        private String opponentName;
        private int timebank;
        private int timePerMove;

        private Settings() {}

        public int getHandsPerLevel() {
            return handsPerLevel;
        }

        public void setHandsPerLevel(int handsPerLevel) {
            this.handsPerLevel = handsPerLevel;
        }

        public int getStartingStack() {
            return startingStack;
        }

        public void setStartingStack(int startingStack) {
            this.startingStack = startingStack;
        }

        public String getBotName() {
            return botName;
        }

        public void setBotName(String botName) {
            this.botName = botName;
        }

        public String getOpponentName() {
            return opponentName;
        }

        public void setOpponentName(String opponentName) {
            this.opponentName = opponentName;
        }

        public int getTimebank() {
            return timebank;
        }

        public void setTimebank(int timebank) {
            this.timebank = timebank;
        }

        public int getTimePerMove() {
            return timePerMove;
        }

        public void setTimePerMove(int timePerMove) {
            this.timePerMove = timePerMove;
        }

        @Override
        public String toString() {
            return "Settings{" +
                    "handsPerLevel=" + handsPerLevel +
                    ", startingStack=" + startingStack +
                    ", botName='" + botName + '\'' +
                    ", opponentName='" + opponentName + '\'' +
                    ", timebank=" + timebank +
                    ", timePerMove=" + timePerMove +
                    '}';
        }
    }
}
