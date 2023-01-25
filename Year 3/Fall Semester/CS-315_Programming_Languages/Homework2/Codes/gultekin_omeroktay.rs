fn main() {
    // Unconditional Exits in Rust
    // Unlabeled Exits in Rust
    println!("----------------Unlabeled Exits----------------");
    for i in 1..7 { // 1..7 => 1 inclusive 7 exclusive
        if i == 3 {
            println!("Unlabeled continue is used to pass the current iteration when the value is {}", i);
            continue;
        } else if i == 5 {
            println!("Unlabeled break is used to break the loop when the value is: {}", i);
            println!("The remaining value 6 will not be printed.");
            break;
        }
        println!("Loop value: {}", i);
    }
    println!("---------------------------------------------------");

    // Labeled Exits in Rust
    println!("-------------------Labeled Exits-------------------");
    'outer: for i in 1..4 {
        println!("Outer value: {}", i);
        'inner: for i2 in 1..7 {
            if i == 1 {
                println!(" Labeled continue is used, skipped outer loop iteration value: {}", i);
                continue 'outer;
            } else if  i == 2 && i2 == 3 {
                println!(" Labeled break is used to terminate current inner loop iteration");
                break 'inner;
            } else if i2 == 3 {
                println!(" Labeled continue is used, skipped inner loop iteration value: {}", i2);
                continue 'inner;
            } else if i2 == 5 {
                println!(" Labeled break is used to terminate current outer loop iteration");
                break 'outer;
            }
        println!(" Inner value: {}", i2);
        }
    }
    println!("Outer Loop is successfully broken");
    println!("---------------------------------------------------");
}