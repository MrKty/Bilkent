<?php
    // Unconditional Exits in PHP
    // Unlabeled Exits in PHP
    echo("----------------Unlabeled Exits----------------\n");
    for($i = 1; $i <= 6; $i++) {
        if ($i == 3) {
            echo("Unlabeled continue is used to pass the current iteration when the value is $i\n");
            continue;
        } else if ($i == 5) {
            echo("Unlabeled break is used to break the loop when the value is: $i\n");
            echo("The remaining value 6 will not be printed.\n");
            break;
        }
        echo("Loop value: $i\n");
    }
    echo("---------------------------------------------------\n");

    // Labeled Exits in PHP
    echo("-------------------Labeled Exits-------------------\n");
    for($i = 1; $i <= 3; $i++) {
        echo("Outer value: $i\n");
        for($i2 = 1; $i2 <= 6; $i2++) {
            if($i == 1) {
                echo(" Labeled continue is used, skipped outer loop iteration value: $i\n");
                continue 2; // continue from 2 previous loop (imagine like a tree)
            } else if($i == 2 && $i2 == 3) {
                echo(" Labeled break is used to terminate current inner loop iteration.\n");
                break 1; // break current loop (default behaviour of using break alone)
            } else if($i2 == 3) {
                echo(" Labeled continue is used, skipped inner loop iteration value: $i2\n");
                continue 1; // default behaviour
            } else if($i2 == 5) {
                echo(" Labeled break is used to terminate current outer loop iteration\n");
                break 2;
            }
        echo(" Inner value: $i2\n");
        }
    }
    echo("Outer Loop is successfully broken\n");
    echo("---------------------------------------------------\n");
?>