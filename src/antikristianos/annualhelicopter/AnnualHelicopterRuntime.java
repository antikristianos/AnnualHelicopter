package antikristianos.annualhelicopter;

import zombie.GameTime;
import zombie.SandboxOptions;
import zombie.core.random.Rand;
import zombie.network.GameClient;

public final class AnnualHelicopterRuntime {
    private AnnualHelicopterRuntime() {
    }

    public static void onGameTimeUpdate(GameTime gameTime) {
        if (gameTime == null || GameClient.client) {
            return;
        }

        int optionValue = SandboxOptions.instance.helicopter.getValue();
        int currentDay = gameTime.getNightsSurvived();
        int scheduledDay = gameTime.getHelicopterDay();
        if (!AnnualHelicopterSchedule.shouldReschedule(optionValue, currentDay, scheduledDay)) {
            return;
        }

        int intervalDays = Rand.Next(
                AnnualHelicopterSchedule.MINIMUM_INTERVAL_DAYS,
                AnnualHelicopterSchedule.MAXIMUM_INTERVAL_EXCLUSIVE);
        int startHour = Rand.Next(9, 19);

        gameTime.setHelicopterDay(AnnualHelicopterSchedule.nextScheduledDay(scheduledDay, intervalDays));
        gameTime.setHelicopterStartHour(startHour);
        gameTime.setHelicopterEndHour(startHour + Rand.Next(4) + 1);
    }
}
