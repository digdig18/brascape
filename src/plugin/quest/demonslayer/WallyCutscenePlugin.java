package plugin.quest.demonslayer;

import org.wildscape.game.content.activity.ActivityPlugin;
import org.wildscape.game.content.activity.CutscenePlugin;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.world.map.Location;
import org.wildscape.game.world.map.build.DynamicRegion;
import org.wildscape.game.world.repository.Repository;
import org.wildscape.net.packet.PacketRepository;
import org.wildscape.net.packet.context.CameraContext;
import org.wildscape.net.packet.context.CameraContext.CameraType;
import org.wildscape.net.packet.out.CameraViewPacket;

/**
 * Represents the wally cutscene plugin.
 * @author 'Vexia
 * @date 3/1/14
 */
public class WallyCutscenePlugin extends CutscenePlugin {

	/**
	 * Constructs a new {@code WallyCutscenePlugin} {@code Object}.
	 */
	public WallyCutscenePlugin() {
		this(null);
	}

	/**
	 * Constructs a new {@code WallyCutscenePlugin} {@code Object}.
	 * @param player the player.
	 */
	public WallyCutscenePlugin(final Player player) {
		super("Wally cutscene");
		this.player = player;
	}

	@Override
	public ActivityPlugin newInstance(Player p) throws Throwable {
		return new WallyCutscenePlugin(p);
	}

	@Override
	public void open() {
		player.unlock();
		PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.POSITION, player.getLocation().getX() + 2, player.getLocation().getY() + 3, 305, 1, 35));
		PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.ROTATION, player.getLocation().getX() + 10, player.getLocation().getY() + 12, 305, 1, 35));
		player.getDialogueInterpreter().open(882, Repository.findNPC(882), this);
	}

	@Override
	public void fade() {
		player.getQuestRepository().getQuest("Demon Slayer").start(player);
		player.getDialogueInterpreter().open(882, Repository.findNPC(882), this);
	}

	@Override
	public Location getSpawnLocation() {
		return Location.create(3203, 3423, 0);
	}

	@Override
	public Location getStartLocation() {
		return base.transform(26, 38, 0);
	}

	@Override
	public void configure() {
		region = DynamicRegion.create(12852);
		setRegionBase();
		registerRegion(region.getId());
	}

}
