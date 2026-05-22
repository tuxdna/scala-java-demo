The Core Hallmarks of Functional Programming:

- Pure Functions: A pure function always returns the same output for the same input and has no side effects. It does not mutate global state, modify arguments, or perform external I/O.
- Immutability: Data is never modified after creation. Instead of changing an existing structure, you create a new one, which prevents unexpected state changes.
- First-Class and Higher-Order Functions: Functions are treated like data. They can be assigned to variables, stored in data structures, passed as arguments, and returned from other functions.
- Function Composition: Small, focused functions are combined to create more complex behavior, improving modularity, reuse, and readability.
- Declarative vs. Imperative Control Flow: Functional programming focuses on what to compute rather than how to iterate step-by-step, often using `map`, `filter`, and `reduce` instead of explicit loops.

Key operators in FP: `map`, `filter`, `fold` and `reduce`

## Declrarative vs Imperative

Imperative code:

```c
#include<stdio.h>

int main() {
  int a[]= {1,2,3,4,5,6,7,8,9};
  int sum = 0;
  for(int i = 0; i <9 ; i++) {

    sum += a[i];
    printf("Sum is %d\n", sum);
  }
}
```

```bash
make sum ; ./sum
```


Declarative code:

```scala
scala> val l = List(1,2,3,4,5,6,7,8,9,10)
val l: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

scala> l.reduce((x: Int, y: Int) => x + y)
val res10: Int = 55

scala> l.filter((x: Int) => x % 2 == 0).reduce((x: Int, y: Int) => x + y)
val res11: Int = 30
```


```python
>>> from functools import reduce
>>> reduce(lambda a, b: a + b, range(1, 11))
55
>>> reduce(lambda a, b: a + b, [ x for x in range(1, 11) if x % 2 == 0])
30
```

```pyspark
# Declarative RDD example in PySpark
raw_rdd = sc.textFile("hdfs://data.txt")

# You declare WHAT you want to happen; Spark figures out HOW to distribute the loops across a cluster
processed_rdd = raw_rdd.filter(lambda line: "ERROR" in line) \
                       .map(lambda line: line.split("\t")[1])

# The calculation finally triggers here
result = processed_rdd.collect()

```
