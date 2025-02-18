package meteor.plugins.voidagility.tasks;

import meteor.plugins.voidagility.VoidTemporossPlugin;
import meteor.plugins.voidutils.OSRSUtils;
import meteor.plugins.voidutils.tasks.Task;
import net.runelite.api.GameObject;

import javax.inject.Inject;

public class RepairTotem extends Task {

    @Inject
    VoidTemporossPlugin plugin;

    @Inject
    OSRSUtils osrs;

    @Override
    public String name() {
        return "Repair Totem";
    }

    @Override
    public boolean shouldExecute() {
        if (plugin.location.equals("ISLAND"))
            if (!plugin.shouldTether)
                if (getBrokenTotem() != null)
                    return true;
        return false;
    }

    @Override
    public void execute() {
        fixBrokenTotem();
    }

    public GameObject getBrokenTotem() {
        return plugin.getSidesObjectNS(41011);
    }

    public void fixBrokenTotem() {
        GameObject brokenTotem = getBrokenTotem();
        if (brokenTotem != null)
            brokenTotem.interact(0);
    }
}
