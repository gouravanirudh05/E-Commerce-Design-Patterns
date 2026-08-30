# Factory Pattern

## Real-life Amazon-style example

An Amazon-style e-commerce application may support multiple delivery methods,
such as **Road** and **Air**. The order service needs to ship an order, but it
should not be responsible for deciding which concrete delivery object to
create.

## Problem: direct object creation

In the bad-practice example ([Problem.java](Problem.java)),
`AmazonOrderServiceWithoutFactory` creates `DirectAirShipping` and
`DirectRoadShipping` objects directly based on a string value.

This mixes object creation with order-processing logic and creates several
design problems:

- [ ] **Tight coupling:** the order service depends directly on concrete shipping classes.
- [ ] **Hard to extend:** adding Drone, Ship, or another delivery method requires modifying the service.
- [ ] **No separation of concerns:** creation logic and business logic are mixed.
- [ ] **Repeated logic:** each new delivery option adds another conditional branch.
- [ ] **Harder testing and maintenance:** the service is difficult to test independently.

## Solution: Factory Pattern

The Factory Pattern moves shipping-object creation into
`AmazonShippingFactory`. The order service requests a shipping method through
the common `AmazonShippingMethod` interface and only focuses on shipping the
order.

The solution is implemented in [Factory.java](Factory.java).

### Implementation checklist

- [x] Define a common interface for all shipping methods.
- [x] Keep Road and Air implementations separate from order-processing logic.
- [x] Centralize object creation in `AmazonShippingFactory`.
- [x] Return the interface type instead of exposing concrete classes.
- [x] Reject unsupported or missing shipping modes clearly.
- [x] Keep `AmazonOrderService` focused on its business responsibility.

## Benefits

- **Loose coupling:** the service depends on an interface rather than concrete classes.
- **Separation of concerns:** the factory creates objects while the service uses them.
- **Easier extension:** a new shipping implementation can be added with minimal changes to the service.
- **Better testing:** shipping methods and the order service can be tested separately.
- **Centralized validation:** unsupported shipping modes are handled in one place.

## Expected output

```text
Shipping order AMZ-1001 by air
Shipping order AMZ-1002 by road
```

## When to use it

Use the Factory Pattern when the application must choose between related
implementations at runtime and the creation details should be kept away from
business logic.
