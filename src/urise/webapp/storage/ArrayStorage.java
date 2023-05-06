package urise.webapp.storage;

import urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final static int CAPACITY = 10000;
    int numOfResumes;
    Resume[] storage = new Resume[CAPACITY];

    public void clear() {
        Arrays.fill(storage, 0, numOfResumes, null);
        numOfResumes = 0;
    }

    public void save(Resume r) {
        if (isContain(r.getUuid())) {
            System.out.println("Резюме " + r.getUuid() + " уже есть в списке");
            return;
        }
        if (numOfResumes == CAPACITY) {
            System.out.println("Список резюме переполнен");
            return;
        }
        storage[numOfResumes++] = r;
    }

    public Resume get(String uuid) {
        if (!isContain(uuid)) {
            System.out.println("Резюме " + uuid + " не найдено");
            return null;
        }
        for (int i = 0; i < numOfResumes; i++) {
            if (isEqualId(storage[i], uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        if (!isContain(uuid)) {
            System.out.println("Резюме " + uuid + " для удаления не найдено");
            return;
        }
        for (int i = 0; i < numOfResumes; i++) {
            if (isEqualId(storage[i], uuid)) {
                numOfResumes--;
                System.arraycopy(storage, i + 1, storage, i, numOfResumes + 1);
                return;
            }
        }
    }

    public void update(Resume resume) {
        if (!isContain(resume.getUuid())) {
            System.out.println("Резюме " + resume.getUuid() + " для обновления не найдено");
            return;
        }
        for (int i = 0; i < numOfResumes; i++) {
            if (storage[i] == resume) {
                storage[i].setUuid(resume.getUuid());
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumes;
        resumes = Arrays.copyOfRange(storage, 0, numOfResumes);
        return resumes;
    }

    public int size() {
        return numOfResumes;
    }

    private boolean isContain(String uuid) {
        for (int i = 0; i < numOfResumes; i++) {
            if (isEqualId(storage[i], uuid)) {
                return true;
            }
        }
        return false;
    }

    private boolean isEqualId(Resume resume, String uuid) {
        return resume.getUuid().equals(uuid);
    }
}
