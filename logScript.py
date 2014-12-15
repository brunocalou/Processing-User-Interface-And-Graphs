from __future__ import division
from math import *
from random import *

def function(x):
	return log(x)*sin(x)*cos(20*x)
	# return randint(0,1)

def createLog(x,y,z,token, path):
	f = open(path,'w')
	lines = []
	for i in range(0,len(x)):
		lines += [str(x[i]) + token + str(y[i]) + token + str(z[i]) + "\n"]
	f.writelines(lines)
	f.close()

def main():

	number_of_lines = 2000
	path = "./log.txt"
	token = ","

	x = []
	y = []
	z = []

	resolution = 100.0

	for i in range(1,number_of_lines):
		x += [i/resolution]
		y += [function(i/resolution)]
		z += [0]

	createLog(x,y,z,token,path)
	print("Done.")

main()