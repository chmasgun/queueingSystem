# queueingSystem
Simulation of queueing systems with exponentially distributed random variables. This algorithm discretizes the continuous distributions by
analizing the time intervals as seconds. It can be considered continuous with this approach since the program aims to simulate 'hours' and
it makes 3600 iterations per hour. Parameter mu denotes process rate of the system, e.g. if mu is 5, we can say that system handles 5 jobs
per hour on average. Parameter lambda denotes arrival rate into the system, e.g. if lambda is 3, we can say that 3 jobs come into the 
system per hour on average. These parameters are transformed into the second (hour/3600) basis and converted into a probability 
that represent occurence probability per second. Other than these stochastic variables, there are some deterministic variables as well.
ServiceCapacity notates the maximum capacity of the system. When system is full, any extra arrival will be refused due to this limitation.
Service notates how many services work simultaneously. More service means faster process and shorter queue times, similar to our real life
experiences. ShowArrive boolean turns on the output for arrivals. If it is true, then a line is printed out to notify the user. Similarly, 
showCompletion boolean controls finished job outputs. ShowRefused enables us to see if someone is refused from the system due to low 
service capacity.

More than informing the user with arrivals, job completions and refusals, this algorithm outputs total system flow and how many people are 
still inside the system in first two lines. Rest of the lines show the probabilities that there are N people in the system at any given 
time. Last four lines show expected number of people in the queue, expected number of people in the system, expected waiting time in the 
queue and expected waiting time in the system, respectively. Running this algorithm for minimum of 1000 hours gives very close outputs to
theoretical calculations as a result of central limit theorem.
