package com.api.batuque.adpater.input.gira.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GiraResponse {
    private Long id;
    private String nomeGira;
    private LocalDateTime dataGira;
    private List<ItemGiraResponse> playlist;
}
