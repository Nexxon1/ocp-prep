# Chapter 4 - Functional Programming
- Lambdas have the same access rules as inner classes
- Lambda expressions can access **static variables**, **instance variables**, **effectively final method parameters** and **effectively final local variables**
    - Effectively final means that you could add the final modifier without breaking the code
      
## Functional interfaces
The package `funcinterfaces` shows the common functional interfaces
that were added with Java8 to the `java.util.function` package with simple use cases.

| Functional Interfaces | #Parameters  | Return Type | Single Abstract Method |
|-----------------------|--------------|-------------|------------------------|
| Supplier&lt;T>        | 0            | T           | get                    |
| Consumer&lt;T>        | 1 (T)        | void        | accept                 |
| BiConsumer<T, U>      | 2 (T, U)     | void        | accept                 |
| Predicate&lt;T>       | 1 (T)        | boolean     | test                   |
| BiPredicate<T, U>     | 2 (T, U)     | boolean     | test                   |
| Function<T, R>        | 1 (T)        | R           | apply                  |
| BiFunction<T, U, R>   | 2 (T, U)     | R           | apply                  |
| UnaryOperator&lt;T>   | 1 (T)        | T           | apply                  |
| BinaryOperator&lt;T>  | 2 (T, T)     | T           | apply                  |

## Optional
The Optional type was also added with Java 8. It can be used to express "we don't know" or "not applicable".
An Optional is like a Box that might have a value inside or might be empty. 
Returning an Optional is a clear statement in the API that there might not be a value in there. 
This also avoids null values and NPE's.

The basics are shown inside the `optional` package.

| Method                  | When Optional Is Empty                       | When Optional Contains a Value |
|-------------------------|----------------------------------------------|--------------------------------|
| get()                   | Throws a NoSuchElementException              | Returns value                  |
| ifPresent(Consumer c)   | Does nothing                                 | Calls Consumer c with value    |
| isPresent()             | Returns false                                | Returns true                   |
| orElse(T other)         | Returns other parameter                      | Returns value                  |
| orElseGet(Supplier s)   | Returns result of calling Supplier           | Returns value                  |
| orElseThrow(Supplier s) | Throws exception created by calling Supplier | Returns value                  |

## Streams
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
With parallel streams the same intermediate operations can be executed in parallel. But this comes with an overhead so sequential streams are better for small Streams.
---

Examples are shown in the `streams` package.

These are possible terminal Stream operations:
(Reductions are a special type of terminal operation where all of the contents of the stream are combined into a single primitive or Object.)

| Method                              | What happens for Infinite Streams? | Return Value    | Reduction |
|-------------------------------------|------------------------------------|-----------------|-----------|
| count()                             | Does not terminate                 | long            | Yes       |
| min()/ max()                        | Does not terminate                 | Optional&lt;T>  | Yes       |
| findAny()/ findFirst()              | Terminates                         | Optional&lt;T>  | No        |
| allMatch()/ anyMatch()/ noneMatch() | Sometimes terminates               | boolean         | No        |
| forEach()                           | Does not terminate                 | void            | No        |
| reduce()                            | Does not terminate                 | Varies          | Yes       |
| collect()                           | Does not terminate                 | Varies          | Yes       |

## Primitive Streams
The package `primitivestreams` shows how to use primitive streams.

There are 3 types of primitive streams in Java:
1. **IntStream** - Used for int, short, byte and char
1. **LongStream** - Used for long
1. **DoubleStream** - Used for double and float

These primitive Streams have the regular Stream methods + convenience methods for common tasks such as calculating an average.
 
There are Mapping methods to map a stream to another Stream type (This is the case for all Streams - Stream, IntStream, LongStream, DoubleStream)

There are special functional Interfaces for the primitive Streams. They are the equivalent to the general functional Interfaces we thematized in the package `funcinterfaces`
- IntSupplier
- IntConsumer
- IntPredicate
- IntFunction
- IntUnaryOperator
- IntBinaryOperator
- (Same for Long and Double)

