package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");
    private static final int date = 0;
    private static final int name = 1;
    private static final int hours = 2;
    private static final int salary = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salaries = new int[names.length];
        LocalDate fromDate = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate toDate = LocalDate.parse(dateTo, dateTimeFormatter);
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                String[] splitData = data[j].split(" ");
                LocalDate currentDate = LocalDate.parse(splitData[date], dateTimeFormatter);
                boolean searchScope = splitData[name].equals(names[i])
                        && (currentDate.isAfter(fromDate)
                        || currentDate.isEqual(fromDate))
                        && (currentDate.isBefore(toDate)
                        || currentDate.isEqual(toDate));
                if (searchScope) {
                    int payment = Integer.parseInt(splitData[hours]) * Integer.parseInt(splitData[salary]);
                    salaries[i] += payment;
                }
            }
        }
        StringBuilder reply = new StringBuilder();
        reply.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            reply.append(System.lineSeparator()).append(names[i]).append(" - ").append(salaries[i]);
        }
        return reply.toString();
    }
}
