/**
 * @author: Aarón Hernández Álvarez
 * Fecha: 09/05/2019
*/

package es.ulpgc.eii.linked;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ExtendedTrie extends Trie {
    public ExtendedTrie() {
        super(); // Llamada al constructor de la clase Trie
    }
      
    /**
     * Método inmersivo.
     * 
     * Recorre recursivamente los punteros comparando el caracter con el equivalente 
     * de la palabra.
     * 
     * Si llega a un asterisco, escribe la palabra y una linea nueva. Si no, autollama 
     * al método transversal (alt) y frontalmente (next).
     * Si ocurre alguna excepción, entra en el catch y retorna false.
     * 
     * @param   Info         Pointer de entrada
     * @param   String       Palabra a escribir.
     * @param   BufferWriter Nombre del archivo a gestionar
     * @return  boolean      True o false según se finalice o no correctamente.
    */
    private boolean writeWords(Info root, String word, BufferedWriter log){
        Info aux = root;
        try{
            if(root != null){
                if(root.c == '*'){
                    log.write(word);
                    log.newLine();
                }
                writeWords(aux.next, word+root.c, log);
                writeWords(aux.alt, word, log);
            }
            return true;
        } catch(IOException e){
            return false;
        }
    }
    
    /**
     * Método Download.
     * 
     * Crea un FW y un BW por defecto a null para evitar problemas si no se descarga nada.
     * Dentro del try, asigna el nombre de archivo al FW y lo reporta BW.
     * Se ejecuta el inmersivo con el pointer root, un string vacío y el BW.
     * 
     * Si ocurre alguna excepción, entra en el catch y retorna false.
     * Por último, en el finally, intenta cerrar el archivo y, en caso de no poder hacerse, 
     * cierra retornando false
     * 
     * @param   String  Nombre del archivo a gestionar
     * @return  boolean True o false según se finalice o no correctamente.
     */
    public boolean download(String file){
        FileWriter log = null;
        BufferedWriter logwr = null;
        try { 
            log = new FileWriter(file);
            logwr = new BufferedWriter(log);
            String words = ""; 
            return writeWords(root, words, logwr);
        } catch(IOException e){
            return false;
        } finally {
            if(logwr != null){
                try{
                    logwr.close();
                } catch (IOException e){
                    return false;
                }
            } 
        }
    }
    
    /**
     * Método Upload.
     *  
     * Crea un FR y un BR por defecto a null para evitar problemas si no se inserta nada.
     * Dentro del try, asigna el nombre de archivo al FR y lo reporta BR.
     *  
     * Lee la primera línea del archivo.
     * Si dicha línea es null, aborta el iterativo principal. Si no lo es, entra en el for
     * y crea un Array de Strings, splitea por espacios y va insertando una por una las 
     * palabras con el método insert.
     *  
     * Si ocurre alguna excepción, entra en el catch y retorna false.
     * Por último, en el finally, intenta cerrar el archivo y, en caso de no poder hacerse, 
     * cierra retornando false
     * 
     * @param   String  Nombre del archivo a gestionar
     * @return  boolean True o false según se finalice o no correctamente.
     */
    public boolean upload(String file){
        FileReader log = null;
        BufferedReader logrd = null;
        try { 
            log = new FileReader(file);
            logrd = new BufferedReader(log);
            String line = logrd.readLine();
            while(line != null){
                for(String word:line.split("\\s+")){
                    insert(word);
                } 
                line = logrd.readLine();
            }
            return true;
        } catch(IOException e){
            return false;
        } finally {
            if(logrd != null){
                try{
                    logrd.close();
                } catch (IOException e){
                    return false;
                }
            }
        }
    }

}
