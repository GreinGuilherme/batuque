package com.api.batuque.domain.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SyncEvent<T> {
    private String acao; // "CREATE", "UPDATE", "DELETE"
    private T dados;     // A entidade completa ou apenas o ID
}
