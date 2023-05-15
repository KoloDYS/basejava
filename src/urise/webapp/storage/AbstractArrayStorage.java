package urise.webapp.storage;

import urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int CAPACITY = 10000;
    protected int size;
    protected final Resume[] storage = new Resume[CAPACITY];

    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public final Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public final void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (size == CAPACITY) {
            System.out.println("Список резюме переполнен");
        } else if (isExist(index)) {
            System.out.println("Резюме " + r.getUuid() + " уже есть в списке");
        } else {
            insertResume(r, index);
            size++;
        }
    }

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (!isExist(index)) {
            System.out.println("Резюме " + uuid + " не найдено");
            return null;
        } else {
            return storage[index];
        }
    }

    public final int size() {
        return size;
    }

    public final void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (!isExist(index)) {
            System.out.println("Резюме " + resume.getUuid() + " для обновления не найдено");
        } else {
            storage[index].setUuid(resume.getUuid());
        }
    }

    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (!isExist(index)) {
            System.out.println("Резюме " + uuid + " для удаления не найдено");
        } else {
            resetResume(index);
            storage[--size] = null;
        }
    }

    protected boolean isExist(int index) {
        return index != -1;
    }

    protected abstract void resetResume(int index);

    protected abstract void insertResume(Resume resume, int index);

    protected abstract int getIndex(String uuid);
}
