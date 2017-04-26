package it.minecube.flowgames.api.kits;/**
 * Created by nini7 on 23.04.2017.
 */

import com.google.common.io.Files;
import it.minecube.flowgames.FlowGames;
import it.minecube.flowgames.api.Minigame;
import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang.Validate;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * @author crazyhoorse961
 */
public class Kit
{
    private ItemStack[] contents;

    private String name;


    public Kit(String name, ItemStack...contents){
        this.name = name;
        this.contents = contents;

    }

    public void save(){
        Validate.notEmpty(name);
        Validate.notEmpty(contents);
        Minigame minigame = FlowGames.getInstance().getMinigame();
        byte[] contentBytes = SerializationUtils.serialize(contents);
        try {
            Files.write(contentBytes, new File(minigame.getDataFolder() + File.separator + "kits", "kit-" + name + ".txt"));
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public static Kit fromBytes(byte[] bytes){
        return (Kit) SerializationUtils.deserialize(bytes);
    }

    public static Kit fromName(String name){
        Minigame minigame = FlowGames.getInstance().getMinigame();
        for(File allFiles : new File(minigame.getDataFolder() + File.separator + "kits").listFiles()){
            if(allFiles.getName().equals("kit-" + name + ".txt")){
                try {
                    byte[] bytes = java.nio.file.Files.readAllBytes(Paths.get(allFiles.toURI()));
                    return (Kit) SerializationUtils.deserialize(bytes);
                }catch(IOException ex){
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

    public ItemStack[] getContents() {
        return contents;
    }

    public String getName() {
        return name;
    }
}
