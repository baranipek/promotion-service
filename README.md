

# Run Test
mvn clean test


## Notes
The promotion rules **have an order** which will influence the behaviour and checkout value.

The promotion rules are items can be loaded from file or any database.

Promotion rule has  two attributes:
1. A match  (`Predicate<Basket>`)
2. An change in the basket  (`Function<Basket, Double>`)

