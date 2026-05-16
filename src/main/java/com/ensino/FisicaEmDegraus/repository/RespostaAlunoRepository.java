package com.ensino.FisicaEmDegraus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ensino.FisicaEmDegraus.model.RespostaAluno;

public interface RespostaAlunoRepository extends JpaRepository<RespostaAluno, Long> {

    long countByArea(String area);

    long countByAreaAndAcertouTrue(String area);

    long countByNomeAlunoAndNivelAndAcertouTrue(
            String nomeAluno,
            int nivel);

    @Query("SELECT r.questaoId FROM RespostaAluno r WHERE r.nomeAluno = :nomeAluno")
    List<Long> findQuestoesRespondidas(@Param("nomeAluno") String nomeAluno);

    long countByNomeAlunoAndAcertouTrue(String nomeAluno);

}