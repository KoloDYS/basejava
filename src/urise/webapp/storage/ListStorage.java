package urise.webapp.storage;

import urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private final List<Resume> list = new ArrayList<Resume>();

    @Override
    protected Resume getResume(int index) {
        return list.get(index);
    }

    @Override
    protected void removeResume(int index) {
        list.remove(index);
    }

    @Override
    protected void updateResume(Resume resume, int index) {
        list.set(index, resume);
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
    protected Integer getIndex(String uuid) {
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
        return list.toArray(new Resume[list.size()]);
    }

    @Override
    public int size() {
        return list.size();
    }
}
