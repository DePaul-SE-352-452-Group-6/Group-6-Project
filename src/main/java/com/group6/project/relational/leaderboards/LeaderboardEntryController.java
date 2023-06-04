package com.group6.project.relational.leaderboards;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/leaderboard")
@Log4j2
public class LeaderboardEntryController {
    @Autowired
    private LeaderboardEntryRepository leaderboardRepo;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("leaderboards", leaderboardRepo.findAll(Sort.by(Sort.Direction.DESC, "amount")));
        return "digitalassets/Leaderboard";
    }


}
