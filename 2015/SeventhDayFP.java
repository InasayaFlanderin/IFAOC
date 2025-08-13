import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.BitSet;
import java.util.function.BiFunction;

public class SeventhDayFP {
	private static final LinkedList<String[]> commands = new LinkedList<>();
	private static final HashMap<String, BitSet> register = new HashMap<>();

	private static BitSet copy(BitSet input) {
		var result = new BitSet(16);

		for(var i = 0; i < 16; i++) result.set(i, input.get(i));

		return result;
	}

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
		var result = copy(original);
		var shiftAmount = convertBitSetToInteger(shift);

		for(var i = 0; i < 16 - shiftAmount; i++) result.set(i, result.get(i + shiftAmount));

		for(var i = 16 - shiftAmount; i < 16; i++) result.set(i, false);

		return result;
	}

	public static BitSet rightShift(BitSet original, BitSet shift) {
		var result = copy(original);
		var shiftAmount = convertBitSetToInteger(shift);

		for(var i = 15; i >= shiftAmount; i--) result.set(i, result.get(i - shiftAmount));

		for(var i = 0; i < shiftAmount; i++) result.set(i, false);

		return result;
	}

	public static BitSet or(BitSet x, BitSet y) {
		var result = copy(x);
		result.or(y);

		return result;
	}

	public static BitSet and(BitSet x, BitSet y) {
		var result = copy(x);
		result.and(y);

		return result;
	}

	public static BitSet not(BitSet input) {
		var result = copy(input);
		result.flip(0, 16);

		return result;
	}

	public static boolean executeBinary(String[] command, BiFunction<BitSet, BitSet, BitSet> function) {
		var firstInput = isDigit(command[1].charAt(0)) ? convertIntegerToBitSet(Integer.parseInt(command[1])) : register.get(command[1]);
		var secondInput = isDigit(command[2].charAt(0)) ? convertIntegerToBitSet(Integer.parseInt(command[2])) : register.get(command[2]);

		if(firstInput == null || secondInput == null) return false;

		register.put(command[3], function.apply(firstInput, secondInput));

		return true;
	}

	public static boolean isDigit(char input) {
		return input >= '0' && input <= '9';
	}

	public static void main(String[] args) {
		try(var scanner = new Scanner(new FileInputStream(new File("SeventhDayI.txt")))) {

			while(scanner.hasNext()) {
				var input = scanner.nextLine().trim().replace(" -> ", " ").split(" ");

				if(input[1].equals("OR") || input[1].equals("LSHIFT") || input[1].equals("AND") || input[1].equals("RSHIFT")) {
					var temp = input[0];
					input[0] = input[1];
					input[1] = temp;

					commands.add(input);
				} else commands.add(input);
			}

			while(!commands.isEmpty()) {
				var execute = new LinkedList<String[]>();

				commands.forEach(command -> {
					switch(command[0]) {
						case "NOT" -> {
							var input = register.get(command[1]);

							if(input == null) break;

							register.put(command[2], not(input));
							execute.add(command);
						}
						case "OR" -> {
							if(executeBinary(command, SeventhDayFP::or)) execute.add(command);
						}
						case "AND" -> {
							if(executeBinary(command, SeventhDayFP::and)) execute.add(command);
						}
						case "LSHIFT" -> {
							if(executeBinary(command, SeventhDayFP::leftShift)) execute.add(command);
						}
						case "RSHIFT" -> {
							if(executeBinary(command, SeventhDayFP::rightShift)) execute.add(command);
						}
						default -> {
							var input = isDigit(command[0].charAt(0)) ? convertIntegerToBitSet(Integer.parseInt(command[0])) : register.get(command[0]);

							if(input == null) break;

							register.put(command[1], input);
							execute.add(command);
						}
					}
				});

				if(!execute.isEmpty()) execute.forEach(command -> commands.remove(command));
				if(register.containsKey("a")) break;
			}
			System.out.println("Result: " + convertBitSetToInteger(register.get("a")));
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			e.printStackTrace();
		}
	}
}
