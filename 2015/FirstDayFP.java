import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FirstDayFP {
	public static void main(String[] args) {
		try(var scanner = new Scanner(new FileInputStream(new File("FirstDayI.txt")))) {
			var input = scanner.nextLine();
			var floor = 0;

			for(var i = 0; i < input.length(); i++) {
				if(input.charAt(i) == '(') floor++;
				else floor--;
			}

			System.out.println("Result: " + floor);
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			e.printStackTrace();
		}
	}
}
