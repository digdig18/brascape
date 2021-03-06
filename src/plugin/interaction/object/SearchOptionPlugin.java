package plugin.interaction.object;

import org.wildscape.cache.def.impl.ObjectDefinition;
import org.wildscape.game.interaction.OptionHandler;
import org.wildscape.game.node.Node;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.item.Item;
import org.wildscape.game.node.object.GameObject;
import org.wildscape.plugin.Plugin;

/**
 * Handles the search option.
 * @author 'Vexia
 */
public class SearchOptionPlugin extends OptionHandler {

	/**
	 * Represents an object to search.
	 * @author 'Vexia
	 */
	public enum Search {
		/**
		 * Represents a search.
		 */
		DEFAULT(-1, new Item(1059, 1));

		public static Search forId(int id) {
			for (Search search : Search.values()) {
				if (search.getObject() == id) {
					return search;
				}
			}
			return null;
		}

		/**
		 * The object id.
		 */
		private int object;

		/**
		 * The item rewarded.
		 */
		private Item item;

		/**
		 * Constructs a new {@code SearchOptionPlugin.java} {@Code
		 * Object}
		 * @param object
		 * @param item
		 */
		Search(int object, Item item) {
			this.object = object;
			this.item = item;
		}

		/**
		 * @return the item.
		 */
		public Item getItem() {
			return item;
		}

		/**
		 * @return the object.
		 */
		public int getObject() {
			return object;
		}
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		if (node.getName().equals("Bookcase")) {
			player.getPacketDispatch().sendMessage("You search the books...");
			player.getPacketDispatch().sendMessage("You find nothing of interest to you.");
			return true;
		}
		player.getPacketDispatch().sendMessage("You search the " + ((GameObject) node).getName().toLowerCase() + " but find nothing.");
		return true;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.setOptionHandler("search", this);
		return this;
	}
}
