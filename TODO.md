# Search Algorithm Implementation TODO

## Exercise 1: Depth First Algorithm
- [x] Download and import source code
- [ ] Complete SimpleTest.java to run depth-first search
- [ ] Test the implementation with the simple graph example

## Exercise 2: Uniform Cost Search
- [ ] Create new package for uniform cost implementation
- [ ] Create UniformCostProblem class
  - [ ] Implement Problem interface
  - [ ] Add edge costs to the graph
- [ ] Create UniformCostSearch class
  - [ ] Extend AbstractTreeSearch
  - [ ] Implement using ArrayList for nodes
  - [ ] Implement chooseLeafNode to select minimum pathCost
- [ ] Create test class for UniformCostSearch

## Exercise 3: Best First Algorithm
- [ ] Create HeuristicState class
  - [ ] Inherit from SimpleState
  - [ ] Add static int values for each node
  - [ ] Implement getHValue() method
- [ ] Create BestFirstSearch class
  - [ ] Extend AbstractTreeSearch
  - [ ] Implement node selection based on heuristic
  - [ ] Add logic to only add new nodes to frontier
  - [ ] Modify isGoal to return true when node value is 0
- [ ] Create test class for BestFirstSearch

## Exercise 4: Bottle Problem & A* Algorithm
- [ ] Create Bottles class
  - [ ] Implement state representation for bottles
  - [ ] Add heuristic function
- [ ] Create Actions enum
  - [ ] Define all possible bottle actions
- [ ] Create BottleProblem class
  - [ ] Implement Problem interface
  - [ ] Set action cost to 1
- [ ] Implement A* Algorithm
  - [ ] Modify BestFirstSearch to use both heuristic and path cost
  - [ ] Add closed list functionality
  - [ ] Implement node expansion rules with closed/open list checks 