/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio7a;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author cristina
 */
public class LecturaEscritura {

    private static String comilla(String s) {
        String quitar = s.substring(1, s.length() - 1);
        return quitar;
    }

    private static void lectura() {
        // Fichero a leer con datos de ejemplo
        String idFichero = "RelPerCen.csv";

        // Variables para guardar los datos que se van leyendo
        String[] tokens;
        String linea;
        int contador = 0;

        ArrayList<Profesor> lista = new ArrayList<>();

        System.out.println("Leyendo el fichero: " + idFichero);

        // Inicialización del flujo "datosFichero" en función del archivo llamado "idFichero"
        // Estructura try-with-resources. Permite cerrar los recursos una vez finalizadas
        // las operaciones con el archivo
        try ( Scanner datosFichero = new Scanner(new File(idFichero), "ISO-8859-1")) {

            datosFichero.nextLine();

            while (datosFichero.hasNextLine()) {

                linea = datosFichero.nextLine();

                // Se guarda en el array de String cada elemento de la
                // línea en función del carácter separador coma
                tokens = linea.split(",");
                Profesor p1 = new Profesor();

                p1.setNombre(comilla(tokens[0] + tokens[1]));
                p1.setDni(comilla(tokens[2]));
                p1.setPuesto(comilla(tokens[3]));

                String inicio = comilla(tokens[4]);
                p1.setFechaToma(LocalDate.parse(inicio, DateTimeFormatter.ofPattern("dd/MM/yyyy")));

                String fin = comilla(tokens[5]);
                if (fin.length() <= 0) {
                    p1.setFechaCese(LocalDate.now());
                } else {
                    p1.setFechaCese(LocalDate.parse(fin, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                }

                p1.setTelf(comilla(tokens[6]));

                if (comilla(tokens[7]).equalsIgnoreCase("no")) {
                    p1.setEvaluador(false);
                } else {
                    p1.setEvaluador(true);
                }
                if (comilla(tokens[8]).equalsIgnoreCase("no")) {
                    p1.setCoordinador(false);
                } else {
                    p1.setCoordinador(true);
                }

                lista.add(p1);
                System.out.println(p1.toString());
                contador++;
            }
            System.out.println("\n\nHay un total de: " + contador + " profesores");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        lectura();

//        Collections.sort(listaVehiculo);
//        for (Vehiculo lista : listaVehiculo) {
//            System.out.println(lista);
//
//        }
    }
}
