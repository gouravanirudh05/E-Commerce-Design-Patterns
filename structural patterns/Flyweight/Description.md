# Flyweight Pattern

## Real-life Amazon-style example

Many sellers can list the same catalog product. Product name, category, and
image are identical across those listings; only seller and price vary.

## Problem: repeated shared data

In [Problem.java](Problem.java), every listing stores a separate copy of the
same product metadata. With many sellers and products, this wastes memory.

## Solution: Flyweight Pattern

[Flyweight.java](Flyweight.java) separates shared **intrinsic** data from
per-listing **extrinsic** data. `AmazonProductType` is the shared flyweight;
`AmazonSellerListing` keeps its seller ID and price. The factory caches one
product type for each unique product definition.

```text
AmazonSellerListing --> AmazonProductTypeFactory --> shared AmazonProductType
       seller ID, price                    product name, category, image
```

## Expected output

```text
Creating shared product type for Noise-cancelling headphones
Seller-A lists Noise-cancelling headphones (Electronics, headphones.jpg) for ₹4999.00
Seller-B lists Noise-cancelling headphones (Electronics, headphones.jpg) for ₹4799.00
Shared product types created: 1
```

## When to use it

Use Flyweight when an application has very many similar objects whose common,
immutable state can be shared safely to reduce memory use.
