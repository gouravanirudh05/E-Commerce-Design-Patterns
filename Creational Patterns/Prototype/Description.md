# Prototype Pattern

## Real-life Amazon-style example

An Amazon-style marketplace may allow sellers to create product listings and
variants. A listing can contain a large amount of common information:

```text
Product Listing
├── Name
├── Description
├── Images
├── Specifications
├── Color
├── Storage or size
├── Price
└── Seller and delivery details
```

For example, a seller may already have an **iPhone 17 - Black - 128 GB**
listing and want to create an **iPhone 17 - Blue - 128 GB** listing. Most of
the information is identical, so creating the new listing from scratch is
unnecessary.

## Problem: repeated construction

In the bad-practice example ([Problem.java](Problem.java)), every product
variant is created with all of its details again:

```java
ProductListingWithoutPrototype bluePhone = new ProductListingWithoutPrototype(
        "iPhone 17", "Blue", "128 GB", 70000.00,
        "Latest generation smartphone", images, specifications);
```

This becomes expensive and error-prone when a listing contains many images,
specifications, shipping details, and seller settings.

### Problems with this approach

- [ ] **Repeated work:** common listing data is supplied for every variant.
- [ ] **Hard to maintain:** changes to the listing structure affect every constructor call.
- [ ] **Error-prone copying:** a variant may accidentally miss or alter shared information.
- [ ] **Complicated construction:** callers need to know every field required by the constructor.
- [ ] **Poor reuse:** an existing, fully configured listing cannot be easily reused.

## Solution: Prototype Pattern

The Prototype Pattern creates a new object by copying an existing object and
then changing only the fields that differ:

```java
ProductListing bluePhone = blackPhone.clone();
bluePhone.setColor("Blue");
```

The implementation is available in [Prototype.java](Prototype.java). It also
deep-copies the mutable image list and specification map so that changing the
blue variant does not change the original black listing.

### Implementation checklist

- [x] Define a complex `ProductListing` object.
- [x] Provide a `clone()` method for creating copies.
- [x] Copy common product information from the existing listing.
- [x] Deep-copy mutable collections such as images and specifications.
- [x] Modify only variant-specific fields after cloning.
- [x] Keep the original listing unchanged.

## Benefits

- **Efficient creation:** expensive or complicated listing setup is reused.
- **Less duplication:** common data is copied instead of entered repeatedly.
- **Readable code:** `clone()` clearly communicates the intention to create a similar variant.
- **Flexible customization:** cloned listings can change color, size, price, or images independently.
- **Safer variants:** deep copying prevents changes to one listing from leaking into another.

## Expected output

```text
Original listing:
Product: iPhone 17
Color: Black
Storage: 128 GB
Price: ₹70000.00
---
Cloned product variant:
Product: iPhone 17
Color: Blue
Storage: 128 GB
Price: ₹70000.00
---
Original and cloned objects are different: true
```

## Prototype vs Builder

- **Builder:** constructs a complex object step by step from the beginning.
- **Prototype:** starts with an existing object, copies it, and modifies the copy.

Easy way to remember:

> **Builder = Build from scratch**  
> **Prototype = Copy and modify**

## When to use it

Use the Prototype Pattern when creating an object is expensive, complicated,
or repetitive, and many new objects are variations of an existing object.
