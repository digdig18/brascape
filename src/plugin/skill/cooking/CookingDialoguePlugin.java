package plugin.skill.cooking;

import org.wildscape.game.content.dialogue.DialoguePlugin;
import org.wildscape.game.content.global.consumable.Consumables;
import org.wildscape.game.content.global.consumable.Food;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.entity.player.link.RunScript;
import org.wildscape.game.node.item.Item;
import org.wildscape.game.node.object.GameObject;
import org.wildscape.net.packet.PacketRepository;
import org.wildscape.net.packet.context.ChildPositionContext;
import org.wildscape.net.packet.out.RepositionChild;

/**
 * Represents the dialogue used to handle the amount to make of a cookable item.
 * @author 'Vexia
 * @version 1.0
 */
public final class CookingDialoguePlugin extends DialoguePlugin {

	/**
	 * Represents the cooking dialogue id.
	 */
	public static final int DIALOGUE_ID = 43989;

	/**
	 * Represents the sinew item.
	 */
	private static final Item SINEW = new Item(9436);

	/**
	 * Represents the meat item.
	 */
	private static final Item MEAT = new Item(2142);

	/**
	 * Represents the food we're cooking.
	 */
	private Food food;

	/**
	 * Represents the obejct we're cooking on.
	 */
	private GameObject object;

	/**
	 * Constructs a new {@code CookingDialoguePlugin} {@code Object}.
	 */
	public CookingDialoguePlugin() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code CookingDialoguePlugin} {@code Object}.
	 * @param player the player.
	 */
	public CookingDialoguePlugin(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new CookingDialoguePlugin(player);
	}

	@Override
	public boolean open(Object... args) {
		food = ((Food) args[0]);
		object = ((GameObject) args[1]);
		if (args.length == 3) {
			interpreter.sendOptions("Select an Option", "Dry the meat into sinew.", "Cook the meat.");
			stage = 100;
			return true;
		}
		if (player.getInventory().getAmount(food.getRaw()) == 1) {
			end();
			food.cook(player, object, 1);
			return true;
		}
		display();
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			end();
			int amount = getAmount(buttonId);
			if (amount == -1) {
				player.setAttribute("runscript", new RunScript() {
					@Override
					public boolean handle() {
						int amount = (int) value;
						food.cook(player, object, amount);
						return false;
					}
				});
				player.getDialogueInterpreter().sendInput(false, "Enter the amount:");
			}
			food.cook(player, object, amount);
			break;
		case 100:
			switch (buttonId) {
			case 1:
				food = Consumables.forFood(SINEW);
				display();
				break;
			case 2:
				food = Consumables.forFood(MEAT);
				display();
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { DIALOGUE_ID };
	}

	/**
	 * Method used to display the content food.
	 */
	public void display() {
		player.getInterfaceManager().openChatbox(307);
		PacketRepository.send(RepositionChild.class, new ChildPositionContext(player, 307, 3, 60, 79));
		player.getPacketDispatch().sendItemZoomOnInterface(food.getRaw().getId(), 160, 307, 2);
		player.getPacketDispatch().sendString(food.getRaw().getName(), 307, 3);
		stage = 0;
	}

	/**
	 * Method used to get the amount to make based off the button id.
	 * @param buttonId the button id.
	 * @return the amount to make.
	 */
	private final int getAmount(final int buttonId) {
		switch (buttonId) {
		case 5:
			return 1;
		case 4:
			return 5;
		case 3:
			return -1;
		}
		return -1;
	}

}