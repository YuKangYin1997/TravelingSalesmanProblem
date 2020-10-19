# Assignment 1 Traveling Salesman Problem



Student Name: Yukang Yin

Student Number: 300162143

Email: yyin050@uottawa.ca



## 0. Environment Setup

Hello, in order to make it convenient for you to compile and run my code, I supplied my implementation files in separate `.java` file associated with several `.tsp` file, so you can simply use command line tool to compile and run, instead of using an IDE.

I developed my code in a MacOS Machine, with Java Version 1.8, so if you're using a MacOS or Linux based machine, it would be definitely easier for you to follow the sections below.

However, I found when I am using `javac` command to compile my code in a Windows based machine, some error will occur, but none of them appears in a MacOS or Linux based machine... So if you are using a Windows based machine, please contact me via email, and I'll 'port' my code to a Java IDE as quickly as possible, and helps you to compile and run them in an IDE.



## 1. Download Project

To download project, you can either download it from BrightSpace, where I uploaded all my implementation files, or you can download it from github, the link to this project is https://github.com/YuKangYin1997/TravelingSalesmanProblem

Once you have downloaded, the project structure should look like this:

```bash
TravelingSalesmanProblem
├── City.java
├── FileWriterUtil.java
├── Graph.java
├── HillClimbing.java
├── OperatorType.java
├── README.md
├── SimulatedAnnealing.java
├── StopWatch.java
├── TabuSearch.java
├── Travel.java
├── TravelingSalesmanProblem.iml
├── VerifierUtil.java
├── a280.tsp
├── berlin52.tsp
├── d1291.tsp
```



## 2. Compile

As you can see from the project structure above, I supplied three solutions for TSP: HillClimbing, SimulatedAnnealing and TabuSearch.

To Compile SimulatedAnnealing, you can use command `javac` in your terminal:

```bash
javac SimulatedAnnealing.java
```



For other two solution (HillClimbing and TabuSearch) , you can also use command `javac` to compile them, which is the same as compiling SimulatedAnnealing.

- to compile HillClimbing

  ```bash
  javac HillClimbing.java
  ```

- to compile TabuSearch

  ```bash
  javac TabuSearch.java
  ```



## 3. Run

To run SimulatedAnnealing solution, you can use command `java` in your terminal, and please remember to select the tsp file you'd like to solve. I'll just illustrate with `a280.tsp`, but you can definitely use the other tsp file as long as you put it in `TravelingSalesmanProblem` folder:

```bash
java SimulatedAnnealing a280.tsp
```

