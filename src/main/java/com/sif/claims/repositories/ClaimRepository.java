package com.sif.claims.repositories;

import com.sif.claims.entities.Claim;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "claims", path = "claims")
public interface ClaimRepository extends PagingAndSortingRepository<Claim, Long> {

    @RestResource(path = "numbers", rel = "numbers")
    Claim findByNumber(String number);
}
