package rainbot.baccarat;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

import rainbot.components.Request;

public class BaccaratRequest extends Request {
    
    @JsonProperty("betstats")
    private BetStatsPacket betstats;

    @JsonProperty("ShoeSummary")
    private ShoeSummaryPacket shoeSummary;

    @JsonProperty("game")
    private GamePacket game;

    @JsonProperty("gameresult")
    private GameResultPacket gameResult;

    @JsonProperty("betsopen")
    private GameEventPacket betsOpen;

    @JsonProperty("betsclosingsoon")
    private GameEventPacket betsClosingSoon;

    @JsonProperty("betsclosed")
    private GameEventPacket betsClosed;

    @JsonProperty("startDealing")
    private GameEventPacket startDealing;

    @JsonProperty("card")
    private CardPacket card;

    private ArrayList<Card> cards;

    public ReturnPacket formatReturn() {
        ReturnPacket returnPacket = new ReturnPacket();

        returnPacket.setTable(betstats.getTable());
        returnPacket.setGame(game.getId());

        returnPacket.setTotalBetsCount(betstats.getTotalBetscount());
        returnPacket.setBankerCount(betstats.getBankercount());
        returnPacket.setBankerPercent(betstats.getBankerpercentage());
        returnPacket.setPlayerCount(betstats.getPlayercount());
        returnPacket.setPlayerPercent(betstats.getPlayerpercentage());
        returnPacket.setTieCount(betstats.getTiecount());
        returnPacket.setTiePercent(betstats.getTiepercentage());

        returnPacket.setTotalGames(shoeSummary.getTotalGames());
        returnPacket.setBankerWin(shoeSummary.getBankerWinCounter());
        returnPacket.setPlayerWin(shoeSummary.getPlayerWinCounter());
        returnPacket.setTie(shoeSummary.getTieCounter());

        returnPacket.setBetsOpen(this.betsOpen != null);
        returnPacket.setBetsClosingSoon(this.betsClosingSoon != null);
        returnPacket.setBetsClosed(this.betsClosed != null);
        returnPacket.setStartDealing(this.startDealing != null);

        returnPacket.setCards(cards);

        returnPacket.setGameResult(this.gameResult);

        return returnPacket;
    }

    public BetStatsPacket getBetstats() {return betstats;}
    public void setBetstats(BetStatsPacket betstats) {this.betstats = betstats;}

    public ShoeSummaryPacket getShoeSummary() {return shoeSummary;}
    public void setShoeSummary(ShoeSummaryPacket shoeSummary) {this.shoeSummary = shoeSummary;}

    public GamePacket getGame() {return game;}
    public void setGame(GamePacket gameData) {this.game = gameData;}

    public GameResultPacket getGameResult() {return gameResult;}
    public void setGameResult(GameResultPacket gameResult) {this.gameResult = gameResult;}

    public GameEventPacket getBetsOpen() {return betsOpen;}
    public void setBetsOpen(GameEventPacket betsOpen) {this.betsOpen = betsOpen;}

    public GameEventPacket getBetsClosingSoon() {return betsClosingSoon;}
    public void setBetsClosingSoon(GameEventPacket betsClosingSoon) {this.betsClosingSoon = betsClosingSoon;}

    public GameEventPacket getBetsClosed() {return betsClosed;}
    public void setBetsClosed(GameEventPacket betsClosed) {this.betsClosed = betsClosed;}

    public GameEventPacket getStartDealing() {return startDealing;}
    public void setStartDealing(GameEventPacket startDealing) {this.startDealing = startDealing;}

    public CardPacket getCard() {return card;}
    public void setCard(CardPacket card) {this.card = card;}

    public void clearCards() {this.cards = null;}
    public void addCard(CardPacket cardPacket) {
        Card card = new Card();

        card.setPlace(cardPacket.getPlace());
        card.setCardCode(cardPacket.getSc());

        this.cards.add(card);
    }

    public class ReturnPacket {
        private String table;
        private String game;
    
        // Bet Stats
        private String totalBetsCount;
        private String bankerCount;
        private String bankerPercent;
        private String playerCount;
        private String playerPercent;
        private String tieCount;
        private String tiePercent;
    
        // Shoe Summary
        private String totalGames;
        private String bankerWin;
        private String playerWin;
        private String tie;
    
        // Game State Flags
        private boolean betsOpen;
        private boolean betsClosingSoon;
        private boolean betsClosed;
        private boolean startDealing;
    
        // Game Data
        private ArrayList<Card> cards;
        private GameResultPacket gameResult;

