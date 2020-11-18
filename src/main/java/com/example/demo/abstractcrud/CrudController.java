package com.example.demo.abstractcrud;

import com.example.demo.lessons.Day;
import com.example.demo.lessons.LessonsService;
import com.example.demo.pages.Info;
import com.example.demo.pages.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class CrudController<M extends com.example.demo.abstractcrud.Model, R extends JpaRepository<M, Long>> {
    @Autowired
    protected R repo;
    @Autowired
    protected LessonsService lessonsService;

    private final String name;

    private final Instance<M> instance;
    private final ListView listViewInstance;
    private final Info infoInstance;

    protected CrudController(String name, Instance<M> instance, ListView listViewInstance, Info infoInstance) {
        this.name = name;
        this.instance = instance;
        this.listViewInstance = listViewInstance;
        this.infoInstance = infoInstance;
    }

    protected String getListPageNameAndSetAttrs(Model model) {
        return "list";
    }

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("viewer", listViewInstance);
        model.addAttribute("info", infoInstance);
        model.addAttribute("name", name);
        model.addAttribute("link", name);

        List<M> list = repo.findAll();
        list.sort(Comparator.comparing(M::getName));

        model.addAttribute("list", list);
        return getListPageNameAndSetAttrs(model);
    }
    
    @PostMapping("/add")
    public String add(@RequestParam("name") String name) {
        repo.save(instance.getInstance(name));
        return "redirect:/" + this.name;
    }

    protected String getInfoPage() {
        return "info";
    }

    public void setEditPropsToModel(Model model, M item) { }

    @GetMapping("/{id}")
    public String getEdit(
            @PathVariable("id") M item,
            Model model
    ) {
        model.addAttribute("info", infoInstance);
        model.addAttribute("viewer", listViewInstance);
        model.addAttribute("name", name);
        model.addAttribute("week", getWeek(item));
        model.addAttribute("instance", item);
        setEditPropsToModel(model, item);
        return getInfoPage();
    }

    protected ArrayList<Day> getWeek(M item) {
        return lessonsService.getBy(name, item);
    }


    @PostMapping("/{id}")
    public String editGroup(
            @PathVariable("id") M item,
            @RequestParam("name") String name
    ) {
        item.setName(name);
        repo.save(item);
        return "redirect:/" + this.name;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") M m) {
        repo.delete(m);
        return "redirect:/" + name;
    }

    public interface Instance<M> {
        M getInstance(String name);
    }

    public interface GetLessons<M> {
        ArrayList<Day> getLessons(M m);
    }
}
