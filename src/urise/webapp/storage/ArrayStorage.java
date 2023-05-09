package urise.webapp.storage;

import urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final static int CAPACITY = 10000;
    int size;
    Resume[] storage = new Resume[CAPACITY];

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size == CAPACITY) {
            System.out.println("Список резюме переполнен");
        } else if (isExist(r.getUuid())) {
            System.out.println("Резюме " + r.getUuid() + " уже есть в списке");
        } else {
            storage[size++] = r;
        }
    }

    public Resume get(String uuid) {
        if (!isExist(uuid)) {
            System.out.println("Резюме " + uuid + " не найдено");
            return null;
        }
        int index = getIndex(uuid);
        if (index != 1) {
            return storage[index];
        }
        return null;
    }

    public void delete(String uuid) {
        if (!isExist(uuid)) {
            System.out.println("Резюме " + uuid + " для удаления не найдено");
            return;
        }
        int index = getIndex(uuid);
        if (index != 1) {
            storage[index] = storage[--size];
        }
    }

    public void update(Resume resume) {
        if (!isExist(resume.getUuid())) {
            System.out.println("Резюме " + resume.getUuid() + " для обновления не найдено");
            return;
        }
        int index = getIndex(resume.getUuid());
        if (storage[index] == resume) {
            storage[index].setUuid(resume.getUuid());
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumes;
        resumes = Arrays.copyOfRange(storage, 0, size);
        return resumes;
    }

    public int size() {
        return size;
    }

    private boolean isExist(String uuid) {
        return getIndex(uuid) != -1;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}