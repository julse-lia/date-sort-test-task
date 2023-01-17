package sample;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DateSorter implements IDateSorter{
  @Override
  public Collection<LocalDate> sortDates(List<LocalDate> unsortedDates) {

    List<LocalDate> sortedDates = new ArrayList<>();

    Map<Boolean, List<LocalDate>> partition = unsortedDates.stream()
        .collect(Collectors.partitioningBy(this::containsR));

    partition.get(true).sort(Comparator.naturalOrder());
    partition.get(false).sort(Comparator.reverseOrder());

    sortedDates.addAll(partition.get(true));
    sortedDates.addAll(partition.get(false));

    return sortedDates;
  }

  public boolean containsR(LocalDate localDate) {
    String month = localDate.getMonth().name();
    return month.toLowerCase().contains("r");
  }
}
