/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.comparator;

import cz.fi.muni.pa165.entity.Player;
import java.util.Comparator;

/**
 *
 * @author maceek
 */
public class SortByGoals  implements Comparator<Player> {

    private SortByName byName;

    @Override
    public int compare(Player p1, Player p2) {

        if (p1.getGoals().size() > p2.getGoals().size()) {
            return -1;
        } else if (p1.getGoals().size() < p2.getGoals().size()) {
            return 1;
        } else {
                byName = new SortByName();
                return byName.compare(p1, p2);
            }
    }
}
