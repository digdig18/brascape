package org.wildscape.net.packet.out;

import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.system.communication.Contact;
import org.wildscape.net.amsc.WorldCommunicator;
import org.wildscape.net.packet.IoBuffer;
import org.wildscape.net.packet.OutgoingPacket;
import org.wildscape.net.packet.PacketHeader;
import org.wildscape.net.packet.context.ContactContext;
import org.wildscape.tools.StringUtils;

/**
 * Handles the contact packet sending.
 * @author Emperor
 */
public final class ContactPackets implements OutgoingPacket<ContactContext> {

	@Override
	public void send(ContactContext context) {
		IoBuffer buffer = null;
		Player player = context.getPlayer();
		switch (context.getType()) {
		case ContactContext.UPDATE_STATE_TYPE:
			buffer = new IoBuffer(197).put(WorldCommunicator.getState().value());
			break;
		case ContactContext.IGNORE_LIST_TYPE:
			buffer = new IoBuffer(126, PacketHeader.SHORT);
			for (String string : player.getCommunication().getBlocked()) {
				if (string.length() == 0) {
					continue;
				}
				buffer.putLong(StringUtils.stringToLong(string));
			}
			break;
		case ContactContext.UPDATE_FRIEND_TYPE:
			buffer = new IoBuffer(62, PacketHeader.BYTE);
			buffer.putLong(StringUtils.stringToLong(context.getName()));
			buffer.putShort(context.getWorldId());
			Contact c = player.getCommunication().getContacts().get(context.getName());
			if (c != null) {
				buffer.put((byte) c.getRank().getValue());
			} else {
				buffer.put((byte) 0);
			}
			if (context.isOnline()) {
				buffer.putString("World " + context.getWorldId());
			}
			break;
		}
		if (buffer != null) {
			player.getSession().write(buffer);
		}
	}

}