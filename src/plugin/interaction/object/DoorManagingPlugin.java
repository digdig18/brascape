package plugin.interaction.object;

import org.wildscape.cache.def.impl.ObjectDefinition;
import org.wildscape.game.content.global.action.DoorActionHandler;
import org.wildscape.game.interaction.OptionHandler;
import org.wildscape.game.node.Node;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.object.GameObject;
import org.wildscape.game.world.map.Location;
import org.wildscape.game.world.map.RegionManager;
import org.wildscape.plugin.Plugin;

/**
 * Plugin used for handling the opening/closing of (double)
 * doors/gates/fences/...
 * @author Emperor
 */
public final class DoorManagingPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.setOptionHandler("open", this);
		ObjectDefinition.setOptionHandler("close", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		GameObject object = (GameObject) node;
		if (object.getType() != 9 && !player.getLocation().equals(node.getLocation()) && !player.getLocation().isNextTo(object)) {
			return true;
		}
		String name = object.getName().toLowerCase();
		if (name.contains("trapdoor") || name.contains("trap door")) {
			Location destination = object.getLocation().transform(0, 6400, 0);
			if (!RegionManager.isTeleportPermitted(destination)) {
				player.getPacketDispatch().sendMessage("This doesn't seem to go anywhere.");
				return true;
			}
			player.getProperties().setTeleportLocation(destination);
			return true;
		}
		if (node.asObject().getId() == 25341) {// Mithril door
			return false;
		}
		if (!name.contains("door") && !name.contains("gate") && !name.contains("fence") && !name.contains("wall") && !name.contains("exit") && !name.contains("entrance")) {
			return false;
		}
		DoorActionHandler.handleDoor(player, object);
		return true;
	}

	@Override
	public Location getDestination(Node n, Node node) {
		GameObject o = (GameObject) node;
		if (o.getType() < 4 || o.getType() == 9) {
			return DoorActionHandler.getDestination((Player) n, o);
		}
		return null;
	}

}