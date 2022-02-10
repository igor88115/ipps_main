package app.controller;

import app.models.Country;
import app.models.EntityModel;
import app.models.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface BaseController<T extends EntityModel> {
    @GetMapping
    public List<T> list();
    @GetMapping("{id}")
    public Optional getById(@PathVariable("id") Long id);
    @PostMapping
    @JsonView(Views.MainView.class)
    public <T extends EntityModel> T create(@RequestBody T model);

}
