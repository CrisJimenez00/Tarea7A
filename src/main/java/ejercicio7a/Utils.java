/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio7a;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author cristina
 */
public class Utils {

    //A partir de una lista de empleados y un nombre, 
    //indique si hay algún empleado con ese nombre.
    public static boolean hayEmpleado(ArrayList<Profesor> lista, String nombre) {

        boolean resultado = false;

        //Recorremos la lista
        for (Profesor profesor : lista) {

            //Si el nombre coincide en la lista la variable cambia
            if (profesor.getNombre().contains(nombre)) {

                resultado = true;

            }

        }

        return resultado;

    }

    //A partir de una lista de empleados y un nombre de departamento,
    //indique el número de empleados Coordinadores de ese departamento.
    public static int numEmpleadosDepartamento(ArrayList<Profesor> lista, String nombreDepartamento) {

        int contador = 0;

        for (Profesor profesor : lista) {

            if (profesor.getPuesto().equalsIgnoreCase(nombreDepartamento) && profesor.isCoordinador() == true) {

                contador++;

            }

        }
        return contador;
    }

    //A partir de una lista de empleados y una letra del NIF (char), 
    //obtener una nueva lista ordenada alfabéticamente SOLO con los 
    //apellidos de los empleados cuyo NIF contenga esa letra.
    public static ArrayList<String> listaApellidos(ArrayList<Profesor> lista, char letra) {
        //Creamos la lista que aguarda los resultados
        ArrayList<String> listaNif = new ArrayList<>();

        //Recorremos la lista completa de profesores
        for (Profesor profesor : lista) {

            //Miramos si el dni de cada uno contiene la letra
            if (profesor.getDni().contains(String.valueOf(letra))) {
                //En caso afirmativo lo añade
                listaNif.add(profesor.getNombre());

            }

        }

        Collections.sort(lista, (Profesor p1, Profesor p2) -> p1.getNombre().compareTo(p2.getNombre()));

        return listaNif;

    }

    //A partir de una lista de empleados y una fecha, obtener una nueva lista 
    //con los NIF (ordenados de forma inversa) de todos los empleados 
    //cuya toma de posesión coincida con esa fecha.
    public static ArrayList<String> comprobarFechaPosesion(ArrayList<Profesor> lista, LocalDate fecha) {

        //Lista que va a devolver
        ArrayList<String> listaNif = new ArrayList<>();

        for (Profesor profesor : lista) {
            if (profesor.getFechaToma().equals(fecha)) {
                listaNif.add(profesor.getDni());
            }
        }

        Collections.sort(listaNif);
        Collections.reverse(listaNif);
        return listaNif;

    }

    public static boolean siHayEmpleadoStream(ArrayList<Profesor> lista, String nombre) {
        boolean resultado = lista.stream()
                .anyMatch(p -> p.getNombre().equals(nombre));
        return resultado;
    }

    public static long numCoordinadoresStream(ArrayList<Profesor> lista, String departamento) {
        return lista.stream()
                .filter(c -> c.isCoordinador())
                .count();
    }

    public static ArrayList<String> listaOrdenadaStream(ArrayList<Profesor> lista, String letra) {
        return (ArrayList<String>) lista.stream()
                .filter(p -> p.getDni().contains(letra))
                .map(p -> p.getNombre())
                .sorted()
                .collect(Collectors.toList());
    }

    public static ArrayList<String> listaNifStream(ArrayList<Profesor> lista, LocalDate fecha) {
        return (ArrayList<String>) lista.stream()
                .filter(profesor -> profesor.getFechaToma().equals(fecha))
                .map(p -> p.getDni())
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());

    }
}
