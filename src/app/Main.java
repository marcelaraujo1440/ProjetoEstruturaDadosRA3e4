package app;

import estrutura.Ordenadores;
import estrutura.TabelaHash;
import model.Prato;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static TabelaHash tabelaHash = new TabelaHash();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpa o buffer

            switch (opcao) {
                case 1:
                    inserirPrato();
                    break;
                case 2:
                    removerPrato();
                    break;
                case 3:
                    buscarPrato();
                    break;
                case 4:
                    listarTodosPratos();
                    break;
                case 5:
                    ordenarEExibir();
                    break;
                case 0:
                    System.out.println("Encerrando sistema... Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

        } while (opcao != 0);

        scanner.close();
    }

    // ========== MENU ==========

    private static void exibirMenu() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║      CARDÁPIO DIGITAL - MENU       ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.println("1. Inserir prato");
        System.out.println("2. Remover prato");
        System.out.println("3. Buscar prato");
        System.out.println("4. Listar todos os pratos");
        System.out.println("5. Ordenar e exibir pratos");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    // ========== OPERAÇÕES BÁSICAS ==========

    private static void inserirPrato() {
        System.out.println("\n--- INSERIR PRATO ---");

        System.out.print("Nome do prato: ");
        String nome = scanner.nextLine();

        System.out.print("Preço (R$): ");
        double preco = scanner.nextDouble();

        System.out.print("Tempo de preparo (min): ");
        int tempo = scanner.nextInt();
        scanner.nextLine(); // limpa o buffer

        Prato prato = new Prato(nome, preco, tempo);
        tabelaHash.inserir(prato);
    }

    private static void removerPrato() {
        System.out.println("\n--- REMOVER PRATO ---");
        System.out.print("Nome do prato a remover: ");
        String nome = scanner.nextLine();

        if (tabelaHash.remover(nome)) {
            System.out.println("✓ Prato removido com sucesso!");
        } else {
            System.out.println("✗ Prato não encontrado!");
        }
    }

    private static void buscarPrato() {
        System.out.println("\n--- BUSCAR PRATO ---");
        System.out.print("Nome do prato: ");
        String nome = scanner.nextLine();

        Prato prato = tabelaHash.buscar(nome);

        if (prato != null) {
            System.out.println("\n✓ Prato encontrado:");
            System.out.println(prato);
        } else {
            System.out.println("✗ Prato não encontrado!");
        }
    }

    private static void listarTodosPratos() {
        System.out.println("\n--- TODOS OS PRATOS CADASTRADOS ---");
        List<Prato> pratos = tabelaHash.exportarParaLista();

        if (pratos.isEmpty()) {
            System.out.println("Nenhum prato cadastrado ainda.");
        } else {
            for (Prato p : pratos) {
                System.out.println(p);
            }
            System.out.println("\nTotal: " + pratos.size() + " prato(s)");
        }
    }



    private static void ordenarEExibir() {
        // 1. exporta os dados
        List<Prato> pratos = tabelaHash.exportarParaLista();

        if (pratos.isEmpty()) {
            System.out.println("\n✗ Nenhum prato cadastrado para ordenar!");
            return;
        }

        // 2. escolha do criterio de ordenacao
        System.out.println("\n--- ORDENAR PRATOS ---");
        System.out.println("Escolha o critério de ordenação:");
        System.out.println("1. Nome");
        System.out.println("2. Preço");
        System.out.println("3. Tempo de preparo");
        System.out.print("Opção: ");
        int criterio = scanner.nextInt();

        // 3. escolhe algoritmo
        System.out.println("\nEscolha o algoritmo:");
        System.out.println("1. BubbleSort");
        System.out.println("2. InsertionSort");
        System.out.println("3. QuickSort");
        System.out.print("Opção: ");
        int algoritmo = scanner.nextInt();
        scanner.nextLine(); // limpa o buffer

        // 4. executa a ordenacao
        long inicio = System.currentTimeMillis(); // mede o tempo de execucao

        ordenarPratos(pratos, criterio, algoritmo);

        long fim = System.currentTimeMillis();
        long tempoExecucao = fim - inicio;

        // 5. exibi os resultados
        exibirPratosOrdenados(pratos, criterio, algoritmo, tempoExecucao);
    }

    private static void ordenarPratos(List<Prato> pratos, int criterio, int algoritmo) {
        switch (algoritmo) {
            case 1: // BubbleSort
                switch (criterio) {
                    case 1: Ordenadores.bubbleSortPorNome(pratos); break;
                    case 2: Ordenadores.bubbleSortPorPreco(pratos); break;
                    case 3: Ordenadores.bubbleSortPorTempo(pratos); break;
                }
                break;

            case 2: // InsertionSort
                switch (criterio) {
                    case 1: Ordenadores.insertionSortPorNome(pratos); break;
                    case 2: Ordenadores.insertionSortPorPreco(pratos); break;
                    case 3: Ordenadores.insertionSortPorTempo(pratos); break;
                }
                break;

            case 3: // QuickSort
                switch (criterio) {
                    case 1: Ordenadores.quickSortPorNome(pratos, 0, pratos.size() - 1); break;
                    case 2: Ordenadores.quickSortPorPreco(pratos, 0, pratos.size() - 1); break;
                    case 3: Ordenadores.quickSortPorTempo(pratos, 0, pratos.size() - 1); break;
                }
                break;

            default:
                System.out.println("Algoritmo inválido!");
        }
    }

    private static void exibirPratosOrdenados(List<Prato> pratos, int criterio, int algoritmo, long tempoExecucao) {
        // nome do criterio
        String nomeCriterio = "";
        switch (criterio) {
            case 1: nomeCriterio = "NOME"; break;
            case 2: nomeCriterio = "PREÇO"; break;
            case 3: nomeCriterio = "TEMPO DE PREPARO"; break;
        }

        // nome do algoritmo
        String nomeAlgoritmo = "";
        switch (algoritmo) {
            case 1: nomeAlgoritmo = "BubbleSort"; break;
            case 2: nomeAlgoritmo = "InsertionSort"; break;
            case 3: nomeAlgoritmo = "QuickSort"; break;
        }

        // exibir resultado
        System.out.println("\n╔════════════════════════════════════════════════════╗");
        System.out.println("║         PRATOS ORDENADOS POR " + nomeCriterio);
        System.out.println("║         Algoritmo: " + nomeAlgoritmo);
        System.out.println("║         Tempo de execução: " + tempoExecucao + " ms");
        System.out.println("╚════════════════════════════════════════════════════╝\n");

        for (int i = 0; i < pratos.size(); i++) {
            System.out.println((i + 1) + ". " + pratos.get(i));
        }

        System.out.println("\nTotal: " + pratos.size() + " prato(s)");
    }
}