package urise.webapp.storage;

import urise.webapp.exception.StorageException;
import urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
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

    public final void saveResume(Resume r, Object index) {
        if (size == CAPACITY) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            insertResume(r, (int) index);
            size++;
        }
    }

    public final Resume getResume(int index) {
        return storage[index];
    }

    public final int size() {
        return size;
    }

    public final void updateResume(Resume resume, int index) {
        storage[index] = resume;
    }

    public final void removeResume(int index) {
        deleteResume(index);
        storage[--size] = null;
    }

    protected boolean isExist(Object index) {
        return (int) index > -1;
    }

    protected abstract void deleteResume(int index);

    protected abstract void insertResume(Resume resume, int index);

    protected abstract Integer getIndex(String uuid);
}
