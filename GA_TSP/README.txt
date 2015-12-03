RUNNING THE APPLICATION

There are two simple ways of running the application, both through the runnable Jar ?le. 

Option 1- Run the Jar directly and use the GUI.
---------------
Option 2-Running the Jar through the command line to produce a command line response. 
	- The primary parameters for this are: 
		-name of csv file containing coordinates; 
		-a mutation rate; - a decimal number 1 or less
		-an evolution amount; - any whole number
		-whether elitism should be enabled; - 'true' or 'false'
		-a selection type; - these can be: 'Tournament', 'Roulette Wheel', 'Rank'
		-a crossover type; - these can be: 'Ordered', 'Cyclic'

e.g. 'java -jar TSP_GA.jar input.csv 0.015 true Tournament Ordered'
---------------
It should be noted that in order to run the command line version, the GUI version may need to be run if the 
user wishes to make use of the random point generation operation, which will only export to CSV whilst run 
in the GUI. 