package com.ensino.FisicaEmDegraus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ensino.FisicaEmDegraus.model.RespostaAluno;

import jakarta.transaction.Transactional;

public interface RespostaAlunoRepository extends JpaRepository<RespostaAluno, Long> {

        long countByArea(String area);

        long countByAreaAndAcertouTrue(String area);

        long countByNomeAlunoAndNivelAndAcertouTrue(
                        String nomeAluno,
                        int nivel);

        @Query("SELECT r.questaoId FROM RespostaAluno r WHERE r.nomeAluno = :nomeAluno")
        List<Long> findQuestoesRespondidas(@Param("nomeAluno") String nomeAluno);

        long countByNomeAlunoAndAcertouTrue(String nomeAluno);

        List<RespostaAluno> findByNomeAlunoAndNivel(String nomeAluno, int nivel);

        long countByNomeAlunoAndNivelAndAcertouFalse(String nomeAluno, int nivel);

        @Query("""
                        SELECT r.questaoId
                        FROM RespostaAluno r
                        WHERE r.nomeAluno = :nomeAluno
                        AND r.nivel = :nivel
                        AND r.acertou = true
                        """)
        List<Long> findQuestoesAcertadas(
                        @Param("nomeAluno") String nomeAluno,
                        @Param("nivel") int nivel);

        @Transactional
        void deleteByNomeAluno(String nomeAluno);
}