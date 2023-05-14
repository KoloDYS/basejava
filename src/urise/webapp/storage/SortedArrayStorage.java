package urise.webapp.storage;

import urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    protected void resetResume(int index) {
        int length = size - index - 1;
        if (length > 0) {
            System.arraycopy(storage, index + 1, storage, index, length);
        }
    }

    protected void insertResume(Resume resume, int index) {
        int newResumeIndex = -index - 1;
        System.arraycopy(storage, newResumeIndex, storage, newResumeIndex + 1, size - newResumeIndex);
        storage[newResumeIndex] = resume;
    }

    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}