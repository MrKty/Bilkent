foo(var key, var value) {
  print("Key: ${key}, Value: ${value}");
}

void main() {
  print("--------------------------- 1 ---------------------------");
  // Map<String, String> ListFinalAllInfos = {'stackoverflow': 'one', 'google': 'two'};
  var cs315Instructors = {
    "Section 1": "Altay Güvenir",
    "Section 2": "Aynur Dayanik",
    "Section 3": "Karani Kardas"
  };
  print("Initial CS 315 Instructors: ${cs315Instructors}");
  print("--------------------------- 1 ---------------------------");

  print("--------------------------- 2 ---------------------------");
  print(cs315Instructors["Section 3"]);
  print("--------------------------- 2 ---------------------------");

  print("--------------------------- 3 ---------------------------");
  cs315Instructors["Section 4"] = "Not Available";
  print(cs315Instructors);
  print("--------------------------- 3 ---------------------------");

  
  print("--------------------------- 4 ---------------------------");
  print(cs315Instructors.remove("Section 4"));
  print(cs315Instructors);
  print("--------------------------- 4 ---------------------------");
  
  print("--------------------------- 5 ---------------------------");
  cs315Instructors["Section 1"] = "Halil Altay Güvenir";
  print(cs315Instructors);
  print("--------------------------- 5 ---------------------------");
  
  print("--------------------------- 6 ---------------------------");
  print(cs315Instructors.containsKey("Section 2"));
  print(cs315Instructors.containsKey("Section 4"));
  print("--------------------------- 6 ---------------------------");

  print("--------------------------- 7 ---------------------------");
  print(cs315Instructors.containsValue("Karani Kardas"));
  print(cs315Instructors.containsValue("Karani"));
  print("--------------------------- 7 ---------------------------");

  print("--------------------------- 8 ---------------------------");
  cs315Instructors.forEach((k, v) => foo(k, v));
  print("--------------------------- 8 ---------------------------");
}