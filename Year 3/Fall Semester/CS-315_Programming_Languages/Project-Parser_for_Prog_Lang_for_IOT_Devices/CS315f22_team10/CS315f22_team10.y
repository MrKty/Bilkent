/* if there is an error prints line number of error, else prints ok */
%token END_OF_STMT ASSIGNMENT_OP ADDITION_OP SUBTRACTION_OP MULTIPLICATION_OP DIVISION_OP LP
%token RP DOT COMMENT LB RB COMMA LESS_EQUAL_OP LESS_THAN_OP GREATER_EQUAL_OP GREATER_THAN_OP
%token NOT_EQUALS_OP EQUALS_OP OR_OP AND_OP DIGIT INTEGER FLOAT STRING BOOL IDENTIFIER
%token PRIMITIVE_VARIABLE_TYPE NUM_VARIABLE_TYPE CONNECTION FUNCTION RETURN INPUT PRINT
%token FOR WHILE IF ELSE CONNECT_URL SWITCH_AND_ACTUATOR GET_TIMESTAMP READ_TEMP READ_HUMIDITY
%token READ_AIR_PRESSURE READ_AIR_QUALITY READ_LIGHT_LEVEL READ_SOUND_LEVEL GET_DATA_FROM_CONN
%token SEND_DATA_BY_CONN
%%
Tutku:		stmts { if(errorNum == 0) {printf("Program is valid\n"); return 0; }};


stmts:		null
 			| stmts stmt
			| stmts error {yyerror("syntax error "); yyclearin;};

stmt: 		safe_stmt | unsafe_stmt;

safe_stmt:	terminated_stmt terminate_op
			| non_terminated_stmt;

unsafe_stmt:	fun_def_stmt;

terminated_stmt:	assign_stmt
			| declare_stmt
			| fun_call
			| io_stmt;

non_terminated_stmt:	comment_stmt
			| cond_stmt
			| loop_stmt;

safe_stmts:	null
			| safe_stmts safe_stmt;

assign_stmt:	declare_stmt assign_op assignable
			| var_name assign_op assignable;

assignable:	expression
			| string_literal
			| fun_call;

expression:	  arithmetic_expression
			| bool_expression
			| lb arithmetic_expression rb
			| lb bool_expression rb;

arithmetic_expression:	arithmetic_expression add_op term_var_name
            | arithmetic_expression sub_op term_var_name
            | funct_var_name add_op term_var_name
            | funct_var_name sub_op term_var_name
            | term;

funct_var_name: var_name | fun_call | string_literal;

term_var_name: var_name | string_literal | term | fun_call;

term:		term higher_op term_type
			| type_needed_op higher_op term_type
			| term higher_op type_needed_op
			| type_needed_op higher_op type_needed_op
			| term_type;

higher_op:	mult_op | div_op;

type_needed_op: fun_call | var_name;

term_type:	num_literal
			| lp arithmetic_expression rp
			| input_stmt;

bool_expression:	logical_expression;

relational_expression:	relational_expression rel_op num_data
						| num_data rel_op num_data;

rel_op:		less_equal_op | less_than_op | greater_equal_op 
			| greater_than_op | equals_op | not_equals_op;

num_data:	arithmetic_expression | var_name | fun_call;

logical_expression:	logical_expression or_op logic_term
			| logical_expression or_op fun_call
			| fun_call or_op logic_term
			| logic_term;

logic_term:		logic_term and_op bool_term | logic_term and_op fun_call | fun_call and_op bool_term | bool_term;

bool_term:	bool_data | lp bool_data rp | lp fun_call rp;
bool_data:		relational_expression
			| bool_literal
			| var_name;

fun_def_stmt:		def_keyword fun_name lp param_list rp lb safe_stmts fun_return rb;

fun_name:		var_name;

fun_return:		null
			| return_keyword returnable terminate_op

returnable:	expression | fun_call | string_literal;

param_list:		null
			| var_type var_name params;

params:			null
			| separator var_type var_name params;

fun_call:	fun_name lp arg_list rp
			| read_sensor
			| read_timer
			| connect_url
			| get_data_from_conn
			| send_data_by_conn
			| change_switch_state_and_control_actuator;

arg_list:	null
			| callable args;

