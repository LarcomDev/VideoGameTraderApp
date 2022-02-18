package com.larcomlabs.lab1.Repos;

import com.larcomlabs.lab1.Models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Integer>
{
}
