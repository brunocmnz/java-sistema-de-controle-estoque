/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bruno_menezes;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author bruno
 */
public class Trabalho1_PC2 {

    static Random rdm = new Random();

    static void init(Produto[] vetProd, String[][] produtos, int num) {
        for (int i = 0; i < num; i++) {
            boolean ins = false;
            do {
                Produto novoProd = new Produto();
                novoProd.código = i + 1;
                int numNome = rdm.nextInt(produtos[produtos.length - 2].length);
                int numMarca = rdm.nextInt(produtos[produtos.length - 1].length);
                if (produtos[produtos.length - 2] != null) {
                    novoProd.nome = produtos[produtos.length - 2][numNome];
                    novoProd.marca = produtos[produtos.length - 1][numMarca];
                    produtos[produtos.length - 2][numNome] = null;
                }

                if (novoProd.nome != null) {
                    novoProd.preço = rdm.nextInt(5000) * 0.01f;
                    novoProd.quantEstoque = rdm.nextInt(50);
                    ins = inserir(novoProd, vetProd);
                }
            } while (ins == false);
        }
    }

    static boolean inserir(Produto p, Produto[] vetProd) {
        for (int i = 0; i < vetProd.length; i++) {
            if (vetProd[i] != null && vetProd[i].nome.equalsIgnoreCase(p.nome)) {
                return false;
            } else {
                if (vetProd[i] == null) {
                    break;
                }
            }
        }
        for (int i = 0; i < vetProd.length; i++) {
            if (vetProd[i] == null) {
                vetProd[i] = new Produto();
                vetProd[i] = p;
                return true;
            }
        }
        return false;
    }

    static boolean excluir(int codigo, Produto[] vetProd) {
        for (int i = 0; i < vetProd.length; i++) {
            if (vetProd[i] != null && vetProd[i].código == codigo) {
                vetProd[i] = null;
                return true;
            }
        }
        return false;
    }

    static boolean alterarCodigo(Produto prod, int cod) {
        prod.código = cod;
        return true;
    }

    static boolean alterarNome(Produto prod, String nome) {
        prod.nome = nome;
        return true;
    }

    static boolean alterarMarca(Produto prod, String marca) {
        prod.marca = marca;
        return true;
    }

    static boolean alterarPreco(Produto prod, float preco) {
        prod.preço = preco;
        return true;
    }

    static boolean alterarQuantEstoque(Produto prod, int quant) {
        prod.quantEstoque = quant;
        return true;
    }

    static Produto buscarPorCodigo(Produto[] vetProd, int cod) {
        for (int i = 0; i < vetProd.length; i++) {
            if (vetProd[i] != null && vetProd[i].código == cod) {
                return vetProd[i];
            }
        }
        return null;
    }

    static Produto buscarPorNome(Produto[] vetProd, String nome) {
        for (int i = 0; i < vetProd.length; i++) {
            if (vetProd[i] != null && vetProd[i].nome.equalsIgnoreCase(nome)) {
                return vetProd[i];
            }
        }
        return null;
    }

    static Produto[] listarTodos(Produto[] vetProdutos) {
        return vetProdutos;
    }

    static Produto[] listarPorNome(Produto[] vetProdutos) {
        int contaNomes = 0;
        for (int i = 0; i < vetProdutos.length; i++) {
            if (vetProdutos[i] != null) {
                if (vetProdutos[i].nome != null) {
                    contaNomes++;
                }
            }
        }

        String[] vetNomes = new String[contaNomes];
        for (int i = 0; i < vetProdutos.length; i++) {
            for (int j = 0; j < vetNomes.length; j++) {
                if (vetProdutos[i] != null && vetNomes[j] == null) {
                    vetNomes[j] = vetProdutos[i].nome;
                    break;
                }
            }
        }

        for (int i = 0; i < vetNomes.length - 1; i++) {
            for (int j = vetNomes.length - 1; j > i; j--) {
                if (vetNomes[j].compareToIgnoreCase(vetNomes[j - 1]) < 0) {
                    String temp = vetNomes[j];
                    vetNomes[j] = vetNomes[j - 1];
                    vetNomes[j - 1] = temp;
                }
            }
        }

        Produto[] vetProdNome = new Produto[contaNomes];
        for (int i = 0; i < vetNomes.length; i++) {
            for (int j = 0; j < vetProdutos.length; j++) {
                if (vetProdutos[j] != null && vetProdutos[j].nome != null) {
                    if (vetNomes[i].equals(vetProdutos[j].nome)) {
                        vetProdNome[i] = vetProdutos[j];
                    }
                }
            }
        }

        return vetProdNome;
    }

