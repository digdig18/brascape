package plugin.npc.quest.priest_in_peril;

import org.wildscape.game.node.entity.Entity;
import org.wildscape.game.node.entity.npc.AbstractNPC;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.entity.player.link.quest.Quest;
import org.wildscape.game.node.item.GroundItemManager;
import org.wildscape.game.node.item.Item;
import org.wildscape.game.world.map.Location;

/**
 * Represents the monk of zamorak npc.
 * @author 'Vexia
 * @version 1.0
 */
public final class MonkOfZamorakNPC extends AbstractNPC {

	/**
	 * The NPC ids of NPCs using this plugin.
	 */
	private static final int[] ID = { 1046 };

	/**
	 * Represents the golden key.
	 */
	private static final Item GOLDEN_KEY = new Item(2944, 1);

	/**
	 * Constructs a new {@code MonkOfZamorakNPC} {@code Object}.
	 */
	public MonkOfZamorakNPC() {
		super(0, null);
	}

	/**
	 * Constructs a new {@code AlKharidWarriorPlugin} {@code Object}.
	 * @param id The NPC id.
	 * @param location The location.
	 */
	private MonkOfZamorakNPC(int id, Location location) {
		super(id, location);
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new MonkOfZamorakNPC(id, location);
	}

	@Override
	public void tick() {
		super.tick();
	}

	@Override
	public void finalizeDeath(final Entity killer) {
		super.finalizeDeath(killer);
		final Player p = ((Player) killer);
		final Quest quest = p.getQuestRepository().getQuest("Priest in Peril");
		if (quest.isStarted(p)) {
			GroundItemManager.create(GOLDEN_KEY, getLocation(), p);
		}
	}

	@Override
	public int[] getIds() {
		return ID;
	}

}
