abstract class Arvore implements Comparable<Arvore> {
    public final int frequency;
    
    public Arvore(int freq) { 
    	frequency = freq; 
    }

    public int compareTo(Arvore arvore) {
        return frequency - arvore.frequency;
    }
}