    static void imprimeProduto(Produto p) {
        imprimeLinha("=");
        System.out.printf("%8s \t%10s \t%10s \t%10s \t%12s\n",
                "Código", "Nome", "Marca", "Preço", "QuantEstoque");
        imprimeLinha("-");
        System.out.printf("%8s \t%10s \t%10s \t%-4s%-10.2"
                + "f \t%12s\n",
                p.código, p.nome, p.marca, "R$", p.preço,
                p.quantEstoque);
    }

    static void imprimeProdutos(Produto[] vetProdutos) {
        imprimeLinha("=");
        System.out.printf("%8s \t%10s \t%10s \t%10s \t%12s\n",
                "Código", "Nome", "Marca", "Preço", "QuantEstoque");
        imprimeLinha("-");
        int imprimiu = 0;
        for (int i = 0; i < vetProdutos.length; i++) {
            if (vetProdutos[i] != null) {
                System.out.printf("%8s \t%10s \t%10s \t%-5s%6.2"
                        + "f \t%12s\n",
                        vetProdutos[i].código, vetProdutos[i].nome,
                        vetProdutos[i].marca, "  R$", vetProdutos[i].preço,
                        vetProdutos[i].quantEstoque);
                imprimiu++;
            }
        }
        if (imprimiu == 0) {
            //System.out.println("Não há produtos cadastrados no momento");
            System.out.printf("%57s\n", "NÃO HÁ PRODUTOS CADASTRADOS NO MOMENTO");
        }
    }

    static void imprimeLinha(String dig) {
        for (int i = 0; i < 90; i++) {
            System.out.print(dig);
        }
        System.out.println("");
    }
    
