grammar Example;

@header {
package lcmc;
}

@lexer::members {
int lexicalErrors = 0;
}

// PARSER RULES

prog: EOF { System.out.println("Parsing finished!"); };

// ... other rules.

// LEXER RULES

// ... some important rules.

// Whitespace remove rule.
WHITESP: (' ' | '\t' | '\n' | '\r')+ -> channel(HIDDEN);

// Catch error rule. Match any char that is any other.
ERR: . {
System.out.println("Invalid char: " + getText());
lexicalErrors++;
} -> channel(HIDDEN);

// Comment rule. Dosen't use maximal match.
COMMENT: '/*' .*? . '*/' -> channel(HIDDEN);