        public String getTable() {
            return table;
        }
        public void setTable(String table) {
            this.table = table;
        }
        public String getGame() {
            return game;
        }
        public void setGame(String game) {
            this.game = game;
        }
        public String getTotalBetsCount() {
            return totalBetsCount;
        }
        public void setTotalBetsCount(String totalBetsCount) {
            this.totalBetsCount = totalBetsCount;
        }
        public String getBankerCount() {
            return bankerCount;
        }
        public void setBankerCount(String bankerCount) {
            this.bankerCount = bankerCount;
        }
        public String getBankerPercent() {
            return bankerPercent;
        }
        public void setBankerPercent(String bankerPercent) {
            this.bankerPercent = bankerPercent;
        }
        public String getPlayerCount() {
            return playerCount;
        }
        public void setPlayerCount(String playerCount) {
            this.playerCount = playerCount;
        }
        public String getPlayerPercent() {
            return playerPercent;
        }
        public void setPlayerPercent(String playerPercent) {
            this.playerPercent = playerPercent;
        }
        public String getTieCount() {
            return tieCount;
        }
        public void setTieCount(String tieCount) {
            this.tieCount = tieCount;
        }
        public String getTiePercent() {
            return tiePercent;
        }
        public void setTiePercent(String tiePercent) {
            this.tiePercent = tiePercent;
        }
        public String getTotalGames() {
            return totalGames;
        }
        public void setTotalGames(String totalGames) {
            this.totalGames = totalGames;
        }
        public String getBankerWin() {
            return bankerWin;
        }
        public void setBankerWin(String bankerWin) {
            this.bankerWin = bankerWin;
        }
        public String getPlayerWin() {
            return playerWin;
        }
        public void setPlayerWin(String playerWin) {
            this.playerWin = playerWin;
        }
        public String getTie() {
            return tie;
        }
        public void setTie(String tie) {
            this.tie = tie;
        }
        public boolean isBetsOpen() {
            return betsOpen;
        }
        public void setBetsOpen(boolean betsOpen) {
            this.betsOpen = betsOpen;
        }
        public boolean isBetsClosingSoon() {
            return betsClosingSoon;
        }
        public void setBetsClosingSoon(boolean betsClosingSoon) {
            this.betsClosingSoon = betsClosingSoon;
        }
        public boolean isBetsClosed() {
            return betsClosed;
        }
        public void setBetsClosed(boolean betsClosed) {
            this.betsClosed = betsClosed;
        }
        public boolean isStartDealing() {
            return startDealing;
        }
        public void setStartDealing(boolean startDealing) {
            this.startDealing = startDealing;
        }
        public ArrayList<Card> getCards() {
            return cards;
        }
        public void setCards(ArrayList<Card> cards) {
            this.cards = cards;
        }
        public GameResultPacket getGameResult() {
            return gameResult;
        }
        public void setGameResult(GameResultPacket gameResult) {
            this.gameResult = gameResult;
        } 
    }

    public class Card {
        private String place;
        private String cardCode;
    
        public String getPlace() { return place; }
        public void setPlace(String place) { this.place = place; }
    
        public String getCardCode() { return cardCode; }
        public void setCardCode(String cardCode) { this.cardCode = cardCode; }
    }

    //Handles card packet.
    public class CardPacket {
        private String sc;
        private String game;
        private String cardCount;
        private String place;
        private String table;
        private int seq;
        private String value;
    
        public String getSc() { return sc; }
        public void setSc(String sc) { this.sc = sc; }
        public String getGame() { return game; }
        public void setGame(String game) { this.game = game; }
        public String getCardCount() { return cardCount; }
        public void setCardCount(String cardCount) { this.cardCount = cardCount; }
        public String getPlace() { return place; }
        public void setPlace(String place) { this.place = place; }
        public String getTable() { return table; }
        public void setTable(String table) { this.table = table; }
        public int getSeq() { return seq; }
        public void setSeq(int seq) { this.seq = seq; }
        public String getValue() { return value; }
        public void setValue(String value) { this.value = value; }
    }

    //Handles packets related to betting window.
    public class GameEventPacket {
        private String game;
        private String table;
        private int seq;
    
        public String getGame() { return game; }
        public void setGame(String game) { this.game = game; }
        public String getTable() { return table; }
        public void setTable(String table) { this.table = table; }
        public int getSeq() { return seq; }
        public void setSeq(int seq) { this.seq = seq; }
    }

    //Handles GameResult packet.
    public class GameResultPacket {

        private String id;
        private String result;
        private String score;
        private boolean natural;

