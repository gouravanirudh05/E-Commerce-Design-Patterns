# Proxy Pattern

## Real-life Amazon-style example

A product-search page can contain hundreds of products. Loading each product's
full description, price, ratings, and images from a remote catalog service
when search results first appear is wasteful: shoppers may open only one item.

## Problem: eager remote loading

In [Problem.java](Problem.java), every search result directly creates a
`DirectRemoteAmazonProductDetails`. The remote request happens immediately,
including for products the shopper never views.

- [ ] Unopened results still make remote catalog calls.
- [ ] Search pages take longer to prepare.
- [ ] The catalog service receives unnecessary traffic.
- [ ] Client code is tied to the remote implementation.

## Solution: Proxy Pattern

[Proxy.java](Proxy.java) introduces `AmazonProductDetailsProxy`, a virtual
proxy. It implements the same `AmazonProductDetails` interface as the real
remote product object, but creates that expensive object only when
`showDetails()` is called. Later calls reuse the already loaded object.

```text
AmazonCatalogPage --> AmazonProductDetails
                            ^
            AmazonProductDetailsProxy --> RemoteAmazonProductDetails
```

### Implementation checklist

- [x] Define a common product-details interface.
- [x] Keep the remote object responsible for real catalog data.
- [x] Put lazy-loading logic in a proxy.
- [x] Create the remote object only on the first request.
- [x] Reuse the loaded object for subsequent requests.

## Benefits

- **Faster initial pages:** unused product details are not loaded.
- **Lower service load:** remote calls happen only when needed.
- **Transparent use:** the catalog page uses the proxy like a real product.
- **Centralized control:** caching, access checks, or logging can be added to the proxy.

## Expected output

```text
Search results created; product details are not loaded yet.
Loading product P-500 from the catalog service...
Product P-500: Noise-cancelling headphones, ₹4,999.00
---
Opening the same product again:
Product P-500: Noise-cancelling headphones, ₹4,999.00
```

## When to use it

Use Proxy when an object is expensive, remote, sensitive, or otherwise needs
controlled access while clients should keep using the same interface.
