package plugin.dialogue;

import org.wildscape.game.content.dialogue.DialoguePlugin;
import org.wildscape.game.content.dialogue.FacialExpression;
import org.wildscape.game.node.entity.npc.NPC;
import org.wildscape.game.node.entity.player.Player;

/**
 * Handles the SethGroatsDialogue dialogue.
 * @author Vexia
 */
public class SethGroatsDialogue extends DialoguePlugin {

	public SethGroatsDialogue() {

	}

	public SethGroatsDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new SethGroatsDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NORMAL, "M'arnin'... going to milk me cowsies!");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		end();
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 452 };
	}
}