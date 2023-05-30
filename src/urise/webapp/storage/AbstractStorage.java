package urise.webapp.storage;

import urise.webapp.exception.ExistStorageException;
import urise.webapp.exception.NotExistStorageException;
import urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected abstract Resume getResume(Object searchKey);

    protected abstract void removeResume(Object searchKey);

    protected abstract void updateResume(Resume resume, Object searchKey);

    protected abstract void saveResume(Resume resume, Object searchKey);

    protected abstract boolean isExist(Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    public Resume get(String uuid) {
        Object index = getExistedResume(uuid);
        return getResume((index));
    }

    public void save(Resume r) {
        Object index = getNotExistedResume(r.getUuid());
        saveResume(r, index);
    }

    public void update(Resume r) {
        Object index = getExistedResume(r.getUuid());
        updateResume(r, index);
    }

    public void delete(String uuid) {
        Object index = getExistedResume(uuid);
        removeResume(index);
    }

    private Object getExistedResume(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistedResume(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }
}
