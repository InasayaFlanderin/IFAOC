import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FifthDayFP {
	public static void main(String[] args) {
		try(var scanner = new Scanner(new FileInputStream(new File("FifthDayI.txt")))) {
			var result = 0;

			while(scanner.hasNext()) {
				var input = scanner.nextLine().trim();
				var d = false;
				var vowel = 0;
				
				for(int i = 0; i < input.length(); i++) {
					var c = input.charAt(i);

					if(!d && i != input.length() - 1 && c == input.charAt(i + 1)) d = true;
					if(vowel < 3 && (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')) vowel++;
				}

				if(vowel == 3 && d && !(input.contains("ab") || input.contains("cd") || input.contains("pq") || input.contains("xy"))) result++;
			}

			System.out.println("Result: " + result);
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			e.printStackTrace();
		}
	}
}
