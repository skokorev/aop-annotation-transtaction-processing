package ru.test.app.service;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.test.app.annotation.GenerateEvents;
import ru.test.app.entity.TestObject;
import ru.test.app.repository.TestObjectRepository;

import java.util.Comparator;
import java.util.function.Function;

@Component
public class TestObjectService {
    @Autowired
    private TestObjectRepository repository;

    @GenerateEvents
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer myBusinessMethod(int id) {
        return repository.findById(id).
                map(o -> new TestObject(o.getId(), "TRAM-PAM-PAM!!!")).
                map(repository::save).
                map(TestObject::getId).
                orElse(null);
    }

    @GenerateEvents
    @Transactional(propagation = Propagation.REQUIRED)
    public void myBusinessMethod2(int id) {
        repository.findById(id).
                map(o -> new TestObject(o.getId(), "TRAM-PAM-PAM!!!")).
                ifPresent(repository::save);
    }

    @GenerateEvents
    @Transactional(propagation = Propagation.REQUIRED)
    public int myBusinessMethod3() {
        return repository.findAll().stream().
                map(TestObject::getId).
                max(Comparator.comparingInt(i -> i)).
                orElse(0);
    }

    @GenerateEvents
    @Transactional(propagation = Propagation.REQUIRED)
    public void myBusinessMethod4() {
        repository.findAll().stream().
                map(o -> new TestObject(o.getId(), o.getName() + " - LALALA!!!")).
                forEach(repository::save);
    }
}
