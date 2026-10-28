package antikristianos.annualhelicopter;

public final class AnnualHelicopterSchedule {
    public static final int ANNUAL_OPTION = 5;
    public static final int MINIMUM_INTERVAL_DAYS = 363;
    public static final int MAXIMUM_INTERVAL_DAYS = 368;
    public static final int MAXIMUM_INTERVAL_EXCLUSIVE = MAXIMUM_INTERVAL_DAYS + 1;

    private AnnualHelicopterSchedule() {
    }

    public static boolean shouldReschedule(int optionValue, int currentDay, int scheduledDay) {
        return optionValue == ANNUAL_OPTION && currentDay > scheduledDay;
    }

    public static int nextScheduledDay(int previousScheduledDay, int intervalDays) {
        if (intervalDays < MINIMUM_INTERVAL_DAYS || intervalDays > MAXIMUM_INTERVAL_DAYS) {
            throw new IllegalArgumentException("Annual helicopter interval must be between 363 and 368 days.");
        }
        return Math.addExact(previousScheduledDay, intervalDays);
    }
}
