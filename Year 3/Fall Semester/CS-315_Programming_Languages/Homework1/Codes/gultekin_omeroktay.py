print("--------------------------- 1 ---------------------------")
cs315_instructors = {
    "Section 1": "Altay Güvenir",
    "Section 2": "Aynur Dayanik",
    "Section 3": "Karani Kardas"
}

print("Initial CS 315 Instructors:", cs315_instructors)
print("--------------------------- 1 ---------------------------")
print()

print("--------------------------- 2 ---------------------------")
print("Instructor of Section 3 is {}".format(cs315_instructors["Section 3"]))
print("--------------------------- 2 ---------------------------")
print()

print("--------------------------- 3 ---------------------------")
cs315_instructors["Section 4"] = "Not Available"
print("CS 315 Instructors After Addition: {}".format(cs315_instructors))
print("--------------------------- 3 ---------------------------")
print()

print("--------------------------- 4 ---------------------------")
# print(del cs315_instructors["Section 4"])
del cs315_instructors["Section 4"]
print("CS 315 Instructors After Deletion:", cs315_instructors)
print("--------------------------- 4 ---------------------------")
print()

print("--------------------------- 5 ---------------------------")
cs315_instructors["Section 1"] = "Halil Altay Güvenir"
print("CS 315 Instructors After Modification:", cs315_instructors)
print("--------------------------- 5 ---------------------------")
print()

print("--------------------------- 6 ---------------------------")
print("Is Section 2 in CS 315 Sections:", "Section 2"
      in cs315_instructors.keys())
print("Is Section 1 in Cs 315 Sections:", "Section 1"
      in cs315_instructors)  # Also Valid
print("--------------------------- 6 ---------------------------")

print("--------------------------- 7 ---------------------------")
print("Is Karani Kardas one of instructors:", "Karani Kardas"
      in cs315_instructors.values())
print("--------------------------- 7 ---------------------------")

print()
print("--------------------------- 8 ---------------------------")
def foo(key, value):
    print(f"Key: {key}, Value: {value}")


print("Looping through associative array and printing key value pair:")
for item in cs315_instructors.items():
    foo(*item)

print()
print("Looping through associative array and printing key value pair (Second Way):")
for item in cs315_instructors.items():
    foo(item[0], item[1])

print()
print("Looping through associative array and printing key value pair (Third Way):")
for key, value in cs315_instructors.items():
    foo(key, value)

# Not work as expected, cs315_instructors returns only keys
# for key, value in cs315_instructors:
#    foo(key, value)
print("--------------------------- 8 ---------------------------")