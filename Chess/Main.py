import sys
f = open(sys.argv[1],"r")
commands = [[line.split()] for line in f.readlines()]
f.close()
black_pieces = ["R1","R2","N1","N2","B1","B2","KI","QU","P1","P2","P3","P4","P5","P6","P7","P8"]
white_pieces = ["r1","r2","n1","n2","b1","b2","ki","qu","p1","p2","p3","p4","p5","p6","p7","p8"]
row = [8, 7, 6, 5, 4, 3, 2, 1]
column = ["a","b","c","d","e","f","g","h"]
squares = []

for i in row:
    result = []
    for j in column:
        a = str(i)+str(j)
        a = a[::-1]
        result.append(a)
    squares.append(result)

coordinates = {}
for i in squares:
    for j in i:
        coordinates[j] = "  "

coordinates.update({"a8":"R1","b8":"N1","c8":"B1","d8":"QU","e8":"KI","f8":"B2","g8":"N2","h8":"R2",
                   "a7":"P1","b7":"P2","c7":"P3","d7":"P4","e7":"P5","f7":"P6","g7":"P7","h7":"P8",
                   "a2":"p1","b2":"p2","c2":"p3","d2":"p4","e2":"p5","f2":"p6","g2":"p7","h2":"p8",
                   "a1":"r1","b1":"n1","c1":"b1","d1":"qu","e1":"ki","f1":"b2","g1":"n2","h1":"r2"})
to_initialize = coordinates.copy()

def initialize():
    global to_initialize, coordinates
    coordinates = to_initialize.copy()
    print("OK")
    print_board()

def print_board():
    global coordinates
    print("-------------------------")
    for i, j in coordinates.items():
        print(j, end=" ")
        if "h" in i:
            print()
    print("-------------------------")
location_list = []
def pawns(pawn):
    global location, location_list, p, number
    location_list.clear()
    if "p" in pawn and number != 8:
        new_location = location[0] + str(number + 1)
        location_list.append(new_location)
        if coordinates[new_location] in white_pieces or coordinates[new_location] == "KI":
            return "FAILED"
        return location_list
    elif "P" in pawn and number != 1:
        new_location = location[0] + str(number - 1)
        location_list.append(new_location)
        if coordinates[new_location] in black_pieces or coordinates[new_location] == "ki":
            return "FAILED"
        return location_list

def knights(knight):
    global location, location_list, p, number, word, row, column
    location_list.clear()
    row = row + ["0","0"]
    column = column + ["0","0"]
    l_move = [
        str(column[column.index(word) - 1]) + str(row[row.index(number) - 1]),
        str(column[column.index(word) - 1]) + str(row[row.index(number) - 2]),
        str(column[column.index(word) - 1]) + str(row[row.index(number) + 1]),
        str(column[column.index(word) - 1]) + str(row[row.index(number) + 2]),
        str(column[column.index(word) - 2]) + str(row[row.index(number) - 1]),
        str(column[column.index(word) - 2]) + str(row[row.index(number) + 1]),
        str(column[column.index(word) + 1]) + str(row[row.index(number) + 1]),
        str(column[column.index(word) + 1]) + str(row[row.index(number) + 2]),
        str(column[column.index(word) + 1]) + str(row[row.index(number) - 1]),
        str(column[column.index(word) + 1]) + str(row[row.index(number) - 2]),
        str(column[column.index(word) + 2]) + str(row[row.index(number) + 1]),
        str(column[column.index(word) + 2]) + str(row[row.index(number) - 1]),
    ]
    diagonel_knight_move = [
        str(column[column.index(word) + 1]) + str(row[row.index(number) - 1]),
        str(column[column.index(word) + 1]) + str(row[row.index(number) + 1]),
        str(column[column.index(word) - 1]) + str(row[row.index(number) - 1]),
        str(column[column.index(word) - 1]) + str(row[row.index(number) + 1]),
    ]
    if "n" in knight:
        for i in l_move:
            if "0" not in i:
                if abs(column.index(word)-column.index(i[0])) <=2 and abs(row.index(number)-row.index(int(i[1])))<= 2 and i not in diagonel_knight_move and coordinates[i] not in white_pieces and coordinates[i] != "KI":
                    location_list.append(i)
                elif abs(column.index(word)-column.index(i[0])) <=2 and abs(row.index(number)-row.index(int(i[1])))<= 2 and i in diagonel_knight_move and coordinates[i] == "  ":
                    location_list.append(i)
        return location_list
    else:
        for i in l_move:
            if "0" not in i:
                if abs(column.index(word)-column.index(i[0])) <=2 and abs(row.index(number)-row.index(int(i[1])))<= 2  and i not in diagonel_knight_move and coordinates[i] not in black_pieces and coordinates[i] != "ki":
                    location_list.append(i)
                elif abs(column.index(word)-column.index(i[0])) <=2 and abs(row.index(number)-row.index(int(i[1])))<= 2 and i in diagonel_knight_move and coordinates[i] == "  ":
                    location_list.append(i)
        row = row [:-2]
        column = column[:-2]
        return location_list

