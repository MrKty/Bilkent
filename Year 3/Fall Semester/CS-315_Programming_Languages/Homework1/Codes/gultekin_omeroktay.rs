use std::collections::HashMap;

fn foo(k: &str, v: &str) {
    println!("{k}: {v}")
}

fn main() {
    println!("--------------------------- 1 ---------------------------");
    let mut cs315_instructors = HashMap::from([
        ("Section 1", "Altay Güvenir"),
        ("Section 2", "Aynur Dayanik"),
        ("Section 3", "Karani Kardas"),
    ]);

    // Error println!("{}", cs315_instructors);
    println!("{:?}", cs315_instructors);
    println!("--------------------------- 1 ---------------------------");

    println!("--------------------------- 2 ---------------------------");
    println!("{}", cs315_instructors["Section 3"]);
    println!("--------------------------- 2 ---------------------------");

    println!("--------------------------- 3 ---------------------------");
    cs315_instructors.insert("Section 4", "Not Available");

    println!("{:?}", cs315_instructors);
    println!("--------------------------- 3 ---------------------------");

    println!("--------------------------- 4 ---------------------------");
    cs315_instructors.remove("Section 4");

    println!("{:?}", cs315_instructors);
    println!("--------------------------- 4 ---------------------------");

    println!("--------------------------- 5 ---------------------------");
    cs315_instructors.insert("Section 1", "Halil Altay Güvenir");
    println!("{:?}", cs315_instructors);
    println!("--------------------------- 5 ---------------------------");

    println!("--------------------------- 6 ---------------------------");
    println!("{}", cs315_instructors.contains_key("Section 1"));
    println!("--------------------------- 6 ---------------------------");

    println!("--------------------------- 7 ---------------------------");
    println!( "{}", cs315_instructors.values() .any(|&val| val == "Karani Kardas"));
    println!("--------------------------- 7 ---------------------------");

    println!("--------------------------- 8 ---------------------------");
    for (key, value) in &cs315_instructors {
        foo(key, value);
    }
    println!("--------------------------- 8 ---------------------------");
}