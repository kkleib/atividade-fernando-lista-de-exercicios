import java.math.BigInteger;
import java.util.*;

/*
 * Lista de Exercicios - Estruturas de Dados
 * Autor: Kleibe Ricelli Ribeiro Castilho
 *
 * Salve como ListaExercicios.java e execute.
 */
public class ListaExercicios {
    private static final Scanner sc = new Scanner(System.in);
    private static final Random random = new Random();

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n===== LISTA DE EXERCICIOS - JAVA =====");
            for (int i = 1; i <= 10; i++) {
                System.out.println(i + " - Exercicio " + i);
            }
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = lerInt();

            switch (opcao) {
                case 1: exercicio1(); break;
                case 2: exercicio2(); break;
                case 3: exercicio3(); break;
                case 4: exercicio4(); break;
                case 5: exercicio5(); break;
                case 6: exercicio6(); break;
                case 7: exercicio7(); break;
                case 8: new TestaPilha().executar(); break;
                case 9: exercicio9(); break;
                case 10: exercicio10(); break;
                case 0: System.out.println("Programa encerrado."); break;
                default: System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }

    private static int lerInt() {
        while (!sc.hasNextInt()) {
            System.out.print("Digite um numero inteiro: ");
            sc.next();
        }
        int valor = sc.nextInt();
        sc.nextLine();
        return valor;
    }

    // 1) Inverter um texto e verificar se e palindromo usando pilha.
    private static void exercicio1() {
        System.out.println("\nEXERCICIO 1");
        System.out.print("Digite um texto: ");
        String texto = sc.nextLine();

        Deque<Character> pilha = new ArrayDeque<>();
        for (int i = 0; i < texto.length(); i++) {
            pilha.push(texto.charAt(i));
        }

        StringBuilder inverso = new StringBuilder();
        while (!pilha.isEmpty()) {
            inverso.append(pilha.pop());
        }
        System.out.println("Texto invertido: " + inverso);

        // Para o palindromo, ignora espacos e pontos e nao diferencia maiusculas.
        StringBuilder normalizado = new StringBuilder();
        for (int i = 0; i < texto.length(); i++) {
            char c = Character.toLowerCase(texto.charAt(i));
            if (c != ' ' && c != '.') {
                normalizado.append(c);
            }
        }

        Deque<Character> pilhaPalindromo = new ArrayDeque<>();
        for (int i = 0; i < normalizado.length(); i++) {
            pilhaPalindromo.push(normalizado.charAt(i));
        }

        boolean palindromo = true;
        for (int i = 0; i < normalizado.length(); i++) {
            if (normalizado.charAt(i) != pilhaPalindromo.pop()) {
                palindromo = false;
                break;
            }
        }

        System.out.println(palindromo ? "O texto e um palindromo."
                                      : "O texto nao e um palindromo.");
    }

    // 2) Remove uma ocorrencia da chave usando somente push e pop para mover itens.
    private static void exercicio2() {
        System.out.println("\nEXERCICIO 2");
        Deque<Integer> pilha = new ArrayDeque<>();
        Deque<Integer> auxiliar = new ArrayDeque<>();

        System.out.print("Quantos elementos tera a pilha? ");
        int quantidade = lerInt();

        for (int i = 0; i < quantidade; i++) {
            System.out.print("Elemento " + (i + 1) + ": ");
            pilha.push(lerInt());
        }

        System.out.println("Pilha original (base para topo): " + pilha);
        System.out.print("Informe a chave que deseja remover: ");
        int chave = lerInt();

        boolean removido = false;
        while (!pilha.isEmpty()) {
            int elemento = pilha.pop();
            if (elemento == chave && !removido) {
                removido = true;
                break;
            }
            auxiliar.push(elemento);
        }

        // Devolve os elementos, mantendo a ordem original.
        while (!auxiliar.isEmpty()) {
            pilha.push(auxiliar.pop());
        }

        if (removido) {
            System.out.println("Uma ocorrencia da chave foi removida.");
        } else {
            System.out.println("Chave nao encontrada.");
        }
        System.out.println("Pilha final (base para topo): " + pilha);
    }

