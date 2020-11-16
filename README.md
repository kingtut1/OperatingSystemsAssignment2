# CSIS 3810 Operating Systems
## Design Programing Project Assignment 2

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;There is a system where there are three producer threads A, B, and C, each creating
output of a unique size (IE. A creates size X, B creates size Y, and C creates size Z).
This system also has four identical consumer threads D, E, F, and G, which can
consume any of the produced elements of size X, Y, or Z. A special repository can hold
up to N elements from A of size X, M elements from B of size Y, and L elements from C
of size Z.   



&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Your task is to write a program in Java using threads and semaphores that coordinates
the producer and consumer thread activity such that a deadlock or indefinite
postponement will not occur. Within the solution to this problem, you must use the
semaphore primitives (acquire and release) to synchronize the producer and consumer
threads. The producer and consumer code must not use any other synchronization
technique outside of the semaphore class primitives of acquire and release. Assume
all threads run at random speeds and production and consumption are infinite.



&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;In your implementation each producer and consumer are required to run as separate
threads. Include print statements that show the state of your system such that the
events can be traced from the output. Use indentation, blank lines and specific labels
to show your output in a clear and concise manner.