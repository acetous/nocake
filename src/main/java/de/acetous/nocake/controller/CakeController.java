package de.acetous.nocake.controller;

import de.acetous.nocake.service.LockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class CakeController {

    private LockService lockService;

    @Autowired
    public CakeController(LockService lockService) {
        this.lockService = lockService;
    }

    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {
        model.put("locked", lockService.isLocked());
        return "home";
    }
}
