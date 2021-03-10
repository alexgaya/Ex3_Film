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
import Services.FilmWriter;
import java.io.IOException;
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
        try {
            filterFilms(readFilms());
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    
    public static void filterFilms(Film[] films) throws IOException {
        if (films.length == 0) return;
        for(GenreType g : GenreType.values()) {
                writeFilms(Arrays.stream(films)
                            .filter(e -> e != null 
                                    && !e.isSentinel() 
                                    && e.getGenre().equals(g))
                            .toArray(Film[]::new), g);
            }
    } 
    
    public static Film[] readFilms() throws IOException, ClassNotFoundException {
            Film[] films = new Film[ConstantsHelper.MAX_FILMS];
            int count = 0;
            FilmReader fr = new FilmReader();
            Film f = fr.getFilm();
            films[count] = f;
            System.out.println("Stored films:\n");
            while(!f.isSentinel()) {
                System.out.println(f);
                count++;
                f = fr.getFilm();
                films[count] = f;
            }
            fr.close();
            return films;
    }
    
    public static void writeFilms(Film[] films, GenreType genre) throws IOException {  
        if (films.length == 0) return;
        FilmWriter fr = new FilmWriter(genre.toString());
        for(Film f : films) {
            fr.post(f);
        }
        fr.post(Film.sentinel);
        fr.close();
    }
    
}
