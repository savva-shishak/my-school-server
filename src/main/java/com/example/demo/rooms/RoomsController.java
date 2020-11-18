package com.example.demo.rooms;

import com.example.demo.abstractcrud.CrudController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("rooms")
public class RoomsController extends CrudController<Room, RoomsRepo> {
    public RoomsController() {
        super("rooms", Room::new, new ListViewImpl(), new InfoImpl());
    }
}
