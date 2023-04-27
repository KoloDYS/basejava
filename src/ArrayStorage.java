/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    static int index = 0;
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < index; i++) {
            storage[i] = null;
        }
        index = 0;
    }

    void save(Resume r) {
        storage[index++] = r;
    }

    Resume get(String uuid) {
        for (int i = 0; i < index; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < index; i++) {
            if (storage[i].uuid.equals(uuid)) {
                index--;
                for (int j = i; j < index; j++) {
                    storage[j] = storage[j + 1];
                }
                return;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[index];
        System.arraycopy(storage, 0, resumes, 0, index);
        return resumes;
    }

    int size() {
        return index;
    }
}
