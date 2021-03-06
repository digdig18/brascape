package plugin.combat.special;

import org.wildscape.game.content.skill.Skills;
import org.wildscape.game.node.entity.Entity;
import org.wildscape.game.node.entity.combat.BattleState;
import org.wildscape.game.node.entity.combat.CombatStyle;
import org.wildscape.game.node.entity.combat.handlers.MeleeSwingHandler;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.plugin.Plugin;

/**
 * Handles the Dragon Pickaxe's special attack.
 * @author Splinter
 * @version 1.0
 */
public final class DragonPickaxeSpecialHandler extends MeleeSwingHandler implements Plugin<Object> {

	/**
	 * The special energy required.
	 */
	private static final int SPECIAL_ENERGY = 100;

	/**
	 * The attack animation.
	 */
	//private static final Animation ANIMATION = new Animation(1057, Priority.HIGH);

	/**
	 * The graphic.
	 */
	//private static final Graphics GRAPHIC = new Graphics(247);

	@Override
	public Object fireEvent(String identifier, Object... args) {
		switch (identifier) {
		case "instant_spec":
		case "ncspec":
			return true;
		}
		return null;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		CombatStyle.MELEE.getSwingHandler().register(14669, this);
		return this;
	}

	@Override
	public int swing(Entity entity, Entity victim, BattleState state) {
		Player p = (Player) entity;
		if (!p.getSettings().drainSpecial(SPECIAL_ENERGY))
			return -1;
		p.sendChat("Smashing!");
		//p.visualize(ANIMATION, GRAPHIC);
		p.getSkills().updateLevel(Skills.MINING, 3, p.getSkills().getStaticLevel(Skills.MINING) + 3);
		return -1;
	}

}