package antikristianos.annualhelicopter;

import java.lang.reflect.Field;
import zombie.config.EnumConfigOption;
import zombie.config.IntegerConfigOption;

public final class AnnualHelicopterOptionSupport {
    private static final String HELICOPTER_OPTION_NAME = "Helicopter";
    private static final Field MAXIMUM_FIELD = resolveMaximumField();

    private AnnualHelicopterOptionSupport() {
    }

    public static boolean expandOption(Object candidate) {
        if (!(candidate instanceof EnumConfigOption option)
                || !HELICOPTER_OPTION_NAME.equals(option.getName())) {
            return false;
        }

        try {
            int currentMaximum = MAXIMUM_FIELD.getInt(option);
            if (currentMaximum >= AnnualHelicopterSchedule.ANNUAL_OPTION) {
                return false;
            }
            MAXIMUM_FIELD.setInt(option, AnnualHelicopterSchedule.ANNUAL_OPTION);
            return true;
        } catch (IllegalAccessException exception) {
            throw new IllegalStateException("Unable to expand the Helicopter enum maximum.", exception);
        }
    }

    private static Field resolveMaximumField() {
        try {
            Field field = IntegerConfigOption.class.getDeclaredField("max");
            field.setAccessible(true);
            return field;
        } catch (ReflectiveOperationException exception) {
            throw new ExceptionInInitializerError(exception);
        }
    }
}
