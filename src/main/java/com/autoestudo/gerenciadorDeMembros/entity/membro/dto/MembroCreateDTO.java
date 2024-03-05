package com.autoestudo.gerenciadorDeMembros.entity.membro.dto;

import java.time.LocalDate;

public record MembroCreateDTO(
        String nomeCompleto,
        String apelido,
        String endereco,
        String telefone,
        LocalDate dataNascimento,
        LocalDate dataInclusao,
        Long diaconoResponsavelId
) { }
