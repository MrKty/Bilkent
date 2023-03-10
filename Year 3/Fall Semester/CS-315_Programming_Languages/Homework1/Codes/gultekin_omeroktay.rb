def foo(k, v)
  puts format('%s : %s', k, v)
end

print "--------------------------- 1 ---------------------------", "\n"
cs315_instructors = {
  'Section 1' => 'Altay Güvenir',
  'Section 2' => 'Aynur Dayanik',
  'Section 3' => 'Karani Kardas'
}

print cs315_instructors, "\n"
print "--------------------------- 1 ---------------------------", "\n"

print "--------------------------- 2 ---------------------------", "\n"
print cs315_instructors['Section 3'], "\n"
print "--------------------------- 2 ---------------------------", "\n"

print "--------------------------- 3 ---------------------------", "\n"
cs315_instructors['Section 4'] = 'Not Available'
print cs315_instructors, "\n"
print "--------------------------- 3 ---------------------------", "\n"

print "--------------------------- 4 ---------------------------", "\n"
puts cs315_instructors.delete('Section 4')
print "#{cs315_instructors}\n"
print "--------------------------- 4 ---------------------------", "\n"

print "--------------------------- 5 ---------------------------", "\n"
cs315_instructors['Section 1'] = 'Halil Altay Güvenir'
print "#{cs315_instructors}\n"
print "--------------------------- 5 ---------------------------", "\n"

print "--------------------------- 6 ---------------------------", "\n"
print cs315_instructors.has_key?('Section 1'), "\n"
print "--------------------------- 6 ---------------------------", "\n"

print "--------------------------- 7 ---------------------------", "\n"
print cs315_instructors.has_value?('Karani Kardas'), "\n"
print "--------------------------- 7 ---------------------------", "\n"

print "--------------------------- 8 ---------------------------", "\n"
cs315_instructors.each_pair { |k, v| foo(k, v) }
print "--------------------------- 8 ---------------------------", "\n"
