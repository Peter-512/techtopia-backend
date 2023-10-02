# Documentation

### Table of Contents

- Bounded Contexts
- Events
- Commands
- Use Cases

##### Bounded Contexts

![Bounded Contexts](./boundedContexts.png)

##### Events

- VisitorLeft
- VisitorEntered
- TicketPurchase
- TicketRefund
- VisitorEnteredAttractionQueue
- VisitorLeftAttractionQueue
- AttractionQueueThresholdExceeded
- AttractionQueueThresholdNotExceeded
- RefreshmentStandOpened
- RefreshmentStandClosed
- HotelRoomBooked

##### Commands

- PayTicket
- RefundTicket
- OpenRefreshmentStand
- CloseRefreshmentStand
- EnableHighThroughputOnAttraction
- DisableHighThroughputOnAttraction

##### Use Cases

- Visitor enters park
- Visitor leaves park
- Visitor buys ticket
- Visitor refunds ticket
- Visitor enters attraction queue
- Visitor leaves attraction queue
- Guest makes hotel reservation
- Guest books hotel room
- Guest checks in to hotel room
- Guest checks out of hotel room
- Guest cancels hotel booking
- Forecast park attendance
- Verify ticket validity
