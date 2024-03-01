package maven.demo;

import java.util.Scanner;

public class Main {
	
	public static void main(String args[])
	{
		ElementoDAO dao = new ElementoDAO();
		Elemento elemento = null;
		Scanner in = new Scanner(System.in);
		
		int escolha, id, numeroAtomico;
		String nome, simbolo, familia;
		dao.connect();
        
        do {
            System.out.println("\n\n=================== Opções ===================");
            System.out.println("1. Listar elementos");            
            System.out.println("2. Pesquisar elemento por número atômico");
            System.out.println("3. Inserir novo elemento");
            System.out.println("4. Atualizar elemento");
            System.out.println("5. Excluir elemento");
            System.out.println("6. Sair");

            System.out.print("> ");
            escolha = in.nextInt();

            switch (escolha) {
                case 1:
            		for(Elemento elm : dao.readElementos()) {
            			System.out.println(elm.toString());
            		}
                    break;
                case 2:
                	
                	System.out.print("Número atômico do elemento: ");
                	numeroAtomico = in.nextInt();
                	
                	elemento = dao.readElemento(numeroAtomico);
                	
                	if(elemento != null) {
                		System.out.print(elemento.toString());
                	}else {
                		System.out.print("\nO elemento com esse número atômico não está registrado!");
                	}
                    
                    break;
                case 3:
                	System.out.print("Número atômico do novo elemento: ");
                	numeroAtomico = in.nextInt();
                	
                	System.out.print("Nome do novo elemento: ");
                	nome = in.next();
                	
                	System.out.print("Símbolo do novo elemento: ");
                	simbolo = in.next();
                	
                	System.out.print("Família do novo elemento: ");
                	in.nextLine();
                	familia = in.nextLine();
                	
                	elemento = new Elemento(numeroAtomico, nome, simbolo, familia);
                	
                	if(dao.createElemento(elemento)) System.out.print("\nNovo elemento adicionado!");
                    
                    break;
                case 4:
                	System.out.print("Número atômico do elemento para atualizar: ");
                	numeroAtomico = in.nextInt();
                	
                	elemento = dao.readElemento(numeroAtomico);
                	
                	if(elemento != null) {
                    	System.out.print("Novo nome do elemento["+elemento.getNome()+"]: ");
                    	nome = in.next();
                    	
                    	System.out.print("Novo simbolo do elemento ["+elemento.getSimbolo()+"]: ");
                    	simbolo = in.next();
                    	
                    	System.out.print("Nova familia do elemento ["+elemento.getFamilia()+"]: ");
                    	in.nextLine();
                    	familia = in.nextLine();
                    	
                    	elemento = new Elemento(numeroAtomico, nome, simbolo, familia);
                    	
                    	if(dao.updateElemento(elemento)) System.out.print("\nElemento atualizado!");
                    	
                	}else {
                		System.out.print("\nO elemento com esse número atômico não está registrado!");
                	}

                    break;
                case 5:
                	System.out.print("Número atômico do elemento para excluir: ");
                	numeroAtomico = in.nextInt();
                	
                	elemento = dao.readElemento(numeroAtomico);
                	
                	if(elemento != null) {
                		
                		System.out.print("Deseja excluir o seguinte elemento? (S/N) \n");
                		in.nextLine();
                		String op = in.nextLine();
                		
                		if(op.charAt(0) == 'S')
                			if(dao.deleteElemento(numeroAtomico)) System.out.print("\nElemento excluido!");
                    	
                	}else {
                		System.out.print("\nO elemento com esse número atômico não está registrado!");
                	}
                	
                	break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
//            System.out.print("\n\n============================================\n");

        } while (escolha != 6);
		
		
//		dao.createElemento(el1);
//		dao.updateElemento(el1);
//		dao.deleteElemento(24);
	}
}
