package com.api.batuque.domain.enums;

import lombok.Getter;

@Getter
public enum LinhaEntidadeEnum {

    ORIXA(1, "Orixá"),
    EXU(2, "Exu"),
    POMBAGIRA(3, "Pombagira"),
    BAIANO(4, "Baiano"),
    CIGANO(5, "Cigano"),
    ERE(6, "Erê"),
    CABOCLO(7, "Caboclo"),
    BOIADEIRO(8, "Boiadeiro"),
    ORIENTE(9, "Oriente"),
    MARINHEIRO(10, "Marinheiro"),
    PRETO_VELHO(11, "Preto Velho");

    private final int code;
    private final String descrition;

    LinhaEntidadeEnum(int code, String descrition) {
        this.code = code;
        this.descrition = descrition;
    }

    public static LinhaEntidadeEnum fromCode(int code) {
        for (LinhaEntidadeEnum entidade : LinhaEntidadeEnum.values()) {
            if (entidade.getCode() == code) {
                return entidade;
            }
        }
        throw new IllegalArgumentException("Código inválido para TipoEntidadeEnum: " + code);
    }

    public static LinhaEntidadeEnum fromDescrition(String descrition) {
        for (LinhaEntidadeEnum entidade : LinhaEntidadeEnum.values()) {
            if (entidade.getDescrition().equals(descrition)) {
                return entidade;
            }
        }
        throw new IllegalArgumentException("Código inválido para EntidadesEnum: " + descrition);
    }
}