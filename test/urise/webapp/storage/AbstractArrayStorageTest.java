package urise.webapp.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import urise.webapp.exception.ExistStorageException;
import urise.webapp.exception.NotExistStorageException;
import urise.webapp.exception.StorageException;
import urise.webapp.model.Resume;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractArrayStorageTest {
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
        assertSize(0);
        assertArrayEquals(new Resume[0], storage.getAll());
    }

    @Test
    void getAll() {
        Resume[] expected = new Resume[]{resume1, resume2, resume3};
        assertArrayEquals(expected, storage.getAll());
    }

    @Test
    void save() {
        storage.save(resume4);
        assertGet(resume4);
        assertSize(4);
    }

    @Test
    void get() {
        assertGet(resume1);
        assertGet(resume2);
        assertGet(resume3);
    }

    @Test
    void size() {
        assertSize(3);
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
        assertSize(2);
        Assertions.assertThrows(NotExistStorageException.class, () -> assertGet(resume2));
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
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.CAPACITY; i++) {
                storage.save(new Resume());
            }
            //Double save resume
//            storage.save(new Resume());
        } catch (StorageException e) {
            System.out.println("Double save resume");
        }
        Assertions.assertThrows(StorageException.class, () -> storage.save(resume4));
    }

    @Test
    void saveExist() {
        Assertions.assertThrows(ExistStorageException.class, () -> storage.save(resume1));
    }

    void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }
}