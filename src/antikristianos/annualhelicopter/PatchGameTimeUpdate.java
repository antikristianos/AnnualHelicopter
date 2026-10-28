package antikristianos.annualhelicopter;

import zombie.GameTime;
import me.zed_0xff.zombie_buddy.Patch;

@Patch(
        className = "zombie.GameTime",
        methodName = "update",
        warmUp = true,
        strictMatch = true)
public final class PatchGameTimeUpdate {
    private PatchGameTimeUpdate() {
    }

    @Patch.OnEnter
    public static void onEnter(
            @Patch.This Object gameTime,
            @Patch.Argument(0) boolean bSleeping) {
        AnnualHelicopterRuntime.onGameTimeUpdate((GameTime) gameTime);
    }
}
