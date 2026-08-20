package com.api.batuque.domain.enums;

import lombok.Getter;

@Getter
public enum FalangeEntidadeEnum {

    FIGUEIRA(1, "Figueira"),
    MULAMBO(2, "Mulambo"),
    SETEGARGALHADAS(3, "Sete Gargalhadas"),
    CAVEIRA(4, "Caveira"),
    MARABO(5, "Marabô"),
    TRANCA_RUAS(6, "Tranca Ruas"),
    TIRIRI(7, "Tiriri"),
    ZE_PILINTRA(8, "Zé Pilintra"),
    PADILHA(9, "Padilha"),
    SETE_ENCRUZILHADAS(10, "Sete Encruzilhadas"),
    MEIA_NOITE(11, "Meia-Noite"),
    CAPA_PRETA(12, "Capa Preta"),
    VELUDO(13, "Veludo"),
    GIRA_MUNDO(14, "Gira Mundo"),
    CHAMA_DINHEIRO(15, "Chama Dinheiro"),

    // --- Orixás Principais da Umbanda ---
    OXALA(16, "Oxalá"),
    OXUM(17, "Oxum"),
    OGUM(18, "Ogum"),
    OXOSSI(19, "Oxóssi"),
    XANGO(20, "Xangô"),
    IANSAN(21, "Iansã"),
    IEMANJA(22, "Iemanjá"),
    NANAN(23, "Nanã"),
    OMOLU(24, "Omolu"),
    OBALUAIE(25, "Obaluaê"),
    OXUMARE(26, "Oxumaré"),
    LOGUNEDE(27, "Logunedé"),
    EWA(28, "Ewá"),
    OBA(29, "Obá"),
    TEMPO_IROKO(30, "Tempo Iroko");

    private final int code;
    private final String descrition;

    FalangeEntidadeEnum(int code, String descrition) {
        this.code = code;
        this.descrition = descrition;
    }

    public static FalangeEntidadeEnum fromCode(int code) {
        for (FalangeEntidadeEnum entidade : FalangeEntidadeEnum.values()) {
            if (entidade.getCode() == code) {
                return entidade;
            }
        }
        throw new IllegalArgumentException("Código inválido para EntidadesEnum: " + code);
    }

    public static FalangeEntidadeEnum fromDescrition(String descrition) {
        for (FalangeEntidadeEnum entidade : FalangeEntidadeEnum.values()) {
            if (entidade.getDescrition().equals(descrition)) {
                return entidade;
            }
        }
        throw new IllegalArgumentException("Código inválido para EntidadesEnum: " + descrition);
    }
}