# OCP Preparation

This project shows some basic Code examples needed for the OCP exam.
It is separated into packages that represent the chapters in the OCP book.

## Chapter 1 - Advanced Class Design
### instanceof
The *usinginstanceof* package shows how the instanceof operator works.
Instanceof is commonly used to determine if an instance is a subclass of a particular object before applying an explicit cast.

### equals, hashCode and toString
These are methods provided by java.lang.Object so they can be overridden by any class.

**String toString()**
- Java calls the toString method when printing an object.
- The default implementation doesn't provide a human readable result: `return getClass().getName() + "@" + Integer.toHexString(hashCode());`

**boolean equals(Object obj)** 
- Java uses *==* to compare primitives and to check if two variables refer to the same object.
- The implementation in the *Object* class does the same: `return (this == obj);`
- Should return false when the object passed is null or of the wrong type
- We can override this method with an own implementation of when objects of a class are considered equal. E.g.:
```
@Override 
public boolean equals(Object obj) {
    if (!(obj instanceof Lion)) return false;
    Lion otherLion = (Lion) obj;
    return this.idNumber == otherLion.idNumber;
}
```
     
**int hashCode()**
- When you override `equals()`, you are also expected to override `hashCode()`
- The hashCode is used when storing the object as **key in a map**
- A hash code is a number that puts instances of a class into a finite number of categories.
- Example: Card deck: Put the 52 cards in 13 categories - one for each number (2, 3, ..., K, A).
- It is common to not include boolean and char variables in the hash code.
- The official JavaDoc stated a contract for hashCode:
    - The result of hashCode() must not change. So don't include variables that change for an object (e.g. include the name but not the weight)
    - If equals() returns true, then calling hashCode() on these objects must return the same result. --> This means that hashCode() can only use a subset of the variables that equals() uses.
    - If equals() returns false, then calling hashCode() on these objects can return the same result.

### Enums
See the *enums* package.
An enumeration is like a fixed set of static final constants with the advantage that we have type-safe checking at compile time.


## Chapter 2 - Design Patterns and Principles
### Designing an Interface
- Interface = abstract data type, similar to a class that defines public abstract methods
- Classes implementing the interface must provide an implementation for the public abstract methods.
- Methods in an interface are public & abstract by default. (Compiler adds public and abstract to all methods that are not static or default)
- Interface can include constant (public static final) variables, default methods and static methods. Default methods can be optionally overridden.
- An interface may extend another interface. An interface can't extend a class. A class can't extend an interface.
    - Interfaces provide limited support for multiple inheritance because a class may implement multiple interfaces
    - Interfaces may not be marked final or be instantiated directly
    - If a class/interface inherits two default methods with the same signature and doesn't provide its own implementation it fails to compile.
- Example: `public class A implements B, C {}` --> An instance of A may be passed to any method that accepts A, B, C or java.lang.Object as input parameter

### Purpose of an Interface
- Develop code without having access to the underlying implementation.
- Work in parallel - Team A develops Code that *uses* the interface (and maybe provides a mock implementation). Team B develops code that *implements* the interface

### Introducing Functional Programming
For examples see the package *'funcinterfaces'*.
- A **Functional Interface** has a single abstract method.
- They are the basis for lambda expressions in functional programming.
- A lambda expression is a block of code that gets passed around, like an anonymous method.
- Optionally use the `@FunctionalInterface` Annotation so the compiler detects if the Interface has more than one abstract method or no abstract methods at all.

### Implementing Functional Interfaces with Lambdas
See the package *'simple_lambdas'*.
Shows a simple example on how to implement functional interfaces using lambda expressions.

### Understanding Lambda Syntax & Spotting invalid lambdas
- Many parts are optional. The following are equivalent.
1. `a -> a.canHop()`
2. `(Animal a) -> { return a.canHop(); }`
- Left side of a lambda: parameter(s)
- Right side of a lambda: body
- Arrow: separates the parameter(s) from the body.
- The parentheses () can be omitted, if there is exactly 1 input parameter and the parameter type is not explicitly stated in the expression.
    - For 0 or more than 1 parameter the parentheses are required.
    - The parameter types don't need to be specified. (But if you specify the type, it needs to be specified for all parameters)
- For single line bodies we can omit the braces {}, semi-colon and return statement.
    - Once its more than 1 line braces, semi-colons (for each statement) and a return statement is required
    - When the return type is void, the return statement is optional
- You can't redeclare a local variable so this is invalid: `(a, b) -> { int a = 0; return 5; }`


## Chapter 4 - Functional Programming
### Functional interfaces
The package *'funcinterfaces'* shows the common functional interfaces
that were added with Java8 to the *java.util.function* package with simple use cases.

| Functional Interfaces | #Parameters | Return Type | Single Abstract Method |
|-----------------------|--------------|-------------|------------------------|
| Supplier&lt;T>           | 0            | T           | get                    |
| Consumer&lt;T>           | 1 (T)        | void        | accept                 |
| BiConsumer<T, U>      | 2 (T, U)     | void        | accept                 |
| Predicate&lt;T>          | 1 (T)        | boolean     | test                   |
| BiPredicate<T, U>     | 2 (T, U)     | boolean     | test                   |
| Function<T, R>        | 1 (T)        | R           | apply                  |
| BiFunction<T, U, R>   | 2 (T, U)     | R           | apply                  |
| UnaryOperator&lt;T>      | 1 (T)        | T           | apply                  |
| BinaryOperator&lt;T>     | 2 (T, T)     | T           | apply                  |

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
| min()/ max()                        | Does not terminate                 | Optional&lt;T>  | Yes       |
| findAny()/ findFirst()              | Terminates                         | Optional&lt;T>  | No        |
| allMatch()/ anyMatch()/ noneMatch() | Sometimes terminates               | boolean      | No        |
| forEach()                           | Does not terminate                 | void         | No        |
| reduce()                            | Does not terminate                 | Varies       | Yes       |
| collect()                           | Does not terminate                 | Varies       | Yes       |


## Primitive Streams
The package 'primitivestreams' shows how to use primitive streams.

There are 3 types of primitive streams in Java:
1. **IntStream** - Used for int, short, byte and char
1. **LongStream** - Used for long
1. **DoubleStream** - Used for double and float

These primitive Streams have the regular Stream methods + convenience methods for common tasks such as calculating an average.
 
There are Mapping methods to map a stream to another Stream type (This is the case for all Streams - Stream, IntStream, LongStream, DoubleStream)

There are special functional Interfaces for the primitive Streams. They are the equivalent to the general functional Interfaces we thematized in the package 'funcinterfaces'
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
This chapter is in the 'advancedstreamconcepts' package.

The class `AdvancedStreamPipeline` contains the following topics:

Contains an example that proves that Streams are lazily evaluated.
Shows that some of the intermediate operations for Streams are also available for Optional.
Points out the problematic with checked Exceptions and Functional Interfaces.

The class `CollectingResults` Shows many predefined collectors. (Remember that Collectors are used in the terminal operation `collect()`)
It includes `joining()`, `averagingInt()`, `toCollection()`, `toMap()`, `groupingBy()` and`partitioningBy()`
