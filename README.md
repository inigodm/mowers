# Assumptions 
Assumed that the robot will stop if it tries to go outside the grid.
Assumed that the robot will be initially inside the grid: if is outside it will not be used and will not throw any exception: is assumed to be a valid input without output
Assume white characters and lowercases as valid inputs

# Spring 

Spring is a big overkill: I am not using anything from it an, so, I should remove it. But being so standard to use it I will leave it for now.

## To execute it

To execute it make a file (p.e. test) containig, p.e:
```
5 5
1 2 N
LMLMLMLMM
3 3 E
MMRMMRMRRM
```

and run this command in the directory where the `build.gradle` is located:

```bash
./gradlew bootRun --args="$(cat test)" --warning-mode none --quiet```
```

## better way to execute it

Or else you can run ```scripts/run-local.sh``` and use the input file which is near it to add your input
