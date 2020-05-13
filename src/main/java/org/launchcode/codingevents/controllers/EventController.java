package org.launchcode.codingevents.controllers;

import com.sun.java.accessibility.util.EventID;
import org.launchcode.codingevents.data.EventData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.launchcode.codingevents.models.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris Bay
 */
@Controller
@RequestMapping("events")
public class EventController {

    private static List<Event> events = new ArrayList<>();

    @GetMapping
    public String displayAllEvents(Model model) {
        model.addAttribute("title", "All Events");
        model.addAttribute("events", EventData.getAll());
        return "events/index";
    }

    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        return "events/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute Event newEvent) {
        EventData.add(newEvent);
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {

        if (eventIds != null) {
            for (int id : eventIds) {
                EventData.remove(id);
            }
        }
        return "redirect:";
    }
    //Create a method to display an edit form with this signature:
    @GetMapping("edit/{eventID}")
    public String displayEditForm(Model model, @PathVariable int EventID) {
        model.addAttribute("title", "Edit Event " + EventData.getById(EventID).);
        model.addAttribute(("events", EventData.getById(EventID));
        System.out.println("displayEditForm method reached");
        return "events/edit";
    }
    //Create a method to process the form with this signature:
    @PostMapping("edit")
    public String processEditForm(int EventId, String name, String description) {
        System.out.println("processEditForm method reached");
        EventData.getById(EventId).setName(name);
        EventData.getById(EventId).setDescription(description);
        return "redirect:";
    }
}
