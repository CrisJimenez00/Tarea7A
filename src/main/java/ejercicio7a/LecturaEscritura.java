/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio7a;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author cristina
 */
public class LecturaEscritura {

    //Método el cual quita las comillas del principio y el final de la palabra
    private static String comilla(String s) {
        String quitar = s.substring(1, s.length() - 1);
        return quitar;
    }

    //Metodo el cual mapea un ArrayList y cuenta los profesores según los departamentos
    private static Map<String, Integer> mapear(ArrayList<Profesor> lista) {
        Map<String, Integer> listaProfesores = new HashMap();
        
        for (Profesor profesor : lista) {
            if (listaProfesores.containsKey(profesor.getPuesto())) {
                int contadorDepartamento = listaProfesores.get(profesor.getPuesto());
                contadorDepartamento++;
                listaProfesores.put(profesor.getPuesto(), contadorDepartamento);
            } else {
                listaProfesores.put(profesor.getPuesto(), 1);

            }
        }

        return listaProfesores;
    }

    //Método el cual crea un objeto tipo Profesor a partir de un array de tokens
    private static Profesor crearProfesor(String[] tokens) {
        Profesor p1 = new Profesor();

        p1.setNombre(comilla(tokens[1]) + " " + tokens[0].substring(1));
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
        return p1;

    }

    //Método el cual lee un csv y lo mete en un ArrayList
    private static ArrayList<Profesor> lectura() {
        // Fichero a leer con datos de ejemplo
        String idFichero = "RelPerCen.csv";

        // Variables para guardar los datos que se van leyendo
        String[] tokens;
        String linea;

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
                Profesor p1 = crearProfesor(tokens);
                lista.add(p1);
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return lista;

    }

    //Escribe el map en un csv
    private static void escrituraMapeo(Map<String, Integer> listita) {

        ArrayList<Profesor> lista = lectura();
        listita = new TreeMap<>();
        listita = mapear(lista);
        // Fichero a crear. Ruta relativa a la carpeta raíz del proyecto
        Scanner teclado = new Scanner(System.in);
        String idFichero = "profesoresPorDepartamento.csv";
        String tmp;

        // Si se utiliza el constructor FileWriter(idFichero, true) entonces se anexa información
        // al final del fichero idFichero
        // Estructura try-with-resources. Instancia el objeto con el fichero a escribir
        // y se encarga de cerrar el recurso "flujo" una vez finalizadas las operaciones
        try ( BufferedWriter flujo = new BufferedWriter(new FileWriter(idFichero))) {
            flujo.write("Departamentos\tNúmero");
            flujo.newLine();
            for (String key : listita.keySet()) {
                flujo.write(key + " \t " + listita.get(key));
                flujo.newLine();
            }

            // Metodo fluh() guarda cambios en disco 
            flujo.flush();
            System.out.println("Fichero " + idFichero + " creado correctamente.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //Devuelve del nº de día entre una fecha y otra
    public static long diaEntreFechas(LocalDate ini, LocalDate fin) {

        //Restamos
        long resultado = ChronoUnit.DAYS.between(ini, fin);

        //Devolvemos el resultado
        return resultado;
    }

    //Metodo el cual crea el csv de los profesores que llevan más de 100 días trabajando
    private static void escrituraListaAntiguedad(ArrayList<Profesor> lista) {

        lista = lectura();
        LocalDate fechaini = LocalDate.of(2020, 1, 1);
        LocalDate fechafin = LocalDate.of(2021, 12, 31);
        // Fichero a crear. Ruta relativa a la carpeta raíz del proyecto
        Scanner teclado = new Scanner(System.in);
        String idFichero = "profesoresAntiguos.csv";
        String tmp;

        // Si se utiliza el constructor FileWriter(idFichero, true) entonces se anexa información
        // al final del fichero idFichero
        // Estructura try-with-resources. Instancia el objeto con el fichero a escribir
        // y se encarga de cerrar el recurso "flujo" una vez finalizadas las operaciones
        try ( BufferedWriter flujo = new BufferedWriter(new FileWriter(idFichero))) {
            flujo.write("Nombre\tDNI/Pasaporte\tPuesto\tFecha de Toma\tFecha de Cese\tTeléfono\tEvaluador\tCoodinador");
            flujo.newLine();
            for (Profesor profesor : lista) {
                if (profesor.getFechaToma().isAfter(fechaini) && profesor.getFechaCese().isBefore(fechafin)) {
                    if (diaEntreFechas(profesor.getFechaToma(), profesor.getFechaCese()) >= 100) {
                        flujo.write(profesor.toString());
                        flujo.newLine();
                    }
                }
            }

            // Metodo fluh() guarda cambios en disco 
            flujo.flush();
            System.out.println("Fichero " + idFichero + " creado correctamente.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        //Lista para los profesores con cierta letra en su dni
        ArrayList<String> listaLetraDni = new ArrayList<>();
        //Lista que guarda a los que han tenido la fecha de toma en ese tiempo
        ArrayList<String> listaFechaToma = new ArrayList<>();

        //Lista de profesores
        ArrayList<Profesor> lista = lectura();

        //Map
        Map<String, Integer> mapProfes = new HashMap<>();
        mapProfes = mapear(lista);

        //Contador el cual nos dice los profesores totales que hay
        int contador = 0;

        for (Profesor profesor : lista) {
            System.out.println(profesor.toString());
            contador++;
        }

        System.out.println("\n\nHay un total de: " + contador + " profesores");

        //Llamamos al método que escribe el archivo con el map
        escrituraMapeo(mapProfes);

        //Llamamos al método que crea un csv con los profesores que llevan más tiempo
        escrituraListaAntiguedad(lista);

        System.out.println("\n\n\n---------------EJERCICIOS DE LA PRÁCTICA 7.1-------------");
        //Llamamos al método de utils el cual nos comprueba si hay un trabajador que se llama así
        System.out.println("¿Algún trabajador se llama Natalia?: "
                + Utils.hayEmpleado(lista, "Natalia"));

        //Llamamos al método que consulta los coordinadores de un departamento
        System.out.println("Del departamento de Matemáticas P.E.S. hay un total de: "
                + Utils.numEmpleadosDepartamento(lista, "Matemáticas P.E.S.") + " Coordinadores");

        //Metemos en la lista el ArrayList resultante del método que busca la letra de dni
        listaLetraDni = Utils.listaApellidos(lista, 'P');

        System.out.println("\nLos trabajadores con la letra P en su DNI son: \n");

        //Recorremos la lista y mostramos el nombre del profesor
        for (String profesor : listaLetraDni) {
            System.out.println(profesor);
        }

        //Metemos en la lista el ArrayList resultante del método el cual mira la fecha y devuelve el dni
        listaFechaToma = Utils.comprobarFechaPosesion(lista, LocalDate.of(2006, Month.SEPTEMBER, 1));
        System.out.println("\nLos trabajadores cuya toma de posesión es el día 01/09/2006 tienen el dni: \n");

        //Recorremos la lista y la mostramos por pantalla
        for (String profesor : listaFechaToma) {
            System.out.println(profesor);
        }

    }
}
