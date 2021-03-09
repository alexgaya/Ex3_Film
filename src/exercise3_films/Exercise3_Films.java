/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise3_films;

import Helpers.ConstantsHelper;
import Models.Film;
import Models.GenreType;
import Services.FilmReader;
import java.util.Arrays;

/**
 *
 * @author m
 */
public class Exercise3_Films {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        readFilms();
    }
    
    public static void readFilms() {
        try {
            Film[] films = new Film[ConstantsHelper.MAX_FILMS];
            int count = 0;
            FilmReader fr = new FilmReader();
            Film f = fr.getFilm();
            films[count] = f;
            while(!f.isSentinel()) {
                System.out.println(f);
                count++;
                f = fr.getFilm();
                films[count] = f;
            }
            fr.close();
            
            for(GenreType g : GenreType.values()) {
                writeFilms(Arrays.stream(films)
                            .filter(e -> e != null 
                                    && !e.isSentinel() 
                                    && e.getGenre().equals(g))
                            .toArray(Film[]::new), g);
            }
            
            
            
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            System.out.println("The program must terminate.");
            System.exit(0);
        } 
    }
    
    public static void writeFilms(Film[] films, GenreType genre) {
        System.out.println(films.length + genre.toString());
    }
    
}
