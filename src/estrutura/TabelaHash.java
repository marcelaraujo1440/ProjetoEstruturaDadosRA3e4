package estrutura;
import model.Prato;

import java.util.*;

public class TabelaHash {
    private static final int TAMANHO = 10;
    private List<Prato>[] tabela;
    public TabelaHash() {
        tabela = new List[TAMANHO];
        for(int i = 0 ; i < TAMANHO; i++){
            tabela[i] = new LinkedList<>();
        }
    }
//charAt vai pegar o valor numerico de cada letra
//vai somar tudo e depois vai pegar o resto da divisao e distrubui entre os 10 indices
    private int hash(String nome) {
        int soma = 0;
        for(int i =0 ; i < nome.length(); i++){
            soma += nome.charAt(i);
        }
        return soma % TAMANHO;
    }
    public void inserir(Prato p) {
        int indice = hash(p.getNome()); //aq ele vai calcular a posicao do vetor
        List<Prato> lista = tabela[indice];
        for (Prato pratoExistente : lista) {
            if(pratoExistente.getNome().equals(p.getNome())){
                System.out.println("Prato ja existente");
                return;
            }
        }
        lista.add(p);
        System.out.println("Prato adicionado com sucesso" + p.getNome());
    }
    public Prato buscar(String nome) {
        int indice = hash(nome);
        List<Prato> lista = tabela[indice];
        for (Prato p : lista) {
            if(p.getNome().equalsIgnoreCase(nome)){ //vai ignorar as letras maiusculas
                return p;
            }
        }
        return null;
    }
    public boolean remover(String nome) {
        int indice = hash(nome);
        List<Prato> lista = tabela[indice];
        for (Prato p : lista) {
            if(p.getNome().equalsIgnoreCase(nome)){
                lista.remove(p);
                System.out.println("Prato removido com sucesso");
                return true;
            }
        }
        System.out.println("Prato não encontrado");
        return false;
    }
    public List<Prato> exportarParaLista() {
        List<Prato> todosPratos = new ArrayList<>();

        // vai percorrer todas as posicoes da tabela
        for (int i = 0; i < TAMANHO; i++) {
            todosPratos.addAll(tabela[i]); // adiciona todos os pratos daquela posicao
        }

        return todosPratos;
    }
}
