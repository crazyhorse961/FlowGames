package it.minecube.flowgames.api.kits;/**
 * Created by nini7 on 23.04.2017.
 */

import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.List;

/**
 * @author crazyhoorse961
 */
public abstract class Kit {

    private String name;
    private ItemStack[] contents;
    private List<PotionEffect> abilities = new ArrayList<>();

    public Kit(String name, ItemStack...contents){
        this.name = name;
        this.contents = contents;
    }

    public String getName() {
        return name;
    }

    public ItemStack[] getContents() {
        return contents;
    }

    public List<PotionEffect> getAbilities() {
        return abilities;
    }

    public void setAbility(PotionEffect effect, int power) {
        this.abilities.add(effect);
    }

    public abstract ItemStack getRepresentativeItem();
}