    static int recebeInt(){
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
    
    static float recebeFloat(){
        Scanner sc = new Scanner(System.in);
        return sc.nextFloat();
    }
    
    static String recebeString(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
        

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        //Vetor com nomes de produtos de moda
        String[] nomesDeProds = {"Camisa", "Oculos", "Cinto", "Sapato", "Camiseta",
            "Short", "Chinelo", "Bermuda", "Relógio", "Calca"};

        //Vetor com as marcas dos produtos de moda
        String[] marcasDeProds = {"Nike", "Adidas", "Lacoste", "Coca-Cola",
            "Puma", "RipCurl", "LEVI'S", "Colcci",
            "Oakley", "Element", "Hilfiger", "Hurley"};

        //Matriz produtos com nomes de produtos
        String[][] produtos = {nomesDeProds, marcasDeProds};

        Produto[] vetProdutos = new Produto[30];
        init(vetProdutos, produtos, 10);
        imprimeLinha("=");
        System.out.printf("%55s\n", "PRODUTOS JÁ PRÉ CADASTRADOS");
        imprimeProdutos(vetProdutos);
        int decisao = 0;
        //Código da interface de interação do programa
        do {
            imprimeLinha("=");
            System.out.println("");
            imprimeLinha("=");
            System.out.println("Insira um número para realizar uma ação");
            imprimeLinha("=");
            System.out.println("1 - Inserir Produtos");
            System.out.println("2 - Excluir Produtos");
            System.out.println("3 - Alterar dados de um produto");
            System.out.println("4 - Listar produtos");
            System.out.println("5 - Encerrar programa");
            imprimeLinha("-");
            System.out.print("Insira a opção desejada: ");
            decisao = sc.nextInt();
            imprimeLinha("-");
            switch (decisao) {
                case 1:
                    Produto p = new Produto();
                    System.out.println("Insira abaixo as informações do novo produto:");
                    System.out.print("Codigo: ");
                    p.código = recebeInt();
                    
                    System.out.print("Nome: ");
                    p.nome = recebeString();
                    
                    System.out.print("Marca: ");
                    p.marca = recebeString();
                    
                    System.out.print("Preço: ");
                    p.preço = recebeFloat();
                    
                    System.out.print("Quantidade de estoque: ");
                    p.quantEstoque = sc.nextInt();
                    boolean ins = inserir(p, vetProdutos);
                    if (ins != true) {
                        imprimeLinha("=");
                        System.out.println("Não foi possível fazer o cadastro,"
                                + "pois já há um produto cadastrado com esse nome");
                    } else {
                        vetProdutos = listarTodos(vetProdutos);
                        imprimeProdutos(vetProdutos);
                        imprimeLinha("=");
                        System.out.printf("%70s\n", ("O produto de nome " + p.nome
                                + " foi inserido com sucesso"));
                    }
                    break;
                case 2:
                    System.out.print("Insira o código do produto que deseja excluir: ");
                    int cod = sc.nextInt();
                    p = buscarPorCodigo(vetProdutos, cod);
                    boolean delet = excluir(cod, vetProdutos);
                    vetProdutos = listarTodos(vetProdutos);
                    if (delet == false) {
                        imprimeLinha("-");
                        System.out.printf("%76s\n", "NÃO FOI POSSÍVEL DELETAR O "
                                + "PRODUTO PORQUE ELE NÃO ESTÁ CADASTRADO");
                    } else {
                        imprimeProdutos(vetProdutos);
                        imprimeLinha("=");
                        System.out.printf("%70s\n", ("O produto " + p.nome + " de código "
                                + p.código + " foi excluído com sucesso"));
                    }
                    break;
                case 3:
                    imprimeLinha("-");
                    System.out.println("Insira abaixo como deseja buscar o "
                            + "produto a ser alterado");
                    System.out.println("1 - Buscar por código");
                    System.out.println("2 - Buscar por nome");
                    imprimeLinha("-");
                    System.out.print("Insira a opção desejada: ");
                    int alterar = sc.nextInt();
                    imprimeLinha("-");
                    int codigoProd = 0;
                    String nomeProd = "";
                    Produto prodAlterar = new Produto();
                    if (alterar > 2 || alterar < 1) {
                        System.out.printf("%50s\n", "OPÇÃO INVÁLIDA");
                        break;
                    }
                    switch (alterar) {
                        case 1:
                            System.out.print("Insira o código do produto que "
                                    + "deseja alterar: ");
                            codigoProd = sc.nextInt();
                            prodAlterar = buscarPorCodigo(vetProdutos, codigoProd);
                            break;
                        case 2:
                            System.out.print("Insira o nome do produto que "
                                    + "deseja alterar: ");
                            nomeProd = sc.next();
                            prodAlterar = buscarPorNome(vetProdutos, nomeProd);
                            break;
                    }
                    if (prodAlterar == null) {
                        imprimeLinha("-");
                        switch (alterar) {
                            case 1:
                                System.out.printf("%60s\n", "Não foi encontrado nenhum "
                                        + "produto com esse código");
                                imprimeLinha("=");
                                System.out.println("");
                                break;
                            case 2:
                                System.out.printf("%60s\n", "Não foi encontrado "
                                        + "nenhum produto com esse nome");
                        }
                    } else {
                        imprimeLinha("-");
                        System.out.println("Insira abaixo o dado que deseja alterar "
                                + "do produto");
                        imprimeLinha("-");
                        System.out.println("1 - Alterar Código");
                        System.out.println("2 - Alterar Nome");
                        System.out.println("3 - Alterar Marca");
                        System.out.println("4 - Alterar Preço");
                        System.out.println("5 - Alterar Quantidade de estoque");
                        imprimeLinha("-");
                        System.out.print("Insira a opção desejada: ");
                        int opcao = sc.nextInt();
                        switch (opcao) {
                            case 1:
                                System.out.print("Insira o novo código do produto: ");
                                cod = sc.nextInt();
                                alterarCodigo(prodAlterar, cod);
                                break;
                            case 2:
                                System.out.print("Insira o novo nome do produto: ");
                                nomeProd = sc.next();
                                alterarNome(prodAlterar, nomeProd);
                                break;
                            case 3:
                                System.out.print("Insira a nova marca do produto: ");
                                String marca = sc.next();
                                alterarMarca(prodAlterar, marca);
                                break;
                            case 4:
                                System.out.print("Insira o novo preço do produto: ");
                                float preco = sc.nextFloat();
                                alterarPreco(prodAlterar, preco);
                                break;
                            case 5:
                                System.out.print("Insira a nova quantidade de estoque: ");
                                int quantEstoque = sc.nextInt();
                                alterarQuantEstoque(prodAlterar, quantEstoque);
                                break;
                        }
                        imprimeLinha("-");
                        if (opcao > 5 || opcao < 1) {
                            System.out.printf("%50s\n", "OPÇÃO INVÁLIDA");
                            break;
                        }
                        vetProdutos = listarTodos(vetProdutos);
                        imprimeProdutos(vetProdutos);
                        imprimeLinha("=");
                        switch (opcao) {
                            case 1:
                                System.out.printf("%70s\n", ("O código do produto de nome "
                                        + prodAlterar.nome + " foi alterado com sucesso"));
                                break;
                            case 2:
                                System.out.printf("%70s\n", ("O nome do produto de código "
                                        + prodAlterar.código + " foi alterado com sucesso"));
                                break;
                            case 3:
                                System.out.printf("%70s\n", ("A marca do produto de nome "
                                        + prodAlterar.nome + " foi alterada com sucesso"));
                                break;
                            case 4:
                                System.out.printf("%70s\n", ("O preço do produto de nome "
                                        + prodAlterar.nome + " foi alterado com sucesso"));
                                break;
                            case 5:
                                System.out.printf("%78s\n", ("A quantidade de estoque do produto"
                                        + " de nome " + prodAlterar.nome + " foi alterada com sucesso"));
                        }
                    }
                    break;
                case 4:
                    System.out.println("Qual a listagem desejada?");
                    System.out.println("1 - Detalhar um produto através do nome "
                            + "ou código");
                    System.out.println("2 - Listar todos os produtos pela ordem "
                            + "de cadastro");
                    System.out.println("3 - Listar todos os produtos por ordem "
                            + "alfabética");
                    imprimeLinha("-");
                    System.out.print("Insira a opção desejada: ");
                    decisao = sc.nextInt();
                    imprimeLinha("-");
                    if (decisao > 3 || decisao < 1) {
                        System.out.printf("%45s\n", "OPÇÃO INVÁLIDA");
                        break;
                    }
                    if (decisao == 1) {
                        System.out.println("Escolha a forma como deseja "
                                + "buscar o produto para detalhar seus dados");
                        imprimeLinha("-");
                        System.out.println("1 - Por código");
                        System.out.println("2 - Por nome");
                        imprimeLinha("-");
                        System.out.print("Insira a forma desejada: ");
                        decisao = sc.nextInt();
                        imprimeLinha("-");
                        if (decisao == 1) {
                            System.out.print("Insira o código do produto para "
                                    + "ver seus dados: ");
                            cod = sc.nextInt();
                            p = buscarPorCodigo(vetProdutos, cod);
                            if (p != null) {
                                imprimeProduto(p);
                            } else {
                                imprimeLinha("=");
                                System.out.printf("%82s\n", "Não foi possível localizar "
                                        + "o produto pois o código inserido não"
                                        + " está cadastrado");
                            }
                            break;
                        } else if (decisao == 2) {
                            System.out.print("Insira o nome do produto desejado: ");
                            nomeProd = sc.next();
                            p = buscarPorNome(vetProdutos, nomeProd);
                            if (p != null) {
                                imprimeProduto(p);
                            } else {
                                imprimeLinha("=");
                                System.out.println("Não foi possível localizar "
                                        + "o produto, pois seu nome pode ter "
                                        + "sido inserido incorretamente");
                            }
                            break;
                        }
                    }
                    if (decisao == 2) {
                        Produto[] vetProduto = listarTodos(vetProdutos);

                        imprimeProdutos(vetProdutos);
                    }
                    if (decisao == 3) {
                        Produto[] vetPorNome = listarPorNome(vetProdutos);
                        System.out.println("LISTA DE PRODUTOS EM ORDEM ALFABÉTICA");
                        imprimeProdutos(vetPorNome);
                    }
                    break;
            }
        } while (decisao != 5);
        imprimeLinha("=");
        System.out.printf("%55s\n", "PROGRAMA FINALIZADO COM SUCESSO");
        imprimeLinha("=");
    }

}
