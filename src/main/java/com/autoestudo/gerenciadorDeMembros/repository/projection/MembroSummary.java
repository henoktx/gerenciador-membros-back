package com.autoestudo.gerenciadorDeMembros.repository.projection;

import java.time.LocalDate;

public interface MembroSummary {
    Long getId();
    String getNomeCompleto();
    String getApelido();
    String getTelefone();
    String getEndereco();
    LocalDate getDataNascimento();
    LocalDate getDataInclusao();
    Long getDiaconoResponsavelId();
}
