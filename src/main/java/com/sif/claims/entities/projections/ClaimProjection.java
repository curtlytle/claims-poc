package com.sif.claims.entities.projections;

import com.sif.claims.entities.Claim;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "claimProjection", types = { Claim.class })
public interface ClaimProjection {
    long getId();

    String getNumber();

    String getDescription();
}
