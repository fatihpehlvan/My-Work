import sys
# Define some errors
class Error(Exception):
    pass
class ParameterNumberError(Error):
    pass
class UndefinedParameterError(Error):
    pass
class TheInputFileCouldNotBeRead(Error):
    pass
class InputFileIsEmpty(Error):
    pass
class InvalidCharacterInInputFile(Error):
    pass
#try the errors
try:
    if (".txt") not in sys.argv[2]:
        raise TheInputFileCouldNotBeRead("Key file cannot be read")
    keyFile = open(sys.argv[2], mode="r")
    keyList = []
    # make key file to matrix
    
    for i in keyFile:
        keyListElements = []
        for k in i.split(","):
            keyListElements.append(int(k))
        keyList.append(keyListElements)
    if keyList == []:
        raise InputFileIsEmpty("Key File is empty error")
    if not(sys.argv[1] == "dec" or sys.argv[1]== "enc"):
        raise UndefinedParameterError("Undefined parameter error")
    if len(sys.argv) != 5:
        raise ParameterNumberError("Parameter number error")
    
    
except ParameterNumberError as Err:
    print(Err)
    exit()
except UndefinedParameterError as Err:
    print(Err)
    exit()
except FileNotFoundError:
    print("Key file not found error")
    exit()
except TheInputFileCouldNotBeRead as Err:
    print(Err)
    exit()
except InputFileIsEmpty as Err:
    print(Err)
    exit()
except InvalidCharacterInInputFile as Err:
    print(Err)
    exit()
except ValueError:
    print("Invalid character in key file error")
    exit()
keyFile.close()
firstTxtName = list(sys.argv)[1]


alphabeth = {"a": 1, "b": 2, "c": 3, "d": 4, "e": 5, "f": 6, "g": 7, "h": 8, "i": 9, "J": 10, "k": 11, "l": 12, "m": 13, "n": 14, "o": 15, "p": 16, "r": 18, "s": 19, "t":20, "u": 21, "v": 22, "w": 23, "x":24, "y":25, "z": 26, " ": 27}
if "enc" in firstTxtName:

    def matrix(encryptedList,keyList):
        a = []
        for l in encryptedList:
            for i in keyList:
                count = 0
                toplam = 0
                for j in i:
                    count += 1
                    count1 = 0
                    for k in l:
                        count1 +=1
                        if count == count1:
                            toplam += j*k[0]
                a.append(toplam)
        return a
    
    try:
        if ".txt" not in sys.argv[3]:
            raise TheInputFileCouldNotBeRead("Input File cannot be read")
        encryptedFile = open(sys.argv[3], mode="r")
        encrypted = encryptedFile.readlines()
        if encrypted == []:
            raise InputFileIsEmpty("Input file is empty error")
        for i in encrypted:
            for j in i:
                j = j.lower()
                if j not in alphabeth.keys():
                    raise InvalidCharacterInInputFile("Invalid character in input file error")
        
    except FileNotFoundError:
        print("Input file not found error")
        exit()
    except TheInputFileCouldNotBeRead as Err:
        print(Err)
        exit()
    except InputFileIsEmpty as Err:
        print(Err)
        exit()
    except InvalidCharacterInInputFile as Err:
        print(Err)
        exit()
    encryptedFile.close()
    textSlicing = len(encrypted[0]) % len(keyList)
    if textSlicing != 0:
        for i in range(textSlicing+1):
            encrypted[0] += " "
    encrypted = [encrypted[0].lower()[i:i+len(keyList)] for i in range(0,len(encrypted[0])-len(keyList)+1,len(keyList))]

    encryptedList = []
    for i in encrypted:
        for j in i:
            encryptedList.append([int(alphabeth[j])])
    encryptedList = [encryptedList[i:i+len(keyList)] for i in range(0,len(encryptedList)-len(keyList)+1,len(keyList))]

    result = []
    
    

    a = matrix(encryptedList, keyList)
    password = ""
    for i in a:
        password += str(i) + ","
    password = password[:-1]
    output_enx = open(sys.argv[4] ,mode="w")
    output_enx.write(password)



elif "dec" in firstTxtName:
    lenMatrix = len(keyList)
    def findDeterminant(list, row):
        global sum
        result = 0
        if row == 1:
            return list[0][0]
        elif row == 2:
            result = (list[0][0] * list[1][1]) - (list[0][1]*list[1][0])
            return result
        elif row > 2:
            sum = 0
            firstRow = list[0].copy()
            for i in range(len(firstRow)):
                new_matrix = [z[:] for z in list]
                new_matrix.pop(0)
                for p in new_matrix:
                    p.pop(i)
                sum += (-1)**(i)*list[0][i] * findDeterminant(new_matrix, len(new_matrix))
        return sum

    def inverse(list):
        global sum
        determinant = findDeterminant(keyList, lenMatrix)
        arj = []
        for i in range(len(list)):
            smallMatrix = []
            for j in range(len(list[i])):
                new_list = [z[:] for z in keyList]
                new_list.pop(i)
                for k in range(len(new_list)):
                    new_list[k].pop(j)
                smallMatrix.append((-1)**(i+j)*findDeterminant(new_list, len(new_list)))
            arj.append(smallMatrix)
        adjugate = []
        for i in range(len(arj)):
            adjugateElements = []
            for k in range(len(arj)):
                adjugateElements.append([])
            adjugate.append(adjugateElements)
        a = 0
        for i in arj:
            b = 0
            for k in i:
                adjugate[b][a] = k * determinant
                b += 1
            a += 1
        return adjugate
    keyList = inverse(keyList)
    
    cipher = open(sys.argv[3], mode="r")
    a = [i.split(",") for i in cipher.readlines()][0]
    cipher.close()
    for i in range(len(a)):
        a[i] = int(a[i])
        
    cipherList = [a[x:lenMatrix+x] for x in range(0, len(a),lenMatrix)]
    lastStatement = []
    for l in cipherList:
        for i in keyList:
            count = 0
            toplam = 0
            for j in i:
                count += 1
                count1 = 0
                for k in l:
                    count1 +=1
                    if count == count1:
                        toplam += j*int(k)
            lastStatement.append(toplam)
    alphabeth = {"a": 1, "b": 2, "c": 3, "d": 4, "e": 5, "f": 6, "g": 7, "h": 8, "i": 9, "J": 10, "k": 11, "l": 12, "m": 13, "n": 14, "o": 15, "p": 16, "r": 18, "s": 19, "t":20, "u": 21, "v": 22, "w": 23, "x":24, "y":25, "z": 26, " ": 27}
    text = ""
    for k in lastStatement:    
        for i, j in alphabeth.items():
            if k == j:
                text += str(i)
    output_dec = open(sys.argv[4], mode="w")
    output_dec.write(text)