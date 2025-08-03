import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

public class SecondDaySP {
	public static int calculateLength(String[] side) {
		int[] integerParse = {Integer.parseInt(side[0]), Integer.parseInt(side[1]), Integer.parseInt(side[2])};
		Arrays.sort(integerParse);

		return 2 * (integerParse[0] + integerParse[1]) + integerParse[0] * integerParse[1] * integerParse[2];
	}

	public static void main(String[] args) {
		try (var scanner = new Scanner(new FileInputStream(new File("SecondDayI.txt")))) {
			var result = 0;

			while(scanner.hasNext()) result += calculateLength(scanner.nextLine().trim().split("x"));

			System.out.println(result);
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			e.printStackTrace();
		}
	}
}
