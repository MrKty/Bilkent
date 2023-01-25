# Unconditional Exits in Ruby
# Unlabeled Exits in Ruby
puts("----------------Unlabeled Exits----------------")
for i in 1..6
    if i == 3
      
        puts("Unlabeled continue (next) is used to pass the current iteration when the value is %d" %[i])
        next
    elsif i == 5 
        puts("Unlabeled break is used to break the loop when the value is: %d" %[i])
        puts("The remaining value 6 will not be printed.")
        break
    end
    puts("Loop value: %d" %[i])
end
puts("---------------------------------------------------")

# Labeled Exits in Ruby
# Ruby does not have Labeled Exists but have a unique way of simulating them using catch statement.
# Label is defined with ":" at the catch statement that will be a returning point when the corresponding label is thrown.
    puts("-------------------Labeled Exits-------------------")
    catch :outerBreak do
    for i in 1..3
      catch :innerBreak do
      puts("Outer value: #{i}")
      for i2 in 1..6
        catch :innerContinue do
        if i == 1
          puts(" Labeled continue is used, skipped outer loop iteration value: #{i}")
          throw :innerBreak # inner break = outer continue
        elsif i == 2 and i2 == 3
          puts(" Labeled break is used to terminate current inner loop iteration.")
          throw :innerBreak
        elsif i2 == 3
          puts(" Labeled continue is used, skipped inner loop iteration value: #{i2}")
          throw :innerContinue
        elsif i2 == 5
          puts(" Labeled break is used to terminate current outer loop iteration")
          throw :outerBreak
        end
        puts(" Inner value: #{i2}")
        end
      end
      end
    end
    end
    puts("Outer Loop is successfully broken.")
    puts("---------------------------------------------------")