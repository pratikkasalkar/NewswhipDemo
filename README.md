
Go to https://replit.com/

Copy-paste 'Main.java' in TextEditor and hit Run.

ADD: adding a URL with an associated social score
REMOVE: removing a URL from the system
EXPORT: exporting statistics about the URLs stored in the system in a CSV style. The export should consist of the aggregated social score for the domains in the system.

As an example, a run of the system might look as follows:
ADD  http://www.rte.ie/news/politics/2018/1004/1001034-cso/ 20
ADD https://www.rte.ie/news/ulster/2018/1004/1000952-moanghan-mine/ 30
ADD http://www.bbc.com/news/world-europe-45746837 10
EXPORT
Produces a report displaying the domains, number of urls for each domain and the sum of social score for each domain, for example:

domain;urls;social_score 
rte.ie;2;50
bbc.com;1;10

REMOVE https://www.rte.ie/news/ulster/2018/1004/1000952-moanghan-mine/ 
EXPORT
Produces a report of:

---------------------------
domain;urls;social_score 
rte.ie;1;20
bbc.com;1;10
---------------------------