def bishops(bishop):
    global location, location_list, p, number, word, row, column
    location_list.clear()
    diagonel_bishop_move = []
    left = column.index(word)
    right = 7 - column.index(word)
    if "b" in bishop or "q" in bishop:
        up = row.index(number)
        min_left = min(left,up)
        min_right = min(right,up)
        for i in range(min_left+1):
            a = (str(column[column.index(word) - i]) + str(row[row.index(number) - i]))
            if a != location:
                if coordinates[a] in white_pieces or coordinates[a] == "KI":
                    break
                elif coordinates[a] in black_pieces:
                    diagonel_bishop_move.append(a)
                    break
                else:
                    diagonel_bishop_move.append(a)
        for i in range(min_right+1):
            a = (str(column[column.index(word) + i]) + str(row[row.index(number) - i]))
            if a != location:
                if coordinates[a] in white_pieces or coordinates[a] == "KI":
                    break
                elif coordinates[a] in black_pieces:
                    diagonel_bishop_move.append(a)
                    break
                else:
                    diagonel_bishop_move.append(a)
        return diagonel_bishop_move
    elif "B" in bishop or "Q" in bishop:
        down = 7 - row.index(number)
        min_left = min(left, down)
        min_right = min(right, down)
        for i in range(min_left+1):
            a = (str(column[column.index(word) - i]) + str(row[row.index(number) + i]))
            if a != location:
                if coordinates[a] in black_pieces or coordinates[a] == "ki":
                    break
                elif coordinates[a] in white_pieces:
                    diagonel_bishop_move.append(a)
                    break
                else:
                    diagonel_bishop_move.append(a)
        for i in range(min_right+1):
            a = (str(column[column.index(word) + i]) + str(row[row.index(number) + i]))
            if a != location:
                if coordinates[a] in black_pieces or coordinates[a] == "ki":
                    break
                elif coordinates[a] in white_pieces:
                    diagonel_bishop_move.append(a)
                    break
                else:
                    diagonel_bishop_move.append(a)
        return diagonel_bishop_move


def rooks(rook):
    global location, location_list, p, number, word, row, column, left, right, up ,down
    location_list.clear()
    rook_move = []
    left = column.index(word)
    right = 7 - column.index(word)
    up = row.index(number)
    down = 7 - row.index(number)
    if "r" in rook or "q" in rook:
        for i in range(left + 1):
            a = (str(column[column.index(word) - i]) + str(row[row.index(number)]))
            if a != location:
                if coordinates[a] in white_pieces or coordinates[a] == "KI":
                    break
                elif coordinates[a] in black_pieces:
                    rook_move.append(a)
                    break
                else:
                     rook_move.append(a)
        for i in range(right + 1):
            a = (str(column[column.index(word) + i]) + str(row[row.index(number)]))
            if a != location:
                if coordinates[a] in white_pieces or coordinates[a] == "KI":
                    break
                elif coordinates[a] in black_pieces:
                    rook_move.append(a)
                    break
                else:
                     rook_move.append(a)

        for i in range(up + 1):
            a = (str(column[column.index(word)]) + str(row[row.index(number)-i]))
            if a != location:
                if coordinates[a] in white_pieces or coordinates[a] == "KI":
                    break
                elif coordinates[a] in black_pieces:
                    rook_move.append(a)
                    break
                else:
                     rook_move.append(a)
        for i in range(down + 1):
            a = (str(column[column.index(word)]) + str(row[row.index(number)+i]))
            if a != location:
                if coordinates[a] in white_pieces or coordinates[a] == "KI":
                    break
                elif coordinates[a] in black_pieces:
                    rook_move.append(a)
                    break
                else:
                     rook_move.append(a)
        return rook_move
    elif "R" in rook or "Q" in rook:
        for i in range(left + 1):
            a = (str(column[column.index(word) - i]) + str(row[row.index(number)]))
            if a != location:
                if coordinates[a] in black_pieces or coordinates[a] == "ki":
                    break
                elif coordinates[a] in white_pieces:
                    rook_move.append(a)
                    break
                else:
                    rook_move.append(a)
        for i in range(right + 1):
            a = (str(column[column.index(word) + i]) + str(row[row.index(number)]))
            if a != location:
                if coordinates[a] in black_pieces or coordinates[a] == "ki":
                    break
                elif coordinates[a] in white_pieces:
                    rook_move.append(a)
                    break
                else:
                    rook_move.append(a)

        for i in range(up + 1):
            a = (str(column[column.index(word)]) + str(row[row.index(number) - i]))
            if a != location:
                if coordinates[a] in black_pieces or coordinates[a] == "ki":
                    break
                elif coordinates[a] in white_pieces:
                    rook_move.append(a)
                    break
                else:
                    rook_move.append(a)
        for i in range(down + 1):
            a = (str(column[column.index(word)]) + str(row[row.index(number) + i]))
            if a != location:
                if coordinates[a] in black_pieces or coordinates[a] == "ki":
                    break
                elif coordinates[a] in white_pieces:
                    rook_move.append(a)
                    break
                else:
                    rook_move.append(a)
        return rook_move
