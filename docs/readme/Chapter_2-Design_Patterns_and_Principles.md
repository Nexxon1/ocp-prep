# Chapter 2 - Design Patterns and Principles
## Designing an Interface
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

___

## Introducing Functional Programming
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

___

## Implementing Polymorphism
See chapter *'polymorphism'*
- Is the ability of an interface to support multiple underlying forms. This allows multiple types of objects to be passed to a single method or class.

### Distinguishing between an Object and a Reference
- Objects are accessed by reference
    - You never have direct access to the memory of the object itself. To be exact, though, the object is an entity that exists in memory, allocated by the JRE
- Regardless of the reference for the object in memory, the object itself doesn't change
```
// Example: 
Lemur lemur = new Lemur();
Object lemurAsObject = lemur;
```
- Even though the Lemur object has been assigned a reference with a different type (here of type Object), the object itself has not changed. It still exists as a Lemur object in memory.
- However the ability to access methods changed. With the lemurAsObject reference we can't access the methods within the Lemur class. (Explicit cast would be needed)

Summary:
1. The **type of object determines** which **properties** exist within the object in memory
2. The **type of the reference** to the object **determines which methods and variables are accessible** to the Java program

### Casting Object References
- We can reclaim access to all fields and methods of the object by casting it back to the specific subclass it came from:
```
Lemur lemur = new Lemur();
Primate primate = lemur; // Primate only has access to the methods/fields defined in the Primate class
Lemur lemur2 = (Lemur) primate; // Still the same object, but now we gained back access to all its methods/fields 
```
Rules of casting:
1. Casting an object from a subclass to a superclass doesn't require an explicit cast.
1. Casting an object from a superclass to a subclass requires an explicit cast.
1. The compiler will not allow casts to unrelated types.
1. Even when the code compiles without issue, an exception may be thrown at runtime if the object being cast is not actually an instance of that class.

___

## Understanding Design Principles
- See package *'design_principles'*
- A design principle is an established idea or best practice. They lead to:
    - Lead to more logical code
    - Code that's easier to understand 
    - Classes that can be reused in other relationships or applications
    - Code that can be maintained more easily

### Encapsulating Data
- Fundamental principle of object-oriented design
- Fields and methods in a class. *Private* instance members that have *public* methods to retrieve or modify data (e.g. getters, setters)
- Idea behind this principle: Only the class itself should have direct access to its data. 
    - Thanks to encapsulation we can ensure that an object of a class only has valid data:
    - E.g. (See `Animal` class in the subpackage encapsulation): Non-null, non-empty species field. Age is >= 0. This can be achieved by following the encapsulation principle.
    - This way anytime an Animal object is passed to a method, it can simply be used without any validation needed.

### Creating Java Beans
- Since encapsulation is so prevalent in Java there is a standard for creating classes that store data, called JavaBeans. Rules:
1. Properties are private
1. Getters begin with `get` for non-boolean properties. Otherwise they may begin with `is` or `get`. (This does not apply for the Boolean wrapper class)
1. Setters begin with `set`
1. The method name must have the prefix `set/get/is` followed by the first letter of the property in uppercase, followed by the rest of the property name

### Applying the Is-a Relationship
- See the instanceof operator which can be used to determine when an object is an instance of a class, superclass or interface
- Example: `Cat extends Pet` --> A Cat is-a Pet
- Is-a Relationship is known as the *inheritance test*
- If A is-a B, then any instance of A can be treated like an instance of B

### Applying the Has-a Relationship = Composition
- When an object contains a particular property or value
- The has-a relationship is the property of an object having a named data object or primitive as member
- The has-a relationship is also known as the *object composition test*
- Example: `Bird` class and `Beak` (=Schnabel) class.
    - Separate classes with different attributes and values
    - The Bird class has a Beak attribute defined --> Bird has-a Beak
- Careful when combining the Is-a and Has-a Relationship:
    - If a parent class has-a object as protected/ public member, then any child of the parent must have that object as a member. (No problem for private members because they are not inherited in Java)
    - Example: `Primate` class has-a `Tail`. 
        - `Monkey extends Primate` --> No Problem. Monkey is-a Primate and has-a Tail
        - `Chimpanzee extends Primate` --> Problem. Chimpanzee is-a Primate but does not have a Tail --> The data model would be incorrect.

### Composing Objects
- *Object composition* is the property of constructing a class using references to other classes in order to reuse the functionality of the other classes.
- Is an alternate to inheritance and often used to simulate polymorphic behavior that cannot be achieved via single inheritance.
    - Example: See package `design_principles.composition` 

### Einschub: Association vs Composition vs Aggregation (Not in the OCP book)
- Association: Foo uses Bar: 
```
public class Foo {
    void myMethod(Bar bar) {
    }
}
```
- Composition: I own an object and am responsible for its lifetime. When Foo dies, so does Bar:
```
public class Foo {
    private Bar bar = new Bar(); 
}
```
- Aggregation: I have an object which I've borrowed from someone else. When Foo dies, Bar may live on:
```
public class Foo { 
    private Bar bar; 
    Foo(Bar bar) { 
       this.bar = bar; 
    }
}
```

___

## Working with Design Patterns
- Design pattern = An established general solution to a commonly occurring software development problem.
- In the next chapter we see 4 *creational patterns* (= Design pattern that manages the creation of objects inside an application)

