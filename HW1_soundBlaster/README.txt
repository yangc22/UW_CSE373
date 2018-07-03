1. How did you test that your stack implementations were correct?

Answer: I just write a test client program to test all the behaviors of the implementations of stack. I also write the toString method which is easy to visualize the results of the test.

2. Your array stacks start with a small array and double in size if they become full. For an input file with 1 million lines, where each line will be pushed on the stack, how many times would this resizing occur? What about with 1 billion lines or 1 trillion lines (assuming the computer had enough memory)? Explain your answer.

Answer: For an input file with 1 million lines, this resizing will occur log2(1,000,000 / 10) times. For the input with 1 billion or 1 trillion lines, it will occur log2(1 billion / 10) or log2(1 trillion / 10) times. Because, the base case is a input with size 10, so the occurrence times should be divided by 10. And then each time, we just double the size, so it should be the logarithmic with base 2.

3. Suppose that, instead of a DStack interface, you were given a fully-functional FIFO Queue class. How might you implement this project (i.e., simulate a Stack) with one or more instances of a FIFO Queue? 

Answer: First, I will create a instance Queue q1, and enqueue all the elements, then create another instance of Queue q2 and dequeue the elements in q1 until the size of q1 is 1. Then create a new instance of Queue q3 and dequeue the elements in q2 and enqueue them into q3 until the size of q2 is 1 and enqueue this final element into q1. And then dequeue the elements in q3 and enqueue them into q2 until the size of q3 is 1 and enqueue this final element into q1. And iterate until both q2 and q3 are empty.
pseudocode:
Queue q1
public void push() {
if q1.isEmpty
	q1.enqueue(d)
else
	Queue q2 = new Queue
	q1.enqueue(d)
	while q1.size() != 1 {
	q2.enqueue(q1.dequeue)
	}
	while !q2.isEmpty() {
	q1.enqueue(q2.dequeue())
	}
}
public void pop() {
if q1.isEmpty()
	throw new EmptyQueueException()
else
	return q1.dequeue();
}

4. In the previous question, what trade-offs did you notice between a Queue implementation of a Stack and your original array-based implementation? Which implementation would you choose, and why?

Answer: For the queue implementation of a stack, I need to create one more data structure (a queue) but I don’t need to resize the data structure if the input size is too large. I would prefer the queue implementation, because there are more waste space for the array-based implementation.

5. Include a description of how your project goes "above and beyond" the basic requirements (if it does).

Answer: The array-based implementation can save a lot of space with the resize.

6. What did you enjoy about this assignment? What did you not enjoy? What could you have done better?

Answer: I enjoy the write-up parts of the assignment because it forced me to think the problem more thoroughly but I don’t enjoy the conversion between the .wav and .dat file. It makes me so annoyed.

7. What else, if anything, would you would like to include related to this homework?

Answer: If we could have a video record for the lecture to help us review what we didn’t get during the class, it would be perfect.

Secret: The scent of bitter almonds always reminded him of the fate of unrequired love.