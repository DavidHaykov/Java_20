import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Locale;

public class PrintCalendarAppl {
    public PrintCalendarAppl() {
    }

    public static void main(String[] args) {
        int nFirst = 0;
        if (args.length == 3) {
            try {
                String weekDayName = args[2];
                nFirst = DayOfWeek.valueOf(weekDayName.toUpperCase()).getValue();
                System.out.println(nFirst);
                args = (String[])Arrays.copyOf(args, 2);
            } catch (Exception var3) {
                args = (String[])Arrays.copyOf(args, 2);
            }
        }

        int[] monthYear = args.length == 2 ? getMonthYear(args) : GetCurrentMonthYear();
        if (monthYear == null) {
            System.out.println("Wrong input, must be <month number> <year number>");
        } else {
            printMonthYear(monthYear[0], monthYear[1], nFirst);
            System.out.println("\n");
        }
    }

    private static void printMonthYear(int month, int year, int nFirst) {
        printTitle(month, year);
        printWeekDayNames(nFirst);
        printDates(month, year, nFirst);
    }

    private static void printDates(int month, int year, int nFirst) {
        System.out.println("\n");
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate firstDayOfTheMonth = yearMonth.atDay(1);
        int firstDay = firstDayOfTheMonth.getDayOfWeek().getValue();
        if (nFirst != 0) {
            firstDay = 7 - nFirst;
        }

        boolean flag = false;
        int i = 1;

        for(int j = 1; j <= Month.of(month).maxLength(); ++i) {
            if (i < firstDay) {
                System.out.printf("%4s", " ");
            }

            if (i == firstDay) {
                flag = true;
            }

            if (flag) {
                System.out.print("\t" + j);
                ++j;
                if (i % 7 == 0) {
                    System.out.print("\n");
                }
            }
        }

    }

    private static void printWeekDayNames(int nFirst) {
        System.out.print("\t");
        int i;
        if (nFirst == 0) {
            for(i = 1; i <= 7; ++i) {
                DayOfWeek day = DayOfWeek.of(i);
                String dayStr = day.getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
                System.out.print(dayStr + " ");
            }
        } else {
            i = nFirst;

            for(int j = 0; j < 7; ++j) {
                String dayStr;
                DayOfWeek day;
                if (i <= 7) {
                    day = DayOfWeek.of(i);
                    dayStr = day.getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
                    System.out.print(dayStr + " ");
                } else {
                    day = DayOfWeek.of(i - 7);
                    dayStr = day.getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
                    System.out.print(dayStr + " ");
                }

                ++i;
            }
        }

    }

    private static void printTitle(int month, int year) {
        Month m = Month.of(month);
        String mStr = m.getDisplayName(TextStyle.FULL, Locale.forLanguageTag("en"));
        System.out.printf("%12s%s %d\n", " ", mStr, year);
    }

    private static int[] GetCurrentMonthYear() {
        LocalDate current = LocalDate.now();
        return new int[]{current.getMonthValue(), current.getYear()};
    }

    private static int[] getMonthYear(String[] args) {
        int[] res = new int[2];

        try {
            res[0] = Integer.parseInt(args[0]);
            res[1] = Integer.parseInt(args[1]);
            return res[0] >= 1 && res[0] <= 12 ? res : null;
        } catch (Exception var3) {
            return null;
        }
    }
}
