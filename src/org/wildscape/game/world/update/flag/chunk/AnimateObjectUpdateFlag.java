package org.wildscape.game.world.update.flag.chunk;

import org.wildscape.game.world.update.flag.UpdateFlag;
import org.wildscape.game.world.update.flag.context.Animation;
import org.wildscape.net.packet.IoBuffer;
import org.wildscape.net.packet.out.AnimateObjectPacket;

/**
 * The animate object update flag.
 * @author Emperor
 */
public final class AnimateObjectUpdateFlag extends UpdateFlag<Animation> {

	/**
	 * Constructs a new {@code AnimateObjectUpdateFlag} {@code Object}.
	 * @param context The animation.
	 */
	public AnimateObjectUpdateFlag(Animation context) {
		super(context);
	}

	@Override
	public void write(IoBuffer buffer) {
		AnimateObjectPacket.write(buffer, context);
	}

	@Override
	public int data() {
		return 0;
	}

	@Override
	public int ordinal() {
		return 0;
	}

}