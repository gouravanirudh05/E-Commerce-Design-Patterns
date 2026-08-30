# Composite Pattern

## Real-life Amazon-style example

An Amazon-style cart can contain individual products and product bundles. A
bundle may itself contain another bundle, such as a workstation bundle holding
a laptop and an accessories bundle. The cart should calculate and display all
of them uniformly.

```text
Shopping Cart
├── Workstation bundle
│   ├── Laptop
│   └── Accessories bundle
│       ├── Wireless Mouse
│       └── Mechanical Keyboard
└── Two-year Protection Plan
```

## Problem: special-case bundle logic

In [Problem.java](Problem.java), the cart has a method with a separate
parameter for each product in a workstation bundle. Adding a product type,
another bundle, or nested bundles requires new special-case methods and price
calculations.

- [ ] Products and bundles have different APIs.
- [ ] Every bundle needs custom total-calculation code.
- [ ] Nested bundles are difficult to represent.
- [ ] Cart code becomes full of type checks and special cases.

## Solution: Composite Pattern

[Composite.java](Composite.java) gives both a leaf product and a bundle the
same `AmazonCartItem` interface. `AmazonProduct` is the leaf, while
`AmazonProductBundle` is the composite that stores other cart items and sums
their prices recursively.

```text
AmazonCartItem
├── AmazonProduct          (leaf)
└── AmazonProductBundle    (composite)
    └── List<AmazonCartItem>
```

### Implementation checklist

- [x] Define one interface for every cart item.
- [x] Implement individual products as leaves.
- [x] Implement product bundles as composites.
- [x] Let a bundle contain products or other bundles.
- [x] Calculate prices recursively through the shared interface.
- [x] Let the shopping cart handle every item uniformly.

## Benefits

- **Uniform treatment:** clients use products and bundles in the same way.
- **Natural nesting:** bundles can contain other bundles without new logic.
- **Simple totals:** each composite calculates the total of its children.
- **Extensible carts:** new item types implement one interface.

## Expected output

```text
Shopping cart:
- Workstation bundle: ₹69700.00
  Laptop: ₹65000.00
  Accessories bundle: ₹4700.00
    Wireless Mouse: ₹1200.00
    Mechanical Keyboard: ₹3500.00
- Two-year Protection Plan: ₹4999.00
Cart total: ₹74699.00
```

## When to use it

Use Composite when individual objects and groups of objects should be treated
the same way, especially when those groups may be nested.
