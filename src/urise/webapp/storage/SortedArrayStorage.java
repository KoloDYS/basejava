package urise.webapp.storage;

import urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    private static final Comparator<Resume> RESUME_COMPARATOR = (o1, o2) -> o1.getUuid().compareTo(o2.getUuid());

    protected void deleteResume(int index) {
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

    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "dummy");
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
    }
}
