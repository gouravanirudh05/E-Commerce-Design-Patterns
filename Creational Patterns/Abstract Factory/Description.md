# Abstract Factory Pattern

## Real-life Amazon-style example

Imagine an Amazon-style e-commerce platform that operates in multiple
markets. Each market may require a compatible family of checkout products:

- **Amazon India:** UPI or card payment with a GST invoice
- **Amazon US:** PayPal or card payment with a sales-tax invoice

The checkout service should work with any market without directly creating
market-specific payment gateways or invoice objects. 

## Problem: hardcoded object creation

In the bad-practice example ([Problem.java](Problem.java)),
`AmazonCheckoutServiceWithoutAbstractFactory` decides which payment gateway
and invoice to create using region and payment-method strings.

This creates several problems:

- [ ] **Tight coupling:** checkout directly depends on concrete regional classes.
- [ ] **Mixed responsibilities:** business logic also handles object creation.
- [ ] **Hard to extend:** adding a new market or payment method requires changing the checkout service.
- [ ] **Invalid combinations are easy to create:** a payment gateway and invoice may not belong to the same market.
- [ ] **Harder testing and maintenance:** the service cannot be tested independently from concrete implementations.

## Solution: Abstract Factory Pattern

The Abstract Factory Pattern creates a complete family of related objects
without exposing their concrete classes to the client. In this example:

- `AmazonRegionFactory` is the abstract factory.
- `AmazonIndiaFactory` and `AmazonUSFactory` are concrete factories.
- `AmazonPaymentGateway` and `AmazonInvoice` are abstract products.
- The regional gateway and invoice classes are concrete products.
- `AmazonCheckoutService` is the client using the products.

The solution is implemented in
[AbstractFactory.java](AbstractFactory.java).

### Implementation checklist

- [x] Define interfaces for each related product type.
- [x] Group compatible products into concrete regional families.
- [x] Define one abstract factory for creating the product family.
- [x] Implement one concrete factory per market or configuration.
- [x] Keep checkout logic dependent only on interfaces.
- [x] Validate unsupported payment methods in the appropriate factory.

## Benefits

- **Consistent product families:** a market receives its matching payment and invoice implementations.
- **Loose coupling:** checkout does not depend on concrete regional classes.
- **Better separation of concerns:** factories create products while checkout processes orders.
- **Easier extension:** a new market can be added with a new factory and product family.
- **Improved testing:** mock factories and product interfaces can be supplied to checkout.

## Expected output

```text
Processing INR payment through UPI: 1999.0
Generating GST invoice for Amazon India.
---
Processing USD payment through PayPal: 49.99
Generating sales-tax invoice for Amazon US.
```

## Factory vs Abstract Factory

- **Factory:** creates one type of product, such as a shipping method.
- **Abstract Factory:** creates multiple related products, such as a payment gateway and an invoice for the same market.

## When to use it

Use Abstract Factory when the application supports multiple product families
and the products in each family must work together consistently.
