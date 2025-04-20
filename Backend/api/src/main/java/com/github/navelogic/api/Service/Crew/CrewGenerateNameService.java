package com.github.navelogic.api.Service.Crew;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CrewGenerateNameService {

    public String generateFirstName(Random random, String gender) {
        String[] maleNames = {
                "Aarav", "Aditya", "Alejandro", "Alex", "Ali", "Amir", "Antonio", "Arjun", "Aryan", "Ashwin", "Bruno",
                "Carlos", "Daniel", "Deepak", "Diego", "Eduardo", "Elias", "Enrique", "Fabio", "Felipe", "Fernando",
                "Gabriel", "Gaurav", "George", "Gustavo", "Harish", "Hector", "Hugo", "Ian", "Ibrahim", "Ishaan",
                "Ivan", "Jack", "Jacob", "Jai", "James", "Javier", "Jay", "Joao", "John", "Jose", "Julio", "Kabir",
                "Karan", "Kevin", "Leo", "Liam", "Lucas", "Luis", "Madhav", "Manish", "Manuel", "Mario", "Mateo",
                "Miguel", "Mohammad", "Naveen", "Noah", "Omar", "Pablo", "Pedro", "Rafael", "Rahul", "Raj", "Ravi",
                "Ricardo", "Rohan", "Ronaldo", "Ruben", "Ryan", "Alec", "Andrei", "Anselmo", "Artem", "Ayaan", "Aziz",
                "Baran", "Bastien", "Benicio", "Bento", "Bilal", "Bogdan", "Brice", "Caio", "Callum", "Casper", "Cem",
                "Ciro", "Conrad", "Danilo", "Dante", "Dario", "Denis", "Dilan", "Dimitri", "Dion", "Domenico", "Dusan",
                "Edoardo", "Efe", "Ege", "Emil", "Enzo", "Esteban", "Ethan", "Ezra", "Fabian", "Fermin", "Filip", "Arian",
                "Weslley", "Marcos", "Matheus", "Matteo", "Maxim", "Milo", "Mohammed", "Nicolas", "Abner", "Adalberto",
                "Adriano", "Agenor", "Ailton", "Airton", "Alcides", "Aldo", "Alef", "Alexandre",
                "Alfredo", "Aloísio", "Altair", "Aluísio", "Amadeu", "Amaro", "Amilcar", "Ananias", "Anderson", "Anselmo",
                "Antônio", "Aparecido", "Aquiles", "Arão", "Aristides", "Armando", "Arnaldo", "Arnoldo", "Artur", "Assis",
                "Ataíde", "Augusto", "Aureliano", "Aurelio", "Benedito", "Benjamim", "Bento", "Bernardo", "Bonifácio", "Bráulio",
                "Caetano", "Camilo", "Carlinhos", "Cássio", "Cauê", "Celso", "Célio", "César", "Cícero", "Cláudio",
                "Clemente", "Conrado", "Cristiano", "Dagoberto", "Damião", "Demétrio", "Deoclécio", "Deraldo", "Diogo", "Djalma",
                "Domingos", "Dorival", "Douglas", "Edgar", "Edilson", "Edimilson", "Edmar", "Edmilson", "Edu", "Elcio",
                "Eli", "Eliseu", "Elvis", "Emanoel", "Emanuel", "Emerson", "Eriberto", "Erick", "Ernani", "Ernesto",
                "Eron", "Eudes", "Evaldo", "Evandro", "Everton", "Ezequiel", "Ezio", "Fabrício", "Fausto", "Feliciano",
                "Flavio", "Florentino", "Francisco", "Franco", "Fred", "Frederico", "Geraldo", "Genival", "Geovani", "Gerson",
                "Getúlio", "Gil", "Gilberto", "Gilmar", "Givanildo", "Guilherme", "Hamilton", "Haroldo", "Heber", "Heitor",
                "Henrique", "Hermes", "Hernani", "Hilário", "Humberto", "Iago", "Igor", "Ilton", "Inácio", "Irineu",
                "Ismael", "Israel", "Itamar", "Ivanildo", "Ivo", "Jacinto", "Jair", "Jaques", "Jean", "Jerônimo",
                "Joaquim", "Joel", "Jonas", "Jonathan", "Jorge", "José", "Josias", "Josué", "Juan", "Juliano",
                "Júlio", "Jurandir", "Juscelino", "Juvenal", "Kadu", "Kaique", "Kleber", "Laerte", "Lauro", "Leandro",
                "Lélio", "Leonardo", "Lindolfo", "Lineu", "Lívio", "Lourenço", "Luiz", "Maciel", "Magno", "Maicon",
                "Maikon", "Manoel", "Marcelo", "Márcio", "Marco", "Marcos", "Marivaldo", "Mário", "Marlon", "Martinho",
                "Mateus", "Mauro", "Michel", "Milton", "Moacir", "Murilo", "Nabor", "Napoleão", "Natan", "Natanael",
                "Nelson", "Nélio", "Neuton", "Newton", "Nilson", "Nivaldo", "Noel", "Norberto", "Octávio", "Odair",
                "Odilon", "Olavo", "Onofre", "Orlando", "Osvaldo", "Otacílio", "Otávio", "Otoniel", "Paulo", "Pedro",
                "Plínio", "Quirino", "Rafael", "Raimundo", "Ramon", "Raul", "Reinaldo", "Renan", "Renato", "Ricardo",
                "Roberto", "Robson", "Rodolfo", "Rodrigo", "Rogério", "Rolando", "Ronaldo", "Roque", "Ruben", "Rubens",
                "Salatiel", "Samuel", "Sandro", "Saulo", "Sebastião", "Serafim", "Sérgio", "Severino", "Silas", "Silvano",
                "Silvério", "Sílvio", "Tadeu", "Tales", "Tarcísio", "Telmo", "Teodoro", "Thiago", "Tito", "Tomás",
                "Ubirajara", "Ulysses", "Uriel", "Valdemar", "Valdo", "Valmir", "Valter", "Vanderlei", "Vando", "Vicente",
                "Victor", "Vidal", "Vinicius", "Virgílio", "Vitor", "Vladimir", "Wagner", "Wallace", "Walter", "Washington",
                "Wellington", "Willian", "Wilson", "Wladimir", "Yago", "Yan", "Yuri", "Zacarias", "Zeca", "Zelito"
        };

        String[] femaleNames = {
                "Aisha", "Alicia", "Alisha", "Ana", "Anika", "Anita", "Anjali", "Beatriz", "Carolina", "Catarina",
                "Diya", "Elisa", "Emma", "Eva", "Fatima", "Gabriela", "Giselle", "Isabella", "Jessica", "Julia",
                "Karina", "Laura", "Leila", "Maria", "Mariana", "Maya", "Monica", "Nadia", "Neha", "Priya", "Sofia",
                "Adelina", "Agata", "Aitana", "Alina", "Amani", "Amina", "Anya", "Apolline", "Aria", "Aurelia",
                "Ayaka", "Ayana", "Basia", "Bianca", "Camila", "Catalina", "Chiara", "Clara", "Cristina", "Dafne",
                "Dalia", "Daria", "Dina", "Dora", "Eleni", "Elif", "Emilia", "Erika", "Esma", "Eunice", "Ewa",
                "Farah", "Felicity", "Florencia", "Francisca", "Galina", "Gema", "Genevieve", "Giada", "Giovanna",
                "Giulia", "Helena", "Hina", "Iara", "Imane", "Inaya", "Ines", "Inez", "Iris", "Isadora", "Itzel"
        };

        String[] nonBinaryNames = {
                "Alex", "Casey", "Dakota", "Emery", "Finley", "Harley", "Hayden", "Jamie", "Jesse", "Jordan", "Kai",
                "Kendall", "Lane", "Lennon", "Logan", "Morgan", "Noel", "Parker", "Phoenix", "Quinn", "Reese", "Remy",
                "Riley", "River", "Robin", "Rowan", "Sage", "Sam", "Shiloh", "Sky", "Spencer", "Taylor", "Toby", "Val",
                "Winter", "Wren", "Zephyr", "Arden", "Blaine", "Blair", "Blue", "Brook", "Cameron", "Carter", "Cory",
                "Dallas", "Darian", "Devin", "Drew", "Ellis", "Emerson", "Ever", "Fallon", "Frankie", "Gray", "Greer"
        };

        return switch (gender.toUpperCase()) {
            case "MALE" -> maleNames[random.nextInt(maleNames.length)];
            case "FEMALE" -> femaleNames[random.nextInt(femaleNames.length)];
            case "NON_BINARY" -> nonBinaryNames[random.nextInt(nonBinaryNames.length)];
            default -> "Unknown";
        };
    }

    public String generateLastName(Random random) {
        String[] lastNames = {
                "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez",
                "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson", "Thomas", "Taylor", "Moore", "Jackson", "Martin",
                "Lee", "Perez", "Thompson", "White", "Harris", "Sanchez", "Clark", "Ramirez", "Lewis", "Robinson",
                "Walker", "Young", "Allen", "King", "Wright", "Scott", "Torres", "Nguyen", "Hill", "Flores", "Green",
                "Adams", "Nelson", "Baker", "Hall", "Rivera", "Campbell", "Mitchell", "Carter", "Roberts", "Kumar",
                "Patel", "Sharma", "Singh", "Khan", "Ali", "Zhang", "Wang", "Li", "Chen", "Yang", "Wu", "Liu", "Choi",
                "Park", "Kim", "Lee", "Suzuki", "Sato", "Takahashi", "Tanaka", "Watanabe", "Yamamoto", "Nakamura",
                "Kobayashi", "Ferrari", "Rossi", "Russo", "Esposito", "Bianchi", "Romano", "Gallo", "Costa", "Fontana",
                "Conti", "Muller", "Schmidt", "Schneider", "Fischer", "Weber", "Meyer", "Wagner", "Becker", "Hoffmann",
                "Dubois", "Bernard", "Thomas", "Petit", "Robert", "Richard", "Durand", "Leroy", "Moreau", "Silva",
                "Santos", "Oliveira", "Pereira", "Costa", "Rodrigues", "Martins", "Ferreira", "Almeida", "Soares",
                "Ivanov", "Smirnov", "Kuznetsov", "Popov", "Vasiliev", "Petrov", "Sokolov", "Mikhailov", "Novikov",
                "Fedorov", "Abdulla", "Hassan", "Ibrahim", "Mohamed", "Ahmed", "Mostafa", "Said", "Mahmoud", "Ali",
                "Suleiman", "Gonzalez", "Rodriguez", "Fernandez", "Lopez", "Martinez", "Garcia", "Sanchez", "Perez",
                "Gomez", "Diaz", "Varela", "Ito", "Nakajima", "Abe", "Kato", "Yoshida", "Yamada", "Sasaki", "Yamaguchi",
                "De Luca", "Monti", "Ricci", "Marino", "Greco", "Bruno", "Gatti", "Mariani", "Leone", "Longo"
        };

        return lastNames[random.nextInt(lastNames.length)];
    }
}