### Applying the Singleton Pattern
- Creating an object in memory only once in an application and have it shared by multiple classes. 
- That object is sharable by all classes and threads.
- Example (see `design_patterns.singleton`: Manage the amount of hay available for food to the zoo animals across all classes.
    - Solution 1: Pass the same `HayManager` object to every class and method that uses it. Problem: Will result in lots of pointers and is hard to manage.
    - Solution 2: Create a singleton `HayManager` object. --> No need to pass it around the application
- Singletons may improve performance by loading reusable data that would otherwise be time consuming to store and reload each time it is needed
- Singletons are created as `private static` variables within the class, often with the name `instance`
- They are accessed via a single `public static` method, often named `getInstance()`, which returns the reference to the singleton object.
- All **constructors** are marked **private**. This ensures that no other class is capable of instantiation another version of the class. (This also implicitly makes the class final.) 
- The methods have the modifier `synchronized` which prevents two processes from running the same method at the exact same time
- Another approach to create a singleton is via a static initialization block (see the `StaffRegister` class)
- Singletons are commonly used for configuration data, reusable data caches, to coordinate access to shared resources etc.

### Applying Lazy Instantiation to Singletons
- Another technique is to delay the creation of the singleton until the first time the getInstance() method is called.
    - The creation itself is in the `getInstance()` method. There is a check - when the instance is null, create a new instance, otherwise return it. (See the `VisitorTicketTracker` class)
- Lazy instantiation is a software design pattern where we create a reusable object the first time it is requested.
    - This results in reduced memory usage and improves the performance when an application starts up.
    - However that also means there might be a noticeable delay for the user the first time a particular type of resource is needed.
- In applications that run across multiple computers, the static singleton solution starts to require special consideration, as each computer would have its own JVM. So a singleton could be implemented with a database or queue server rather than a static object.

### Creating Immutable Object
- Goal is to create **read-only** objects that can be shared and used by multiple classes
- The state of an immutable object does not change after they are created.
- Thread safe (because the state never changes)
1. Use a constructor to set all properties.
1. Mark all instance variables `private` and `final`
1. No setters
1. Don't allow referenced mutable objects to be modified or accessed directly
    - Example: No getter for an ArrayList - otherwise we could modify the contents since an ArrayList itself is mutable. Instead create wrapper methods e.g. to get an element of the List or create a copy when returning the List. The Collections API also has a convenient `Collections.unmodifiableList()` method.
1. Prevent methods from being overridden --> Mark the class or methods with `final` (or make the constructor private and use the factory pattern)
- Example: See package `design_patterns.immutable`, class `Animal`

### "Modifying" an Immutable Object
- To be exact you can't modify immutable objects.
- We can create a new immutable object that contains all of the same information as the original object + whatever we want to change.

### Builder Pattern (Not relevant for OCP Exam)
- Commonly used for objects that require numerous values to be set at the time the object is instantiated.
- Imagine: Constructor with 3 input parameters. Over the time the constructor grows and has 5 more input parameters
    - Problem: We have to add five new values to the constructor which makes it hard to create such an object
    - Problem: Every time we add a parameter, Users of the class are required to update their constructor calls --> difficult to use & maintain
    - Possible solution 1: Several constructors --> Problem: can be difficult to manage
    - Possible solution 2: Setter methods --> Problem: The object is not immutable and maybe not possible because the new attributes might depend on each other
    - Best solution: **Builder pattern** - The parameters are passed to a builder object through method chaining. A final build call will then create that object. This is also commonly used for immutable objects, since they don't have setters. See package `design_patterns.builder`.
- Setters of the Builder return an instance of the Builder itself - `this`
    - That way the Builder methods can be chained together and be called in any order.
- The `build()` Method actually creates the object we want.
    - We can also add some validation in the build() method to check whether all required fields got set.
    - Or we can set default values for anything the user failed to specify on the builder object.
- Leads to more maintainable code
- Tightly coupled vs Loosely coupled Code
    - **Tightly coupled**: Classes are highly dependent - A minor change in one class may greatly impact the other class
    - **Loosely coupled**: Classes with minimal dependencies on each other. This is preferred in practice.
    - In the Builder Pattern the class and its Builder are tightly coupled. In this case that's fine because it is required.
    - Often the Builder is written as static inner class or at least in the same package. 
        - Advantage: If one is changed, the other can be quickly updated.
        - Advantage: The constructor of the class can be made private which forces users to use the Builder for creating the actual object they want.
          
### Factory Pattern (Not relevant for OCP Exam)
- Write code that creates objects in which the precise type of the object may not be known until runtime.
- That way we are only loosely coupling the underlying creation implementation.
- Using a factory class to produce instances of objects based on a set of input parameters.
- Similar to the builder pattern but focused on supporting class polymorphism.
- Factory patterns are often implemented using `static` methods that return objects and do not require a pointer to an instance of the factory class (because it is a static method)
- Good practice to postfix `Factory` to the class name of the Factory
- Supports loose coupling between the class that the Factory produces and the class using the Factory. We can change the rules in the Factory without any other code changes.
- Example see the `design_patterns.factory` package.
    - Abstract `Food` class with 3 implementations: `Fish, Hay, Pellets`
    - `FoodFactory` with a `Food getFood()` method that returns specific Food depending on the input
    - `ZooKeeper` that calls `FoodFactory.getFood()` with some input parameter to get Food. 
    - Thanks to the FoodFactory the `ZooKeeper` and `Food` class is loosely coupled. 
