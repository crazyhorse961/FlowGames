package it.minecube.flowgames.api;/**
 * Created by nini7 on 23.04.2017.
 */

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author crazyhoorse961
 */
public class MenuBuilder
{

    public Menu newMenu(){
        return new Menu();
    }

    public static MenuBuilder get(){
        return new MenuBuilder();
    }

    public class Menu{

        private int size;

        private Map<Integer, ItemStack> items = new HashMap<Integer, ItemStack>();

        private Menu menu;

        public Menu(){
            this.menu = this;
        }

        public Menu setSize(int size){
            this.size = size;
            return menu;
        }

        public Menu item(ItemStack item, int slot){
            items.put(slot,item);
            return menu;
        }
    }
}
