package com.calculadora;

import com.calculadora.modelo.Matriz;
import com.calculadora.logica.CalculadoraEcuaciones;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            try {
                System.out.println("\n--- SOLUCIONADOR DE SISTEMAS LINEALES ---");
                System.out.print("Número de incógnitas (2-5) o 0 para salir: ");
                int n = sc.nextInt();

                if (n == 0) {
                    continuar = false;
                    System.out.println("Saliendo del programa ...");
                    break;
                }

                Matriz sistema = new Matriz(n, n + 1);
                for (int i = 0; i < n; i++) {
                    System.out.println("Ecuación # " + (i + 1));
                    for (int j = 0; j <= n; j++) {
                        if (j < n) {
                            System.out.print("  Coeficiente de x" + (j + 1) + " (solo el número): ");
                        } else {
                            System.out.print("  Resultado de la ecuación (término independiente): ");
                        }
                        sistema.setElemento(i, j, sc.nextDouble());
                    }
                }

                System.out.println("\nMatriz ingresada:\n" + sistema);
                double[] x = CalculadoraEcuaciones.resolverGaussJordan(sistema);

                System.out.println("Solución: ");
                for (int i = 0; i < x.length; i++) {
                    System.out.printf("  x%d = %.0f\n", (i + 1), x[i]);
                }

            } catch (InputMismatchException e) {
                System.err.println("\n [Error]: Entrada inválida!! Por favor, ingresa solo números.");
                sc.nextLine();
            } catch (ArithmeticException e) { 
                System.err.println("\n [Error Matemático]: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.err.println("\n [Error de configuración]: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("\n [ERROR INESPERADO]: " + e.getMessage());
            }
        }
        
        sc.close(); 
    }
}
