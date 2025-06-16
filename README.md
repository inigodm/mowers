# Assumptions 
* Assumed that the robot will stop if it tries to go outside the grid.
* Assumed that the robot will be initially inside the grid: if is outside it will not be used and will not throw any exception: is assumed to be a valid input and will be outputted as it entered
* Assume white characters and lowercases as valid inputs

# Spring 

Spring is a big overkill: I am not using anything from it and, so, I would remove it. But being so standard to use it I will leave it for now.

# Architecture

I have use hexagonal/onion/clean architecture, so the domain is not aware of the infra and the infra is not aware of the domain. Thinking that clearly a real program could change the input to something less complex than a simple text to parse.

I have not use events, design patters or abstractions because I have not need them and adding them would be adding complexity to the code. Don't take me wrong: I am very able to use them but I prefer not to use them in this case.

I have parse the input in infra so the domain is not aware of the input format. The domain is only aware of the commands and the grid. Validations are done in the infra layer for things about format as invalid characters, usually are more validations in domain but it has not been neccesary to do it in this case.

# Technical decisions

* I like to do those 'Errors', I find it more clean and exceptions are easier to track
* I like to create domain objects in the command (opinionated) because I find it cleaner than doing it in the usecase
* I was hesitant to name classes with a single character, like X, Y... but seeing that JetBrains does it too, I assume it's less ugly than I thought.
* I have tried to keep it simple, avoiding unnecessary complexity such as design patterns or abstractions. I only use abstractions when needed, and I have worked with design patterns so much that I have learned they can mostly be avoided by keeping the code simple.¯\_(ツ)_/¯
* Robot was a data class with inner data classes as Movement and Position, and I have moved them to a separate package outside the Robot class, not sure but I feel it more readable this way
* Robots is not needed, but usually is util to have a specific cllass to handle collections to manage the meaning of, as example, 'count' in the context of the domain, so I have added it. Usually I don't put them until needed but it adds low complexity IMHO
* I have put the less number of unit tests possible.

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

Or else you can run ```scripts/run-local.sh``` and use the ```input``` file which is near it to add your input
