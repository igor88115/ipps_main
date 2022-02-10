package app.controller;

import app.models.Country;
import app.models.EntityModel;
import app.models.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface BaseController<T extends EntityModel> {
    @GetMapping
    public List<T> list();
    @GetMapping("{id}")
    public Optional getById(@PathVariable("id") Long id);

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id);


}
