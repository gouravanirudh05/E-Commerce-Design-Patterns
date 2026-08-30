# Adapter Pattern

## Real-life Amazon-style example

An Amazon-style store may integrate with a courier that is already used by a
seller. The application ships orders through its own `AmazonShippingProvider`
contract, while the courier SDK exposes a different method:

```java
dispatchParcel(String trackingNumber, String destination)
```

The courier library is useful, but its API cannot be changed to match the
application.

## Problem: incompatible interfaces

In [Problem.java](Problem.java), the order service calls
`DirectLegacyCourierApi` directly and creates the tracking number itself. This
couples application business logic to a vendor-specific API.

- [ ] The order service knows the courier's method names and data format.
- [ ] Every caller must translate an order ID into a tracking number.
- [ ] Replacing or adding courier partners changes business logic.
- [ ] Testing shipping behavior requires the third-party API shape.

## Solution: Adapter Pattern

[Adapter.java](Adapter.java) defines the application's target interface,
`AmazonShippingProvider`. `LegacyCourierAdapter` implements that interface and
wraps `LegacyCourierApi`, translating `ship()` into `dispatchParcel()`.

```text
AmazonOrderShippingService --> AmazonShippingProvider
                                      ^
                     LegacyCourierAdapter --> LegacyCourierApi
```

### Implementation checklist

- [x] Define the interface required by the e-commerce application.
- [x] Keep the third-party courier API unchanged.
- [x] Wrap the incompatible API in an adapter.
- [x] Perform order-ID and parameter translation in one place.
- [x] Let the order service use in-house and third-party shipping uniformly.

## Benefits

- **Loose coupling:** application code does not depend on courier-specific APIs.
- **Reuse:** existing courier integrations work without modification.
- **Clear translation:** vendor-specific data conversion has one home.
- **Easy extension:** another courier only needs another adapter.

## Expected output

```text
Shipping order 1001 with Amazon Delivery to Bengaluru
Dispatching parcel AMZ-1002 through Legacy Courier to Mumbai
```

## When to use it

Use Adapter when an existing service is valuable but its interface does not
match the interface the application expects.
