package ru.test.app.conroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.test.app.entity.TestObject;
import ru.test.app.repository.TestObjectRepository;
import ru.test.app.service.TestObjectService;

import java.util.List;

@RestController
@RequestMapping("/objects")
public class TestObjectController {
    @Autowired
    private TestObjectRepository repository;
    @Autowired
    private TestObjectService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<TestObject> getAllObjects() {
        return repository.findAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Integer create(@RequestBody String name) {
        TestObject result = repository.save(new TestObject(null, name));
        return result.getId();
    }

    @RequestMapping(value = "/{Id}", method = RequestMethod.POST)
    public Integer trampampam(@PathVariable("Id") int id) {
        return service.myBusinessMethod(id);
    }

    @RequestMapping(value = "/{Id}", method = RequestMethod.PUT)
    public void trampampam2(@PathVariable("Id") int id) {
        service.myBusinessMethod2(id);
    }

    @RequestMapping(value = "/max", method = RequestMethod.GET)
    public int max() {
        return service.myBusinessMethod3();
    }

    @RequestMapping(value = "/la", method = RequestMethod.POST)
    public void lalala() {
        service.myBusinessMethod4();
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public Integer update(@RequestBody TestObject object) {
        return repository.save(object).getId();
    }
}
