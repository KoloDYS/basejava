package urise.webapp.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import urise.webapp.exception.ExistStorageException;
import urise.webapp.exception.NotExistStorageException;
import urise.webapp.exception.StorageException;
import urise.webapp.model.Resume;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AbstractArrayStorageTest {
    Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final Resume resume1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    private static final Resume resume2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    private static final Resume resume3 = new Resume(UUID_3);
    private static final String UUID_4 = "uuid4";
    private static final Resume resume4 = new Resume(UUID_4);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void setUp() {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    void getAll() {
        Resume[] array = storage.getAll();
        assertEquals(3, array.length);
        assertEquals(resume1, array[0]);
        assertEquals(resume2, array[1]);
        assertEquals(resume3, array[2]);
    }

    @Test
    void save() {
        storage.save(resume4);
        assertEquals(4, storage.size());
        assertEquals(resume4, storage.get(UUID_4));
    }

    @Test
    void get() {
        assertEquals(resume2, storage.get(UUID_2));
    }

    @Test
    void size() {
        assertEquals(3, storage.size());
    }

    @Test
    void update() {
        Resume updatingResume = new Resume(UUID_1);
        storage.update(updatingResume);
        assertEquals(updatingResume, storage.get(UUID_1));
    }

    @Test
    void delete() {
        storage.delete(UUID_2);
        assertEquals(resume3, storage.get(UUID_3));
    }

    @Test
    void getNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get("dummy"));
    }

    @Test
    void deleteNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.delete("uuid45"));
    }

    @Test
    void updateNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.update(new Resume("uuid45")));
    }

    @Test
    void saveOverflow() {
        for (int i = 0; i < AbstractArrayStorage.CAPACITY - 3; i++) {
            storage.save(new Resume());
        }
        Assertions.assertThrows(StorageException.class, () -> storage.save(resume4));
    }

    @Test
    void saveExist() {
        Assertions.assertThrows(ExistStorageException.class, () -> storage.save(resume1));
    }
}