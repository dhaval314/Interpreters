package JLox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Lox {
  //static flag, it is set to true when an error is encountered
  static boolean hadError = false;
  public static void main(String[] args) throws IOException {
    //if more than one arguement is provided, then it displays error and exits
    if (args.length > 1) {
      System.out.println("Usage: jlox [script]");
      System.exit(64); 
    } 
    //if an arguement is provided, then the runFile() function is called which takes in the path
    else if (args.length == 1) {
      runFile(args[0]);
    }
    //If no arguements are provided, then an interactive prompt starts
    else {
      runPrompt();
    }
  }

  //reads the file in the specified path as a byte array, then converts this array into a string and passes it to run()
  private static void runFile(String path) throws IOException {
    byte[] bytes = Files.readAllBytes(Paths.get(path));
    run(new String(bytes, Charset.defaultCharset()));
     // Indicate an error in the exit code.
     if (hadError) System.exit(65);
  }

  //starts the interactive prompt
  private static void runPrompt() throws IOException {
    InputStreamReader input = new InputStreamReader(System.in);
    BufferedReader reader = new BufferedReader(input);

    for (;;) { 
      System.out.print("> ");
      //reads input line by line
      String line = reader.readLine();
      if (line == null) break;
      run(line);
      //resets haderror flag after each input, so that errors don't carry onto next commands
      hadError = false;
    }
  }
  //creates an instance of the scanner class and scans the tokens from source
  private static void run(String source) {
      Scanner scanner = new Scanner(source);
      List<Token> tokens = scanner.scanTokens();

      // For now, just print the tokens.
      for (Token token : tokens) {
          System.out.println(token);
      }
  }
  //called when an error occurs
  static void error(int line, String message) {
    report(line, "", message);
  }

  private static void report(int line, String where,String message) {
    System.err.println(
        "[line " + line + "] Error" + where + ": " + message);
    hadError = true;
  }

}