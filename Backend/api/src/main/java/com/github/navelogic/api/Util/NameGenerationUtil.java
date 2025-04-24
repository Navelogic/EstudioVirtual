package com.github.navelogic.api.Util;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class NameGenerationUtil {

    private static final Random random = new Random();

    private static final List<String> STUDIO_PREFIXES = Arrays.asList(
            "Aurora", "Brisa", "Claraboia", "Caleidoscópio", "Origem", "Radiante", "Alvorada", "Miragem",
            "Luminosa", "Astro", "Épico", "Icone", "Arquetipo", "Sinapse", "Nimbus", "Zeppelin", "Vértice",
            "Arcanjo", "Metrópole", "Atlântico", "Tropikos", "Orbe", "Caravela", "Horizonte", "Maré", "Atlântida",
            "Savanna", "Ipiranga", "Cariri", "Ibiúna", "Ybyrá", "Tapajós", "Caruá", "Urucum", "Mandacaru", "Uirapuru",
            "Cacau", "Guaraná", "Aquarela", "Encanto", "Odisséia", "Lenda", "Estigma", "Verve", "Veredas", "Vagalume",
            "Infinito", "Solar", "Nômade", "Éden", "Orum", "Egrégora", "Quintessência", "Êxtase", "Zênite", "Obsidiana",
            "Neon", "Cítrica", "Quasar", "Vector", "Glitch", "Drone", "Criptico", "Nimbo", "Cipher", "Versátil",
            "Prisma", "Oásis", "Aether", "Labirinto", "Quimera", "Chama", "Fulgor", "Nimbus", "Singular"
    );

    private static final List<String> STUDIO_SUFFIXES = Arrays.asList(
            "Studios", "Filmes", "Pictures", "Produções", "Media", "Cinema", "Narrativas", "Criações", "Dreamworks",
            "Motion", "Imagem", "Frames", "Visão", "Imagine", "Tela", "Reel", "Roteiro", "Cenas", "Ateliê", "Núcleo",
            "Trama", "Casa Criativa", "Coletivo", "Fábrica", "Oficina", "Guilda", "Estúdio Criativo", "Fronteira",
            "Quintal", "Narrativa", "Manifesto", "Projeto", "Ciclo", "Fusão", "Plano", "Lab", "Imersão", "Vertigem",
            "Vortex", "Circuito", "Paisagem", "Alquimia", "Espiral", "Câmara", "Panorama", "Universo", "Constelação",
            "Verbo", "Código", "Voz", "Cena Livre", "Trem de Histórias", "Caixa de Sonhos", "Pixel", "Trilho", "Traço",
            "Som", "Ritmo", "Batida", "Orquestra", "Sintonia", "Estrofe", "Metáfora", "Verso", "Luz", "Chama", "Fluxo",
            "Inspiração", "Reflexo", "Fábula", "Sopro", "Semente", "Raiz", "Galho", "Flor", "Alma", "Aura", "Essência"
    );

    public String generateStudioName() {
        String prefix = STUDIO_PREFIXES.get(random.nextInt(STUDIO_PREFIXES.size()));
        String suffix = STUDIO_SUFFIXES.get(random.nextInt(STUDIO_SUFFIXES.size()));
        return prefix + " " + suffix;
    }

}
