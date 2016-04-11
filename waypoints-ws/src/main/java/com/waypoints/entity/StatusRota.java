package com.waypoints.entity;

public enum StatusRota {
    
    AGND("Agendada", "c77130"),
    CANC("Cancelada", "ab2222"),
    CONC("Conclu�da", "30c741"),
    EAND("Em andamento", "f3ff52"),
    NINC("N�o Iniciada", "8a8785");
    
    private final String descricao;
    private final String hexaCor;

    private StatusRota(String descricao, String hexaCor) {
        this.descricao = descricao;
        this.hexaCor = hexaCor;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getHexaCor() {
        return hexaCor;
    }
    
}
