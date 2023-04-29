/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    int numOfResumes;
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < numOfResumes; i++) {
            storage[i] = null;
        }
        numOfResumes = 0;
    }

    void save(Resume r) {
        storage[numOfResumes++] = r;
    }

    Resume get(String uuid) {
        for (int i = 0; i < numOfResumes; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < numOfResumes; i++) {
            if (storage[i].uuid.equals(uuid)) {
                numOfResumes--;
                System.arraycopy(storage, i + 1, storage, i, numOfResumes + 1);
                return;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[numOfResumes];
        System.arraycopy(storage, 0, resumes, 0, numOfResumes);
        return resumes;
    }

    int size() {
        return numOfResumes;
    }
}
