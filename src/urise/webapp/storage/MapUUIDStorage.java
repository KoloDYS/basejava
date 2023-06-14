package urise.webapp.storage;

import urise.webapp.model.Resume;

import java.util.*;

public class MapUUIDStorage extends AbstractStorage<String> {

    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Resume getResume(String searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected void removeResume(String searchKey) {
        storage.remove(searchKey);
    }

    @Override
    protected void updateResume(Resume resume, String searchKey) {
        storage.put(searchKey, resume);
    }

    @Override
    protected void saveResume(Resume resume, String searchKey) {
        storage.put(searchKey, resume);
    }

    @Override
    protected boolean isExist(String searchKey) {
        return storage.containsKey(searchKey);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }
}