import java.io.*;

public class MatrixMultiplier {
    public static void main(String[] args) {
        String matriz1File = "matriz1.txt";
        String matriz2File = "matriz2.txt";
        String resultadoFile = "resultado.txt";

        try {
            int[][] matriz1 = readMatrixFromFile(matriz1File);
            int[][] matriz2 = readMatrixFromFile(matriz2File);

            if (matriz1[0].length != matriz2.length) {
                throw new IllegalArgumentException("Las dimensiones de las matrices no son compatibles para la multiplicación");
            }

            int[][] resultado = multiplyMatrices(matriz1, matriz2);

            writeMatrixToFile(resultado, resultadoFile);
            System.out.println("La multiplicación de las matrices se ha guardado en el archivo " + resultadoFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[][] readMatrixFromFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        int rows = 0;
        int cols = 0;

        while ((line = reader.readLine()) != null) {
            String[] elements = line.trim().split("\\s+");
            if (cols == 0) {
                cols = elements.length;
            }
            rows++;
        }

        reader.close();

        int[][] matrix = new int[rows][cols];
        reader = new BufferedReader(new FileReader(fileName));
        int i = 0;

        while ((line = reader.readLine()) != null) {
            String[] elements = line.trim().split("\\s+");
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = Integer.parseInt(elements[j]);
            }
            i++;
        }

        reader.close();
        return matrix;
    }

    public static void writeMatrixToFile(int[][] matrix, String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                writer.write(matrix[i][j] + " ");
            }
            writer.newLine();
        }

        writer.close();
    }

    static int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2) {
        int rows1 = matrix1.length;
        int cols1 = matrix1[0].length;
        int cols2 = matrix2[0].length;

        int[][] resultado = new int[rows1][cols2];

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                for (int k = 0; k < cols1; k++) {
                    resultado[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        return resultado;
    }
}

