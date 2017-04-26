package it.minecube.flowgames.api;/**
 * Created by nini7 on 23.04.2017.
 */

import it.minecube.flowgames.exceptions.InvalidMinigameException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

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

        private Integer size;

        private Map<Integer, ItemStack> items = new HashMap<>();

        private InventoryType type;

        private Player player;

        private String name;

        private Menu menu;

        public Menu(){
            this.menu = this;
        }

        public Menu size(Integer size){
            this.size = size;
            return menu;
        }

        public Menu item(ItemStack item, int slot){
            items.put(slot,item);
            return menu;
        }

        public Menu type(InventoryType type){
            this.type = type;
            return menu;
        }

        public Menu player(Player holder){
            this.player = holder;
            return menu;
        }

        public Menu name(String name){
            this.name = name;
            return menu;
        }

        public Inventory build(){
            Inventory inv;
            if(type == null){
                inv = Bukkit.createInventory(player, size, name);
            }else if(size == null){
                inv = Bukkit.createInventory(player, size, name);
            }else{
                throw new InvalidMinigameException("Both size and type are null!");
            }
            for(int item : items.keySet()){
                ItemStack ofInt = items.get(item);
                inv.setItem(item, ofInt);
            }
            return inv;
        }
    }

    class InvalidMenuException extends RuntimeException{

        public InvalidMenuException(){}

        public InvalidMenuException(String msg){ super(msg); }

    }
}
