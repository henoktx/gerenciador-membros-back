package com.autoestudo.gerenciadorDeMembros.entity.membro.dto;

import java.time.LocalDate;

public record MembroUpdateDTO(
        Long id,
        String nomeCompleto,
        String apelido,
        String endereco,
        String telefone,
        LocalDate dataNascimento,
        LocalDate dataInclusao,
        Long diaconoResponsavelId
) { }
