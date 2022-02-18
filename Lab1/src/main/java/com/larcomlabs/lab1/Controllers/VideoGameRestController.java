package com.larcomlabs.lab1.Controllers;

import com.larcomlabs.lab1.Models.User;
import com.larcomlabs.lab1.Models.VideoGame;
import com.larcomlabs.lab1.Repos.VideoGameRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class VideoGameRestController
{
    private VideoGameRepository repo;

    public VideoGameRestController(VideoGameRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<VideoGame> getVideoGames() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public VideoGame getGameById(@PathVariable int id){
        return repo.findById(id).get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createGame(@AuthenticationPrincipal User u, @RequestBody VideoGame game){
        game.setOwner(u);
        repo.save(game);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable int id) {
        repo.deleteById(id);
    }
}
