package urise.webapp.model;

import java.util.Objects;

public class URL {
    private final String name;
    private final String url;

    public URL(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "URL{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        URL url1 = (URL) o;
        return name.equals(url1.name) && url.equals(url1.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url);
    }
}
