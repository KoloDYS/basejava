package urise.webapp.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    // Unique identifier
    private final String uuid;
    private final String fullName;
    private final Map<SectionType, Section> sections = new HashMap<>();
    private final Map<ContactType, String> contacts = new HashMap<>();

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public Section getSection(SectionType section) {
        return sections.get(section);
    }

    public String getContact(ContactType contact) {
        return contacts.get(contact);
    }

    public void addSection(SectionType type, Section section) {
        sections.put(type, section);
    }

    public void addContact(ContactType type, String content) {
        contacts.put(type, content);
    }

    public void printAllResume() {
        System.out.println(uuid + fullName + contacts + sections);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public String toString() {
        return uuid;
    }

    @Override
    public int compareTo(Resume o) {
        int nameComparator = fullName.compareTo(o.getFullName());
        return nameComparator != 0 ? nameComparator : uuid.compareTo(o.uuid);
    }

    public String getFullName() {
        return fullName;
    }
}
