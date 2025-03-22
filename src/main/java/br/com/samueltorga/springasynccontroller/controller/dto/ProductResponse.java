package br.com.samueltorga.springasynccontroller.controller.dto;

import java.util.List;

public record ProductResponse(String name, List<TagResponse> tags) {

    public record TagResponse(Integer id, String name) {
    }
}
