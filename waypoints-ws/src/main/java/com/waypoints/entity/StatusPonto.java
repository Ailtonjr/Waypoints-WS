package com.waypoints.entity;

public enum StatusPonto {
    
    CANC("Cancelado", "ab2222"),
    CONC("Concluído", "30c741"),
    EAND("Em andamento", "f3ff52"), 
    NINC("Não Iniciado", "8a8785");
    
    private final String descricao;
    private final String hexaCor;

    private StatusPonto(String descricao, String hexaCor) {
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
