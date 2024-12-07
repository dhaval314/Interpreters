package JLox;

class Token {
  final TokenType type; //type of token, ex: identifier, number
  final String lexeme; // text which represents the token
  final Object literal; 
  final int line; //line number in source code where the token appears

  Token(TokenType type, String lexeme, Object literal, int line) {
    this.type = type;
    this.lexeme = lexeme;
    this.literal = literal;
    this.line = line;
  }
  //converts token into a readable string
  public String toString() {
    return type + " " + lexeme + " " + literal;
  }
}
