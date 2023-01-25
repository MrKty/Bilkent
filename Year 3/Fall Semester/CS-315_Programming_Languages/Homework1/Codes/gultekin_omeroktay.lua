function foo(key, value)
    print(key, value)
end

function printDict(dict)
  print()
  for k, v in pairs(dict) do
    foo(k, v)
  end
  print()
end

function hasValue(dict, val)
    for k, v in pairs(dict) do
      if v == val then
            return true
      end
    end
    return false
end

print("--------------------------- 1 ---------------------------");
cs315_instructors = {
    ["Section 1"] = "Altay Güvenir",
    ["Section 2"] = "Aynur Dayanik",
    ["Section 3"] = "Karani Kardas",
    Section       = "Different way to create key"
}

print(cs315_instructors)
printDict(cs315_instructors)
print("--------------------------- 1 ---------------------------");

print("--------------------------- 2 ---------------------------");
print(cs315_instructors["Section 1"])
print("--------------------------- 2 ---------------------------");

print("--------------------------- 3 ---------------------------");
cs315_instructors["Section 4"] = "Not Available"

printDict(cs315_instructors)
print("--------------------------- 3 ---------------------------");

print("--------------------------- 4 ---------------------------");
cs315_instructors["Section 4"] = nil

printDict(cs315_instructors)
print("--------------------------- 4 ---------------------------");

print("--------------------------- 5 ---------------------------");
cs315_instructors["Section 1"] = "Halil Altay Güvenir"

printDict(cs315_instructors)
print("--------------------------- 5 ---------------------------");

print("--------------------------- 6 ---------------------------");
--[[
~= is negation operator in lua
--]]

print(cs315_instructors["Section 1"] ~= nil)
print("--------------------------- 6 ---------------------------");

print("--------------------------- 7 ---------------------------");
print(hasValue(cs315_instructors, "Karani Kardas"));
print("--------------------------- 7 ---------------------------");

print("--------------------------- 8 ---------------------------");
print()
for k, v in pairs(cs315_instructors) do
    foo(k, v)
end
print()

print("---------------------------  8 ---------------------------");