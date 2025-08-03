import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.BitSet;
import java.util.function.BiFunction;

public class SeventhDayFP {
	private static final LinkedList<String[]> commands = new LinkedList<>();
	private static final HashMap<String, BitSet> register = new HashMap<>();

	public static BitSet convertIntegerToBitSet(int input) {
		var result = new BitSet(16);
		
		for(var i = 15; i >= 0; i--) if((input & (1 << (15 - i))) != 0) result.set(i, true);

		return result;
	}

	public static int convertBitSetToInteger(BitSet input) {
		var result = 0;

		for(var i = 15; i >= 0; i--) if(input.get(i)) result |= (1 << (15 - i));

		return result;
	}

	public static BitSet leftShift(BitSet original, BitSet shift) {
		var result = new BitSet(16);
		var shiftAmount = convertBitSetToInteger(shift);

		for(var i = 0; i < 16 - shiftAmount; i++) result.set(i, original.get(i + shiftAmount));

		return result;
	}

	public static BitSet rightShift(BitSet original, BitSet shift) {
		var result = new BitSet(16);
		var shiftAmount = convertBitSetToInteger(shift);

		for(var i = 15; i >= shiftAmount; i--) result.set(i, original.get(i - shiftAmount));

		return result;
	}

	public static BitSet or(BitSet x, BitSet y) {
		var result = (BitSet) x.clone();
		result.or(y);

		return result;
	}

	public static BitSet and(BitSet x, BitSet y) {
		var result = (BitSet) x.clone();
		result.and(y);

		return result;
	}

	public static BitSet not(BitSet input) {
		var result = (BitSet) input.clone();
		result.flip(0, 16);

		return result;
	}

	public static boolean executeBinary(String[] command, BiFunction<BitSet, BitSet, BitSet> function) {
		var firstInput = getInput(command, 1);
		var secondInput = getInput(command, 2);

		if(firstInput == null || secondInput == null) return false;

		register.put(command[3], function.apply(firstInput, secondInput));

		return true;
	}

	public static boolean isDigit(char input) {
		return input >= '0' && input <= '9';
	}

	public static BitSet getInput(String[] command, int index) {
		return isDigit(command[index].charAt(0)) ? convertIntegerToBitSet(Integer.parseInt(command[index])) : register.get(command[index]);
	}

	public static void main(String[] args) {
		try(var scanner = new Scanner(new FileInputStream(new File("SeventhDayI.txt")))) {
			while(scanner.hasNext()) {
				var input = scanner.nextLine().trim().replace(" -> ", " ").split(" ");

				if(input[1].equals("OR") || input[1].equals("LSHIFT") || input[1].equals("AND") || input[1].equals("RSHIFT")) {
					var temp = input[0];
					input[0] = input[1];
					input[1] = temp;
				}

				commands.add(input);
			}

			while(!commands.isEmpty()) {
				var iterator = commands.listIterator(0);

				while(iterator.hasNext()) {
					var command = iterator.next();

					switch(command[0]) {
						case "NOT" -> {
							var input = register.get(command[1]);

							if(input == null) break;

							register.put(command[2], not(input));
							iterator.remove();
						}
						case "OR" -> {
							if(executeBinary(command, SeventhDayFP::or)) iterator.remove();
						}
						case "AND" -> {
							if(executeBinary(command, SeventhDayFP::and)) iterator.remove();
						}
						case "LSHIFT" -> {
							if(executeBinary(command, SeventhDayFP::leftShift)) iterator.remove();
						}
						case "RSHIFT" -> {
							if(executeBinary(command, SeventhDayFP::rightShift)) iterator.remove();
						}
						default -> {
							var input = getInput(command, 0);

							if(input == null) break;

							register.put(command[1], input);
							iterator.remove();
						}
					}
				}
			}

			System.out.println("Result: " + convertBitSetToInteger(register.get("a")));
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			e.printStackTrace();
		}
	}
}
