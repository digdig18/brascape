package plugin.npc.revenant;

import org.wildscape.game.container.impl.EquipmentContainer;
import org.wildscape.game.node.entity.Entity;
import org.wildscape.game.node.entity.combat.equipment.DegradableEquipment;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.item.Item;
import org.wildscape.plugin.PluginManager;

/**
 * Handles the degrading of corrupt equipment.
 * @author Vexia
 *
 */
public class CorruptEquipment extends DegradableEquipment {

	/**
	 * Constructs a new @{Code CorruptEquipment} object.
	 * @param slot The slot.
	 * @param itemIds The item ids.
	 */
	public CorruptEquipment(int slot, int[] itemIds) {
		super(slot, itemIds);
	}
	
	/**
	 * Initializes the corrupt equipment degarding.
	 */
	public static void init() {
		PluginManager.definePlugin(new CorruptEquipment(EquipmentContainer.SLOT_CHEST, new int[] {13958, 13960}));
	}

	@Override
	public void degrade(Player player, Entity entity, Item item) {
		
	}

	@Override
	public int getDropItem(int itemId) {
		return 0;
	}

}
