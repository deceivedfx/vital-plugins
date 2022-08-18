package dev.vital.quester.quests.misthalin_mystery.tasks;

import dev.vital.quester.ScriptTask;
import dev.vital.quester.VitalQuesterConfig;
import dev.vital.quester.tasks.BasicTask;
import net.runelite.api.ItemID;
import net.runelite.api.coords.WorldPoint;
import net.unethicalite.api.entities.TileObjects;
import net.unethicalite.api.game.Vars;
import net.unethicalite.api.items.Inventory;
import net.unethicalite.api.quests.QuestVarbits;

public class GetRubyKey implements ScriptTask
{
    WorldPoint note_point = new WorldPoint(1632, 4833, 0);
    VitalQuesterConfig config;

    public GetRubyKey(VitalQuesterConfig config)
    {
        this.config = config;
    }

    @Override
    public boolean validate()
    {
        return Vars.getBit(QuestVarbits.QUEST_MISTHALIN_MYSTERY.getId()) == 40;
    }

    BasicTask get_ruby_key = new BasicTask(() -> {
        if(Inventory.contains(ItemID.RUBY_KEY_21053)) {
            return 0;
        }

        var painting = TileObjects.getNearest(29650);
        if(painting.hasAction("Search")) {
            painting.interact("Search");
        }
        else{
            Inventory.getFirst(ItemID.KNIFE).useOn(TileObjects.getNearest(29650));
        }
        return 0;
    });

    @Override
    public int execute() {

        if (!get_ruby_key.taskCompleted()) {
            return get_ruby_key.execute();
        }

        return -1;
    }
}
