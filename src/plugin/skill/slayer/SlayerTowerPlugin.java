package plugin.skill.slayer;

import org.wildscape.cache.def.impl.ObjectDefinition;
import org.wildscape.game.content.global.action.DoorActionHandler;
import org.wildscape.game.interaction.OptionHandler;
import org.wildscape.game.node.Node;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.object.GameObject;
import org.wildscape.game.node.object.ObjectBuilder;
import org.wildscape.game.world.map.Location;
import org.wildscape.game.world.map.RegionManager;
import org.wildscape.plugin.Plugin;

/**
 * Handles the interactions for the slayer tower.
 * @author Vexia
 */
public final class SlayerTowerPlugin extends OptionHandler {

	/**
	 * The locations of the states.
	 */
	private static final Location[] LOCATIONS = new Location[] { new Location(3430, 3534, 0), new Location(3426, 3534, 0) };

	/**
	 * The open id.
	 */
	private static final int OPEN_ID = 5117;

	/**
	 * The closed id.
	 */
	private static final int CLOSED_ID = 5116;

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(4490).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(4487).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(4492).getConfigurations().put("option:close", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		switch (node.getId()) {
		case 4490:
		case 4487:
			DoorActionHandler.handleAutowalkDoor(player, (GameObject) node);
			switchStatue();
			return true;
		}
		return true;
	}

	/**
	 * Switches the object id of the statue.
	 */
	private void switchStatue() {
		for (Location l : LOCATIONS) {
			GameObject object = RegionManager.getObject(l);
			if (object != null) {
				int id = object.getId() == OPEN_ID ? CLOSED_ID : OPEN_ID;
				ObjectBuilder.replace(object, object.transform(id));
			}
		}
	}
}
