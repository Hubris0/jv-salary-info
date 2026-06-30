package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salaries = new int[names.length];
        LocalDate fromDate = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate toDate = LocalDate.parse(dateTo, dateTimeFormatter);
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                String[] splitData = data[j].split(" ");
                LocalDate currentDate = LocalDate.parse(splitData[0], dateTimeFormatter);
                boolean searchScope = splitData[1].equals(names[i])
                        && (currentDate.isAfter(fromDate)
                        || currentDate.isEqual(fromDate))
                        && (currentDate.isBefore(toDate)
                        || currentDate.isEqual(toDate));
                if (searchScope) {
                    int salary = Integer.parseInt(splitData[2]) * Integer.parseInt(splitData[3]);
                    salaries[i] += salary;
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
