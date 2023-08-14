import random
import time

def values():
    values = random.randint(0,1000)
    return values;

def fillFile(fileSize, fileName):
    # for i in range(fileSize):
    #     values = random.randint(0,1000)
    file = open(fileName + ".txt", "w")
    for i in range(fileSize):
        file.write(f"{values()}\n")

fileSizes = [1000, 5000, 10000, 25000, 50000, 100000, 200000]

def readFile(fileName):
    files= open(str(fileName) + ".csv", "r")
    values = []
    for line in files:
        values.append(line.split())
    return values

zz = readFile("sele")
print(zz)