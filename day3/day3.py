import re

file = open("input.txt", "r")
text = file.read()

pattern = r"mul\(\d+,\d+\)"
mulOperations = re.findall(pattern, text)

sumPart1 = 0
for operation in mulOperations:
    number1, number2 = re.findall(r"\d+", operation)
    sumPart1 += int(number1) * int(number2)

print("[Part 1] Sum: ", sumPart1)

#########################################################################

pattern = r"mul\(\d+,\d+\)|do\(\)|don\'t\(\)"
operations = re.findall(pattern, text)
flag = True
sumPart2 = 0
for operation in operations:
    if operation == "do()":
        flag = True
    elif operation == "don't()":
        flag = False
    else:
        if flag:
            number1, number2 = re.findall(r"\d+", operation)
            sumPart2 += int(number1) * int(number2)

print("[Part 2] Sum: ", sumPart2)
