package antikristianos.annualhelicopter;

import me.zed_0xff.zombie_buddy.Patch;

@Patch(
        className = "zombie.config.EnumConfigOption",
        methodName = "<init>",
        warmUp = true,
        strictMatch = true)
public final class PatchEnumConfigOptionConstructor {
    private PatchEnumConfigOptionConstructor() {
    }

    @Patch.OnExit
    public static void onExit(
            @Patch.This Object option,
            @Patch.Argument(0) String name,
            @Patch.Argument(1) int maximum,
            @Patch.Argument(2) int defaultValue) {
        if (AnnualHelicopterOptionSupport.expandOption(option)) {
            System.out.println("[AnnualHelicopter] Helicopter sandbox option expanded to 5 values");
        }
    }
}
