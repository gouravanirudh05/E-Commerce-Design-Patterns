# Bridge Pattern

## Real-life Amazon-style example

An order system has independent dimensions: the order event being communicated
(placed, shipped, delivered) and the delivery channel (email, SMS, push).

## Problem: class-combination growth

In [Problem.java](Problem.java), each event/channel pair needs its own class,
such as `OrderPlacedEmailNotification` and `OrderPlacedSmsNotification`.
Adding event types and channels multiplies classes.

## Solution: Bridge Pattern

[Bridge.java](Bridge.java) separates the two dimensions. `AmazonOrderNotification`
models the notification type, while `AmazonNotificationChannel` models delivery.
The abstraction holds a channel and delegates sending to it.

```text
AmazonOrderNotification                 AmazonNotificationChannel
├── OrderPlacedNotification       -->   ├── EmailNotificationChannel
└── OrderShippedNotification            └── SmsNotificationChannel
```

## Expected output

```text
Email to customer@example.com: Your order AMZ-4001 has been placed.
SMS to +91-9876543210: Your order AMZ-4001 has been shipped.
```

## When to use it

Use Bridge when two independent dimensions vary separately and combining them
through subclasses would create too many classes.
