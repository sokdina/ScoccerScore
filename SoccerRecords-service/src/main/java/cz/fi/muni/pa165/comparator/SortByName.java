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
public class SortByName implements Comparator<Player> {

    @Override
    public int compare(Player p1, Player p2) {
        if (p1.getName().compareTo(p2.getName()) > 0) {
            return 1;
        } else{
            return -1;
        } 
    }
}