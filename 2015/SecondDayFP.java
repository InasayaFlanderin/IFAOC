import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SecondDayFP {
	public static int calculateSquare(String[] side) {
		var firstSide = Integer.parseInt(side[0]);
		var secondSide = Integer.parseInt(side[1]);
		var thirdSide = Integer.parseInt(side[2]);
		var firstArea = firstSide * secondSide;
		var secondArea = firstSide * thirdSide;
		var thirdArea = secondSide * thirdSide;

		return 2 * (firstArea + secondArea + thirdArea) + Math.min(Math.min(firstArea, secondArea), thirdArea);
	}

	public static void main(String[] args) {
		try (var scanner = new Scanner(new FileInputStream(new File("SecondDayI.txt")))) {
			var result = 0;

			while(scanner.hasNext()) result += calculateSquare(scanner.nextLine().trim().split("x"));

			System.out.println(result);
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			e.printStackTrace();
		}
	}
 }
