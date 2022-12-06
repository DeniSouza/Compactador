import java.util.*;

public class Compactador {
	
	public static String CompactadorTexto(String texto) {
		
        String texto_compactado = texto;
        String caminho = "teste123.txt";
        
        int[] charFrequencia = new int[256];
        for (char c : texto_compactado.toCharArray())
            charFrequencia[c]++;
        

        Arvore arvore = buildArvore(charFrequencia);
        
        System.out.println("Tabela de compactacao");
        System.out.println("Caracter\tQuantidade\tCodigo Huffman");
        printCodes(arvore, new StringBuffer());

        // Compacta o texto
        String encode = encode(arvore,texto_compactado);

        // Mostra o texto Compactado
        System.out.println("\nTexto Compactado");
        System.out.println(encode); 
        leituraArquivo.gravaArquivo(caminho,encode);
        return "";
    }

	
    public static Arvore buildArvore(int[] charFrequencia) {

        PriorityQueue<Arvore> arvores = new PriorityQueue<Arvore>();
        
        for (int i = 0; i < charFrequencia.length; i++){
            if (charFrequencia[i] > 0)
                arvores.offer(new Folha(charFrequencia[i], (char)i));
        }
        while (arvores.size() > 1) {
           
            Arvore a = arvores.poll(); 
            Arvore b = arvores.poll(); 
 
          
            arvores.offer(new No(a, b)); 
        }
       
        return arvores.poll();
    }
 
    public static String encode(Arvore arvore, String encode){
    	assert arvore != null;
    	
    	String encodeText = "";
        for (char c : encode.toCharArray()){
        	encodeText+=(getCodes(arvore, new StringBuffer(),c));
        }
    	return encodeText; 
    }
 
   
    public static void printCodes(Arvore arvore, StringBuffer prefix) {
        assert arvore != null;
        
        if (arvore instanceof Folha) {
            Folha folha = (Folha)arvore;
            
            // Imprime na tela a Lista
            System.out.println(folha.value + "\t\t" + folha.frequency + "\t\t" + prefix);
 
        } else if (arvore instanceof No) {
            No no = (No)arvore;
 
            prefix.append('0');
            printCodes(no.esquerda, prefix);
            prefix.deleteCharAt(prefix.length()-1);
 
            prefix.append('1');
            printCodes(no.direita, prefix);
            prefix.deleteCharAt(prefix.length()-1);
        }
    }
    
   
    public static String getCodes(Arvore arvore, StringBuffer prefix, char w) {
        assert arvore != null;
        
        if (arvore instanceof Folha) {
            Folha folha = (Folha)arvore;
          
            if (folha.value == w ){
            	return prefix.toString();
            }
            
        } else if (arvore instanceof No) {
            No no = (No)arvore;
 
            prefix.append('0');
            String esquerda = getCodes(no.esquerda, prefix, w);
            prefix.deleteCharAt(prefix.length()-1);
 
           
            prefix.append('1');
            String direita = getCodes(no.direita, prefix,w);
            prefix.deleteCharAt(prefix.length()-1);
            
            if (esquerda==null) return direita; else return esquerda;
        }
		return null;
    }

    

}
