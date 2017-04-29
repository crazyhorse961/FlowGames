package it.minecube.flowgames.api;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Gio on 23/04/2017.
 */
public final class ItemBuilder {

    private static ItemBuilder instance;
    public static void setInstance(ItemBuilder instance) {
        ItemBuilder.instance = instance;
    }

    private static Material type;
    private short data = 0;
    private String name = "";
    private List<String> lore;
    private HashMap<Enchantment, Integer> enchantments = new HashMap<>();
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
        this.enchantments.put(enchantment, 1);
        return this;
    }

    public ItemBuilder enchant(Enchantment enchantment, int power) {
        this.enchantments.put(enchantment, power);
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
        if(!enchantments.isEmpty()) {
            enchantments.keySet().forEach(e -> meta.addEnchant(e, enchantments.get(e), true));
        }
        meta.addItemFlags(flags.toArray(new ItemFlag[flags.size()]));
        stack.setItemMeta(meta);
        return stack;
    }
}
