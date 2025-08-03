import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class ThirdDaySP {
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

			var positionSanta = new Point(0, 0);
			var positionRobo = new Point(0, 0);
			var list = new LinkedList<Point>();
			list.add(positionSanta);

			for(var i = 0; i < input.length(); i++) {
				if(i % 2 == 0) {
					positionSanta = switch(input.charAt(i)) {
						case '>': yield positionSanta.withX(positionSanta.x() + 1);
						case '<': yield positionSanta.withX(positionSanta.x() - 1);
						case '^': yield positionSanta.withY(positionSanta.y() + 1);
						case 'v': yield positionSanta.withY(positionSanta.y() - 1);
						default : throw new RuntimeException("Unknown character");
					};

					if(!list.contains(positionSanta)) list.add(positionSanta);
				} else {
					positionRobo = switch(input.charAt(i)) {
						case '>': yield positionRobo.withX(positionRobo.x() + 1);
						case '<': yield positionRobo.withX(positionRobo.x() - 1);
						case '^': yield positionRobo.withY(positionRobo.y() + 1);
						case 'v': yield positionRobo.withY(positionRobo.y() - 1);
						default : throw new RuntimeException("Unknown character");
					};

					if(!list.contains(positionRobo)) list.add(positionRobo);

				}
			}

			System.out.println("Result: " + list.size());
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			e.printStackTrace();
		}
	}
}
