import java.util.Scanner;

public class GaussEliminationPivoting {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter the number of variables: ");
    int n = scanner.nextInt();

    double[][] augmentedMatrix = new double[n][n + 1];

    System.out.println("Enter the augmented matrix row by row:");
    for (int i = 0; i < n; i++) {
      for (int j = 0; j <= n; j++) {
        augmentedMatrix[i][j] = scanner.nextDouble();
      }
    }

    solveByGaussElimination(augmentedMatrix, n);

    scanner.close();
  }

  public static void solveByGaussElimination(double[][] matrix, int n) {

    for (int i = 0; i < n; i++) {

      int maxRow = i;
      for (int k = i + 1; k < n; k++) {
        if (Math.abs(matrix[k][i]) > Math.abs(matrix[maxRow][i])) {
          maxRow = k;
        }
      }

      double[] temp = matrix[i];
      matrix[i] = matrix[maxRow];
      matrix[maxRow] = temp;

      if (matrix[i][i] == 0) {
        System.out.println("Mathematical error! Zero pivot found.");
        return;
      }

      for (int j = i + 1; j < n; j++) {
        double factor = matrix[j][i] / matrix[i][i];

        for (int k = i; k <= n; k++) {
          matrix[j][k] -= factor * matrix[i][k];
        }
      }
    }

    double[] solution = new double[n];
    for (int i = n - 1; i >= 0; i--) {
      solution[i] = matrix[i][n];

      for (int j = i + 1; j < n; j++) {
        solution[i] -= matrix[i][j] * solution[j];
      }

      solution[i] /= matrix[i][i];
    }

    System.out.println("Solution:");
    for (int i = 0; i < n; i++) {
      System.out.printf("x[%d] = %.4f%n", i + 1, solution[i]);
    }
  }
}