callable:		returnable;

args:			null
			| separator callable args;

read_sensor:		readTemp lp rp
			| readHumidity lp rp
			| readAirPressure lp rp
			| readAirQuality lp rp
			| readLightLevel lp rp
			| readSoundLevel lp rp;

read_timer:		getTimestamp lp rp;

connect_url:		connectURL lp string_literal rp
			| connectURL lp var_name rp;

change_switch_state_and_control_actuator:
			changeSwitchStateAndControlActuator lp switch_num rp;

switch_num:		digit | var_name | fun_call;

get_data_from_conn:	conn dot getDataFromConn lp rp;

send_data_by_conn: conn dot sendDataByConn lp callable rp;

conn:		connect_url
			|conn_name;

conn_name:		var_name;

io_stmt:		input_stmt
			| output_stmt;

input_stmt:		inp_keyword lp rp;

output_stmt:	out_keyword lp callable rp
				| out_keyword lp rp;

loop_stmt:		while_stmt | for_stmt;

while_stmt:		while lp bool_expression rp lb safe_stmts rb
			| while lp bool_expression rp safe_stmt;

for_base:		for lp assign_and_null terminate_op bool_and_null terminate_op arithmetic_and_null rp;

assign_and_null:	assign_stmt | null;

bool_and_null:		logical_expression | null;

arithmetic_and_null:	var_name assign_op arithmetic_expression 
						| null;

for_stmt:		for_base lb safe_stmts rb
			| for_base safe_stmt;

if_stmt:		if_base lb safe_stmts rb;

cond_stmt:		if_stmt | if_stmt else lb safe_stmts rb
				| if_stmt else safe_stmt;

if_base:		if lp bool_expression rp
				| if lp fun_call rp;


declare_stmt:		var_type var_name;


getTimestamp:		GET_TIMESTAMP;
terminate_op:		END_OF_STMT;
assign_op:		ASSIGNMENT_OP;
add_op:			ADDITION_OP;
sub_op:			SUBTRACTION_OP;
mult_op:		MULTIPLICATION_OP;
div_op:			DIVISION_OP;
lp:			LP;
rp:			RP;
dot:			DOT;
comment_stmt:		COMMENT;
lb:			LB;
rb:			RB;
separator:		COMMA;
less_equal_op:		LESS_EQUAL_OP;
less_than_op:		LESS_THAN_OP;
greater_equal_op:	GREATER_EQUAL_OP;
greater_than_op:	GREATER_THAN_OP;
not_equals_op:		NOT_EQUALS_OP;
equals_op:		EQUALS_OP;
or_op:			OR_OP;
and_op:			AND_OP;
digit:			DIGIT;
num_literal:		int_literal | float_literal;
int_literal:		INTEGER | DIGIT;
float_literal:		FLOAT;
string_literal:		STRING;
bool_literal:		BOOL;
var_name:		IDENTIFIER;
var_type:		primitive_var_type | num_var_type | conn_type;
primitive_var_type:	PRIMITIVE_VARIABLE_TYPE;
num_var_type:		NUM_VARIABLE_TYPE;
conn_type:		CONNECTION;
def_keyword:		FUNCTION;
return_keyword:		RETURN;
inp_keyword:		INPUT;
out_keyword:		PRINT;
for:			FOR;
while:			WHILE;
if:			IF;
else:			ELSE;
connectURL:		CONNECT_URL;		
changeSwitchStateAndControlActuator:
			SWITCH_AND_ACTUATOR;
readTemp:		READ_TEMP;
readHumidity:		READ_HUMIDITY;
readAirPressure:	READ_AIR_PRESSURE;
readAirQuality:		READ_AIR_QUALITY;
readLightLevel:		READ_LIGHT_LEVEL;
readSoundLevel:		READ_SOUND_LEVEL;
getDataFromConn:	GET_DATA_FROM_CONN;
sendDataByConn:		SEND_DATA_BY_CONN;
null: ;

%%
#include "lex.yy.c"
int lineno=0;
int errorNum = 0;
int errLine = -1;
void yyerror(char *s) { if(errLine != lineno) {
						printf("%s at line %d\n", s, lineno); errorNum += 1; errLine = lineno;
					  }}
int main() {
 return yyparse();
}