package com.example.demo.abstractcrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class ModelsRestController {


    class ModelHolder {
        private String name;
        private Long id;

        public ModelHolder(Model m) {
            name = m.getName();
            id = m.getId();
        }
    }
}
