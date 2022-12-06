class No extends Arvore {
    public final Arvore esquerda, direita;
 
    public No(Arvore l, Arvore r) {
        super(l.frequency + r.frequency);
        esquerda = l;
        direita = r;
    }
}