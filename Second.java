public class Second {
    public static void main(String[] args) {
        int[][] x = { {20, 34, 2}, {9, 12, 18}, {3, 4, 5} };
        int min = findMin(x);
        System.out.println("Минимальный элемент массива: " + min);
    }

    public static int findMin(int[][] array) {
        int min = array[0][0];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] < min) {
                    min = array[i][j];
                }
            }
        }
        return min;
    }
}
