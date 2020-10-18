# Assignment 1 Traveling Salesman Problem



Student Name: Yukang Yin

Student Number: 300162413

Email: yyin050@uottawa.ca

Java Version: 1.8



## 0. Download Project

To download project, you can either download it from BrightSpace, where I uploaded all my implementation files, or you can download it from github, the link to this project is https://github.com/YuKangYin1997/TravelingSalesmanProblem

Once you have downloaded, the project structure should look like this:

```bash
TravelingSalesmanProblem
├── City.java
├── FileWriterUtil.java
├── Graph.java
├── HillClimbing.java
├── OperatorType.java
├── SimulatedAnnealing.java
├── StopWatch.java
├── TabuSearch.java
├── Travel.java
├── VerifierUtil.java
├── a280.tsp
├── berlin52.tsp
├── d1291.tsp
└── d198.tsp
```



## 1. Compile

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



## 2. Run

To run SimulatedAnnealing solution, you can use command `java` in your terminal, and please remember to select the tsp file you'd like to solve. I'll just illustrate with `a280.tsp`, but you can definitely use the other tsp file as long as you put it in `TravelingSalesmanProblem` folder:

```bash
java SimulatedAnnealing a280.tsp
```

Once the program finished, the shortest distance will be displayed in terminal, and there will be a `solution.csv` file generated in folder `TravelingSalesmanProblm`.

To read `solution.csv`, you can use command `cat` to see every city number:

```bash
cat solution.csv
```

Besides, if you want to verify the correctness of the code (i.e. whether the number of cities in `solution.csv` is equal to the number of cities in `.tsp` file), you can use command `cat [fileName] wc -l` in terminal:

```bash
cat solution.csv | wc -l
```

Then you'll see the number of cities in `solution.csv` in terminal.



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

    

## 3. Different methods in one solution

### 3.1 Hill Climbing

I've supplied four methods: SteepestAscent, RandomAscent, FirstAscent and FirstAscentWithRandomRestart for HillClimbing solution, and I made FirstAscent the default one for HillClimbing solution, so when you compile and run `HillClimbing.java`, you are actually using Hill Climbing FirstAscent.

So if you want to use other Hill Climbing methods instead of Hill Climbing with FirstAscent, you should firstly uncomment corresponding line in `HillClimbing.java`. Then compile and run the program.

- for Hill Climbing SteepestAscent, uncomment line 149-155 and comment line 167-172
- for Hill Climbing RandomAscent, uncomment line 168-164 and comment line 167-172
- for Hill Climbing FirstAscentWithRandomRestart, uncomment line 175-184 and comment line 167-172



### 3.2 Simulated Annealing

I've supplied two methods: basic Simulated Annealing(hereafter: basic_SA) and Dynamic Simulated Annealing-Cooling Enhancer-Modified Acceptance Possibility Simulated Annealing(hereafter: DSA_CE_MAP_SA) for SimulatedAnnealing solution, and I made DSA_CE_MAP_SA as the default one for SimulatedAnnealing solution, so when you compile and run `SimulatedAnnealing.java`, you are actually using DSA_CE_MAP_SA.



So if you want to use basic_SA, you should firstly uncomment line 155-164 and comment line 167-178 in `SimulatedAnnealing.java`. Then compile and run the program.



## 3.3 Tabu Search

I've only support one method for TabuSearch solution, the one delivered in the lecture.