    // Classe que representa um aviao.
    private static class Aviao {
        String nome;
        int identificador;

        Aviao(String nome, int identificador) {
            this.nome = nome;
            this.identificador = identificador;
        }

        @Override
        public String toString() {
            return "Nome: " + nome + " | Identificador: " + identificador;
        }
    }

    // 3) Controle de fila de decolagem.
    private static void exercicio3() {
        System.out.println("\nEXERCICIO 3");
        Queue<Aviao> fila = new LinkedList<>();
        int opcao;

        do {
            System.out.println("\n--- CONTROLE DA PISTA ---");
            System.out.println("1 - Listar quantidade de avioes");
            System.out.println("2 - Autorizar decolagem");
            System.out.println("3 - Adicionar aviao");
            System.out.println("4 - Listar todos os avioes");
            System.out.println("5 - Mostrar primeiro aviao");
            System.out.println("0 - Voltar ao menu principal");
            System.out.print("Escolha: ");
            opcao = lerInt();

            switch (opcao) {
                case 1:
                    System.out.println("Avioes aguardando: " + fila.size());
                    break;
                case 2:
                    if (fila.isEmpty()) {
                        System.out.println("Nao ha avioes aguardando.");
                    } else {
                        System.out.println("Decolagem autorizada: " + fila.remove());
                    }
                    break;
                case 3:
                    System.out.print("Nome do aviao: ");
                    String nome = sc.nextLine();
                    System.out.print("Identificador inteiro: ");
                    int id = lerInt();
                    fila.add(new Aviao(nome, id));
                    System.out.println("Aviao adicionado a fila.");
                    break;
                case 4:
                    if (fila.isEmpty()) {
                        System.out.println("Fila vazia.");
                    } else {
                        System.out.println("Fila de espera:");
                        for (Aviao aviao : fila) {
                            System.out.println(aviao);
                        }
                    }
                    break;
                case 5:
                    if (fila.isEmpty()) {
                        System.out.println("Nao ha primeiro aviao: fila vazia.");
                    } else {
                        System.out.println("Primeiro da fila: " + fila.peek());
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }

    // 4) Cria F1 com 100 numeros e gera F2 na ordem inversa.
    private static void exercicio4() {
        System.out.println("\nEXERCICIO 4");
        Queue<Integer> f1 = new LinkedList<>();
        Queue<Integer> f2 = new LinkedList<>();
        Deque<Integer> pilha = new ArrayDeque<>();

        for (int i = 0; i < 100; i++) {
            f1.add(random.nextInt(1000));
        }

        // Usa uma copia de F1 para preservar a fila original.
        Queue<Integer> copia = new LinkedList<>(f1);
        while (!copia.isEmpty()) {
            pilha.push(copia.remove());
        }
        while (!pilha.isEmpty()) {
            f2.add(pilha.pop());
        }

        System.out.println("Fila F1 original: " + f1);
        System.out.println("Fila F2 invertida: " + f2);
    }

    // 5) Numeros repetidos vao para a pilha P.
    private static void exercicio5() {
        System.out.println("\nEXERCICIO 5");
        Queue<Integer> f = new LinkedList<>();
        Deque<Integer> p = new ArrayDeque<>();

        // Faixa de 1 a 500 para que ocorram repeticoes entre os 1000 sorteios.
        for (int i = 0; i < 1000; i++) {
            int numero = random.nextInt(500) + 1;
            if (f.contains(numero)) {
                p.push(numero);
            } else {
                f.add(numero);
            }
        }

        System.out.println("Fila F (valores sem repeticao): " + f);
        System.out.println("Pilha P (valores repetidos, topo primeiro): " + p);
    }

    // 6) Positivos sao empilhados; negativos retiram e imprimem o topo.
    private static void exercicio6() {
        System.out.println("\nEXERCICIO 6");
        Queue<Integer> fila = new LinkedList<>();
        Deque<Integer> pilha = new ArrayDeque<>();

        for (int i = 0; i < 1000; i++) {
            fila.add(random.nextInt(100) + 1);       // positivo
            fila.add(-(random.nextInt(100) + 1));   // negativo
        }
        List<Integer> mistura = new ArrayList<>(fila);
        Collections.shuffle(mistura);
        fila.clear();
        fila.addAll(mistura);

        int retirados = 0;
        while (!fila.isEmpty()) {
            int numero = fila.remove();

            if (numero > 0) {
                pilha.push(numero);
            } else {
                if (!pilha.isEmpty()) {
                    System.out.println("Negativo " + numero
                            + " -> saiu da pilha: " + pilha.pop());
                    retirados++;
                } else {
                    System.out.println("Negativo " + numero
                            + " encontrado, mas a pilha esta vazia.");
                }
            }
        }

        System.out.println("Processamento concluido. Elementos retirados da pilha: "
                + retirados);
        System.out.println("Elementos que sobraram na pilha: " + pilha.size());
    }

    // 7) Inverte uma fila de 20 inteiros usando pilha auxiliar.
    private static void exercicio7() {
        System.out.println("\nEXERCICIO 7");
        Queue<Integer> fila = new LinkedList<>();
        Deque<Integer> pilha = new ArrayDeque<>();

        for (int i = 0; i < 20; i++) {
            fila.add(random.nextInt(100));
        }

        System.out.println("Fila original: " + fila);
        while (!fila.isEmpty()) {
            pilha.push(fila.remove());
        }
        while (!pilha.isEmpty()) {
            fila.add(pilha.pop());
        }
        System.out.println("Fila invertida: " + fila);
    }

    // 9) Soma recursiva dos elementos de uma lista.
    private static int somarRecursivo(List<Integer> numeros, int indice) {
        if (indice == numeros.size()) {
            return 0;
        }
        return numeros.get(indice) + somarRecursivo(numeros, indice + 1);
    }

    private static void exercicio9() {
        System.out.println("\nEXERCICIO 9");
        System.out.print("Quantos numeros deseja inserir? ");
        int quantidade = lerInt();
        List<Integer> numeros = new ArrayList<>();

        for (int i = 0; i < quantidade; i++) {
            System.out.print("Numero " + (i + 1) + ": ");
            numeros.add(lerInt());
        }

        System.out.println("Lista: " + numeros);
        System.out.println("Soma dos elementos: " + somarRecursivo(numeros, 0));
    }

    // 10) Potencia por recursao, sem usar Math.pow.
    private static BigInteger potenciaRecursiva(int a, int b) {
        if (b == 0) {
            return BigInteger.ONE;
        }
        return BigInteger.valueOf(a).multiply(potenciaRecursiva(a, b - 1));
    }

    private static void exercicio10() {
        System.out.println("\nEXERCICIO 10");
        System.out.print("Digite a base (a): ");
        int a = lerInt();
        System.out.print("Digite o expoente (b): ");
        int b = lerInt();

        if (a < 0 || b < 0) {
            System.out.println("Informe valores positivos ou zero.");
            return;
        }
        System.out.println(a + " elevado a " + b + " = "
                + potenciaRecursiva(a, b));
    }
}

// Exercicio 8: classe com duas pilhas, N para negativos e P para positivos.
class TestaPilha {
    private final Deque<Integer> N = new ArrayDeque<>();
    private final Deque<Integer> P = new ArrayDeque<>();
    private final Random random = new Random();

    public void executar() {
        System.out.println("\nEXERCICIO 8 - TESTA PILHA");

        for (int i = 0; i < 1000; i++) {
            int numero = random.nextInt(201) - 100; // de -100 ate 100

            if (numero > 0) {
                P.push(numero);
            } else if (numero < 0) {
                N.push(numero);
            } else {
                System.out.println("Foi sorteado zero.");
                if (!P.isEmpty()) {
                    System.out.println("Retirado da pilha P: " + P.pop());
                } else {
                    System.out.println("Pilha P vazia.");
                }

                if (!N.isEmpty()) {
                    System.out.println("Retirado da pilha N: " + N.pop());
                } else {
                    System.out.println("Pilha N vazia.");
                }
            }
        }

        System.out.println("Elementos restantes em P: " + P.size());
        System.out.println("Elementos restantes em N: " + N.size());
    }
}
