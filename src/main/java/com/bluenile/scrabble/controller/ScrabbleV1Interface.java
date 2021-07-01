package com.bluenile.scrabble.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping
public interface ScrabbleV1Interface {

    @GetMapping(value = "words/{letters}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<String> getScrabble(@NotNull @PathVariable String letters);
}
