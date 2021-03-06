package plugin.interaction.inter;

import org.wildscape.game.component.Component;
import org.wildscape.game.component.ComponentDefinition;
import org.wildscape.game.component.ComponentPlugin;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.entity.player.info.login.LoginConfiguration;
import org.wildscape.game.system.task.Pulse;
import org.wildscape.plugin.Plugin;

/**
 * Represents the plugin used for the login interface.
 * @author 'Vexia
 * @version 1.0
 */
public final class LoginInterfacePlugin extends ComponentPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.put(378, this);
		return null;
	}

	@Override
	public boolean handle(final Player player, Component component, int opcode, int button, int slot, int itemId) {
		switch (button) {
		case 140:
			if (player.getLocks().isLocked("login")) {
				return true;
			}
			player.getLocks().lock("login", 2);
			player.getInterfaceManager().close();
			player.getPulseManager().run(new Pulse(1) {
				@Override
				public boolean pulse() {
					LoginConfiguration.configureGameWorld(player);
					return true;
				}
			});
			break;
		case 145://credits
			break;
		case 204://message centre
			break;
		}
		return true;
	}

}
