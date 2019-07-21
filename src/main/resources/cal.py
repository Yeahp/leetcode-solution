# coding=utf-8
from sys import argv
import pandas as pd
import xgboost as xgb

if __name__ == "__main__":
    num1 = argv[1]
    num2 = argv[2]
    sum = int(num1) + int(num2)
    print(sum)
