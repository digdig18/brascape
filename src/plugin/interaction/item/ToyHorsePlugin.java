package plugin.interaction.item;

import org.wildscape.cache.def.impl.ItemDefinition;
import org.wildscape.game.interaction.OptionHandler;
import org.wildscape.game.node.Node;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.item.Item;
import org.wildscape.game.world.update.flag.context.Animation;
import org.wildscape.plugin.Plugin;
import org.wildscape.tools.RandomFunction;

/**
 * Handles the option for the toy horse.
 * @author Vexia
 */
public class ToyHorsePlugin extends OptionHandler {

	/**
	 * The force chat's you can say.
	 */
	private static final String CHATS[] = { "Come-on Dobbin, we can win the race!", "Hi-ho Silver, and away!", "Neaahhhyyy!" };

	@Override
	public boolean handle(Player player, Node node, String option) {
		player.lock(2);
		int id = ((Item) node).getId();
		int anim = id == 2524 ? 920 : id == 2526 ? 921 : id == 2522 ? 919 : 918;
		player.animate(new Animation(anim));
		player.sendChat(CHATS[RandomFunction.random(CHATS.length)]);
		return true;
	}

	@Override
	public boolean isWalk() {
		return false;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		int[] ids = new int[] { 2524, 2526, 2522 };
		for (int id : ids) {
			ItemDefinition.forId(id).getConfigurations().put("play-with", this);
		}
		return this;
	}
}
