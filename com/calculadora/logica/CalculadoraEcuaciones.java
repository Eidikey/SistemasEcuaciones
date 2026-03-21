package com.calculadora.logica;
import com.calculadora.modelo.Matriz;

public class CalculadoraEcuaciones {
    public static double[] resolverGaussJordan(Matriz aumentada) {
        int n = aumentada.getFilas();
        int m = aumentada.getColumnas();
        Matriz temp = aumentada.copiar();

        for (int i = 0; i < n; i++) {
            int filaMax = i;
            for (int k = i + 1; k < n; k++) {
                if (Math.abs(temp.getElemento(k, i)) > Math.abs(temp.getElemento(filaMax, i))) {
                    filaMax = k;
                }
            }

            intercambiarFilas(temp, i, filaMax);

            double pivote = temp.getElemento(i, i);
            if (Math.abs(pivote) < 1e-10) {
              //Aui se setea el mensaje que llegara al Main
                throw new ArithmeticException("El sistema no tiene solución única.");
            }

            for (int j = i; j < m; j++) {
                temp.setElemento(i, j, temp.getElemento(i, j) / pivote);
            }

            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = temp.getElemento(k, i);
                    for (int j = i; j < m; j++) {
                        double nuevo = temp.getElemento(k, j) - (factor * temp.getElemento(i, j));
                        temp.setElemento(k, j, nuevo);
                    }
                }
            }
        }

        double[] sol = new double[n];
        for (int i = 0; i < n; i++) sol[i] = temp.getElemento(i, m - 1);
        return sol;
    }

    private static void intercambiarFilas(Matriz m, int f1, int f2) {
        if (f1 == f2) return;
        for (int j = 0; j < m.getColumnas(); j++) {
            double aux = m.getElemento(f1, j);
            m.setElemento(f1, j, m.getElemento(f2, j));
            m.setElemento(f2, j, aux);
        }
    }
}
