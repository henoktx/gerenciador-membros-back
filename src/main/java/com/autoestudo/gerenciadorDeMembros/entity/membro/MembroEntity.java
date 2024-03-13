package com.autoestudo.gerenciadorDeMembros.entity.membro;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Table(name = "membro")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MembroEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String nomeCompleto;

    private String apelido;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false)
    private LocalDate dataInclusao;

    @ManyToOne()
    @JoinColumn(name = "diacono_responsavel_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MembroEntity diaconoResponsavel;
}