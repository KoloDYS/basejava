package urise.webapp.storage;

import urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private final List<Resume> list = new ArrayList<Resume>();

    @Override
    protected Resume getResume(Object searchKey) {
        return list.get((int) searchKey);
    }

    @Override
    protected void removeResume(Object searchKey) {
        list.remove((int) searchKey);
    }

    @Override
    protected void updateResume(Resume resume, Object searchKey) {
        list.set((int) searchKey, resume);
    }

    @Override
    protected void saveResume(Resume resume, Object index) {
        list.add(resume);
    }

    @Override
    protected boolean isExist(Object index) {
        return index != null;
    }

    @Override
    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Resume[] getAll() {
        return list.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return list.size();
    }
}
