package rainbot.baccarat;

import rainbot.components.Request;

public class BaccaratRequest extends Request {
    
    private PacketObject betstats;
    
    public PacketObject get() {
        return betstats;
    }

    public void set(PacketObject packet) {
        this.betstats = packet;
    }

    class PacketObject {

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

        public String getTable() {
            return table;
        }
    
        public void setTable(String table) {
            this.table = table;
        }
    
        public int getSeq() {
            return seq;
        }
    
        public void setSeq(int seq) {
            this.seq = seq;
        }
    
        public String getTietotal() {
            return tietotal;
        }
    
        public void setTietotal(String tietotal) {
            this.tietotal = tietotal;
        }
    
        public String getTiepercentage() {
            return tiepercentage;
        }
    
        public void setTiepercentage(String tiepercentage) {
            this.tiepercentage = tiepercentage;
        }
    
        public String getTiecount() {
            return tiecount;
        }
    
        public void setTiecount(String tiecount) {
            this.tiecount = tiecount;
        }
    
        public String getTotalBetscount() {
            return totalBetscount;
        }
    
        public void setTotalBetscount(String totalBetscount) {
            this.totalBetscount = totalBetscount;
        }
    
        public String getBankertotal() {
            return bankertotal;
        }
    
        public void setBankertotal(String bankertotal) {
            this.bankertotal = bankertotal;
        }
    
        public String getBankerpercentage() {
            return bankerpercentage;
        }
    
        public void setBankerpercentage(String bankerpercentage) {
            this.bankerpercentage = bankerpercentage;
        }
    
        public String getBankercount() {
            return bankercount;
        }
    
        public void setBankercount(String bankercount) {
            this.bankercount = bankercount;
        }
    
        public String getPlayertotal() {
            return playertotal;
        }
    
        public void setPlayertotal(String playertotal) {
            this.playertotal = playertotal;
        }
    
        public String getPlayerpercentage() {
            return playerpercentage;
        }
    
        public void setPlayerpercentage(String playerpercentage) {
            this.playerpercentage = playerpercentage;
        }
    
        public String getPlayercount() {
            return playercount;
        }
    
        public void setPlayercount(String playercount) {
            this.playercount = playercount;
        }
    }

}
