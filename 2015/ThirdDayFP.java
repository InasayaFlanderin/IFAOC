import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class ThirdDayFP {
	public static void main(String[] args) {
		try(var scanner = new Scanner(new FileInputStream(new File("ThirdDayI.txt")))) {
			var input = scanner.nextLine();

			record Point(int x, int y) {
				public Point withX(int newX) {
					return new Point(newX, this.y);
				}

				public Point withY(int newY) {
					return new Point(this.x, newY);
				}
			}

			var position = new Point(0, 0);
			var list = new LinkedList<Point>();
			list.add(position);

			for(var i = 0; i < input.length(); i++) {
				position = switch(input.charAt(i)) {
					case '>': yield position.withX(position.x() + 1);
					case '<': yield position.withX(position.x() - 1);
					case '^': yield position.withY(position.y() + 1);
					case 'v': yield position.withY(position.y() - 1);
					default : throw new RuntimeException("Unknown character");
				};

				if(!list.contains(position)) list.add(position);
			}

			System.out.println("Result: " + list.size());
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			e.printStackTrace();
		}
	}
}
