# Unconditional Exits in Python
# Unlabeled Exits in Python
print("----------------Unlabeled Exits----------------")
for i in range(1, 7): # 1 inclusive 7 exclusive 
  if i == 3:
    print(f"Unlabeled continue is used to pass the current iteration when the value is {i}")
    continue
  elif i == 5:
    print(f"Unlabeled break is used to break the loop when the value is: {i}")
    print("The remaining value 6 will not be printed.")
    break
    print(f"Loop value: {i}")
print("---------------------------------------------------")

# Labeled Exits in Python
# No labeled exits available in python but unique simulation way exists
# using for ... else ... block in which else block only runs the corresponding
# for statements terminates normally without break. Otherwise, the remaining code
# will be runs that breaks the outer loop.
print("-------------------Labeled Exits-------------------")
for i in range(1, 4):
  print(f"Outer value: {i}")
  for i2 in range(1, 7):
    if i2 == 5:
      print(f" Unlabeled break is used to terminate current inner loop iteration when the inner value is {i2}")
      break
    print(f" Inner value: {i2}")
  else:
      continue  
  print("Inner Break triggers termination of the current outer loop iteration")
  break

print("Outer Loop is successfully broken.")
print("---------------------------------------------------")