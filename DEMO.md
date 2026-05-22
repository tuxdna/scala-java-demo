# The Core Hallmarks of Functional Programming:

* Pure Functions
* Immutability
* First-Class and Higher-Order Functions
* Function Composition
* Declarative over Imperative Control Flow

# Pure Functions

- Pure Functions: A pure function always returns the same output for the same input and has no side effects. It does not mutate global state, modify arguments, or perform external I/O.


## Scala Examples

```scala
scala> def pureFunction(x: Int) = {x*x}
def pureFunction(x: Int): Int

scala> def impureFunction(x: Int) = { print(x); x*x }
def impureFunction(x: Int): Int

scala> impureFunction(3)
3val res8: Int = 9

scala> var y = 0
def impureFunction(x: Int) = { 
  // mutate some state of the system
  println(y)
  y += 1
  x*x + y
}

scala> pureFunction(3)
val res12: Int = 9

scala> pureFunction(3)
val res13: Int = 9

scala> impureFunction(3)
0
val res6: Int = 10

scala> impureFunction(3)
1
val res7: Int = 11

scala> impureFunction(3)
2
val res8: Int = 12

```

# Immutability


- Immutability: Data is never modified after creation. Instead of changing an existing structure, you create a new one, which prevents unexpected state changes.


## Scala examples


```scala
scala> 1 + 1
val res0: Int = 2

scala> val x = 129 + 90
val x: Int = 219

scala> x.getClass()
val res1: Class[Int] = int

scala> val words = List("Java", "Scala", "Kotlin")
val words: List[String] = List(Java, Scala, Kotlin)

scala> words.getClass()
val res2: Class[? <: List[String]] = class scala.collection.immutable.$colon$colon

scala> words ++ List("and", "JavaScript")
val res3: List[String] = List(Java, Scala, Kotlin, and, JavaScript)

scala> val words2 = java.util.ArrayList()
val words2: java.util.ArrayList[Object] = []

scala> words2.add("and"); words2.add("JavaScript")
val res4: Boolean = true
val res5: Boolean = true

scala> words2
val res6: java.util.ArrayList[Object] = [and, JavaScript]

scala> case class User(name: String, age: Int)
// defined case class User

scala> val user1 = User("Jon", 43)
val user1: User = User(Jon,43)

```


# First-Class and Higher-Order Functions

- First-Class and Higher-Order Functions: Functions are treated like data. They can be assigned to variables, stored in data structures, passed as arguments, and returned from other functions.


# Function Composition


- Function Composition: Small, focused functions are combined to create more complex behavior, improving modularity, reuse, and readability.- Declarative vs. Imperative Control Flow: Functional programming focuses on what to compute rather than how to iterate step-by-step, often using `map`, `filter`, and `reduce` instead of explicit loops.

## Python examples


```python

>>> f1 = lambda x: x*x
>>> print(f1)
<function <lambda> at 0x107f049a0>
>>> def do_square(some_function, value):
...     return some_function(value)
...
>>> do_square(f1, 3)
9
>>> def applier(some_function):
...
...     return lambda x: some_function(x)
...
>>>
>>> applier(f1)
<function applier.<locals>.<lambda> at 0x107f04a40>
>>> do_square(f1, "some_string")
Traceback (most recent call last):
  File "<python-input-9>", line 1, in <module>
    do_square(f1, "some_string")
    ~~~~~~~~~^^^^^^^^^^^^^^^^^^^
  File "<python-input-2>", line 2, in do_square
    return some_function(value)
  File "<python-input-0>", line 1, in <lambda>
    f1 = lambda x: x*x
                   ~^~
TypeError: can't multiply sequence by non-int of type 'str'
```

# Declarative over Imperative Control Flow


Key operators in FP:

 * `map`
    - Transforms the data via given function
    - Conceptually same as SQL functions and operators applied on some columns
 * `filter`
    - Selects data when predicate is True
    - Conceptually same as conditions in WHERE clause of SQL
 * `fold`
    - Successively applies a Binary function ( with provided accumulator value / zero value ), until no more items are left, which eventually returns one final value or zero value
    - Conceptually same as aggregate functions like, sum, avg etc. but more powerful and generic
    - There are other variants like `foldLeft`, `foldRight` etc.
 * `reduce`
    - Successively applies a Binary function (assumes first or last element as the accumulator), until no more items are left, which eventually returns one value
    - Does not work on empty collection of items
    - Same as `fold` without zero value
    - Conceptually same as aggregate functions like, sum, avg etc. but more powerful and generic
    - There are other variants like `reduceLeft`, `reduceRight` etc.


### Imperative code:

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


### Declarative code:

```
TABLE_NAME: numbers
+-------+
| value |
+-------+
|     1 | 
|     2 |
|     3 |
|     4 |
|     5 |
|     6 |
|     7 |
|     8 |
|     9 |
|    10 |
+-------+

SQL:

-- applicaton of fold or reduce
SELECT sum(value) FROM numbers;

-- application of filter() followed by reduce() / fold()
SELECT sum(value) FROM numbers WHERE value % 2 = 0; 
```

### Scala Example

```scala
scala> val l = List(1,2,3,4,5,6,7,8,9,10)
val l: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

scala> l.reduce((x: Int, y: Int) => x + y)
val res10: Int = 55

scala> l.filter((x: Int) => x % 2 == 0).reduce((x: Int, y: Int) => x + y)
val res11: Int = 30
```


### Python Example

```python
>>> from functools import reduce
>>> reduce(lambda a, b: a + b, range(1, 11))
55
>>> reduce(lambda a, b: a + b, [ x for x in range(1, 11) if x % 2 == 0])
30
```

### Pyspark Example

```pyspark
# Declarative RDD example in PySpark
raw_rdd = sc.textFile("hdfs://data.txt")

# You declare WHAT you want to happen; Spark figures out HOW to distribute the loops across a cluster
processed_rdd = raw_rdd.filter(lambda line: "ERROR" in line) \
                       .map(lambda line: line.split("\t")[1])

# The calculation finally triggers here
result = processed_rdd.collect()

```
