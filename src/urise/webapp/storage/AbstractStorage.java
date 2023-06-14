package urise.webapp.storage;

import urise.webapp.exception.ExistStorageException;
import urise.webapp.exception.NotExistStorageException;
import urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {
    private final Comparator<Resume> comparator =
            (Comparator.comparing(Resume::getFullName).thenComparing(Resume::compareTo));
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract Resume getResume(SK searchKey);

    protected abstract void removeResume(SK searchKey);

    protected abstract void updateResume(Resume resume, SK searchKey);

    protected abstract void saveResume(Resume resume, SK searchKey);

    protected abstract boolean isExist(SK searchKey);

    protected abstract SK getSearchKey(String searchKey);

    protected abstract List<Resume> doCopyAll();

    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SK index = getExistedResume(uuid);
        return getResume((index));
    }

    public void save(Resume r) {
        LOG.info("Save " + r);
        SK index = getNotExistedResume(r.getUuid());
        saveResume(r, index);
    }

    public void update(Resume r) {
        LOG.info("Update " + r);
        SK index = getExistedResume(r.getUuid());
        updateResume(r, index);
    }

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK index = getExistedResume(uuid);
        removeResume(index);
    }

    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> list = doCopyAll();
        list.sort(comparator);
        return list;
    }

    private SK getExistedResume(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getNotExistedResume(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }
}
