package urise.webapp.storage;

import urise.webapp.exception.ExistStorageException;
import urise.webapp.exception.NotExistStorageException;
import urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected abstract Resume getResume(int index);

    protected abstract void removeResume(int index);

    protected abstract void updateResume(Resume resume, int index);

    protected abstract void saveResume(Resume resume, Object index);

    protected abstract boolean isExist(Object searchKey);

    protected abstract Integer getIndex(String uuid);

    public Resume get(String uuid) {
        Object index = getExistedResume(uuid);
        return getResume(((int) index));
    }

    public void save(Resume r) {
        Object index = getNotExistedResume(r.getUuid());
        saveResume(r, index);
    }

    public void update(Resume r) {
        Object index = getExistedResume(r.getUuid());
        updateResume(r, (int) index);
    }

    public void delete(String uuid) {
        Object index = getExistedResume(uuid);
        removeResume((int) index);
    }

    private Object getExistedResume(String uuid) {
        Object searchKey = getIndex(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistedResume(String uuid) {
        Object searchKey = getIndex(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }
}
