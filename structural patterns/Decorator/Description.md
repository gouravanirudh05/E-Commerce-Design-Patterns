# Decorator Pattern

## Real-life Amazon-style example

An Amazon-style checkout flow often adds optional services to the same order:
gift wrapping, extended protection, express delivery, or premium packaging.
Each option can be layered on top of the base order without changing the core
order class.

```text
Base Order
  -> Gift Wrap
  -> Extended Protection
  -> Express Delivery
```

## Problem: too many order subclasses

In [Problem.java](Problem.java), the checkout service hard-codes separate
methods for each combination of services. That quickly becomes unmanageable as
more add-ons are introduced.

- [ ] Every service combination needs a different method.
- [ ] Adding a new add-on creates more branching.
- [ ] Core order code changes whenever a new option appears.
- [ ] The checkout flow becomes difficult to extend and test.

## Solution: Decorator Pattern

[Decorator.java](Decorator.java) defines a common `AmazonOrder` interface.
`BaseOrder` is the core component, while decorators such as
`GiftWrapDecorator` and `ExpressDeliveryDecorator` wrap another
`AmazonOrder` and add behavior before or after delegating.

```text
AmazonOrder
├── BaseOrder
└── OrderDecorator
    ├── GiftWrapDecorator
    ├── ProtectionPlanDecorator
    └── ExpressDeliveryDecorator
```

### Implementation checklist

- [x] Define one interface for the base order and all add-ons.
- [x] Implement the core order as the component.
- [x] Create decorators that wrap another order.
- [x] Add extra charges or messages in the decorator layer.
- [x] Allow decorators to stack in any order.

## Benefits

- **Flexible composition:** features can be combined dynamically.
- **Open for extension:** new services are added as new decorators.
- **Single responsibility:** each add-on owns its own behavior.
- **No subclass explosion:** combinations do not require many classes.

## Expected output

```text
Base order: AMZ-2001
Adding gift wrap for AMZ-2001
Adding protection plan for AMZ-2001
Adding express delivery for AMZ-2001
Final order total for AMZ-2001: Rs. 1899.00
```

## When to use it

Use Decorator when you want to add responsibilities to objects dynamically
without changing the base class or creating many subclasses for combinations.
