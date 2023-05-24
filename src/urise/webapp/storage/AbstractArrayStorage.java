package urise.webapp.storage;

import urise.webapp.exception.ExistStorageException;
import urise.webapp.exception.NotExistStorageException;
import urise.webapp.exception.StorageException;
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
            throw new StorageException("Storage overflow", r.getUuid());
        } else if (isExist(index)) {
            throw new ExistStorageException(r.getUuid());
        } else {
            insertResume(r, index);
            size++;
        }
    }

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (!isExist(index)) {
            throw new NotExistStorageException(uuid);
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
            throw new NotExistStorageException(resume.getUuid());
        } else {
            storage[index] = resume;
        }
    }

    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (!isExist(index)) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteResume(index);
            storage[--size] = null;
        }
    }

    protected boolean isExist(int index) {
        return index > -1;
    }

    protected abstract void deleteResume(int index);

    protected abstract void insertResume(Resume resume, int index);

    protected abstract int getIndex(String uuid);
}
