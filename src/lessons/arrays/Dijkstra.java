package com.java.lessons.arrays;

/*
Approach 1: Dijkstra's Algorithm
        Intuition

        If we forget about the part where the number of stops is limited, then the problem simply becomes the shortest
        path problem on a weighted graph, right? We can treat this as a graph problem where:

        the cities can be treated as nodes in a graph
        the connections between each of the cities can be treated as the edges and finally
        the cost of going from one city to another would be the weight of the edges in the graph.
        It's important to model the problem in a way that standard algorithms or their slight variations can be used for
         the solutions. Whenever we have a problem where we're given a bunch of entities and they have some sort of connections between them, more often than not it can be modeled as a graph problem. Once you've figured out that the question can be modeled as a graph problem, you then need to think about the various aspects of a graph i.e.

        directed vs undirected
        weighted vs unweighted
        cyclic vs acyclic
        These aspects will help define the algorithm that you can consider for solving the problem at hand. For example
        a standard rule of thumb that is followed for solving shortest path problems is that we mostly use Breadth-first
        search for unweighted graphs and use Dijkstra's algorithm for weighted graphs. An implied condition to apply the
        Dijkstra's algorithm is that the weights of the graph must be positive. If the graph has negative weights and can
         have negative weighted cycles, we would have to employ another algorithm called the Bellman Ford's. The point here
          is that the properties of the graph and the goal define the kind of algorithms we might be able to use.

        Coming back to the original statement at the beginning of the article. If we don't consider the part where the
        number of stops is limited, this problem becomes a standard shortest paths problem in a weighted graph with positive
         weights and hence, it becomes a prime candidate for Dijkstra's. As we all know, Dijkstra's uses a min-heap (priority queue) as the main data structure for always picking out the node which can be reached in the shortest amount of time/cost/weight from the current point starting all the way from the source. That approach as it is won't work out for this problem.

        First of all, we need to keep track of the number of stops taken to reach a node (city), in addition to the shortest path from the source node. This is important because if at any point we find that we have exhausted KK stops, we can't progress any further from that node because the number of stops are bounded by the problem. Let's consider a simple example and run through it with the basic Dijkstra's algorithm and see why we might run into a problem with the off-the-shelf code i.e. Dijkstra's without any modifications.


        Now suppose that we want to go from the source node A in the graph above to the destination node E via the cheapest possible route with at most 2 stops. Let's ignore the number of stops for now and see how the usual Dijkstra would unfold and pick the nodes. So first of all, we will consider the neighbors of the source node and add them to our min-heap. Next, we will pick the element with the current shortest distance which would be D with a value of 5 as opposed to B with 7.


        Moving on, the next node that will be picked is B since it has the current shortest distance from the source. Let's see what the heap looks like once we pick B and process its neighbors. Note that according to the algorithm, once a node has been processed i.e., once a node is popped from the min-heap, we never consider that node again in some other node's neighbors i.e., we never add it again to the heap down the line. This is because of the greedy nature of the algorithm. When a node is removed from the heap, it is guaranteed that the distance from the source at that point is the shortest distance. The processed nodes are marked in blue in the figures here.


        Moving on, the algorithm will pick C and its neighbor E will be added into the heap. You'll notice that there are two nodes containing the city E which is fine since E hasn't been processed yet and this just means there are multiple paths of reaching E.


        Next, we will remove the node E with a distance of 12 from the source and 3 stops from the source. At this point, we cannot go any further i.e. we cannot consider its neighbor because We have already exhausted the number of stops in this example. So, we don't add the neighbor which also happens to be the destination node to the heap. The only node left in the heap is E with a distance of 15 from source and 2 stops from the source.

        Here's the problem now. We will not consider this node because we have already processed the node E in the previous step. Clearly, the distance 15 is greater than 12. So Dijkstra's will discard this heap node and the algorithm will finish, without ever reaching the destination!

        The thing we need to modify here is that we need to re-consider a node if the distance from the source is shorter than what we have recorded. So we won't change the min-heap's priority which is to pick nodes with the shortest distance from the source. However, if we ever encounter a node that has already been processed before but the number of stops from the source is lesser than what was recorded before, we will add it to the heap so that it gets considered again! That's the only change we need to make to make Dijkstra's compliant with the limitation on the number of stops.

        Algorithm

        Initialize a min-heap or a priority queue. Let's call it H for our algorithm.

        We will need a couple of arrays here. One would be for maintaining the shortest distances of each node from the source and another one would be for maintaining the shortest number of stops from the source.

        Next, we need to convert the input into an adjacency matrix format. So, we will process the given input and build an adjacency matrix out of it.

        Add (\text{source}, 0, 0)(source,0,0) into the heap. The middle value represents the current shortest distance from the source and the last value represents the current minimum number of stops from the source to reach this node.

        We assume that these values for all the other nodes in the graph are inf.

        We continue processing the nodes until either of the following conditions are met:

        We reach the destination node or
        We exhaust the heap which would mean we were not able to reach the destination at all.
        At each step, we remove a node from the heap i.e. ExtractMin operation on the min-heap. This would represent the node with the shortest distance from the source amongst the ones in the heap. Let's call this node C.

        We iterate over all of C's neighbors which we can obtain from our adjacency matrix. For each neighbor, we check if the value \text{dC} + \text{W}_\text{C, V}dC+W
        C, V
        ​
        is less than \text{dV}dV where VV represents the neighbor node, \text{dC}dC and \text{dV}dV represent the shortest distances (from the dictionary) of these nodes from the source and finally, \text{W}_\text{C, V}W
        C, V
        ​
        represents the weight (cost of the flight) from node (city) CC to VV.

        If this is not the case then we check if number of stops for node CC + 1 is lower than the number of stops for the node VV (from the other dictionary). If that is the case, then it means there is a path from the source to the node VV which is slightly expensive than what we have right now, but it has lesser stops and hence, it should be considered.

        If either of the two conditions above are satisfied, we add the node VV to the heap with updated distance and number of stops. In any case, we will update the corresponding dictionary as well.


        Complexity Analysis

        Time Complexity: Let EE represent the number of flights and VV represent the number of cities. The time complexity is mainly governed by the number of times we pop and push into the heap. We will process each node (city) atleast once and for each city popped from the queue, we iterate over its adjacency matrix and can potentially add all its neighbors to the heap. Thus, the time taken for extract min and then addition to the heap (or simply, heap replace) would be \text{O}(\text{V}^2 \cdot \text{log V})O(V
        2
        ⋅log V).

        Let's talk a bit more about the implementation of Dijkstra's here. The traditional algorithm is not exactly written the way we've explained above.
        The traditional algorithm adds all the nodes into the heap with the source having a distance value of 0 and all others having a value inf.
        When we process the neighbors of a node and find that a particular neighbor can be reached in a shorter distance (or lesser number of stops), we update its value in the heap. In our implementation, we add a new node with updated values rather than updating the value of the existing node. To do that, we will need another dictionary that will probably keep the index location for a node in the heap or something like that. This would be necessary because a heap is not a binary search tree and it doesn't have any search properties for quick search and updates.
        If we keep the number of nodes in the heap fixed to VV, then the complexity would be \text{O}((\text{V} + \text{E}) \cdot \text{log V})O((V+E)⋅log V). Granted, in our case, the heap might contain more than \text{V}V nodes at some point due to the same city being added multiple times. Therefore, the complexity would be slightly more. That is not being accounted for here since that is an implementation detail and not necessary for the algorithm we discussed here.
        Yet another point to keep in mind here is that we are using an adjacency matrix rather than adjacency list here. The typical Dijkstra's algorithm would use an adjacency list and that brings down the complexity slightly because you don't "check" if a connection exists or not unlike in adjacency matrix. However, since the number of nodes are very less for this problem, we preferred to take the route of adjacency matrix as that gives us sequential access to elements and leads to speed-ups due to cache localization.
        Space Complexity: \text{O}(\text{V}^2)O(V
        2
        ) is the overall space complexity. \text{O}(\text{V})O(V) is occupied by the two dictionaries and also by the heap and \text{V}^2V
        2
        by the adjacency matrix structure. As mentioned above, there might be duplicate cities in the heap with different distances and number of stops due to our implementation. But we are not taking that into consideration here. This is the space complexity of the traditional Dijkstra's and it doesn't change with the algorithm modifications (not the implementation modifications) we've done here.


        Approach 2: Depth-First-Search with Memoization
        Intuition

        This problem can easily be modeled as a dynamic programming problem on graphs. What does a dynamic programming problem entail?

        It has a recursive structure.
        A bunch of choices to explore at each step.
        Use the optimal solutions for sub-problems to solve top-level problems.
        A base case.
        This problem fits the bill. We have a dedicated start and endpoint. We have a bunch of choices for each node in the form of its neighbors. And, we want to minimize the overall shortest distance from the source to the destination which can be represented as a recursive structure in terms of shortest distances of its neighbors to the destination. So, we can apply a dynamic programming approach to solve this problem. We'll look at a recursive implementation here with memoization first and then talk about the iterative approach as well.

        As with any recursive approach, we need to figure out the state of recursion. There are two parameters here which will control our recursion. One is obviously the node itself. The other is the number of steps. Let's call our recursion function recurse and define what the state of recursion looks like. \text{recurse}(\text{node},\text{stops})recurse(node,stops) will basically return the shortest distance for us to reach the destination from \text{node}node considering that there are stops left. This being said, it's easy to figure out what the top-level problem would be. It would be \text{recurse}(\text{0},\text{K})recurse(0,K).

        Let's consider the following graph to understand why memoization (or caching) is required here.


        Say we start the source node A and build our recursion tree from there. There are two possible routes of getting to the node C with exactly 2 stops. Let's look at what these are.


        While the cost of these two paths is different, once we are at the node C, we have 2 steps less than what we had when we started off from the source node A. Our recursion representation doesn't care about the path you took to get to a node. It is about the shortest (cheapest) path from the current node with the given number of steps to get to a destination. In that sense, both these scenarios are exactly the same because both lead us to the same recursion state which is (\text{recurse}(\text{C}, \text{K-2}))(recurse(C,K-2)) and hence, the result for this recursion state can be cached or memoized.

        Algorithm

        We'll define a function called recurse which will take two inputs: node and stops.

        We'll also define a dictionary memo of tuples that will store the optimal solution for each recursion state encountered.

        At each stage, we'll first check if we have reached the destination or not. If we have, then no more moves have to be made and we return a value of 0 since the destination is at a zero distance from itself.

        Next, we check if we have any more stops left. If we don't then we return inf basically representing that we cannot reach the destination from the current recursion state.

        Finally, we check if the current recursion state is cached in the memo dictionary and if it is, we return the answer right away.

        If none of these conditions are met,we progress in our recursion. For that we will iterate over the adjacency matrix to obtain the neighbors for the current node and make a recursive call for each one of them. The node would be the neighboring node and the number of stops would incremeneted by 1.

        To each of these recursion calls, we add the weight of the corresponding edge i.e.

        recurse(neighbor, stops + 1) + weight(node, neighbor)
        We need to return the result of recurse(src, 0) as the answer.


        Complexity Analysis

        Time Complexity: The time complexity for a recursive solution is defined by the number of recursive calls we make and the time it takes to process one recursive call. The number of recursive calls we can potentially make is \text{O}(\text{V} \cdot \text{K})O(V⋅K). In each recursive call, we iterate over a given node's neighbors. That takes time O(\text{V})O(V) because we are using an adjacency matrix. Thus, the overall time complexity is \text{O}(\text{V}^2 \cdot \text{K})O(V
        2
        ⋅K).
        Space Complexity: \text{O}(\text{V} \cdot \text{K} + \text{V}^2)O(V⋅K+V
        2
        ) where \text{O}(\text{V} \cdot \text{K})O(V⋅K) is occupied by the memo dictionary and the rest by the adjacency matrix structure we build in the beginning.

        Approach 3: Bellman-Ford
        Intuition

        Let's look at the official definition of the Bellman-Ford algorithm straight from Wikipedia:

        Like Dijkstra's algorithm, Bellman-Ford proceeds by relaxation, in which approximations to the correct distance are replaced by better ones until they eventually reach the solution. In both algorithms, the approximate distance to each vertex is always an overestimate of the true distance and is replaced by the minimum of its old value and the length of a newly found path.

        However, Dijkstra's algorithm uses a priority queue to greedily select the closest vertex that has not yet been processed, and performs this relaxation process on all of its outgoing edges; by contrast, the Bellman-Ford algorithm simply relaxes all the edges and does this {|V|-1}∣V∣−1 times, where |V|∣V∣ is the number of vertices in the graph. In each of these repetitions, the number of vertices with correctly calculated distances grows, from which it follows that eventually, all vertices will have their correct distances. This method allows the Bellman-Ford algorithm to be applied to a wider class of inputs than Dijkstra.

        The term relax an edge simply means that for a given edge U -> V we check if \text{dU} + W_{\text{U,V}} < \text{dV}dU+W
        U,V
        ​
<dV where \text{dU}dU and \text{dV}dV represent the shortest path distances of these nodes from the source right now. To relax an edge means to see if the shortest distance can be updated or not.

        An important part to understanding the Bellman Ford's working is that at each step, the relaxations lead to the discovery of new shortest paths to nodes. After the first iteration over all the vertices, the algorithm finds out all the shortest paths from the source to nodes which can be reached with one hop (one edge). That makes sense because the only edges we'll be able to relax are the ones that are directly connected to the source as all the other nodes have shortest distances set to inf initially.

        Similarly, after the (\text{K}+1)^{\text{th}}(K+1)
        th
        step, Bellman-Ford will find the shortest distances for all the nodes that can be reached from the source using a maximum of K stops. Isn't that what the question asks us to do? If we run Bellman-Ford for \text{K} + 1K+1 iterations, it will find out shortest paths of length KK or less and it will find all such paths. We can then check if our destination node was reached or not and if it was, then the value for that node would be our shortest path!

        Let's quickly look at a couple of iterations of Bellman-Ford on a sample graph to understand how relaxation works and how \text{K+1}K+1 iterations can possibly give us our solution. The image below showcases the initial setup before the first iteration of Bellman-Ford is executed.


        Let's look at what the graph looks like after a single iteration.


        It's important to understand the meaning of what we said in the figure above. We are not saying that after the first iteration we will find the absolute shortest distance from A to B and D. We are just saying that the shortest distance using a single edge only will be found after first iteration. What happens in the next iteration? Well, we will find all the shortest paths that can be reached from the source by using at-most 2 edges. In this example, since the values for nodes B and D were updated in the previous iteration, they will be re-used in the next iteration to relax edges B -> E, B -> C, and D -> C.

        Isn't that what Dynamic Programming is all about......

        Well, yes! Using the optimal solutions to sub-problems to find optimal solutions to bigger problems. We use the optimal solutions to shortest paths using 1 edge to find shortest paths using 2 edges and so on.


        We'll go one final iteration here since this is where things get interesting and this will bring some more clarity. The node E was discovered in the second iteration and we have the value 15 corresponding to it. However, one of the incoming edges C -> E wasn't relaxed in the second iteration because C was also discovered during that iteration. Now that we have a non-infinite value associated with C, we can use it to relax the edge C -> E and that leads to an even shorter path from A ... E!


        Another important thing to note about this algorithm is that we don't need to build an adjacency matrix. This algorithm simply iterates over the edges of the graph and that information is already available in the input for the program. So we save on space there as opposed to other algorithms which we've seen.

        Algorithm

        We have a loop that does K + 1 iterations. The plus one is because we need to find the cheapest flight route with at most K stops in between. That translates to K + 1 edges at most.

        In each iteration, we loop over all the edges in the graph and try to relax each one of them. Again, note that the edges or the flights are already given to us in the input and don't need to build any kind of adjacency list or matrix structure which is otherwise standard for other graph algorithms.

        After K + 1 iterations, we check if the destination has been reached or not. If it's been discovered, then the distance at that point will be the shortest using at most K + 1 edges.

        We use an array to store the current shortest distances of each node from the source. This is possible because the number of nodes is less and we don't need to use a dictionary here. However, a single array is not sufficient here because any values updated in a particular iteration cannot be used to update other values in the same iteration. Thus, we need another distance array which will kind of server as values in the previous iteration. So we essentially use 2 arrays of size VV and we swap between them in each iteration i.e.

        Iteration-0 ----
        Array-1 is the main array
        Array-2 becomes the previous array
        Iteration-1 ----
        Array-2 is the main array
        Array-1 becomes the previous array
        Let's look at how the two arrays look like at the start of the first iteration. We'll take a look at a couple of iterations so that the it's easier to understand the implementations.


        We discovered two new vertices which are directly connected from the source and their corresponding distances were updated accordingly.


        Now let's look at how the two arrays would look like at the start of the second iteration. Now the roles would be reversed. The current array in the previous iteration now servers as the previous array.


        Notice how the two arrays have swapped roles. You might be thinking that even though the red array is the current one, it doesn't have the latest values 7 and 5. Well, they will be used for the calculation of distance of node C and also, they will be copied over (re-calculated again due to the node A in previous array). Let's see how the two arrays look after the second iteration is complete.



        Complexity Analysis

        Time Complexity: O(\text{K} \cdot \text{E})O(K⋅E) since we have \text{K}+1K+1 iterations and in each iteration, we go over all the edges in the graph.
        Space Complexity: \text{O}(\text{V})O(V) occupied by the two distance arrays.

        Approach 4: Breadth First Search
        Intuition

        We say that the breadth-first search is a good algorithm to use if we want to find the shortest path in an undirected, unweighted graph. The claim for BFS is that the first time a node is discovered during the traversal, that distance from the source would give us the shortest path. The same cannot be said for a weighted graph. For a weighted graph, there is no correlation between the number of edges composing the path and the actual length of the path which is composed of the weights of all the edges in it. Thus, we cannot employ breadth-first search for weighted graphs.

        Breadth-first search has no way of knowing if a particular discovery of a node would give us the shortest path to that node. And so, the only possible way for BFS (or DFS) to find the shortest path in a weighted graph is to search the entire graph and keep recording the minimum distance from source to the destination vertex.

        That being said, Breadth-first search is actually a great algorithm of choice for this problem because the number of levels to be explored by the algorithm is bounded by K

        The number of levels that the search would go to is limited by the value K+1 in the question. So essentially, we would be trying to find the shortest path, but we won’t have to explore the entire graph as such. We will just go up to the level K+1 and we just need to return the shortest path to the destination (if reachable by level K+1) at the end of the algorithm.

        An important consideration here is the size of the queue. We need to control it somehow otherwise, even at very small depths, the graph could grow exponentially. For this very problem however, we will be able to bound the size of a given level (and hence the queue) by VV, the number of vertices in the graph. Let's think about what it means to encounter the same node multiple times during breadth first traversal.

        Since we will be going only till the level K+1, we don't really have to worry about the number of stops getting exhausted or something. So if the number of stops are out of the way, the only way we will consider adding a node again to the queue is if we found a shorter distance from the source than what we already have stored for that node. If that is not the case then on encountering a node again during the traversal, we can safely discard it i.e not add it to the queue again.

        Since this is weighted graph, we cannot assume anything about the shortest distance from source to a node when its first discovered after being popped from the queue. We will have to go to all the K+1 levels and once we've exhausted K+1 levels, we can be sure that the shortest distances we have are the "best" we can find with K+1 edges or less.

        Algorithm

        This is standard BFS and we'll be using a queue here. Let's call it Q.
        We'll need a dictionary to keep track of shortest distances from the source. An important thing to note in this
        approach is that we need to keep a dictionary with the node, stops as the key. Basically, we need to keep track of the shortest distance of a node from the source provided that it takes stops stops to reach it.
        Add the source node to the queue. There are multiple ways of tracking the level of a node during breadth-first traversal. We'll be using the size of the queue at the beginning of the level to loop over a particular level.
        We iterate until we exhaust the queue or K+1 levels whichever comes first.
        For each iteration, we pop a node from the queue and iterate over its neighbors which we can get from the adjacency matrix.
        For each of the neighbors, we check if the current edge improves that neighbor's shortest distance from source or not. If it does, then we update the shortest distance dictionary (array) accordingly and also add the neighbor to the queue.
        We continue doing this for until one of our terminal conditions are met.
        We will also maintain an ans variable to track the minimum distance of the destination from the source. At each step, whenever we update the shortest distance of a node from source, we check if that node is the destination and if it is, we will update the ans variable accordingly.
        At the end, we simply check if we were able to reach the destination node by looking at the ans variable's value. If we did reach it, then the recorded distance would be the shortest in under K hops (or K + 1 edges at most).

        Complexity Analysis

        Time Complexity: O(\text{E} \cdot \text{K})O(E⋅K) since we can process each edge multiple times depending upon the improvement in the shortest distances. However, the maximum number of times an edge would be processed is bounded by \text{K + 1}K + 1 since that's the number of levels we are going to explore in this algorithm.
        Space Complexity: O(\text{V}^2 + \text{V} \cdot \text{K})O(V
        2
        +V⋅K). The first part is the standard memory occupied by the adjacency matrix and in addition to that, the distances dictionary can occupy a maximum of O(\text{V} \cdot \text{K})O(V⋅K).

*/
public class Dijkstra {
}
