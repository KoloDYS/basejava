package urise.webapp.storage;

import urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private final Map<Integer, Resume> mapStorage = new HashMap<Integer, Resume>();

    @Override
    protected Resume getResume(Object searchKey) {
        return mapStorage.get((int) searchKey);
    }

    @Override
    protected void removeResume(Object searchKey) {
        mapStorage.remove((int) searchKey);
    }

    @Override
    protected void updateResume(Resume resume, Object searchKey) {
        mapStorage.put((int) searchKey, resume);
    }

    @Override
    protected void saveResume(Resume resume, Object searchKey) {
        mapStorage.put((int) searchKey, resume);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null && mapStorage.containsKey((Integer) searchKey);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return ((uuid.indexOf("uuid")) == 0) ? Integer.parseInt(uuid.split("uuid")[1]) : null;
    }

    @Override
    public void clear() {
        mapStorage.clear();
    }

    @Override
    public Resume[] getAll() {
        return mapStorage.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return mapStorage.size();
    }
}
