package org.wildscape.game.world.update.flag.npc;

import org.wildscape.game.world.update.flag.UpdateFlag;
import org.wildscape.game.world.update.flag.context.Graphics;
import org.wildscape.net.packet.IoBuffer;

/**
 * Handles an NPC's graphic update flag.
 * @author Emperor
 *
 */
public final class NPCGraphic extends UpdateFlag<Graphics> {

	/**
	 * Constructs a new {@code NPCGraphic} {@code Object}.
	 * @param context The graphics.
	 */
	public NPCGraphic(Graphics context) {
		super(context);
	}

	@Override
	public void write(IoBuffer buffer) {
		buffer.putShortA(context.getId()).putLEInt(context.getHeight() << 16 | context.getDelay());
	}

	@Override
	public int data() {
		return maskData();
	}

	@Override
	public int ordinal() {
		return 4;
	}

	/**
	 * Gets the mask data.
	 * @return The mask data.
	 */
	public static int maskData() {
		return 0x80;
	}

}