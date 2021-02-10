import es.ulpgc.eii.linked.Trie;
import es.ulpgc.eii.linked.ExtendedTrie;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ExtendedTrie t = new ExtendedTrie();
    // Descomentar las siguientes líneas para probar
        t.upload("palabras.txt");
        t.insert("casa");
        t.download("nuevo.txt");
        
    }

}
