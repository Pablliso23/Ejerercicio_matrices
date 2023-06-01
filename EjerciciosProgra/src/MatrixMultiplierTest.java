import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

public class MatrixMultiplierTest {

    @Test
    public void testMultiplyMatrices() throws IOException {
        // Archivos de prueba
        String matriz1File = "matriz1.txt";
        String matriz2File = "matriz2.txt";
        String resultadoFile = "resultado.txt";

        // Matrices para las pruebas
        int[][] matriz1 = {{1, 2}, {3, 4}};
        int[][] matriz2 = {{5, 6}, {7, 8}};
        int[][] matrizOverflow = {{Integer.MAX_VALUE, 2}, {3, 4}};
        int[][] matrizInvalida = {{1, 2}, {3, 4, 5}};
        int[][] matrizOutOfBounds = {{1, 2}, {3, 4}};
        int[][] matrizInvalidNumberFormat = {{1, 2}, {3, 4}};

        // Prueba de multiplicaci�n de matrices v�lidas
        int[][] expected = {{19, 22}, {43, 50}};
        int[][] resultado = MatrixMultiplier.multiplyMatrices(matriz1, matriz2);
        Assertions.assertArrayEquals(expected, resultado, "La multiplicaci�n de matrices v�lidas no es correcta");
        System.out.println("La multiplicaci�n de matrices v�lidas es correcta");

        // Prueba de desbordamiento de enteros
        Assertions.assertThrows(ArithmeticException.class, () -> {
            MatrixMultiplier.multiplyMatrices(matriz1, matrizOverflow);
        }, "La multiplicaci�n produce un desbordamiento de enteros");
        System.out.println("La multiplicaci�n produce un desbordamiento de enteros");

        // Prueba de n�meros enteros en el archivo de matriz
        Assertions.assertDoesNotThrow(() -> {
            MatrixMultiplier.writeMatrixToFile(matriz1, matriz1File);
            MatrixMultiplier.writeMatrixToFile(matriz2, matriz2File);
            MatrixMultiplier.main(new String[0]);
        }, "Los n�meros en el archivo de matriz no son enteros v�lidos");
        System.out.println("Los n�meros en el archivo de matriz son enteros v�lidos");

        // Prueba de existencia de archivo
        File resultadoFileObj = new File(resultadoFile);
        Assertions.assertTrue(resultadoFileObj.exists(), "El archivo de resultado no existe");
        System.out.println("El archivo de resultado existe");

        // Prueba de formato y estructura del archivo
        int[][] resultadoFromFile = MatrixMultiplier.readMatrixFromFile(resultadoFile);
        Assertions.assertArrayEquals(expected, resultadoFromFile, "El archivo de resultado tiene un formato o estructura incorrectos");
        System.out.println("El archivo de resultado tiene un formato y estructura correctos");

        // Prueba de formato de n�mero no v�lido en el archivo de matriz
        String matrizInvalidaFile = "matrizInvalida.txt";
        Assertions.assertThrows(NumberFormatException.class, () -> {
            MatrixMultiplier.writeMatrixToFile(matrizInvalida, matrizInvalidaFile);
            MatrixMultiplier.readMatrixFromFile(matrizInvalidaFile);
        }, "El archivo de matriz contiene un formato de n�mero no v�lido");
        System.out.println("El archivo de matriz contiene un formato de n�mero no v�lido");

        // Prueba de desbordamiento de �ndice de matriz
        String matrizOutOfBoundsFile = "matrizOutOfBounds.txt";
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            MatrixMultiplier.writeMatrixToFile(matrizOutOfBounds, matrizOutOfBoundsFile);
            MatrixMultiplier.readMatrixFromFile(matrizOutOfBoundsFile);
        }, "El archivo de matriz produce un desbordamiento de �ndice");
        System.out.println("El archivo de matriz produce un desbordamiento de �ndice");
    }
}
