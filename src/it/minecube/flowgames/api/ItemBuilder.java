package it.minecube.flowgames.api;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Gio on 23/04/2017.
 */
public class ItemBuilder {

    private static ItemBuilder instance;
    public static void setInstance(ItemBuilder instance) {
        ItemBuilder.instance = instance;
    }

    private static Material type;
    private short data = 0;
    private String name = "";
    private List<String> lore;
    private Enchantment enchantment;
    private List<ItemFlag> flags;

    public static ItemBuilder type(Material type) {
        ItemBuilder.type = type;
        return instance;
    }

    public ItemBuilder withData(int data) {
        this.data = (short) data;
        return this;
    }

    public ItemBuilder enchant(Enchantment enchantment) {
        this.enchantment = enchantment;
        return this;
    }

    public ItemBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder lore(String...lore) {
        this.lore = Arrays.asList(lore);
        return this;
    }

    public ItemBuilder addFlag(ItemFlag flag) {
        flags.add(flag);
        return this;
    }

    public ItemStack build() {
        ItemStack stack = new ItemStack(type, 1, data);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(name);
        if(lore != null)
            meta.setLore(lore);
        if(enchantment != null) {
            meta.addEnchant(enchantment, 1, true);
        }
        meta.addItemFlags(flags.toArray(new ItemFlag[flags.size()]));
        stack.setItemMeta(meta);
        return stack;
    }
}
