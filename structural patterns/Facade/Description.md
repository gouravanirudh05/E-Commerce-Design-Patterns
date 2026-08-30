# Facade Pattern

## Real-life Amazon-style example

An Amazon-style checkout page talks to several subsystems: inventory, payment,
shipping, and notifications. The customer should place one order, while the
checkout flow hides the complexity behind a single simple API.

```text
Checkout Page -> Order Facade -> Inventory / Payment / Shipping / Notification
```

## Problem: checkout logic reaches every subsystem directly

In [Problem.java](Problem.java), the UI or service layer calls each subsystem
itself. That means the checkout flow knows too much about the internal order
processing steps.

- [ ] The client coordinates every subsystem manually.
- [ ] Checkout steps are duplicated across screens or services.
- [ ] Any subsystem change forces client code changes.
- [ ] The flow is harder to read and maintain.

## Solution: Facade Pattern

[Facade.java](Facade.java) introduces `AmazonCheckoutFacade`, a single entry
point that coordinates inventory reservation, payment, shipping, and
notification. The client only calls the facade.

```text
AmazonCheckoutFacade
├── InventoryService
├── PaymentService
├── ShippingService
└── NotificationService
```

### Implementation checklist

- [x] Create a simple interface for the checkout workflow.
- [x] Move subsystem coordination into one facade class.
- [x] Keep subsystem classes focused on their own work.
- [x] Let clients call one method for the whole order placement.
- [x] Hide ordering details and integration steps from the client.

## Benefits

- **Simpler client code:** one method starts the full workflow.
- **Lower coupling:** clients do not depend on subsystem details.
- **Centralized orchestration:** checkout steps live in one place.
- **Easier maintenance:** subsystem changes are isolated behind the facade.

## Expected output

```text
Starting checkout for AMZ-3001
Inventory reserved for AMZ-3001
Payment captured for AMZ-3001
Shipment created for AMZ-3001
Order confirmation sent for AMZ-3001
Checkout completed for AMZ-3001
```

## When to use it

Use Facade when a system has many moving parts but the client should interact
with it through one clean, easy-to-use entry point.
