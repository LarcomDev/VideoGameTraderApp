package com.larcomlabs.lab1.Repos;

import com.larcomlabs.lab1.Models.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource
public interface VideoGameRepository extends JpaRepository<VideoGame, Integer>
{
}
