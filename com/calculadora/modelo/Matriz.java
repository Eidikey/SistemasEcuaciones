package com.calculadora.modelo;

public class Matriz {
    private final double[][] datos;
    private final int filas;
    private final int columnas;

    public Matriz(int filas, int columnas) {
        if (filas <= 0 || columnas <= 0) {
          //Aqui igual se setea el mensaje
            throw new IllegalArgumentException("Dimensiones deben ser > 0");
        }
        this.filas = filas;
        this.columnas = columnas;
        this.datos = new double[filas][columnas];
    }

    public int getFilas() {
     return filas; 
    }
    
    public int getColumnas() { 
      return columnas; 
    }

    public double getElemento(int f, int c) {
        validarIndices(f, c);
        return datos[f][c];
    }

    public void setElemento(int f, int c, double valor) {
        validarIndices(f, c);
        this.datos[f][c] = valor;
    }

    private void validarIndices(int f, int c) {
        if (f < 0 || f >= filas || c < 0 || c >= columnas) {
          //este otro igual
            throw new IndexOutOfBoundsException("Posición inválida: [" + f + "][" + c + "]. La matriz es de " + filas + "x" + columnas);
        }
    }

    public Matriz copiar() {
        Matriz copia = new Matriz(this.filas, this.columnas);
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                copia.setElemento(i, j, this.datos[i][j]);
            }
        }
        return copia;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (double[] fila : datos) {
            sb.append("| ");
            for (double v : fila) sb.append(String.format("%8.2f ", v));
            sb.append("|\n");
        }
        return sb.toString();
    }
}
