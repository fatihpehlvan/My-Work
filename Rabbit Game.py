"""This program takes a feeding map (include rabbbit(*), carrot(C), apple(A), meat(M), poision(P),
wall(W), and empty areas(X)) and directions of movements (include Up(U), Down(D), Right(R), and Left(L)) from user.
Then, the program leads the rabbit to the target."""

# User inputs
board = input("Please enter feeding map as a list:\n")
directions = input("Please enter direction of movements as a list:\n")

#Variables
row1 = int(board.count("[")) - 1
list = []
map = []
score = 0
row_rabbit = 0
column_rabbit = 0

#Convert string to list
for i in board:
    if i == "C" or i == "A" or i == "M" or i == "P" or i == "W" or i == "*" or i == "X":
        list.append(i)
column1 = int(len(list)/row1)

#Convert list to 2D array
map = [list[i : i + column1] for i in range(0 , len(list), column1)]
row = 0

#Find the rabbit locations
for i in map:
    row += 1
    column = 0
    for j in i:
        column += 1
        if j == "*":
            row_rabbit = row
            column_rabbit = column

#print the first board
print("Your board is: ")
for i in map:
    for j in i:
        print(j, end=" ")
    print()


#Convert locations to their indexs
row_rabbit -= 1
column_rabbit -= 1


def up(control):
    """
    The function leads to rabbit one unit up.
    :param control: str(directions)
    :return: feeding map
    """
    global row_rabbit , column_rabbit , map , score
    if map[row_rabbit - 1][column_rabbit] == "W":
        pass
    elif map[row_rabbit - 1][column_rabbit] == "C":
        score += 10
        map[row_rabbit][column_rabbit] = "X"
        map[row_rabbit - 1][column_rabbit] = "*"
        row_rabbit -= 1
    elif map[row_rabbit - 1][column_rabbit] == "A":
        score += 5
        map[row_rabbit][column_rabbit] = "X"
        map[row_rabbit - 1][column_rabbit] = "*"
        row_rabbit -= 1
    elif map[row_rabbit - 1][column_rabbit] == "M":
        score -= 5
        map[row_rabbit][column_rabbit] = "X"
        map[row_rabbit - 1][column_rabbit] = "*"
        row_rabbit -= 1
    elif map[row_rabbit - 1][column_rabbit] == "X":
        map[row_rabbit][column_rabbit] = "X"
        map[row_rabbit - 1][column_rabbit] = "*"
        row_rabbit -= 1
    return map


def down(control):
    """
        The function leads to rabbit one unit down.
        :param control: str(directions)
        :return: feeding map
        """
    global row_rabbit , column_rabbit , map , score ,row1
    if map[row_rabbit + 1][column_rabbit] == "W":
        pass
    elif map[row_rabbit + 1][column_rabbit] == "C":
        score += 10
        map[row_rabbit][column_rabbit] = "X"
        map[row_rabbit + 1][column_rabbit] = "*"
        row_rabbit += 1
    elif map[row_rabbit + 1][column_rabbit] == "A":
        score += 5
        map[row_rabbit][column_rabbit] = "X"
        map[row_rabbit + 1][column_rabbit] = "*"
        row_rabbit += 1
    elif map[row_rabbit + 1][column_rabbit] == "M":
        score -= 5
        map[row_rabbit][column_rabbit] = "X"
        map[row_rabbit + 1][column_rabbit] = "*"
        row_rabbit += 1
    elif map[row_rabbit + 1][column_rabbit] == "X":
        map[row_rabbit][column_rabbit] = "X"
        map[row_rabbit + 1][column_rabbit] = "*"
        row_rabbit += 1
    return map


def right(control):
    """
    The function leads to rabbit one unit right.
    :param control: str(directions)
    :return: feeding map
    """
    global row_rabbit , column_rabbit , map , score , column1
    if map[row_rabbit][column_rabbit + 1] == "W":
        pass
    elif map[row_rabbit][column_rabbit + 1] == "C":
        score += 10
        map[row_rabbit][column_rabbit] = "X"
        map[row_rabbit][column_rabbit + 1] = "*"
        column_rabbit += 1
    elif map[row_rabbit][column_rabbit + 1] == "A":
        score += 5
        map[row_rabbit][column_rabbit] = "X"
        map[row_rabbit][column_rabbit + 1] = "*"
        column_rabbit += 1
    elif map[row_rabbit][column_rabbit + 1] == "M":
        score -= 5
        map[row_rabbit][column_rabbit] = "X"
        map[row_rabbit][column_rabbit + 1] = "*"
        column_rabbit += 1
    elif map[row_rabbit][column_rabbit + 1] == "X":
        map[row_rabbit][column_rabbit] = "X"
        map[row_rabbit][column_rabbit + 1] = "*"
        column_rabbit += 1
    return map


def left(control):
    """
        The function leads to rabbit one unit left
        :param control: str(directions)
        :return: feeding map
        """
    global row_rabbit , column_rabbit , map , score
    if map[row_rabbit][column_rabbit - 1] == "W":
        pass
    elif map[row_rabbit][column_rabbit - 1] == "C":
        score += 10
        map[row_rabbit][column_rabbit] = "X"
        map[row_rabbit][column_rabbit - 1] = "*"
        column_rabbit -= 1
    elif map[row_rabbit][column_rabbit - 1] == "A":
        score += 5
        map[row_rabbit][column_rabbit] = "X"
        map[row_rabbit][column_rabbit - 1] = "*"
        column_rabbit -= 1
    elif map[row_rabbit][column_rabbit - 1] == "M":
        score -= 5
        map[row_rabbit][column_rabbit] = "X"
        map[row_rabbit][column_rabbit - 1] = "*"
        column_rabbit -= 1
    elif map[row_rabbit][column_rabbit - 1] == "X":
        map[row_rabbit][column_rabbit] = "X"
        map[row_rabbit][column_rabbit - 1] = "*"
        column_rabbit -= 1
    return map

#Convert string to list
list.clear()
for i in directions:
    if i == "U" or i == "D" or i == "L" or i == "R":
        list.append(i)

#Apply directions, and watch out endpoints and poisions.
for i in list:
    if i == "U":
        if row_rabbit == 0:
            continue
        elif map[row_rabbit - 1][column_rabbit] == "P":
            map[row_rabbit][column_rabbit] = "X"
            map[row_rabbit - 1][column_rabbit] = "*"
            break
        else:
            up(i)
    elif i == "D":
        if row_rabbit == row1-1:
            continue
        elif map[row_rabbit + 1][column_rabbit] == "P":
            map[row_rabbit][column_rabbit] = "X"
            map[row_rabbit + 1][column_rabbit] = "*"
            break
        else:
            down(i)
    elif i == "R":
        if column_rabbit == column1-1:
            continue
        elif map[row_rabbit][column_rabbit + 1] == "P":
            map[row_rabbit][column_rabbit] = "X"
            map[row_rabbit][column_rabbit + 1] = "*"
            break
        else:
            right(i)
    elif i == "L":
        if column_rabbit == 0:
            continue
        elif map[row_rabbit][column_rabbit - 1] == "P":
            map[row_rabbit][column_rabbit] = "X"
            map[row_rabbit][column_rabbit - 1] = "*"
            break
        else:
            left(i)

#print the last board and the score
print("Your output should be like this: ")
for i in map:
    for j in i:
        print(j, end=" ")
    print()
print("Your Score is:", score)