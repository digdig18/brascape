package plugin.interaction.inter;

import org.wildscape.game.component.Component;
import org.wildscape.game.component.ComponentDefinition;
import org.wildscape.game.component.ComponentPlugin;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.entity.player.link.grave.GraveType;
import org.wildscape.game.node.item.Item;
import org.wildscape.plugin.Plugin;

/**
 * Represents the component plugin used for the grave purchasing interface.
 * @author Vexia
 */
public final class GravePurchaseInterface extends ComponentPlugin {

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
	ComponentDefinition.put(652, this);
	return this;
    }

    @Override
    public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
	if (slot == -1) {
	    return true;
	}
	final GraveType grave = GraveType.values()[slot];
	int cost = grave.getCost();
	if (!player.getInventory().containsItem(new Item(995, cost))) {
	    return true;
	}
	if (!player.getInventory().remove(new Item(995, cost)) && grave != GraveType.MEMORIAL_PLAQUE) {
	    player.getPacketDispatch().sendMessage("You don't have enough coins to buy this grave stone.");
	    return true;
	}
	player.getGraveManager().setType(grave);
	player.getDialogueInterpreter().sendDialogue("Your gravestone has been changed as you requested.");
	player.getInterfaceManager().close();
	return true;
    }
}
