package dev.vital.quester.quests.x_marks_the_spot.tasks;

import dev.vital.quester.ScriptTask;
import dev.vital.quester.VitalQuesterConfig;
import dev.vital.quester.tasks.DialogTask;
import net.runelite.api.ItemID;
import net.runelite.api.coords.WorldPoint;
import net.unethicalite.api.items.Inventory;

public class GetAxe implements ScriptTask
{
	private final WorldPoint shop_keeper_point = new WorldPoint(3227, 3244, 0);

	VitalQuesterConfig config;
	DialogTask talk_to_wood_guy = new DialogTask("Woodsman tutor", shop_keeper_point, "Can you teach me");

	public GetAxe(VitalQuesterConfig config)
	{
		this.config = config;
	}

	@Override
	public boolean validate()
	{
		return !Inventory.contains(ItemID.BRONZE_AXE) && !Inventory.contains(ItemID.SPADE) && Inventory.getCount(true, ItemID.COINS_995) < 3;
	}

	@Override
	public int execute()
	{

		if (!talk_to_wood_guy.taskCompleted())
		{
			return talk_to_wood_guy.execute();
		}

		return -2;
	}
}