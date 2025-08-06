import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

public class SixthDaySP {
	public static void main(String[] args) {
		try(var scanner = new Scanner(new FileInputStream(new File("SixthDayI.txt")))) {
			var lights = new int[1000][1000];

			for(int i = 0; i < lights.length; i++) for(int j = 0; j < lights.length; j++) lights[i][j] = 0;

			while(scanner.hasNext()) {
				var input = scanner.nextLine().trim().split(" ");

				if(input[0].equals("turn")) {
					var firstCoordinate = input[2].split(",");
					var secondCoordinate = input[4].split(",");

					if(input[1].equals("on")) for(var i = Integer.parseInt(firstCoordinate[0]); i <= Integer.parseInt(secondCoordinate[0]); i++) for(var j = Integer.parseInt(firstCoordinate[1]); j <= Integer.parseInt(secondCoordinate[1]); j++) lights[i][j]++;
					else for(var i = Integer.parseInt(firstCoordinate[0]); i <= Integer.parseInt(secondCoordinate[0]); i++) for(var j = Integer.parseInt(firstCoordinate[1]); j <= Integer.parseInt(secondCoordinate[1]); j++) lights[i][j] = Math.max(lights[i][j] - 1, 0);
				} else {
					var firstCoordinate = input[1].split(",");
					var secondCoordinate = input[3].split(",");

					for(var i = Integer.parseInt(firstCoordinate[0]); i <= Integer.parseInt(secondCoordinate[0]); i++) for(var j = Integer.parseInt(firstCoordinate[1]); j <= Integer.parseInt(secondCoordinate[1]); j++) lights[i][j] += 2;
				}
			}

			var result = 0;

			for(int i = 0; i < lights.length; i++) for(int j = 0; j < lights.length; j++) result += lights[i][j];

			System.out.println("Result: " + result);
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			e.printStackTrace();
		}
	}
}
