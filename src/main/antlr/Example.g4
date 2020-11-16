grammar Example;

@header {
package lcmc;
}

@lexer::members {
// Istanzio variabili del lexer.
public int lexicalErrors = 0;
}

// PARSER RULES

prog: exp EOF { System.out.println("Parsing finished!"); };
exp: exp PLUS term | term;
term: term TIMES value | value;
value: NUM | LPAR exp RPAR;

// LEXER RULES

PLUS: '+';
MINUS: '-';
TIMES: '*';
DIV: '/';
LPAR: '(';
RPAR: ')';
NUM: ('0' | ('1' .. '9')) ('0'..'9')*;

WHITESP: (' ' | '\t' | '\n' | '\r')+ -> channel(HIDDEN);

ERR: . {
System.out.println("Invalid char: " + getText());
lexicalErrors++;
} -> channel(HIDDEN);

COMMENT: '/*' .*? '*/' -> channel(HIDDEN);