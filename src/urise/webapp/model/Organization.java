package urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class Organization {
    private final List<Period> periods;
    private final URL url;
    private final String name;

    public Organization(List<Period> periods, URL url, String name) {
        this.periods = periods;
        this.url = url;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "periods=" + periods +
                ", url=" + url +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(periods, that.periods) &&
                Objects.equals(url, that.url) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(periods, url, name);
    }
}
