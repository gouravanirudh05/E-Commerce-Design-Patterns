# Visitor Pattern

## Real-life Amazon-style example

An Amazon-style product catalog may contain different product types:

```text
Product
├── Book
├── Electronics
└── Clothing
```

The product classes may be stable, while the business frequently introduces
new operations:

```text
Operations
├── Calculate GST
├── Calculate shipping charges
├── Apply discounts
└── Generate reports
```

The Visitor Pattern keeps these operations outside the product classes.

## Problem: putting every operation in product classes

In the bad-practice example ([Problem.java](Problem.java)), each product class
contains its own tax, shipping, and discount calculations.

This creates several problems:

- [ ] **Growing product classes:** every new operation adds more methods to every product.
- [ ] **Mixed responsibilities:** product data and business rules are combined.
- [ ] **Repeated logic:** similar operations are implemented across multiple classes.
- [ ] **Harder maintenance:** changing a tax or shipping rule requires editing product classes.
- [ ] **Difficult extension:** the catalog becomes harder to test and evolve.

## Solution: Visitor Pattern

The Visitor Pattern moves related operations into separate visitor classes. A
product accepts a visitor, and the visitor performs the operation appropriate
for that product type.

The solution is available in [Visitor.java](Visitor.java).

```java
Product book = new Book("Java Design Patterns", 800.00);
ProductVisitor taxVisitor = new TaxVisitor();

book.accept(taxVisitor);
```

### Main participants

- `Product` — element interface with `accept(ProductVisitor)`.
- `Book`, `Electronics`, and `Clothing` — concrete elements.
- `ProductVisitor` — declares one `visit()` method for each product type.
- `TaxVisitor`, `ShippingVisitor`, and `DiscountVisitor` — concrete operations.
- `Visitor` — demonstrates applying different operations to the catalog.

### How double dispatch works

1. The client calls `product.accept(visitor)`.
2. The concrete product calls `visitor.visit(this)`.
3. Java selects the visitor method for the concrete product type.

This allows one visitor to apply different rules to books, electronics, and
clothing without placing those rules inside the product classes.

### Implementation checklist

- [x] Define a stable product interface with `accept()`.
- [x] Implement `accept()` in every concrete product type.
- [x] Define a visitor interface with an overloaded `visit()` method for each product type.
- [x] Create separate visitors for tax, shipping, and discounts.
- [x] Apply multiple operations to the same product catalog.
- [x] Keep product classes focused on product data and visitor dispatch.

## Benefits

- **Easy to add operations:** create a new visitor without changing existing product classes.
- **Separation of concerns:** pricing, tax, shipping, and reporting rules stay outside product data classes.
- **Centralized business rules:** each operation is maintained in one visitor.
- **Reusable operations:** the same visitor can process the entire product catalog.
- **Better testing:** each visitor can be tested independently.

## Expected output

```text
Tax calculation:
Java Design Patterns GST: ₹40.0
Wireless Headphones GST: ₹900.0
Amazon Essentials Jacket GST: ₹240.0
---
Shipping calculation:
Java Design Patterns shipping: ₹40.0
Wireless Headphones shipping: ₹90.0
Amazon Essentials Jacket shipping: ₹60.0
---
Discount calculation:
Java Design Patterns discount: ₹80.0
Wireless Headphones discount: ₹250.0
Amazon Essentials Jacket discount: ₹300.0
```

## Important trade-off

Visitor is a good choice when product types are stable and new operations are
added frequently. However, adding a new product type requires a new `visit()`
method in `ProductVisitor` and updates to every concrete visitor.

Easy way to remember:

> **Visitor = Add new operations without changing the objects being operated on.**

## When to use it

Use Visitor when an object structure has a fixed or relatively stable set of
types, but the application frequently needs new operations over those types.
