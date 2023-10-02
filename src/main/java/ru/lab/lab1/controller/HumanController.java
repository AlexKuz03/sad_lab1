package ru.lab.lab1.controller;

import org.springframework.web.bind.annotation.*;
import ru.lab.lab1.model.Human;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/humans")
public class HumanController {
    private final ArrayList<Human> humans = new ArrayList<>();

    public HumanController() {
        try {
            String strDate1 = "11.10.1997";
            DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            Date date1 = formatter.parse(strDate1);

            String strDate2 = "07.12.1991";
            Date date2 = formatter.parse(strDate2);

            Human u1 = new Human(1L, "Robert", date1, 175, 72);
            Human u2 = new Human(2L, "Michael", date2, 181, 80);

            humans.add(u1);
            humans.add(u2);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public List<Human> getHumans() {
        return humans;
    }

    @GetMapping("/{human_id}")
    public Human getHuman(@PathVariable("human_id") Long humanId) {
        return humans.stream()
                .filter(human -> human.id().equals(humanId))
                .findAny()
                .orElse(null);
    }

    @PostMapping
    Human postHuman(@RequestBody Human newHuman) {
        humans.add(newHuman);
        return newHuman;
    }
    @DeleteMapping("/{human_id}")
    Boolean deleteHuman(@PathVariable("human_id") Long humanId) {
        return humans.remove(humans.stream()
                .filter(human -> human.id().equals(humanId))
                .findAny()
                .orElse(null));
    }

    @PutMapping("/{human_id}")
    Human putHuman(@PathVariable("human_id") Long humanId, @RequestBody Human renewHuman) {
        humans.set(humans.indexOf(humans.stream()
                .filter(human -> human.id().equals(humanId))
                .findAny()
                .orElse(null)), renewHuman);
        return renewHuman;
    }

}
