package com.ensino.FisicaEmDegraus.service;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.ensino.FisicaEmDegraus.model.Questao;
import com.ensino.FisicaEmDegraus.repository.QuestaoRepository;
import com.ensino.FisicaEmDegraus.repository.RespostaAlunoRepository;

@Service
public class QuestaoService {

    private final QuestaoRepository questaoRepository;
    private final RespostaAlunoRepository respostaAlunoRepository;

    public QuestaoService(
            QuestaoRepository questaoRepository,
            RespostaAlunoRepository respostaAlunoRepository) {

        this.questaoRepository = questaoRepository;
        this.respostaAlunoRepository = respostaAlunoRepository;
    }

    public List<Questao> listarTodas() {
        return questaoRepository.findAll();
    }

    public Questao buscarPorId(Long id) {
        return questaoRepository.findById(id).orElse(null);
    }

    public Questao buscarQuestaoNaoRespondida(
            String nomeAluno,
            int nivel) {

        List<Long> idsAcertados = respostaAlunoRepository
                .findQuestoesAcertadas(
                        nomeAluno,
                        nivel);

        List<Questao> questoes;

        if (idsAcertados.isEmpty()) {

            questoes = questaoRepository
                    .findByNivelOrderById(nivel);

        } else {

            questoes = questaoRepository
                    .findByNivelAndIdNotInOrderById(
                            nivel,
                            idsAcertados);
        }

        if (questoes.isEmpty()) {
            return null;
        }

        Random random = new Random();

        return questoes.get(
                random.nextInt(questoes.size()));
    }
}