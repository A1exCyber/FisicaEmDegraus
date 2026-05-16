package com.ensino.FisicaEmDegraus.model;

import jakarta.persistence.Column;
//import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Questao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String enunciado;

    @Column(name = "alternativa_a")
    private String alternativaA;

    @Column(name = "alternativa_b")
    private String alternativaB;

    @Column(name = "alternativa_c")
    private String alternativaC;

    @Column(name = "alternativa_d")
    private String alternativaD;

    @Column(name = "alternativa_e")
    private String alternativaE;

    @Column(name = "resposta_correta")
    private String resposta;

    @Column(name = "area")
    private String area;

    @Column(name = "resolucao_comentada")
    private String resolucaoComentada;

    public String getResolucaoComentada() {
        return resolucaoComentada;
    }

    public void setResolucaoComentada(String resolucaoComentada) {
        this.resolucaoComentada = resolucaoComentada;
    }

    private Integer nivel;

    public void setId(Long id) {
        this.id = id;
    }

    public String getRespostaCorreta() {
        return resposta;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public void setAlternativaA(String alternativaA) {
        this.alternativaA = alternativaA;
    }

    public void setAlternativaB(String alternativaB) {
        this.alternativaB = alternativaB;
    }

    public void setAlternativaC(String alternativaC) {
        this.alternativaC = alternativaC;
    }

    public void setAlternativaD(String alternativaD) {
        this.alternativaD = alternativaD;
    }

    public void setAlternativaE(String alternativaE) {
        this.alternativaE = alternativaE;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Long getId() {
        return id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public String getAlternativaA() {
        return alternativaA;
    }

    public String getAlternativaB() {
        return alternativaB;
    }

    public String getAlternativaC() {
        return alternativaC;
    }

    public String getAlternativaD() {
        return alternativaD;
    }

    public String getAlternativaE() {
        return alternativaE;
    }

    public String getResposta() {
        return resposta;
    }

    public Integer getNivel() {
        return nivel;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Questao() {
    }

}