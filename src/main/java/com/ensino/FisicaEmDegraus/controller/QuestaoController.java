package com.ensino.FisicaEmDegraus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ensino.FisicaEmDegraus.model.Questao;
import com.ensino.FisicaEmDegraus.model.RespostaAluno;
import com.ensino.FisicaEmDegraus.repository.RespostaAlunoRepository;
import com.ensino.FisicaEmDegraus.service.QuestaoService;

@Controller
public class QuestaoController {

    private final QuestaoService questaoService;

    private final RespostaAlunoRepository respostaAlunoRepository;

    public QuestaoController(QuestaoService questaoService,
            RespostaAlunoRepository respostaAlunoRepository) {
        this.questaoService = questaoService;
        this.respostaAlunoRepository = respostaAlunoRepository;
    }

    @GetMapping("/")
    public String inicio() {
        return "inicio"; // nova página
    }

    @PostMapping("/responder")
    public String responder(
            @RequestParam String resposta,
            @RequestParam Long questaoId,
            @RequestParam String nomeAluno,
            Model model) {

        Questao questao = questaoService.buscarPorId(questaoId);

        boolean acertou = resposta.equals(questao.getRespostaCorreta());

        model.addAttribute("questao", questao);
        model.addAttribute("acertou", acertou);
        model.addAttribute("resposta", resposta);
        model.addAttribute("nomeAluno", nomeAluno);

        RespostaAluno r = new RespostaAluno();
        r.setNomeAluno(nomeAluno);
        r.setQuestaoId(questaoId);
        r.setRespostaMarcada(resposta);
        r.setAcertou(acertou);
        r.setArea(questao.getArea());
        r.setNivel(questao.getNivel());

        respostaAlunoRepository.save(r);

        return "index";
    }

    @GetMapping("/proxima")
    public String proximaQuestao(
            @RequestParam int nivel,
            @RequestParam String nomeAluno,
            Model model) {

        long acertosNivel = respostaAlunoRepository
                .countByNomeAlunoAndNivelAndAcertouTrue(nomeAluno, nivel);

        // sobe de nível
        if (acertosNivel >= 5) {
            nivel++;
            acertosNivel = 0;
        }

        Questao proxima = questaoService.buscarQuestaoNaoRespondida(nomeAluno, nivel);

        if (proxima == null) {
            model.addAttribute("mensagem",
                    "🎮 Game Over! Não há mais questões.");
            return "index";
        }

        model.addAttribute("questao", proxima);
        model.addAttribute("nomeAluno", nomeAluno);
        model.addAttribute("acertosNivel", acertosNivel);

        return "index";
    }

    @GetMapping("/relatorio")
    public String relatorio(Model model) {

        long totalMecanica = respostaAlunoRepository.countByArea("MECANICA");
        long acertosMecanica = respostaAlunoRepository.countByAreaAndAcertouTrue("MECANICA");

        double taxaMecanica = (totalMecanica > 0)
                ? (acertosMecanica * 100.0 / totalMecanica)
                : 0;

        model.addAttribute("totalMecanica", totalMecanica);
        model.addAttribute("acertosMecanica", acertosMecanica);
        model.addAttribute("taxaMecanica", taxaMecanica);

        return "relatorio";
    }

    @PostMapping("/iniciar")
    public String iniciar(@RequestParam String nomeAluno, Model model) {

        Questao q = questaoService
                .buscarQuestaoNaoRespondida(nomeAluno, 1);

        model.addAttribute("questao", q);
        model.addAttribute("nomeAluno", nomeAluno);
        model.addAttribute("acertosNivel", 0);

        return "index";
    }
}