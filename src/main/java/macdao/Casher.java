package macdao;

import java.util.HashMap;

/**
 * Created by yjshi on 3/26/16.
 */
public class Casher {
    public HashMap<Integer, Integer> getCasher() {
        return casher;
    }

    public void setCasher(HashMap<Integer, Integer> casher) {
        this.casher = casher;
    }

    HashMap<Integer,Integer> casher = new HashMap<>();
}
