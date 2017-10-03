grammar projet;

p :
	(var "(" e ":" type ")")*
	d*
	i;

d :
	function"("( e ":" type)*")"
	var "(" e ":" type ")")*
	i;

i :
	e "=" e
	|e"["e"] =" e
	|" if " e " then " e " else " e
	|"while" e "do" i
	|phi"("e*")"
	|skip
	|i";"i;

e :
	k
	|x
	|uop e
	|e bop e
	|phi"("e*")"
	|e"["e"]"
	|"new array of" type "["e"]";

ca :
	"read"
	|"write"
	|function;

bop :
	"+"|"-"|"x"|"/"
	|"and"|"or"
	|"<"|"<="|"="|">="|">";

uop :
	"-"
	|"not"

k returns [int value]:
	n = number {$value = Integer.parseInt($n.text);}
	|"true"
	|"false";

type :
	"integer"
	|"boolean"
	|"array of " type;

number : ('0'..'9')+
WS : [ \t\r\n]+ -> skip ;
