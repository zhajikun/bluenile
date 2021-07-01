package com.bluenile.scrabble.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ScrabbleServiceTest {

    @Autowired
    private ScrabbleService scrabbleService;

    @Test
    void getScrabbleList() {

        List<String> resultList = scrabbleService.getScrabbleList("hat");
        assertNotNull(resultList);
        assertEquals(6, resultList.size());
        assertEquals("hat", resultList.get(0));
        assertEquals("a", resultList.get(resultList.size() - 1 ));

        resultList = scrabbleService.getScrabbleList("zzz");
        assertNotNull(resultList);
        assertEquals(0, resultList.size());
    }


}