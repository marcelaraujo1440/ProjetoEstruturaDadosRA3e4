package estrutura;

import model.Prato;
import java.util.List;

public class Ordenadores {

    /*
      BubbleSort - Ordena por nome
     */
    public static void bubbleSortPorNome(List<Prato> pratos) {
        int n = pratos.size();
        for (int i = 0; i < n - 1; i++) {
            boolean trocou = false;
            for (int j = 0; j < n - i - 1; j++) {
                // compara nomes (ordem alfabética)
                if (pratos.get(j).getNome().compareToIgnoreCase(pratos.get(j + 1).getNome()) > 0) {
                    // troca
                    Prato temp = pratos.get(j);
                    pratos.set(j, pratos.get(j + 1));
                    pratos.set(j + 1, temp);
                    trocou = true;
                }
            }
            // otimizacao: se não trocou nada, já está ordenado
            if (!trocou) break;
        }
    }

    /*
      BubbleSort - Ordena por preço
     */
    public static void bubbleSortPorPreco(List<Prato> pratos) {
        int n = pratos.size(); //pega quantos pratos existem
        for (int i = 0; i < n - 1; i++) { //vai repetir ate a lista ser totalmente percorrida
            //-1 pq no final ja tem um na posicao correta
            boolean trocou = false;
            for (int j = 0; j < n - i - 1; j++) { //ele vai fazer esse loop ate ficar na posicao correta
                if (pratos.get(j).getPreco() > pratos.get(j + 1).getPreco()) {
                    Prato temp = pratos.get(j);
                    pratos.set(j, pratos.get(j + 1));
                    pratos.set(j + 1, temp);
                    trocou = true;
                }
            }
            if (!trocou) break;
        }
    }

    /*
      BubbleSort - Ordena por tempo de preparo
     */
    public static void bubbleSortPorTempo(List<Prato> pratos) {
        int n = pratos.size();
        for (int i = 0; i < n - 1; i++) {
            boolean trocou = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (pratos.get(j).getTempoPreparo() > pratos.get(j + 1).getTempoPreparo()) {
                    Prato temp = pratos.get(j);
                    pratos.set(j, pratos.get(j + 1));
                    pratos.set(j + 1, temp);
                    trocou = true;
                }
            }
            if (!trocou) break;
        }
    }

    // INSERTION SORT

    /*
      InsertionSort - Ordena por nome
     */
    public static void insertionSortPorNome(List<Prato> pratos) {
        int n = pratos.size();
        for (int i = 1; i < n; i++) {
            Prato chave = pratos.get(i);
            int j = i - 1;

            // move elementos maiores para a direita
            while (j >= 0 && pratos.get(j).getNome().compareToIgnoreCase(chave.getNome()) > 0) {
                pratos.set(j + 1, pratos.get(j));
                j--;
            }
            pratos.set(j + 1, chave);
        }
    }

    /*
      InsertionSort - Ordena por preço
     */
    public static void insertionSortPorPreco(List<Prato> pratos) {
        int n = pratos.size();
        for (int i = 1; i < n; i++) { //comeca no indice 1 e vai ate o final, nao no 0 pq é considerado como ja ordenado
            Prato chave = pratos.get(i);
            int j = i - 1;
            //aqui ele ta empurrando os numeros maiores para a direita
            while (j >= 0 && pratos.get(j).getPreco() > chave.getPreco()) {
                pratos.set(j + 1, pratos.get(j));
                j--;
            }
            pratos.set(j + 1, chave);
        }
    }

    /*
      InsertionSort - Ordena por tempo de preparo
      Complexidade: O(n²) no pior caso, O(n) no melhor caso
     */
    public static void insertionSortPorTempo(List<Prato> pratos) {
        int n = pratos.size();
        for (int i = 1; i < n; i++) {
            Prato chave = pratos.get(i);
            int j = i - 1;

            while (j >= 0 && pratos.get(j).getTempoPreparo() > chave.getTempoPreparo()) {
                pratos.set(j + 1, pratos.get(j));
                j--;
            }
            pratos.set(j + 1, chave);
        }
    }

    // QUICK SORT

    /*
     * QuickSort - Ordena por nome
     */
    public static void quickSortPorNome(List<Prato> pratos, int inicio, int fim) {
        if (inicio < fim) {
            int indicePivo = particionarPorNome(pratos, inicio, fim);
            quickSortPorNome(pratos, inicio, indicePivo - 1);  // Recursão: lado esquerdo
            quickSortPorNome(pratos, indicePivo + 1, fim);     // Recursão: lado direito
        }
    }

    private static int particionarPorNome(List<Prato> pratos, int inicio, int fim) {
        Prato pivo = pratos.get(fim);
        int i = inicio - 1;

        for (int j = inicio; j < fim; j++) {
            if (pratos.get(j).getNome().compareToIgnoreCase(pivo.getNome()) < 0) {
                i++;
                // troca
                Prato temp = pratos.get(i);
                pratos.set(i, pratos.get(j));
                pratos.set(j, temp);
            }
        }

        // coloca o pivo na posição correta
        Prato temp = pratos.get(i + 1);
        pratos.set(i + 1, pratos.get(fim));
        pratos.set(fim, temp);

        return i + 1;
    }

    /*
      QuickSort - Ordena por preço
     */
    public static void quickSortPorPreco(List<Prato> pratos, int inicio, int fim) {
        if (inicio < fim) {
            int indicePivo = particionarPorPreco(pratos, inicio, fim);
            quickSortPorPreco(pratos, inicio, indicePivo - 1);
            quickSortPorPreco(pratos, indicePivo + 1, fim);
        }
    }

    private static int particionarPorPreco(List<Prato> pratos, int inicio, int fim) {
        Prato pivo = pratos.get(fim);
        int i = inicio - 1;

        for (int j = inicio; j < fim; j++) {
            if (pratos.get(j).getPreco() < pivo.getPreco()) {
                i++;
                Prato temp = pratos.get(i);
                pratos.set(i, pratos.get(j));
                pratos.set(j, temp);
            }
        }

        Prato temp = pratos.get(i + 1);
        pratos.set(i + 1, pratos.get(fim));
        pratos.set(fim, temp);

        return i + 1;
    }

    /*
      QuickSort - Ordena por tempo de preparo
     */
    public static void quickSortPorTempo(List<Prato> pratos, int inicio, int fim) {
        if (inicio < fim) {
            int indicePivo = particionarPorTempo(pratos, inicio, fim);
            quickSortPorTempo(pratos, inicio, indicePivo - 1);
            quickSortPorTempo(pratos, indicePivo + 1, fim);
        }
    }

    private static int particionarPorTempo(List<Prato> pratos, int inicio, int fim) {
        Prato pivo = pratos.get(fim);
        int i = inicio - 1;

        for (int j = inicio; j < fim; j++) {
            if (pratos.get(j).getTempoPreparo() < pivo.getTempoPreparo()) {
                i++;
                Prato temp = pratos.get(i);
                pratos.set(i, pratos.get(j));
                pratos.set(j, temp);
            }
        }

        Prato temp = pratos.get(i + 1);
        pratos.set(i + 1, pratos.get(fim));
        pratos.set(fim, temp);

        return i + 1;
    }
}