package urise.webapp.model;

import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

import static urise.webapp.util.DateUtil.NOW;
import static urise.webapp.util.DateUtil.of;

public class Period {
    private final String title;
    private final String description;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Period(String title, String description, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Period(String title, String description, int startYear, Month startMonth, int endYear, Month endMonth) {
        this.title = title;
        this.description = description;
        this.startDate = of(startYear, startMonth);
        this.endDate = of(endYear, endMonth);
    }

    public Period(String title, String description, int startYear, Month startMonth) {
        this.title = title;
        this.description = description;
        this.startDate = of(startYear, startMonth);
        this.endDate = NOW;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Period{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return Objects.equals(title, period.title) &&
                Objects.equals(description, period.description) &&
                Objects.equals(startDate, period.startDate) && Objects.equals(endDate, period.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, startDate, endDate);
    }
}
