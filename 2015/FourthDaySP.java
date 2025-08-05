import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class FourthDaySP {
	public static void main(String[] args) {
		var hf = HexFormat.of();

		try {
			var md = MessageDigest.getInstance("MD5");
			
			for(int i = 0;; i++) if(hf.formatHex(md.digest(("ckczppom" + i).getBytes())).startsWith("000000")) {
				System.out.println("Result: " + i);
				break;
			}
		} catch (NoSuchAlgorithmException e) {
			System.out.println("No MD5!");
			e.printStackTrace();
		}
	}
}
