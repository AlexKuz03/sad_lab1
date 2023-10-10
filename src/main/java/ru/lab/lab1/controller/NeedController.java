package ru.lab.lab1.controller;

import org.springframework.web.bind.annotation.*;
import ru.lab.lab1.model.Human;
import ru.lab.lab1.model.Need;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("api/needs")
public class NeedController {
    private final ArrayList<Need> needs = new ArrayList<>();
//
    public NeedController() {
        needs.add(new Need(1L, 1L, "sleep", "H", 8.0));
        needs.add(new Need(2L, 2L, "sleep", "H", 8.5));
        needs.add(new Need(3L, 1L, "water", "L", 1.5));
        needs.add(new Need(4L, 2L, "water", "L", 2.0));
    }

    @GetMapping
    public List<Need> getNeeds() {
        return needs;
    }

    @GetMapping("/{need_id}")
    public List<Need> getNeeds(@PathVariable("need_id") Long needId) {
        return needs.stream()
                .filter(need -> need.humanId().equals(needId))
                .toList();
    }
    @PostMapping
    Need createNeed(@RequestBody Need newNeed) {
        needs.add(newNeed);
        return newNeed;
    }
    @DeleteMapping("/{need_id}")
    Boolean deleteNeed(@PathVariable("need_id") Long needId) {
        return needs.remove(needs.stream()
                .filter(human -> human.id().equals(needId))
                .findAny()
                .orElseThrow(IndexOutOfBoundsException::new));
    }

    @PutMapping("/{need_id}")
    Need editNeed(@PathVariable("need_id") Long needId, @RequestBody Need renewNeed) {
        needs.set(needs.indexOf(needs.stream()
                .filter(need -> need.id().equals(needId))
                .findAny()
                .orElseThrow(IndexOutOfBoundsException::new)), renewNeed);
        return renewNeed;
    }
}
