-- Unconditional Exits in Lua
-- Unlabeled Exits in Lua
print("----------------Unlabeled Exits----------------")
for i = 1, 6
do
    if i == 4 then
        print("Unlabeled break is used to break the loop when the value is:", i)
        print("The remaining values 5 and 6 will not be printed.")
        break
    end
    print("Loop value:", i)
end
print("---------------------------------------------------")

-- Labeled Exits in Lua
print("-------------------Labeled Exits-------------------")
for i = 1, 3
do
    print("Outer value:", i)
    for i2 = 1, 6
    do
        if i == 1 then
            print(" Labeled continue is used, skipped outer loop iteration value:", i)
            -- This works for lua >= 5.2 (goto introduced here)
            goto continueOuter
        elseif i == 2 and i2 == 3 then
            print(" Labeled break is used to terminate current inner loop iteration")
            goto breakInner
        elseif i2 == 3 then 
            print(" Labeled continue is used, skipped inner loop iteration value:", i2)
            goto continueInner
        elseif i2 == 5 then
            print(" Labeled break is used to terminate current outer loop iteration")
            goto breakOuter
        end
        print(" Inner value:", i2)
        ::continueInner::
    end
    ::breakInner::
    ::continueOuter::
end
::breakOuter::
print("Outer Loop is successfully broken")
print("---------------------------------------------------")