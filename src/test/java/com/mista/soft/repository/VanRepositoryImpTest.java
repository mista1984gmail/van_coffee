package com.mista.soft.repository;

import com.mista.soft.db.DB;
import com.mista.soft.domain.Van;
import com.mista.soft.util.VanFixture;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class VanRepositoryImpTest {
    private VanRepositoryImp vanRepository = new VanRepositoryImp();

    @BeforeAll
    public void setUp() {
        log.info("Test for 'VanRepositoryImpl' are started.");
    }

    @AfterAll
    public void tearDown() {
        log.info("Test for 'VanRepositoryImpl' are finished.");
    }

    @Test
    public void testSaveVan() throws Exception {
        // GIVEN
        Van van = VanFixture.createVan();

        // WHEN
        boolean isUserSaved = vanRepository.saveVan(van);

        // THEN
        assertThat(isUserSaved).isTrue();
        Van vanFromDB = DB.getInstance().executeGetOperation(0)
                .orElseThrow(RuntimeException::new);
        Van vanForAssert = VanFixture.createVan();
        assertThat(vanForAssert).usingRecursiveComparison().ignoringFields("id")
                .isEqualTo(vanForAssert);
    }

    @Test
    public void  testGetVan() throws Exception {
        // GIVEN
        Van van = VanFixture.createVan();
        DB.getInstance().executeSaveOperation(van);

        // WHEN
        Optional<Van> vanFromRepo = vanRepository.getVan(0);

        // THEN
        assertThat(vanFromRepo).contains(van);

    }

    @Test
    public void testGetVans() throws Exception {
        // GIVEN
        int expectedSize = 4;
        for (int i = 0; i < expectedSize; i++) {
            Van van = VanFixture.createVan();
            DB.getInstance().executeSaveOperation(van);
        }

        // WHEN
        List<Van> vans;
        vans = vanRepository.getVans();

        // THEN
        assertThat(vans).hasSize(expectedSize).doesNotContainNull();

    }

}
