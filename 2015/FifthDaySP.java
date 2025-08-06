import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FifthDaySP {
	public static void main(String[] args) {
		try(var scanner = new Scanner(new FileInputStream(new File("FifthDayI.txt")))) {
			var result = 0;

			while(scanner.hasNext()) {
				var input = scanner.nextLine().trim();
				var good = false;
				var duplicateSpace = false;

				for(var i = 0; i < input.length() - 1; i++) {
					var pair = input.substring(i, i + 2);

					for(int j = i + 2; j < input.length() - 1; j++) if(input.substring(j, j + 2).equals(pair)) {
						good = true;
						break;
					}

					if(good == true) break;
				}

				for(int i = 0; i < input.length() - 2; i++) if(input.charAt(i) == input.charAt(i + 2)) {
						duplicateSpace = true;
						break;
				}

				if(good && duplicateSpace) result++;
			}

			System.out.println("Result: " + result);
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			e.printStackTrace();
		}
	}
}
