
## Story 
Marketing team want to offer promotions as an incentive for to purchase these items.

If you spend over £60, then you get 10% off your purchase
If you buy 2 or more travel card holders then the price drops to £8.50.

## Run Test
mvn clean test


## Notes
The promotion rules **have an order** which will influence the behaviour and checkout value.

The promotion rules are hardcoded in test classed now  can  can be loaded from file or any database , the same for items.

Promotion rule has  two attributes:
1. A match  (`Predicate<Basket>`) 
2. An change value in the basket  (`Function<Basket, Double>`)
3. Promotion rule description 

