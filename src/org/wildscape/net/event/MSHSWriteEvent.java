package org.wildscape.net.event;

import java.nio.ByteBuffer;

import org.wildscape.cache.misc.buffer.ByteBufferUtils;
import org.wildscape.net.IoSession;
import org.wildscape.net.IoWriteEvent;

/**
 * Handles the management server handshake write event.
 * @author Emperor
 */
public final class MSHSWriteEvent extends IoWriteEvent {

	/**
	 * The password used to verify
	 */
	private static final String PASSWORD = "0x14ari0SSbh98989910";

	/**
	 * Constructs a new {@code MSHSWriteEvent} {@code Object}
	 * @param session The session.
	 * @param context The context.
	 */
	public MSHSWriteEvent(IoSession session, Object context) {
		super(session, context);
	}

	@Override
	public void write(IoSession session, Object context) {
		ByteBuffer	buffer = ByteBuffer.allocate(2 + PASSWORD.length());
		buffer.put((byte) 88);
		ByteBufferUtils.putString(PASSWORD, buffer);
		session.queue((ByteBuffer) buffer.flip());
	}

}