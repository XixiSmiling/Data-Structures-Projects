import java.io.*;

class Nuke2 {
	
	public static void main(String[] arg) throws Exception{
		
		BufferedReader keyboard;
		String inputLine;
		
		keyboard = new BufferedReader(new InputStreamReader(System.in));
		inputLine = keyboard.readLine();
		
		StringBuilder inputString = new StringBuilder(inputLine);
		System.out.println(inputString.deleteCharAt(1));
		
	}
}