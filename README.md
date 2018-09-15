## Install

```bash
boot run
open localhost:4000
```

## Description

This story is about opening-up the above functionality via a web site. For now the web site will only be used by employees of Tickets4Sale. So,
you don’t have to worry about security.
The web site will be used to check the ticket status of a performance for a show. The inventory that is used by the web site is provided as a
separate CSV file, and is part of the web site. In other words the employee does not have to upload the inventory file. Also the query-date is used
internally as the date that the employee uses the web site. Hence, this will be "today".

## The UI consists of two main parts:

* Selecting the show date
* Showing a list of show performances with ticket
    * information
    * Title of show
    * Tickets left for the performance of the show on the show date
    * Tickets available for sale
    * Status of the ticket sale
    * NEW: Price of a ticket

The mock-up is purely for clarification. You have all the freedom for presenting this information the way you feel is best. Also don't forget about
validation feedback on incorrect input.
