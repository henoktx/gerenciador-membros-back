package com.autoestudo.gerenciadorDeMembros.controller;

import com.autoestudo.gerenciadorDeMembros.repository.DiaconoRepository;
import com.autoestudo.gerenciadorDeMembros.repository.projection.DiaconoSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/diaconos")
public class DiaconoController {
    private final DiaconoRepository diaconoRepository;

    public DiaconoController(DiaconoRepository diaconoRepository) {
        this.diaconoRepository = diaconoRepository;
    }

    @GetMapping
    public ResponseEntity<Page<DiaconoSummary>> findAllDiaconos(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DiaconoSummary> res = this.diaconoRepository.findByDiaconoResponsavelNull(
                pageable,
                DiaconoSummary.class
        );

        return ResponseEntity.ok(res);
    }

    @GetMapping("/supervisionados/{diaconoId}")
    public ResponseEntity<Page<DiaconoSummary>> findMembrosByDiacono(
            @PathVariable Long diaconoId,
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DiaconoSummary> res = this.diaconoRepository.findByDiaconoResponsavelId(
                pageable,
                diaconoId,
                DiaconoSummary.class
        );

        return ResponseEntity.ok(res);
    }
}
