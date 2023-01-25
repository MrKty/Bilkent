void main() {
  // Unconditional Exits in Dart
  // Unlabeled Exits in Dart
  print("----------------Unlabeled Exits----------------");
  for(var i = 1; i <= 6; i++) {
    if (i == 3) {
      print("Unlabeled continue is used to pass the current iteration when the value is $i");
      continue;
    } else if (i == 5) {
      print("Unlabeled break is used to break the loop when the value is: $i");
      print("The remaining value 6 will not be printed.");
      break;
      }
      print("Loop value: $i");
    }
    print("---------------------------------------------------");

    // Labeled Exits in Dart
    print("-------------------Labeled Exits-------------------");
    Outer: for(var i = 1; i <= 3; i++) {
      print("Outer value: $i");
      Inner: for(var i2 = 1; i2 <= 6; i2++) {
            if(i == 1) {
              print(" Labeled continue is used, skipped outer loop iteration value: $i");
              continue Outer;
            } else if(i == 2 && i2 == 3) {
              print(" Labeled break is used to terminate current inner loop iteration.");
              break Inner;
            } else if(i2 == 3) {
              print(" Labeled continue is used, skipped inner loop iteration value: $i2");
              continue Inner;
            } else if(i2 == 5) {
              print(" Labeled break is used to terminate current outer loop iteration");
              break Outer;
            }
      print(" Inner value: $i2");
      }
    }
    print("Outer Loop is successfully broken.");
    print("---------------------------------------------------");
}