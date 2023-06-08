package um.poo;

public class Str extends Object implements CharSequence{
    private static final int INIT_DIM = 16;
    private char[] text = new char[INIT_DIM];
    private int dim;

    public Str(String txt) {
        final int len = txt.length();
        if(INIT_DIM <len) expand(len);
        txt.getChars(0,len, text, 0);
        dim = len;
    }

    public Str(CharSequence txt) {
        final int len = txt.length();
        if(INIT_DIM <len) expand(len);
        for(int i =0; i<len; ++i) text[i] = txt.charAt(i);
        dim = len;
    }

    public  Str(){

    }

    public Str(Str other){
        dim = other.dim;
        text = new char[other.text.length];
        System.arraycopy(other.text,0,text,0,dim);
    }

    public void append(char c){
        final int len = text.length;
        if(dim==len) expand(dim+1);
        text[dim++] = c;
    }

    public void append(String s){
        final int l = s.length();
        int len = dim + l;
        if(len > text.length) expand(len);
        s.getChars(0, l, text, dim);
        dim += l;
    }

    private void expand(int size) {
        int dim = text.length*2;
        while(dim < size) dim*=2;
        char[] big = new char[size];
        System.arraycopy(text, 0, big, 0, this.dim);
        text = big;
    }

    public void remove(int from, int to){ //text="xptoabc"
        final int len = to - from;
        if(from<0 || to>dim)
            throw  new IndexOutOfBoundsException(from <0 ? from : to);
        if(to>from) {
            System.arraycopy(text, to, text, from, len+1);
            dim -= len;
        }
    }

    @Override
    public int length(){
        return dim;
    }
    @Override
    public String toString() {
        return new String(text,0,dim);
    }
    @Override
    public char charAt(int idx) {
        if(idx>=dim) throw new IndexOutOfBoundsException(idx);
        return text[idx];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        Str res = new Str();
        for(int i = start; i < end; i++)
            res.append(charAt(i));
        return res;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if (!(obj instanceof Str)) return false;
        Str o = (Str)obj; //((Str)obj)
        if (dim != o.dim) return  false;
        for (int i=0; i < dim; i++)
            if (text[i] != o.text[i]) return false;
        return true;
    }
}
