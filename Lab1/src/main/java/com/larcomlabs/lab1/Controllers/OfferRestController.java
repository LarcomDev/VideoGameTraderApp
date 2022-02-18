package com.larcomlabs.lab1.Controllers;

import com.larcomlabs.lab1.Models.Offer;
import com.larcomlabs.lab1.Models.VideoGame;
import com.larcomlabs.lab1.Repos.OfferRepository;
import com.larcomlabs.lab1.Repos.UserRepository;
import com.larcomlabs.lab1.Repos.VideoGameRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/offer")
public class OfferRestController
{
    private OfferRepository repo;
    private UserRepository userRepo;
    private VideoGameRepository gameRepo;

    public OfferRestController(OfferRepository repo, UserRepository userRepo, VideoGameRepository gameRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
        this.gameRepo = gameRepo;
    }

    @GetMapping
    public List<Offer> getAllOffers() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Offer getById(@PathVariable int id) {
        return repo.findById(id).get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOffer(@RequestBody String req) {
        JSONObject obj = new JSONObject(req);
        Offer offer = new Offer();
        offer.setDateTime(LocalDateTime.now());
        offer.setOfferee(userRepo.findById(obj.getString("offeree")).get());
        System.out.println(req);

        JSONArray forArr = obj.getJSONArray("offeredFor");
        for(int i : getGameIdsFromRequest(forArr)) {
            offer.getOfferedFor().add(gameRepo.findById(i).get());
        }
        JSONArray toArr = obj.getJSONArray("offeredTo");
        for(int i : getGameIdsFromRequest(toArr)) {
            offer.getOfferedTo().add(gameRepo.findById(i).get());
        }
        repo.save(offer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOfferById(@PathVariable int id) {
        repo.deleteById(id);
    }

    @PutMapping("/{id}")
    public void updateStatus(@PathVariable int id, @RequestParam String status) {
        Offer offer = repo.findById(id).get();
        switch(status.toUpperCase()) {
            case "ACCEPTED":
                offer.setStatus(Offer.eStatus.ACCEPTED);
                repo.save(offer);
                break;
            case "REJECTED":
                offer.setStatus(Offer.eStatus.REJECTED);
                repo.save(offer);
                break;
            default:
                break;
        }
    }

    //used to get the game ids from the json array which stores the numbers as type Object
    public int[] getGameIdsFromRequest(JSONArray arr) {
        int[] ints = new int[arr.length()];
        for(int i = 0; i < arr.length(); i++) {
            ints[i] = arr.optInt(i);
        }
        return ints;
    }
}
