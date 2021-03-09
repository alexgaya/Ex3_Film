/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Helpers.ConstantsHelper;
import Models.Film;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author m
 */
public class FilmReader {
    private final ObjectInputStream ois;

    public FilmReader() throws FileNotFoundException, IOException {
        ois = new ObjectInputStream(new FileInputStream(
                ConstantsHelper.DATA_FOLDER+"/"+ConstantsHelper.SOURCE_FILE));
    }
    
    public Film getFilm() throws IOException, ClassNotFoundException {
        return (Film) ois.readObject();
    }
    
    public void close() throws IOException {
        ois.close();
    }
    
    
}
