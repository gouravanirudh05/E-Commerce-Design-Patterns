# Builder Pattern

## Real-life Amazon-style example

An Amazon-style e-commerce application may need to create an `Order` with
many details. Some details are required, while others are optional:

```text
Order
├── User
├── Product
├── Quantity
├── Address
├── Payment method
├── Coupon              ← optional
├── Gift wrapping       ← optional
├── Delivery instructions ← optional
└── Delivery option     ← optional
```

## Problem: a large constructor

In the bad-practice example ([Problem.java](Problem.java)), an order is created
with a long list of constructor parameters:

```java
OrderWithoutBuilder order = new OrderWithoutBuilder(
        "U101", "P500", 2, "Delhi", "UPI",
        "AMAZON10", true, "Leave at the front desk", "Prime One-Day");
```

This design is difficult to read because the caller must remember the exact
order of every parameter. It also becomes more confusing whenever a new
optional field is added.

### Problems with this approach

- [ ] **Unclear construction:** parameter values do not explain themselves.
- [ ] **Parameter-order mistakes:** values can be passed in the wrong position.
- [ ] **Large constructors:** the constructor becomes difficult to maintain.
- [ ] **Poor handling of optional fields:** callers must pass `null`, `false`, or default values.
- [ ] **Difficult extension:** adding an option requires changing the constructor and every call site.

## Solution: Builder Pattern

The Builder Pattern constructs the order step by step using clearly named
methods. The implementation is available in [Builder.java](Builder.java).

```java
Order order = new Order.Builder()
        .setUserId("U101")
        .setProductId("P500")
        .setQuantity(2)
        .setAddress("Delhi")
        .setPaymentMethod("UPI")
        .setCoupon("AMAZON10")
        .setGiftWrap(true)
        .setDeliveryOption("Prime One-Day")
        .build();
```

Optional fields can be omitted safely:

```java
Order order = new Order.Builder()
        .setUserId("U202")
        .setProductId("P900")
        .setQuantity(1)
        .setAddress("Mumbai")
        .setPaymentMethod("Card")
        .build();
```

### Implementation checklist

- [x] Keep the `Order` constructor private.
- [x] Create a nested `Order.Builder` class.
- [x] Use one readable setter method for each attribute.
- [x] Return the builder from each setter to support method chaining.
- [x] Apply default values for optional fields.
- [x] Validate required fields inside `build()`.
- [x] Create the final order only when `build()` is called.

## Benefits

- **Readable construction:** each method identifies the value being assigned.
- **Safe optional fields:** callers only set the options they need.
- **Fewer mistakes:** there is no long positional parameter list.
- **Easy validation:** required-field checks are centralized in `build()`.
- **Maintainability:** new optional fields can be added to the builder without changing the construction style.
- **Immutability:** the completed `Order` stores final values after construction.

## Expected output

```text
Order for user: U101
Product: P500, Quantity: 2
Delivery address: Delhi
Payment method: UPI
Coupon: AMAZON10
Gift wrap: true
Delivery instructions: Leave at the front desk
Delivery option: Prime One-Day
---
Order for user: U202
Product: P900, Quantity: 1
Delivery address: Mumbai
Payment method: Card
Coupon: None
Gift wrap: false
Delivery instructions: None
Delivery option: Standard
```

## When to use it

Use the Builder Pattern when an object has many attributes, especially when
several of them are optional, and the object should be created in a readable,
step-by-step manner.
