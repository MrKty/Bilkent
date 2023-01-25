//
// Omer Oktay Gultekin 21901413
//

#include <sstream>
#include "AlgebraicExpression.h"


int main() {

    cout << infix2postfix("210 * 5 + 100 / 10 - 20") << endl; // 210 5 * 100 10 / + 20 -
    cout << postfix2prefix("210 5 * 100 10 / + 20 -") << endl; // - + * 210 5 / 100 10 20
    cout << evaluateInfix("210 * 5 + 100 / 10 - 20") << endl; // 1040
    cout << endl;

    cout << evaluateInfix("210 * ( 5 + 100 ) / 10 - 20") << endl; // 2185
    cout << endl;

    cout << infix2postfix("78 + ( 30 - 0.5 * ( 28 + 8 ) ) / 6") << endl; // 78 30 0.5 28 8 + * - 6 / +
    cout << infix2postfix("2 * ( 1 + ( 4 * ( 2 + 1 ) + 3 ) )") << endl; // 2 1 4 2 1 + * 3 + + *
    cout << endl;

    cout << infix2prefix("78 + ( 30 - 0.5 * ( 28 + 8 ) ) / 6") << endl; // + 78 / - 30 * 0.5 + 28 8 6
    cout << infix2prefix("2 * ( 1 + ( 4 * ( 2 + 1 ) + 3 ) )") << endl; // * 2 + 1 + * 4 + 2 1 3
    cout << endl;

    cout << prefix2infix("+ 78 / - 30 * 0.5 + 28 8 6") << endl; // (78 + ((30 - (0.5 * (28 + 8))) / 6))
    cout << prefix2infix("* 2 + 1 + * 4 + 2 1 3") << endl; // (2 * (1 + ((4 * (2 + 1)) + 3)))
    cout << endl;

    cout << prefix2postfix("+ 78 / - 30 * 0.5 + 28 8 6") << endl; // 78 30 0.5 28 8 + * - 6 / +
    cout << prefix2postfix("* 2 + 1 + * 4 + 2 1 3") << endl; // 2 1 4 2 1 + * 3 + + *
    cout << endl;

    cout << postfix2prefix("78 30 0.5 28 8 + * - 6 / +") << endl; // + 78 / - 30 * 0.5 + 28 8 6
    cout << postfix2prefix("2 1 4 2 1 + * 3 + + *") << endl; // * 2 + 1 + * 4 + 2 1 3
    cout << endl;

    cout << postfix2infix("78 30 0.5 28 8 + * - 6 / +") << endl; // (78 + ((30 - (0.5 * (28 + 8))) / 6))
    cout << postfix2infix("2 1 4 2 1 + * 3 + + *") << endl; // (2 * (1 + ((4 * (2 + 1)) + 3)))
    cout << endl;

    cout << evaluateInfix("78 + ( 30 - 0.5 * ( 28 + 8 ) ) / 6") << endl; // 80
    cout << evaluateInfix("2 * ( 1 + ( 4 * ( 2 + 1 ) + 3 ) )") << endl; // 32
    cout << endl;

    cout << evaluatePrefix("+ 78 / - 30 * 0.5 + 28 8 6") << endl; // 80
    cout << evaluatePrefix("* 2 + 1 + * 4 + 2 1 3") << endl; // 32
    cout << endl;

    cout << evaluatePostfix("78 30 0.5 28 8 + * - 6 / +") << endl; // 80
    cout << evaluatePostfix("2 1 4 2 1 + * 3 + + *") << endl; // 32
    cout << endl;

    cout << infix2prefix("( 12 + 5 ) - 20 * 4") << endl; // - + 12 5 * 20 4
    cout << endl;

    cout << infix2postfix("( 12 + 5 ) - 20 * 4") << endl; // 12 5 + 20 4 * -
    cout << infix2postfix("( a - (b + c * d) / e ") << endl; // a b c d * + e / -
    cout << infix2postfix("( ( 100 + ( ( 12 - 2 ) * 8 ) ) / 4 )") << endl; // 100 12 2 - 8 * + 4 /
    cout << endl;

    cout << evaluatePrefix("* + * 100 2 4 - 12 4") << endl; // 1632
    cout << evaluatePrefix("+ 9 * 2 6") << endl; // 21
    cout << endl;

    cout << evaluateInfix("( 12 + 5 ) - 20 * 4") << endl; // -63
    cout << endl;

    cout << prefix2infix("* + * 100 2 4 - 12 4") << endl; // ( ( ( 100 * 2 ) + 4 ) * ( 12 - 4 ) )
    cout << endl;

    cout << postfix2infix("100 12 2 - 8 * + 4 /") << endl; // ( ( 100 + ( ( 12 - 2 ) * 8 ) ) / 4 )
    cout << postfix2infix("a b c + +") << endl; // ( a + ( b + c ) )
    cout << postfix2infix("a b * c +") << endl; // ( ( a * b ) + c )
    cout << endl;

    cout << postfix2prefix("100 12 2 - 8 * + 4 /") << endl; // / + 100 * - 12 2 8 4
    cout << postfix2prefix("A B + C D - *") << endl; // * + A B - C D
    cout << postfix2prefix("A B C / - A K / L - *") << endl; // * - A / B C - / A K L
    cout << endl;

    cout << evaluatePostfix("100 12 2 - 8 * + 4 /") << endl; // 45
    cout << endl;

    cout << prefix2postfix("* + * 100 2 4 - 12 4") << endl; // 100 2 * 4 + 12 4 - *
    cout << prefix2postfix("* + A B - C D") << endl; // A B + C D - *
    cout << prefix2postfix("* - A / B C - / A K L") << endl; // A B C / - A K / L - *
    cout << endl;

    return 0;

}

