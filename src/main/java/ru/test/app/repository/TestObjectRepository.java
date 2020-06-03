package ru.test.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.test.app.entity.TestObject;

import java.util.List;
import java.util.Optional;

@Transactional(isolation = Isolation.READ_COMMITTED)
public interface TestObjectRepository extends CrudRepository<TestObject, Integer> {
    TestObject save(TestObject toSave);
    @Transactional(readOnly = true)
    Optional<TestObject> findById(Integer id);
    @Transactional(readOnly = true)
    List<TestObject> findAll();
}
