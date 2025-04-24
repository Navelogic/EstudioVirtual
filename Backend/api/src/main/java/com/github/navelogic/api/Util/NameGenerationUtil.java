package com.github.navelogic.api.Util;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class NameGenerationUtil {

    private static final Random random = new Random();

    public String generateStudioDescription(String studioName) {
        String[] introductions = {
                studioName + " é um estúdio audiovisual que acredita no poder das boas histórias.",
                "No coração da criatividade, nasce o " + studioName + ", especialista em contar histórias marcantes.",
                studioName + " transforma ideias em experiências visuais únicas.",
                "Criado para emocionar, o " + studioName + " trabalha com paixão por cada frame.",
                studioName + " é sinônimo de inovação no universo do entretenimento.",
                "Inspirado pelo cinema e pela cultura, o " + studioName + " dá vida a narrativas inesquecíveis.",
                studioName + " é onde a arte encontra a tecnologia para criar magia audiovisual.",
                "Mais que um estúdio, o " + studioName + " é um laboratório de ideias.",
                "Com ousadia e criatividade, o " + studioName + " transforma visão em realidade.",
                "O " + studioName + " é um coletivo de talentos movidos por propósito e emoção.",
                studioName + " é um espaço onde estética, impacto e técnica andam juntos.",
                "Fundado por apaixonados por cinema, o " + studioName + " entrega obras que marcam.",
                studioName + " nasce com o propósito de provocar, emocionar e inspirar.",
                studioName + " é a nova cara da produção cultural e audiovisual no Brasil.",
                "O " + studioName + " acredita que cada história tem o poder de transformar o mundo.",
                "Mais do que produzir, o " + studioName + " quer conectar pessoas por meio de histórias reais.",
                "Surgido da paixão pela sétima arte, o " + studioName + " redefine o audiovisual contemporâneo.",
                "O " + studioName + " é um espaço de criação onde sonhos ganham vida na tela.",
                "Construído sobre alicerces de talento e visão, o " + studioName + " é referência em sua área.",
                studioName + " se destaca como um polo criativo de narrativas impactantes.",
                "Em cada projeto, o " + studioName + " imprime sua assinatura única e reconhecível.",
                "Combinando tradição e inovação, o " + studioName + " cria experiências memoráveis.",
                "O " + studioName + " surgiu para desafiar convenções e expandir horizontes criativos.",
                "Com um olhar único sobre o cotidiano, o " + studioName + " transforma o ordinário em extraordinário.",
                studioName + " é um polo de criatividade que vai além do convencional.",
                "A história do " + studioName + " é escrita com frames que emocionam e inspiram.",
                "O " + studioName + " nasceu da convicção de que histórias bem contadas mudam o mundo.",
                studioName + " revoluciona a maneira como entendemos narrativas visuais.",
                "Movido pela paixão, o " + studioName + " busca a excelência em cada produção.",
                "Com equipe multidisciplinar, o " + studioName + " cria conteúdos que transcendem.",
                studioName + " é um berço de ideias inovadoras e execuções impecáveis.",
                "O " + studioName + " reinventa constantemente a arte de contar histórias.",
                "No universo audiovisual brasileiro, o " + studioName + " representa um novo paradigma.",
                "O jovem e dinâmico " + studioName + " traz frescor ao cenário audiovisual.",
                studioName + " é o encontro entre criatividade arrojada e técnica apurada.",
                "Celebrando a diversidade narrativa, o " + studioName + " constrói pontes entre mundos.",
                "O " + studioName + " cria universos onde memória, emoção e técnica dialogam.",
                studioName + ": onde cada história encontra sua melhor forma de ser contada.",
                "Com um time apaixonado e talentoso, o " + studioName + " redefine possibilidades.",
                "Idealizado por visionários, o " + studioName + " materializa o inimaginável.",
                "No " + studioName + ", cada produção é uma jornada de descobertas visuais."
        };

        String[] specialties = {
                "Especializado em filmes que emocionam, séries que marcam e conteúdos que viralizam.",
                "Nossa especialidade são narrativas autênticas e visuais impactantes para todas as telas.",
                "Focamos em produções que misturam arte, técnica e originalidade em cada detalhe.",
                "Desenvolvemos obras com identidade forte e linguagem contemporânea.",
                "Produzimos conteúdo audiovisual feito com alma, ritmo e estética única.",
                "Criamos projetos que exploram o novo sem perder a essência do storytelling.",
                "Somos especialistas em filmes publicitários, documentários e ficções de alta performance.",
                "Trabalhamos com estéticas modernas e narrativas com DNA brasileiro.",
                "Criamos experiências visuais para marcas, plataformas e pessoas.",
                "Desenvolvemos produções com impacto social, cultural e emocional.",
                "Nossa marca são conteúdos que cruzam fronteiras entre o real e o imaginário.",
                "Investimos em projetos com olhar autoral e sensibilidade visual.",
                "Nosso foco são narrativas que exploram o futuro com os pés no presente.",
                "Realizamos criações com excelência técnica e visão artística.",
                "Especializados em transformar conceitos abstratos em experiências visuais tangíveis.",
                "Nosso diferencial está na criação de universos cinematográficos imersivos.",
                "Somos referência em narrativas transmídia que expandem a experiência do público.",
                "Trabalhamos na interseção entre tecnologia de ponta e sensibilidade narrativa.",
                "Desenvolvemos formatos inovadores para o consumo de conteúdo contemporâneo.",
                "Nossa expertise está em contar histórias que ressoam com a alma brasileira.",
                "Produzimos desde webséries virais até longas-metragens premiados.",
                "Nosso trabalho equilibra estética sofisticada e mensagens acessíveis.",
                "Somos especialistas em campanhas audiovisuais de alto impacto para marcas.",
                "Transformamos briefings desafiadores em narrativas inesquecíveis.",
                "Destacamo-nos na criação de conteúdos que geram engajamento orgânico.",
                "Criamos documentários que revelam verdades essenciais sobre nossa sociedade.",
                "Especializados em dar voz a histórias marginalizadas com qualidade cinematográfica.",
                "Nossa marca são as produções que unem entretenimento e conscientização.",
                "Produzimos desde animações encantadoras até dramas intensos e reflexivos.",
                "Nossa força está em narrativas episódicas que cativam audiências fiéis."
        };

        String[] values = {
                " Cada projeto é tratado com cuidado, empatia e excelência.",
                " Aqui, criatividade, diversidade e autenticidade são prioridade.",
                " O estúdio acredita que histórias poderosas constroem mundos melhores.",
                " Valorizamos a pluralidade de vozes e a força da coletividade.",
                " Nosso compromisso é com o impacto positivo, dentro e fora da tela.",
                " A inovação não é um objetivo, é parte da nossa essência.",
                " Criar com propósito, emocionar com verdade e entreter com qualidade.",
                " Nossa missão é dar visibilidade a narrativas que merecem ser ouvidas.",
                " Trabalhamos com ética, paixão e um toque de ousadia.",
                " Cada frame é uma oportunidade de criar algo memorável.",
                " O estúdio é um espaço de liberdade criativa e respeito às diferenças.",
                " Com técnica refinada e paixão autêntica, entregamos resultados extraordinários.",
                " Valorizamos processos colaborativos e histórias com identidade.",
                " Nosso compromisso é com a excelência em cada etapa do processo criativo.",
                " Acreditamos no poder transformador da representatividade nas telas.",
                " Priorizamos relações transparentes e resultados que superam expectativas.",
                " Sustentabilidade e responsabilidade social guiam nossas produções.",
                " Buscamos o equilíbrio perfeito entre arte, técnica e negócios.",
                " Nossa força está na diversidade de perspectivas de nossa equipe.",
                " Acreditamos que grandes histórias nascem de perguntas corajosas.",
                " Nosso trabalho reflete o compromisso com a qualidade e a inovação.",
                " Cultivamos um ambiente que valoriza experimentação e aprendizado contínuo.",
                " O diálogo entre tradição narrativa e novas tecnologias define nossa abordagem.",
                " Defendemos um audiovisual brasileiro diverso, plural e globalmente relevante.",
                " Acreditamos que a emoção é o verdadeiro idioma universal.",
                " Nosso norte é sempre a conexão genuína com o público.",
                " Valorizamos parcerias duradouras baseadas em confiança e respeito mútuo.",
                " O respeito ao espectador é nosso princípio fundamental.",
                " Buscamos constantemente romper barreiras e explorar novos territórios narrativos.",
                " Nossa equipe é movida pela busca incessante da excelência audiovisual."
        };

        return introductions[random.nextInt(introductions.length)] +
                specialties[random.nextInt(specialties.length)] +
                values[random.nextInt(values.length)];
    }

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
