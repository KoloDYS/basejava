package urise.webapp.storage;

import urise.webapp.exception.StorageException;
import urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

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

    protected List<Resume> doCopyAll() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    public final void saveResume(Resume r, Object index) {
        if (size == CAPACITY) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            insertResume(r, (int) index);
            size++;
        }
    }

    public final Resume getResume(Object searchKey) {
        return storage[(int) searchKey];
    }

    public final int size() {
        return size;
    }

    public final void updateResume(Resume resume, Object searchKey) {
        storage[(int) searchKey] = resume;
    }

    public final void removeResume(Object searchKey) {
        deleteResume((int) searchKey);
        storage[--size] = null;
    }

    protected boolean isExist(Object index) {
        return (int) index > -1;
    }

    protected abstract void deleteResume(int index);

    protected abstract void insertResume(Resume resume, int index);

    protected abstract Object getSearchKey(String searchKey);
}
