package net.synthesyzer.oredeposits.util;

import org.jetbrains.annotations.NotNull;

public class TimeUnit implements Comparable<TimeUnit> {

    private final int ticks;

    public static TimeUnit inTicks(int ticks) {
        return new TimeUnit(ticks);
    }

    public static TimeUnit inSeconds(int seconds) {
        return new TimeUnit(seconds * 20);
    }

    public static TimeUnit inMinutes(int minutes) {
        return new TimeUnit(TimeUnit.inSeconds(minutes * 60));
    }

    public static TimeUnit inHours(int hours) {
        return new TimeUnit(TimeUnit.inMinutes(hours * 60));
    }

    public static TimeUnit inDays(int days) {
        return new TimeUnit(TimeUnit.inHours(days * 24));
    }

    public int getTicks() {
        return this.ticks;
    }

    public int getSeconds() {
        return this.ticks / 20;
    }

    public int getMinutes() {
        return this.getSeconds() / 60;
    }

    public int getHours() {
        return this.getMinutes() / 60;
    }

    public int getDays() {
        return this.getHours() / 24;
    }

    public TimeUnit subtract(TimeUnit timeUnit) {
        return new TimeUnit(this.ticks - timeUnit.ticks);
    }

    public TimeUnit add(TimeUnit timeUnit) {
        return new TimeUnit(this.ticks + timeUnit.ticks);
    }

    private TimeUnit(int ticks) {
        this.ticks = ticks;
    }

    private TimeUnit(TimeUnit timeUnit) {
        this.ticks = timeUnit.ticks;
    }

    @Override
    public int compareTo(@NotNull TimeUnit o) {
        return Integer.compare(this.ticks, o.ticks);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        TimeUnit residual = new TimeUnit(this);

        if (residual.getDays() >= 1) {
            sb.append(residual.getDays());

            if (residual.getDays() == 1) {
                sb.append(" Day");
            } else {
                sb.append(" Days");
            }
            residual = residual.subtract(TimeUnit.inDays(residual.getDays()));

            if (residual.getHours() >= 1) {
                sb.append(", ");
            }
        }

        if (residual.getHours() >= 1) {
            sb.append(residual.getHours());

            if (residual.getHours() == 1) {
                sb.append(" Hour");
            } else {
                sb.append(" Hours");
            }
            residual = residual.subtract(TimeUnit.inHours(residual.getHours()));

            if (residual.getMinutes() >= 1) {
                sb.append(", ");
            }
        }

        if (residual.getMinutes() >= 1) {
            sb.append(residual.getMinutes());

            if (residual.getMinutes() == 1) {
                sb.append(" Minute");
            } else {
                sb.append(" Minutes");
            }
            residual = residual.subtract(TimeUnit.inMinutes(residual.getMinutes()));

            if (residual.getSeconds() >= 1) {
                sb.append(", ");
            }
        }

        if (residual.getSeconds() >= 1) {
            sb.append(residual.getSeconds());

            if (residual.getSeconds() == 1) {
                sb.append(" Second");
            } else {
                sb.append(" Seconds");
            }
        }

        if (sb.length() == 0) {
            sb.append("0 Seconds");
        }

        return sb.toString();
    }
}