The type name tells us what primitive type is involved. 
 
### Optional with primitive Streams
There are 3 types of Optional primitives
1. OptionalInt
1. OptionalLong
1. OptionalDouble

The difference to a regular Optional is that a regular Optional such as `Optional<Double>` is for the Double wrapper class whereas the OptionalDouble is for the primitive double.

## Working with advanced Stream Pipeline Concepts
This chapter is in the `advancedstreamconcepts` package.

The class `AdvancedStreamPipeline` contains the following topics:

Contains an example that proves that Streams are lazily evaluated.
Shows that some of the intermediate operations for Streams are also available for Optional.
Points out the problematic with checked Exceptions and Functional Interfaces.

The class `CollectingResults` Shows many predefined collectors. (Remember that Collectors are used in the terminal operation `collect()`)
It includes `joining()`, `averagingInt()`, `toCollection()`, `toMap()`, `groupingBy()` and`partitioningBy()`

Here an overview of predefined Collectors:

| Collector                                                                                                                                       | Description                                                                                                     | Return Value When Passed to collect                                         |
|-------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------|
| averagingDouble(ToDoubleFunction f)<br>averagingInt(ToIntFunction f)<br>averagingLong(ToLongFunction f)                                         | Calculates the average<br>for our three core<br>primitive types                                                 | Double                                                                      |
| counting()                                                                                                                                      | Counts number of elements                                                                                       | Long                                                                        |
| groupingBy(Function f)<br>groupingBy(Function f, Collector dc)<br>groupingBy(Function f, Supplier s, Collector dc)                              | Creates a map grouping by the specified<br>function with the optional type and<br>optional downstream collector | Map<K, List&lt;T>>                                                          |
| joining()<br>joining(CharSequence cs)                                                                                                           | Creates a single String using cs as a delimiter<br>between elements if one is specified                         | String                                                                      |
| maxBy(Comparator c)<br>minBy(Comparator c)                                                                                                      | Finds the largest/smallest<br>elements                                                                          | Optional&lt;T>                                                              |
| mapping(Function f, Collector dc)                                                                                                               | Adds another level of collectors                                                                                | Collector                                                                   |
| partitioningBy(Predicate p)<br>partitioningBy(Predicate p, Collector dc)                                                                        | Creates a map grouping by the specified predicate <br>with the optional further downstream collector            | Map<Boolean, List&lt;T>>                                                    |
| summarizingDouble(ToDoubleFunction f)<br>summarizingInt(ToIntFunction f)<br>summarizingLong(ToLongFunction f)                                   | Calculates average,<br>min, max, and so on                                                                      | DoubleSummaryStatistics<br>IntSummaryStatistics<br>LongSummaryStatistics    |
| summingDouble(ToDoubleFunction f)<br>summingInt(ToIntFunction f)<br>summingLong(ToLongFunction f)                                               | Calculates the sum for<br>our three core primitive types                                                        | Double<br>Integer<br>Long                                                   |
| toList()<br>toSet()                                                                                                                             | Creates an arbitrary<br>type of list or set                                                                     | List<br>Set                                                                 |
| toCollection(Supplier s)                                                                                                                        | Creates a Collection<br>of the specified type                                                                   | Collection                                                                  |
| toMap(Function k, Function v)<br>toMap(Function k, Function v, BinaryOperator m)<br>toMap(Function k, Function v, BinaryOperator m, Supplier s) | Creates a map using functions to map the<br>keys, values, an optional merge function, and an<br>optional type   | Map                                                                         |

toMap(Function k, Function v)
    toMap(Function k, Function v, BinaryOperator m)
    toMap(Function k, Function v, BinaryOperator m, Supplier s)
groupingBy(Function f)<br>groupingBy(Function f, Collector dc)<br>groupingBy(Function f, Supplier s, Collector dc)
partitioningBy(Predicate p)<br>partitioningBy(Predicate p, Collector dc)  
mapping(Function f, Collector dc)
