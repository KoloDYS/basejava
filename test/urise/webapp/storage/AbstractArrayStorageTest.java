package urise.webapp.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import urise.webapp.exception.StorageException;
import urise.webapp.model.Resume;

abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test
    void saveOverflow() {
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.CAPACITY; i++) {
                storage.save(new Resume("" + i));
            }
        } catch (StorageException e) {
            Assertions.fail("StorageException ahead of time");
        }
        Assertions.assertThrows(StorageException.class, () -> storage.save(resume4));
    }
}