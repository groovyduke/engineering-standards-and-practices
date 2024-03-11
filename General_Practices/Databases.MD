# Naming

All object name(tables, columns, views, etc) should be lowercase, and use underscores to separate words
example:
```sql
SELECT * FROM user_role;
```

## Indexes
You should use indexes on columns or sets of columns that your use to look up tables regularly. 
An index that is a set of columns that uniquely identifies a row is often called a natural key.

Naming for an index should follow this convention:
```sql
${table_name}_${colunm_name}..._${colunm_name_n}_idx
```

for example
```sql
user_email_idx
user_role_user_id_role_id_idx
```

## Normalizing vs Denormalizing tables
Normalization is the process of breaking up tables to eliminate redundant data, this was very popular when storage was at a premium, and 
does still have merit in some cases, but not all. The downside to an over normalized database is you usually have to do many joins to 
stitch the data back together and be able to filter to get the results you are looking for, for thing that go beyond basic CRUD of
a single table.

Denormalization is where you allow some duplication to make querying easier, and quicker.  You may also store precalculated fields for
calculations that are very expensive to calculate on the fly. Denormilization is often used for data warehousing and reporting.

Most modern application database have to find a balance between the two, because going to either extreme can either create a database
that is hard and inefficient to query, or a database that has a lot of duplicate data, and it may be unclear what is the definitive source of
truth.

## SQL vs No SQL
SQL is good for storing important structured data, that you may want to slice and dice using SQL queries. While many SQL DBs have incorporated
JSON which gives them a flavor of No SQL when dealing with unstructured data, they often deal with that data in a structured way. No SQL on
the other had is good for storing not structured, denormalized data and scaling beyond the normal confines of SQL.  Transactions are supported
in both but SQL favors consistently, where and NO SQL because of it's scaling properties favors eventual consistency.

Which Data store you use depends on the project you are working on, and the requirements of that project. You can use more than one and this
is actually fairly common if you used a SQL database and redis(used for caching and distribute sessions), which is an in memory key/value 
store, and could be considered part of No_SQL 


## Rant about views/stored procedures/triggers
Views and stored procedures when written well can be faster than programing logic in your application, however they can also be can also
be hard to maintain, and debug, due to there nature, security and procedures put in place to control database updates. I would recommend
to use them sparingly and only when necessary, and you need that level of performance. You should also do profiling to make sure you
are actually getting the performance you expect that make the trade-off of having them worthwhile.
