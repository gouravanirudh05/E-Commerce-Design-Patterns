# Singleton Pattern

## Intent

Ensure that a class has only one instance and provide a single, global access
point to that instance.

## Problem

In the bad design example ([Problem.java](Problem.java)), each service creates
its own `ConfigurationManager` object:

- Product Service → `ConfigurationManager #1`
- Order Service → `ConfigurationManager #2`
- Payment Service → `ConfigurationManager #3`

This can lead to:

- [ ] Duplicate configuration objects
- [ ] Inconsistent configuration values
- [ ] Unnecessary object creation
- [ ] Changes in one configuration object not being reflected in another

## Singleton Solution

The Singleton pattern ensures that every service uses the same
`ConfigurationManager` instance. The implementation is demonstrated in
[Singleton.java](Singleton.java).

### Implementation checklist

- [x] Declare a private static instance of the class.
- [x] Make the constructor private so other classes cannot create objects directly.
- [x] Provide a public static `getInstance()` method as the global access point.
- [x] Create the instance only when it is first requested (lazy initialization).
- [x] Return the same instance for every subsequent request.

### Expected result

```text
Product Service and Order Service use the same ConfigurationManager object.
productConfig == orderConfig  →  true
```

## When to use it

Use Singleton when exactly one shared instance is required, such as a
configuration manager, application logger, or shared cache. Avoid using it
when a normal object can be passed through the application, because excessive
use of global state can make testing and maintenance more difficult.
