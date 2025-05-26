import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int max = 1;
            int[] numberCounts = new int[10];
            String roomNumber = br.readLine();

            for (char number : roomNumber.toCharArray()) {
                numberCounts[number - '0']++;
            }

            numberCounts[6] = (int) Math.ceil(( numberCounts[6] + numberCounts[9] ) / 2.0);
            numberCounts[9] = numberCounts[6];

            for (int count : numberCounts) {
                max = Math.max(max, count);
            }

            System.out.println(max);
        }
    }
}
