package com.sif.claims;

import com.sif.claims.entities.Claim;
import com.sif.claims.repositories.ClaimRepository;
import com.sif.claims.utils.TestPostgresContainer;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testcontainers.containers.Container;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ClaimsApplicationTests extends TestPostgresContainer {

    private static final Logger log = LoggerFactory.getLogger(ClaimsApplicationTests.class);

    @Autowired
    private ClaimRepository claimRepository;

    //@Test
//    @Disabled
    public void takeSnapshot() throws IOException, InterruptedException {
        // TODO this is using the hardcoded db name of claimdb, which the test container uses a dynamic name
        Container.ExecResult execResult = postgreDBContainer.execInContainer(
                "sh", "-c", "pg_dump -U claimdb claimdb > /snapshot.sql"
        );

        log.info("----------> TAKING SNAPSHOT <----------------");
        log.info(execResult.getStdout());
        log.error(execResult.getStderr());

        log.info("------------> Saving snapshot file...");
        postgreDBContainer.copyFileFromContainer("/snapshot.sql", "src/test/resources/snapshot.sql");
    }

    @Test
    void simpleTest() {

        List<Claim> claims = Lists.newArrayList(claimRepository.findAll());

        assertEquals(2, claims.size());
    }


}
