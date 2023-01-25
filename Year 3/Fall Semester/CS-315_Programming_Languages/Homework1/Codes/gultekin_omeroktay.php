<?php
function foo($k, $v) {
  print "Key: $k Value: $v \n";
}

print "--------------------------- 1 ---------------------------" . "\n";
// as of PHP 5.4
$cs315Instructors = [
    "Section 1" => "Altay Güvenir",
    "Section 2" => "Aynur Dayanik",
    "Section 3" => "Karani Kardas"
];


// $search_array = array('first' => 1, 'second' => 4);
print_r($cs315Instructors);
print "--------------------------- 1 ---------------------------" . "\n";

print "--------------------------- 2 ---------------------------" . "\n";
print "Instructor of " . "Section 3 is ". $cs315Instructors["Section 3"] . "\n";
print "--------------------------- 2 ---------------------------" . "\n";

print "--------------------------- 3 ---------------------------" . "\n";
$cs315Instructors["Section 4"] = "Not Available";
print_r($cs315Instructors);
print "--------------------------- 3 ---------------------------" . "\n";

print "--------------------------- 4 ---------------------------" . "\n";
unset($cs315Instructors["Section 4"]);
print_r($cs315Instructors);
print "--------------------------- 4 ---------------------------" . "\n";

print "--------------------------- 5 ---------------------------" . "\n";
$cs315Instructors["Section 1"] = "Halil Altay Güvenir";
print_r($cs315Instructors);
print "--------------------------- 5 ---------------------------" . "\n";

print "--------------------------- 6 ---------------------------" . "\n";
print array_key_exists("Section", $cs315Instructors) . "\n";
print array_key_exists("Section 2", $cs315Instructors) . "\n";
print isset($cs315Instructors["Section 2"]) . "\n";
print "--------------------------- 6 ---------------------------" . "\n";

print "--------------------------- 7 ---------------------------" . "\n";
print (array_search("Karani Kardas", $cs315Instructors) == True) . "\n";
print "--------------------------- 7 ---------------------------" . "\n";

print "--------------------------- 8 ---------------------------" . "\n";
foreach($cs315Instructors as $k => $v) {
  foo($k, $v);
}
print "--------------------------- 8 ---------------------------" . "\n";
