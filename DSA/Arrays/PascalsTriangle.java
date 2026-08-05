public class PascalsTriangle {
    static void printPascal(int n) {
        for (int row = 1; row <= n; row++) {

            // nC0 = 1
            int c = 1;
            for (int i = 1; i <= row; i++) {

                // The first value in a row is always 1
                System.out.print(c + " ");
                c = c * (row - i) / i;
            }
            System.out.println();
        }
    }

}
