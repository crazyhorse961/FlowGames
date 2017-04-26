package it.minecube.flowgames.placeholders;/**
 * Created by nini7 on 23.04.2017.
 */


import it.minecube.flowgames.FlowGames;
import it.minecube.flowgames.api.players.MinigamePlayer;
import me.clip.placeholderapi.external.EZPlaceholderHook;
import org.bukkit.entity.Player;

/**
 * @author crazyhoorse961
 */
public class CoinPlaceholder extends EZPlaceholderHook
{

    public CoinPlaceholder(FlowGames plugin){
        super(plugin, "coins");
    }

    @Override
    public String onPlaceholderRequest(Player player, String s) {
        return String.valueOf(new MinigamePlayer(player).getCoins());
    }
}
