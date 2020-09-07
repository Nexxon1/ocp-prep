# OCP Preparation

This project shows some basic Code examples needed for the OCP exam.
It is separated into packages that represent the chapters in the OCP book.

## Chapter 4 - Functional Programming
### Functional interfaces
The package *'funcinterfaces'* shows the common functional interfaces
that were added with Java8 to the *java.util.function* package with simple use cases.

| Functional Interfaces | #Parameters | Return Type | Single Abstract Method |
|-----------------------|--------------|-------------|------------------------|
| Supplier<T>           | 0            | T           | get                    |
| Consumer<T>           | 1 (T)        | void        | accept                 |
| BiConsumer<T, U>      | 2 (T, U)     | void        | accept                 |
| Predicate<T>          | 1 (T)        | boolean     | test                   |
| BiPredicate<T, U>     | 2 (T, U)     | boolean     | test                   |
| Function<T, R>        | 1 (T)        | R           | apply                  |
| BiFunction<T, U, R>   | 2 (T, U)     | R           | apply                  |
| UnaryOperator<T>      | 1 (T)        | T           | apply                  |
| BinaryOperator<T>     | 2 (T, T)     | T           | apply                  |

### Optional
The Optional type was also added with Java 8. It can be used to express "we don't know" or "not applicable".
An Optional is like a Box that might have a value inside or might be empty. 
Returning an Optional is a clear statement in the API that there might not be a value in there. 
This also avoids null values and NPE's.

The basics are shown inside the *'optional'* package.


### Streams
A stream is a sequence of data. 
Streams are comparable with an assembly line in a factory
A Stream consists of 3 parts:
* 1 Source: Where the stream comes from
* 0..n Intermediate operations: Transforms the stream into another one.
Since streams use lazy evaluation, the intermediate operations do not run until the terminal operation runs
* 1 Terminal operation: Actually produces a result. Streams can only be used once. The stream is no longer
valid after a terminal operation completes.

Source --> Intermediate operations --> Output from terminal operation

---
Generally the flow is like this: 
1. Elem 1 gets passed from the Source to intermediate operation 1
2. Elem 1 gets passed from intermediate operation 1 to intermediate operation 2 (and so on for all intermediate operations)

However, there are exceptions: e.g. the intermediate operation *sorted()* needs all elements of the stream to work.

---

Examples are shown in the *'streams'* package.



| Method                              | What happens for Infinite Streams? | Return Value | Reduction |
|-------------------------------------|------------------------------------|--------------|-----------|
| count()                             | Does not terminate                 | long         | Yes       |
| min()/ max()                        | Does not terminate                 | Optional<T>  | Yes       |
| findAny()/ findFirst()              | Terminates                         | Optional<T>  | No        |
| allMatch()/ anyMatch()/ noneMatch() | Sometimes terminates               | boolean      | No        |
| forEach()                           | Does not terminate                 | void         | No        |
| reduce()                            | Does not terminate                 | Varies       | Yes       |
| collect()                           | Does not terminate                 | Varies       | Yes       |


