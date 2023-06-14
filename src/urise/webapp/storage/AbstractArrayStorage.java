package urise.webapp.storage;

import urise.webapp.exception.StorageException;
import urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
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

    public final void saveResume(Resume r, Integer index) {
        if (size == CAPACITY) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            insertResume(r, index);
            size++;
        }
    }

    public final Resume getResume(Integer searchKey) {
        return storage[searchKey];
    }

    public final int size() {
        return size;
    }

    public final void updateResume(Resume resume, Integer searchKey) {
        storage[searchKey] = resume;
    }

    public final void removeResume(Integer searchKey) {
        deleteResume(searchKey);
        storage[--size] = null;
    }

    protected boolean isExist(Integer index) {
        return index > -1;
    }

    protected abstract void deleteResume(int index);

    protected abstract void insertResume(Resume resume, int index);

    protected abstract Integer getSearchKey(String searchKey);
}
