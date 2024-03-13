package com.autoestudo.gerenciadorDeMembros.controller;

import com.autoestudo.gerenciadorDeMembros.entity.membro.MembroEntity;
import com.autoestudo.gerenciadorDeMembros.entity.membro.dto.MembroCreateDTO;
import com.autoestudo.gerenciadorDeMembros.entity.membro.dto.MembroUpdateDTO;
import com.autoestudo.gerenciadorDeMembros.repository.MembroRepository;
import com.autoestudo.gerenciadorDeMembros.repository.projection.MembroSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/membros")
public class MembroController {
    private final MembroRepository membroRepository;

    public MembroController(MembroRepository membroRepository) {
        this.membroRepository = membroRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<MembroSummary>> findById(
            @PathVariable long id
    ) {
        Optional<MembroSummary> membro = this.membroRepository.findById(id, MembroSummary.class);

        if (membro.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(membro);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Page<MembroSummary>> findByNome(
            @RequestParam("name") String nome,
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        Page<MembroSummary> membros = this.membroRepository.findByNomeCompletoContainsIgnoreCase(
                pageable,
                nome,
                MembroSummary.class
        );

        return ResponseEntity.ok(membros);
    }

    @PostMapping("/create")
    public ResponseEntity<MembroEntity> create(@RequestBody MembroCreateDTO novoMembro) {
        MembroEntity diaconoResponsavel = null;

        if (novoMembro.diaconoResponsavelId() != null) {
            if (!this.membroRepository.existsById(novoMembro.diaconoResponsavelId())) {
                return ResponseEntity.notFound().build();
            }

            diaconoResponsavel = this.membroRepository.findById(novoMembro.diaconoResponsavelId()).get();
        }

        MembroEntity novoMembroSalvo = membroRepository.save(
                MembroEntity
                        .builder()
                        .nomeCompleto(novoMembro.nomeCompleto())
                        .apelido(novoMembro.apelido())
                        .endereco(novoMembro.endereco())
                        .dataNascimento(novoMembro.dataNascimento())
                        .dataInclusao(novoMembro.dataInclusao())
                        .telefone(novoMembro.telefone())
                        .diaconoResponsavel(diaconoResponsavel)
                        .build()
        );
        return new ResponseEntity<>(novoMembroSalvo, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody MembroUpdateDTO membroAlterado) {
        Optional<MembroEntity> membro = membroRepository.findById(membroAlterado.id());
        MembroEntity diaconoResponsavel = null;

        if (membro.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (membroAlterado.diaconoResponsavelId() != null) {
            if (!this.membroRepository.existsById(membroAlterado.diaconoResponsavelId())) {
                return ResponseEntity.notFound().build();
            }

            diaconoResponsavel = this.membroRepository.findById(membroAlterado.diaconoResponsavelId()).get();
        }

        membro.get().setNomeCompleto(membroAlterado.nomeCompleto());
        membro.get().setApelido(membroAlterado.apelido());
        membro.get().setEndereco(membroAlterado.endereco());
        membro.get().setTelefone(membroAlterado.telefone());
        membro.get().setDataNascimento(membroAlterado.dataNascimento());
        membro.get().setDataInclusao(membroAlterado.dataInclusao());
        membro.get().setDiaconoResponsavel(diaconoResponsavel);

        this.membroRepository.save(membro.get());

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!this.membroRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        this.membroRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}