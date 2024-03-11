# QA

While I do believe that developers should write integration and unit tests, QA, especially manual QA is important. Manually QA will try to
break your code in ways you never imagined. If you can convert those manual tests to automated tests that's great because it will give QA more
time to come up with other ways to break your application. Having QA break the application is better than the customer breaking the application.

The best QA that I've known are meticulous and anal. They will find bugs and as many edge cases around the bug as they can. If they can work
around the bug they will to see what lies beyond the bug, which could be more bugs or everything else working fine. Manual QA also develops
and maintains lists of test cases to ensure before you release that you didn't introduce any new bugs.

----

## How you should write tickets/issues/Bugs for a bug system. 
Tickets/Issues/Bugs should be as detailed as possible/time allows without feeling like you're reading a book or log essay.

A bug ticket should have the following sections:

* Overview a description of what is going wrong, which can include and go a bit beyond the title.
* If there are multiple environments what environment tha bug was found on
* Steps to reproduce(for new developers you may need to be more explicit)
* If applicable screenshots or video capture
* Expected results
* Actual results
* Logs from when the issue happened with a stack trace if available or a link back to the time period of the logs. Also noting if no stack trace is seen can be helpful.
* Console logs. In the front end any error or logs that come up in the Chrome Console(can be opened with the f12 key, Ctrl + Shift + I, or under the more tools -> developer tools)
* In some cases it also helps to look at the network tab and see what is being sent/received from calls to the backend and including that info.

How much goes into a ticket can be a balancing act, while you shouldn't put multiple different use cases in one ticket, you may combine
into one ticket small minor variations and edge cases for a single use case. You don't want to write a book, but give enough detail for the 
developer to be able to find and fix the bug without a lot of running around or back and forth.

The more due diligence that goes into a ticket, the less the developer has to do to find this information on their own, and they might be able
to turn it around a lot quicker.
----
