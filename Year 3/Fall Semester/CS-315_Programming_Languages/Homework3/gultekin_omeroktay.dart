class PrimitiveWrapper {
  var primitive;
  PrimitiveWrapper(this.primitive);
}

void change(PrimitiveWrapper data) {
  data.primitive++;
}

void main() {
  // --------------------Part 1 Nested subprogram definitions-----------------
  print(
      "--------------------Part 1 Nested subprogram definitions-----------------");
  void outerFun(String param) {
    print(param + " (outer) parameter accessible in outer fun");
    print("");

    // Subprogram 1
    void innerFun(String y) {
      int x = 10;
      print(
          "Accessible Parameters in Inner Fun: $param (outside parameter), $y (inner parameter) $x (inner local param)");
      print("");

      // Subprogram 2
      void innerInnerFun(var z) {
        print(
            "Accessible Parameters in Inner Inner Fun: $param (outside parameter), $y (inner parameter) $z (inner of inner param)");
        print("");
      }

      innerInnerFun(15);
    }

    // Not Accessible Here
    //innerInnerFun(10);
    innerFun("Karani");
  }
  // Not Accessible Here
  //innerInnerFun(10);
  //innerFun("Karani");

  outerFun("Adayanik");
  print("-------------------- End Of Part 1 -----------------------------");
  print("");
  // ------------------------- End Of Part 1 --------------------------------------

  // -------------------- Part 2 Scope of local variables -----------------
  print(
      "-------------------- Part 2 Scope of local variables -----------------");
  print("");

  void outerFun2(String param1, String param2) {
    String sec3 = param1;
    String sec2 = param2;

    print(
        "Outer Fun local variables sec3=$sec3 and sec2=$sec2 accessible in outer fun but inner fun variable sec1 not accesible (Compiler error)");
    print("");

    // Since above inner fun is not in here, declaration with the same name is allowed
    void innerFun(String param3) {
      String sec1 = param3;

      print(
          "Outer Fun local variables sec3=$sec3 and sec2=$sec2 as well as inner fun variable sec1=$sec1 is accesible in inner fun");
      print("");
    }

    innerFun("Halil Altay");

    //print("Sec1 lecturer=$sec1") -- compile error because of illegal statement since sec1 is not in this referencing environment
  }

  // Testing outer fun
  outerFun2("Karani", "Aynur");

  print("-------------------- End Of Part 2 -----------------");
  print("");

  // ---------------------------- End Of Part 2 --------------------------------------

  //-------------------- Part 3 Parameter Passing Methods -----------------

  print(
      "-------------------- Part 3 Parameter Passing Methods -----------------");
  print("");

  //Dart is, in default, supporting pass-by-value.

  String sec1 = "Altay Güvenir";

  void part3Fun(String param) {
    param = "Halil Altay Güvenir";
    print("Sec1 value inside method call: " + param);
    print("");
  }

  // Primitive types are passed by pass-by-value
  print("Sec1 value before method call: " + sec1);
  print("");

  part3Fun(sec1);
  print("Sec1 value after method call: " + sec1);
  print("");

  // Primitive types can be passed into wrappers to simulate pass-by-reference
  var sec1Attendance = new PrimitiveWrapper(30);
  print("Wrapper value before method: ${sec1Attendance.primitive}"); // 30
  print("");

  change(sec1Attendance);
  print("Wrapper value after method: ${sec1Attendance.primitive}"); // 31
  print("");

  print("-------------------- End Of Part 3 -----------------");
  print("");

  // ---------------------------- End Of Part 3 --------------------------------------

  //-------------------- Part 4: Keyword and Default Parameters. -----------------

  print(
      "-------------------- Part 4: Keyword and Default Parameters. -----------------");
  print("");

  //Optional parameters can be named (keyword) and positional parameters in dart

  void requiredParamMethod(String sec1, String sec2) {
    // sec1 and sec2 are required parameters and can not have default values, caller should always give values in method call, otherwise there will be compilation error.
    print("Required parameters sec1=$sec1 and sec2=$sec2 are printed");
    print("");
  }

  requiredParamMethod("Altay", "Aynur");
  // requiredParamMethod("Aynur"); error

  // Optional Positional Parameters Syntax
  // Here sec2 has a default value which can

  void requiredAndPositionalParamMethod(String sec1,
      [String sec2 = "Aynur", String sec3 = "Karani"]) {
    print(
        "Required parameter sec1=$sec1 and Positional Parameters sec2=$sec2 and sec3=$sec3 are printed");
    print("");
  }

  requiredAndPositionalParamMethod(
      "Altay", "Fazlı"); // Fazlı hoca is sec2 lecturer now

  requiredAndPositionalParamMethod(
      "Altay"); // Sec2 and Sec3 uses their default values

  requiredAndPositionalParamMethod("Altay", "Selim",
      "Özcan"); // Sec2 and Sec3 lecturers are changed (One cannot change sec3 lecturer without changing sec2 lecturer in positional parameters)

  // Optional Named (Keyword) Parameters Syntax

  void requiredAndKeywordParamMethod(String sec1,
      {String sec2 = "Aynur", String sec3 = "Karani"}) {
    print(
        "Required parameter sec1=$sec1 and Keyword Parameters sec2=$sec2 and sec3=$sec3 are printed");
    print("");
  }

  requiredAndKeywordParamMethod(
      "Altay"); // Sec2 and Sec3 uses default lecturers

  requiredAndKeywordParamMethod("Altay",
      sec3: "Özcan",
      sec2:
          "Fazlı"); // Order of the parameters is not important for keyword parameters

  requiredAndKeywordParamMethod("Altay",
      sec3:
          "Özcan"); // Caller does not have to pass all keyword parameters before sec3 as opposed to positional parameters (i.e parameter passing is not restricted to positions )

  // Not allowed (required params must be before keyword params)
  // void requiredAndKeywordParam2Method({String sec2 = "Aynur", String sec3 = "Karani"}, String sec1) {}

  // Not allowed (required params must be before positional params)
  // void requiredAndKeywordParam2Method([String sec2 = "Aynur", String sec3 = "Karani"], String sec1) {}

  print("-------------------- End Of Part 4 -----------------");
  print("");

  //-------------------- Part 5: Closures -----------------
  print("-------------------- Part 5: Closures -----------------");
  print("");

  // Closure Enables Dart to
  // 1) Declare anonymous functions
  (String sec1) {
    print("Sec1 lecturer: $sec1");
    print("");
  };

  // 2) Use Anonymous functions directly
  (String sec2) {
    print("Sec2 lecturer: $sec2");
    print("");
  }("Aynur");

  // 3) Assign alias variable to function
  var printLecturers = (var sec1, var sec2, var sec3) {
    print("sec1: $sec1, sec2: $sec2, sec3: $sec3");
    print("");
  };

  printLecturers("Altay", "Aynur", "Karani");

  // 4) Arrow Syntax Function Definitions
  var difference = (var bigNum, var smallNum) => bigNum - smallNum;

  print("Diff between 3 and 2 is ${difference(3, 2)}");
  print("");

  // 5) Use Functions in Parameter
  void calculateGPA(int grade, String getLetterGrade(int grade)) {
    print("Letter Grade= ${getLetterGrade(grade)}");
    print("");
  }

  var getGermanLetterGrade = (int grade) {
    return grade > 95 ? "A" : "Other";
  };

  var getMathLetterGrade = (int grade) {
    return grade > 85 ? "A" : "Other";
  };

  print("");
  calculateGPA(90, getGermanLetterGrade);
  calculateGPA(90, getMathLetterGrade);
  print("");

  // 6) Using outer function local variables even after they should be not accessible anymore

  // Normal subprogram definitions (number are not accesible in main scope (only the state of the number is accessible))
  int subtract() {
    var number = 10;
    void minus() {
      number--;
    }

    minus();
    return number;
  }

  print("");
  print("Normal function print values");
  print(subtract());
  print(subtract());
  print(subtract());
  print("----------------------------");
  print("");

  // Closure subprogram definition (key point is returning the function)

  Function() subtractClosure() {
    var number = 10;
    return () => number--;
  }

  var subtractor = subtractClosure();

  print("");
  print("Closure print values");
  print(subtractor());
  print(subtractor());
  print(subtractor());
  print("----------------------------");
  print("");

  print("-------------------- End Of Part 5 -----------------");
  // -------------------- End Of Part 5 -----------------
}