        @JsonProperty("player_pair")
        private boolean playerPair;
        
        @JsonProperty("banker_pair")
        private boolean bankerPair;

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public String getResult() { return result; }
        public void setResult(String result) { this.result = result; }
        public String getScore() { return score; }
        public void setScore(String score) { this.score = score; }
        public boolean isNatural() { return natural; }
        public void setNatural(boolean natural) { this.natural = natural; }
        public boolean isPlayerPair() { return playerPair; }
        public void setPlayerPair(boolean playerPair) { this.playerPair = playerPair; }
        public boolean isBankerPair() { return bankerPair; }
        public void setBankerPair(boolean bankerPair) { this.bankerPair = bankerPair; }
    }

    //Handles Game packet.
    public class GamePacket {

        private String id;
        private long starttime;
        private String table;
        private int seq;
        private String value;
    
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public long getStarttime() { return starttime; }
        public void setStarttime(long starttime) { this.starttime = starttime; }
        public String getTable() { return table; }
        public void setTable(String table) { this.table = table; }
        public int getSeq() { return seq; }
        public void setSeq(int seq) { this.seq = seq; }
        public String getValue() { return value; }
        public void setValue(String value) { this.value = value; }
    }

    // Handles ShoeSummary packet.
    public static class ShoeSummaryPacket {

        private String tieCounter;
        private String playerPairCounter;
        private String totalGames;
        private String bankerWinCounter;
        private String table;
        private String playerWinCounter;
        private String bankerPairCounter;
        private int seq;

        public String getTieCounter() { return tieCounter; }
        public void setTieCounter(String tieCounter) { this.tieCounter = tieCounter; }
        public String getPlayerPairCounter() { return playerPairCounter; }
        public void setPlayerPairCounter(String playerPairCounter) { this.playerPairCounter = playerPairCounter; }
        public String getTotalGames() { return totalGames; }
        public void setTotalGames(String totalGames) { this.totalGames = totalGames; }
        public String getBankerWinCounter() { return bankerWinCounter; }
        public void setBankerWinCounter(String bankerWinCounter) { this.bankerWinCounter = bankerWinCounter; }
        public String getTable() { return table; }
        public void setTable(String table) { this.table = table; }
        public String getPlayerWinCounter() { return playerWinCounter; }
        public void setPlayerWinCounter(String playerWinCounter) { this.playerWinCounter = playerWinCounter; }
        public String getBankerPairCounter() { return bankerPairCounter; }
        public void setBankerPairCounter(String bankerPairCounter) { this.bankerPairCounter = bankerPairCounter; }
        public int getSeq() { return seq; }
        public void setSeq(int seq) { this.seq = seq; }
    }

    // Handles BetStats packet.
    public static class BetStatsPacket {

        private String table;
        private int seq;
        private String tietotal;
        private String tiepercentage;
        private String tiecount;
        private String totalBetscount;
        private String bankertotal;
        private String bankerpercentage;
        private String bankercount;
        private String playertotal;
        private String playerpercentage;
        private String playercount;

        public String getTable() { return table; }
        public void setTable(String table) { this.table = table; }
        public int getSeq() { return seq; }
        public void setSeq(int seq) { this.seq = seq; }
        public String getTietotal() { return tietotal; }
        public void setTietotal(String tietotal) { this.tietotal = tietotal; }
        public String getTiepercentage() { return tiepercentage; }
        public void setTiepercentage(String tiepercentage) { this.tiepercentage = tiepercentage; }
        public String getTiecount() { return tiecount; }
        public void setTiecount(String tiecount) { this.tiecount = tiecount; }
        public String getTotalBetscount() { return totalBetscount; }
        public void setTotalBetscount(String totalBetscount) { this.totalBetscount = totalBetscount; }
        public String getBankertotal() { return bankertotal; }
        public void setBankertotal(String bankertotal) { this.bankertotal = bankertotal; }
        public String getBankerpercentage() { return bankerpercentage; }
        public void setBankerpercentage(String bankerpercentage) { this.bankerpercentage = bankerpercentage; }
        public String getBankercount() { return bankercount; }
        public void setBankercount(String bankercount) { this.bankercount = bankercount; }
        public String getPlayertotal() { return playertotal; }
        public void setPlayertotal(String playertotal) { this.playertotal = playertotal; }
        public String getPlayerpercentage() { return playerpercentage; }
        public void setPlayerpercentage(String playerpercentage) { this.playerpercentage = playerpercentage; }
        public String getPlayercount() { return playercount; }
        public void setPlayercount(String playercount) { this.playercount = playercount; }
    }
}
