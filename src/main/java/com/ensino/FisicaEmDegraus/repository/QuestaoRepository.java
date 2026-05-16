package com.ensino.FisicaEmDegraus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensino.FisicaEmDegraus.model.Questao;

public interface QuestaoRepository extends JpaRepository<Questao, Long> {

    List<Questao> findByNivel(int nivel);

    List<Questao> findByNivelOrderById(int nivel);

    Questao findFirstByIdGreaterThanOrderByIdAsc(Long id);

    Questao findFirstByAreaAndIdGreaterThanOrderByIdAsc(String area, Long id);

    Questao findFirstByAreaAndNivelOrderByIdAsc(String area, int nivel);

    List<Questao> findByNivelAndIdNotInOrderById(
            int nivel,
            List<Long> ids);
}