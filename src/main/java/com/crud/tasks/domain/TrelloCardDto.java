package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data                       //Zamiast: @Getter & @AllArgsConstructor & @NoArgsConstructor
@AllArgsConstructor
public class TrelloCardDto {

    private String name;
    private String description;
    private String pos;
    private String listId;
}
