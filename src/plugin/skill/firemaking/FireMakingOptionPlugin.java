package plugin.skill.firemaking;

import org.wildscape.game.content.global.tutorial.TutorialSession;
import org.wildscape.game.content.global.tutorial.TutorialStage;
import org.wildscape.game.content.skill.free.firemaking.FireMakingPulse;
import org.wildscape.game.interaction.NodeUsageEvent;
import org.wildscape.game.interaction.UseWithHandler;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.item.Item;
import org.wildscape.plugin.Plugin;

/**
 * Represents the plugin used to handle the lighting of a fire.
 * @author 'Vexia
 * @version 1.0
 */
public final class FireMakingOptionPlugin extends UseWithHandler {

	/**
	 * Constructs a new {@code FireMakingOptionPlugin} {@code Object}.
	 */
	public FireMakingOptionPlugin() {
		super(1511, 1521, 1513, 1515, 1517, 1519, 1521, 2862, 3438, 3440, 3442, 3444, 3446, 3448, 6211, 6213, 6332, 6333, 7404, 7405, 7406, 8934, 9067, 10328, 10329, 10808, 10810, 10812, 11035, 12581, 12583, 3125);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		addHandler(590, ITEM_TYPE, this);
		return this;
	}

	@Override
	public boolean handle(NodeUsageEvent event) {
		Item item = event.getUsedItem();
		if (item == null) {
			return false;
		}
		Player player = event.getPlayer();
		if (TutorialSession.getExtension(player).getStage() == 8) {
			TutorialStage.load(player, 9, false);
		}
		player.getPulseManager().run(new FireMakingPulse(player, item, null));
		return true;
	}

}
