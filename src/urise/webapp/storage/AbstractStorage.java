package urise.webapp.storage;

import urise.webapp.exception.ExistStorageException;
import urise.webapp.exception.NotExistStorageException;
import urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {
    private final Comparator<Resume> comparator = (Comparator.comparing(Resume::getFullName).thenComparing(Resume::compareTo));
    protected abstract Resume getResume(Object searchKey);

    protected abstract void removeResume(Object searchKey);

    protected abstract void updateResume(Resume resume, Object searchKey);

    protected abstract void saveResume(Resume resume, Object searchKey);

    protected abstract boolean isExist(Object searchKey);

    protected abstract Object getSearchKey(String searchKey);

    protected abstract List<Resume> doCopyAll();

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

    public List<Resume> getAllSorted() {
        List <Resume> list = doCopyAll();
        list.sort(comparator);
        return list;
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
