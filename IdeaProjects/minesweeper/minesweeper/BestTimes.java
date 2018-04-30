package minesweeper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Player times.
 */
public class BestTimes implements Iterable<BestTimes.PlayerTime> {
    /** List of best player times. */
    private List<PlayerTime> playerTimes = new ArrayList<PlayerTime>();

    /**
     * Returns an iterator over a set of  best times.
     * @return an iterator
     */
    public Iterator<PlayerTime> iterator() {
        return playerTimes.iterator();
    }

    /**
     * Adds player time to best times.
     * @param name name ot the player
     * @param time player time in seconds
     */
    public void addPlayerTime(String name, int time) {
        playerTimes.add(new PlayerTime(name, time));
        Collections.sort(playerTimes);

    }

    /**
     * Returns a string representation of the object.
     * @return a string representation of the object
     */
    public String toString() {
       int placement=1;
       StringBuilder leaderboard = new StringBuilder();
        for(PlayerTime pt:playerTimes){
            leaderboard.append(placement+". "+pt.getName()+"- "+pt.getTime()+" s"+System.lineSeparator());
            placement++;
        }
        return leaderboard.toString();
    }

    public void reset(){
        playerTimes.clear();
    }
    /**
     * Player time.
     */
    public static class PlayerTime implements Comparable<PlayerTime> {
        private final String name;

        private final int time;


        /**
         * Constructor.
         * @param name player name
         * @param time playing game time in seconds
         */
        public PlayerTime(String name, int time) {
            this.name = name;
            this.time = time;
        }

        /** Player name. */
        public String getName() {
            return name;
        }

        /** Playing time in seconds. */
        public int getTime() {
            return time;
        }

        @Override
        public int compareTo(PlayerTime o) {
            return this.compareTo(o);
        }


    }
}