Note: In order to make the output simple and clear, I delete output (such as, Iteration # 100 Best Distance 10000) for each iteration. So please be patient when you are waiting for the final shorest distance : )

Once the program finished, the shortest distance will be displayed in terminal, and there will be a `solution.csv` file generated in folder `TravelingSalesmanProblm`.

To read `solution.csv`, you can use command `cat` to see every city number:

```bash
cat solution.csv
```

Besides, if you want to verify the correctness of the code (i.e. whether the number of cities in `solution.csv` is equal to the number of cities in `.tsp` file), you can use command `cat [fileName] wc -l` in terminal:

```bash
cat solution.csv | wc -l
```


For other two solution (HillClimbing and TabuSearch) , once you have them compiled, you can also use command  `java` to run program, use `cat` to see every city number in `solution.csv` and use `cat [fileName] | wc -l` to verify the correctness of the program, which is the same as running SimulatedAnnealing.

- HillClimbing

  - run

    ```bash
    java HillClimbing a280.tsp
    ```

  - see every city number in `solution.csv`

    ```bash
    cat solution.csv
    ```

  - verify the correctness of the program

    ```bash
    cat solution.csv | wc -l
    ```

- TabuSearch

  - run

    ```bash
    java TabuSearch a280.tsp
    ```

  - see every city number in `solution.csv`

    ```bash
    cat solution.csv
    ```

  - verify the correctness of the program

    ```bash
    cat solution.csv | wc -l
    ```

    

## 4. Different methods in one solution

### 4.1 Hill Climbing

I've supplied four methods: *Steepest Ascent*, *Random Ascent*, *First Ascent* and *First Ascent With Random Restart* for HillClimbing solution, and I made *First Ascent* the default one for HillClimbing solution, so when you compile and run `HillClimbing.java`, you are actually running *Hill Climbing FirstAscent*.

So if you want to use other Hill Climbing methods instead of Hill Climbing with FirstAscent, you should firstly uncomment corresponding line in `HillClimbing.java`. Then compile and run the program.

- for *Hill Climbing Steepest Ascent*, uncomment line 149-155 and comment line 167-172
- for *Hill Climbing Random Ascent*, uncomment line 158-164 and comment line 167-172
- for *Hill Climbing First Ascent With Random Restart*, uncomment line 175-184 and comment line 167-172



### 4.2 Simulated Annealing

I've supplied two methods: *basic Simulated Annealing* (hereafter: *basic_SA*) and *Dynamic Simulated Annealing-Cooling Enhancer-Modified Acceptance Possibility Simulated Annealing* (hereafter: *DSA_CE_MAP_SA*) for SimulatedAnnealing solution, and I made *DSA_CE_MAP_SA* as the default one for SimulatedAnnealing solution, so when you compile and run `SimulatedAnnealing.java`, you are actually running *DSA_CE_MAP_SA*.

So if you want to use *basic_SA*, you should firstly uncomment line 155-164 and comment line 167-178 in `SimulatedAnnealing.java`. Then compile and run the program.



### 4.3 Tabu Search

I've only support one method for *TabuSearch* solution, the one delivered in the lecture.




## 5. Declaration of parameters in different solutions

### 5.1 Hill Climbing Solution

- Two parameters in *hill climbing steepest ascent* that you can modify
  - `numberOfMaxIterations` maximum iterations that we are allowed to go through neighbours. Default 10000 for `a280.tsp`.
  - `timeThreshold` maximum minutes we are allowed to spend on *hill climbing steepest ascent*. Default 10 for `a280.tsp`.



- Two parameters in *hill climbing random ascent* that you can modify
  - `numberOfMaxIterations` maximum iterations that we are allowed to go through neighbours in *hill climbing random ascent* . Default 10000 for `a280.tsp`.
  - `timeThreshold` maximum minutes we are allowed to spend on *hill climbing random ascent*. Default 10 for `a280.tsp`.



- One parameter in *hill climbing first ascent* that you can modify
  - `timeThreshold` maximum minutes we are allowed to spend on *hill climbing first ascent*. Default 10 for `a280.tsp`.
  - Note: I didn't set `numberOfMaxIterations` in *hill climbing first ascent* because we immediately jump out of a for-loop when we meet a better neighbour, so it's not proper to call this procedure an iteration.



- Two parameters in *hill climbing first ascent with random restart* that you can modify
  - `restartTimes` maximum times we are allowed to restart *hill climbing first ascent*. Default 5 for `a280.tsp`.
  -  `timeThresholdForEachRestart` maximum minutes we are allowed to spend on *hill climbing first ascent* each time. Default 10 for `a280.tsp`.





### 5.2 Simulated Annealing Solution

- Five parameters in *basic_SA* that you can modify
  - `startingTemperature` temperature at the beginning of *basic_SA*. Default 100000.0 for `a280.tsp`.
  - `endingTemperature` temperature at the end of *basic_SA*. Default 0.0001 for `a280.tsp`.
  - `coolingRate` rate of cooling current temperature in *basic_SA*. Default 0.001 for `a280.tsp`.
  - `operatorType` method of finding neighbours in *basic_SA*. Default `REVERSE`  for `a280.tsp`.
  - `timeThreshold` maximum minutes we are allowed to spend on basic_SA. Default 5 for `a280.tsp`.



- Six parameters in *DSA_CE_MAP_SA* that you can modify

  - `startingTemperature` temperature at the beginning of *DSA_CE_MAP_SA*. Default 100000.0 for `a280.tsp`.
  - `endingTemperature` temperature at the end of *DSA_CE_MAP_SA*. Default 0.0001 for `a280.tsp`.
  - `coolingRate` rate of cooling current temperature in *DSA_CE_MAP_SA*. Default 0.001 for `a280.tsp`.
  - `coolingEnhancer` enhancer for basic cooling strategy, adaptive to number of cities. Automatically set to 0.005 for `a280.tsp`.
  - `operatorType` method of finding neighbours in *DSA_CE_MAP_SA*. Default `REVERSE`  for `a280.tsp`.
  - `timeThreshold` maximum minutes we are allowed to spend on *DSA_CE_MAP_SA*. Default 5 for `a280.tsp`.



### 5.3 Tabu Search Solution

Three parameters in *Tabu Search* that you can modify

- `numberOfMaxIterations` maximum iterations that we are allowed to go through neighbours in *Tabu Search*. Default 2000 for `a280.tsp`
- `maxTabuListSize` maximum length of tabu list in *Tabu Search*. Default 10 for `a280.tsp`.
- `timeThreshold` maximum minutes we are allowed to spend on *Tabu Search*. Default 10 for `a280.tsp`.

