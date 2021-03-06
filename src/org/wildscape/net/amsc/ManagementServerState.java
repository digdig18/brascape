package org.wildscape.net.amsc;

import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.world.repository.Repository;
import org.wildscape.net.packet.PacketRepository;
import org.wildscape.net.packet.context.ContactContext;
import org.wildscape.net.packet.out.ContactPackets;

/**
 * The management server state.
 * @author Emperor
 */
public enum ManagementServerState {

	/**
	 * If the management server is not available.
	 */
	NOT_AVAILABLE(2),

	/**
	 * If we're still connecting to the Management server.
	 */
	CONNECTING(1),

	/**
	 * If we're connected to the management server.
	 */
	AVAILABLE(2);

	/**
	 * The value of this state.
	 */
	private final int value;

	/**
	 * Constructs a new {@code ManagementServerState} {@code Object}
	 * @param value The value.
	 */
	private ManagementServerState(int value) {
		this.value = value;
	}

	/**
	 * Called when the state gets set.
	 */
	public void set() {
		for (Player player : Repository.getPlayers()) {
			PacketRepository.send(ContactPackets.class, new ContactContext(player, ContactContext.UPDATE_STATE_TYPE));
		}
	}

	/**
	 * Gets the state value.
	 * @return The value.
	 */
	public int value() {
		return value;
	}
}