def queens(queen):
    global location, location_list, p, number, word, row, column, left, right, up ,down
    queen_move = []
    if "q" in queen:
        queen_move = rooks(queen) + bishops(queen)
        min_left = min(left, down)
        min_right = min(right, down)
        for i in range(min_left + 1):
            a = (str(column[column.index(word) - i]) + str(row[row.index(number) + i]))
            if a != location:
                if coordinates[a] in white_pieces:
                    break
                elif coordinates[a] in black_pieces:
                    queen_move.append(a)
                    break
                else:
                    queen_move.append(a)
        for i in range(min_right + 1):
            a = (str(column[column.index(word) + i]) + str(row[row.index(number) + i]))
            if a != location:
                if coordinates[a] in white_pieces:
                    break
                elif coordinates[a] in black_pieces:
                    queen_move.append(a)
                    break
                else:
                    queen_move.append(a)
        return queen_move
    elif "Q" in queen:
        queen_move = rooks(queen) + bishops(queen)
        min_left = min(left, up)
        min_right = min(right, up)
        for i in range(min_left + 1):
            a = (str(column[column.index(word) - i]) + str(row[row.index(number) - i]))
            if a != location:
                if coordinates[a] in white_pieces:
                    break
                elif coordinates[a] in black_pieces:
                    queen_move.append(a)
                    break
                else:
                    queen_move.append(a)
        for i in range(min_right + 1):
            a = (str(column[column.index(word) + i]) + str(row[row.index(number) - i]))
            if a != location:
                if coordinates[a] in black_pieces:
                    break
                elif coordinates[a] in white_pieces:
                    queen_move.append(a)
                    break
                else:
                    queen_move.append(a)
        return queen_move

def kings(king):
    global location, location_list, p, number, word, row, column
    location_list.clear()
    row.append("0")
    column.append("0")
    king_moves = [str(column[column.index(word) - 1]) + str(row[row.index(number) - 1]),
    str(column[column.index(word) - 1]) + str(row[row.index(number) + 1]),
    str(column[column.index(word) + 1]) + str(row[row.index(number) + 1]),
    str(column[column.index(word) + 1]) + str(row[row.index(number) - 1]),
    str(column[column.index(word) - 1]) + str(row[row.index(number)]),
    str(column[column.index(word) + 1]) + str(row[row.index(number)]),
    str(column[column.index(word)]) + str(row[row.index(number) + 1]),
    str(column[column.index(word)]) + str(row[row.index(number) - 1])]
    if "k" in king:
        for i in king_moves:
            if "0" not in i:
                if abs(column.index(word) - column.index(i[0])) <= 1 and abs(row.index(number) - row.index(int(i[1]))) <= 1 and coordinates[i] not in white_pieces and coordinates[i] != "KI":
                    location_list.append(i)
        row = row[:-1]
        column = column[:-1]
        return location_list
    else:
        for i in king_moves:
            if "0" not in i:
                if abs(column.index(word)-column.index(i[0])) <=1 and abs(row.index(number)-row.index(int(i[1])))<= 1 and coordinates[i] not in black_pieces and coordinates[i] != "ki":
                    location_list.append(i)
        row = row[:-1]
        column = column[:-1]
        return location_list
def list_to_string(list):
    str = ""
    if len(list) == 0:
        return "FAILED"
    if list == "FAILED":
        return "FAILED"
    for i in sorted(list):
        str += i + " "
    return str
def showmoves(pieces):
    global location, new_location, coordinates, p, number, word, number
    for location,piece in coordinates.items():
        if piece == pieces:
            number = int(location[1])
            word = location[0]
            if "p" in pieces or "P" in pieces:
                return list_to_string(pawns(pieces))
            elif "n" in pieces or "N" in pieces:
                return list_to_string(knights(pieces))
            elif "b" in pieces or "B" in pieces:
                return list_to_string(bishops(pieces))
            elif "r" in pieces or "R" in pieces:
                return list_to_string(rooks(pieces))
            elif "q" in pieces or "Q" in pieces:
                return list_to_string(queens(pieces))
            elif "k" in pieces or "K" in pieces:
                return list_to_string(kings(pieces))
            else:
                return "FAILED"
    else:
        return "FAILED"
def move(pieces, square):
    global location, location_list, coordinates, new_location
    a = showmoves(pieces).split(" ")
    for i in a:
        if i == square:
            coordinates.update({location:"  ", i:pieces})
            return "OK"
    else:
        return "FAILED"

for i in commands:
    for j in i:
        if j[0] == "move":
            print(">", j[0], j[1], j[2])
            print(move(j[1], j[2]))
        elif j[0] == "print":
            print(">", j[0])
            print_board()
        elif j[0] == "showmoves":
            print(">", j[0], j[1])
            print(showmoves(j[1]))
        elif j[0] == "initialize":
            print(">", j[0])
            initialize()
        elif j[0] == "exit":
            print(">", j[0])
            exit()
