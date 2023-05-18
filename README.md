# SAP LUW or Business Transaction

**Problem:** user can change data on many screens without saving to DB. Also user want to
query data in DB with attention to modified data.

The query is executed on DB can contain JOIN and WHERE blocks.

### How to resolve problem by Jmix

Jmix has components:

- EntityManager
- DataManager
- DataContext/DataContainers

Create common screen (0 screen) with DataContainers to share them between fragments.
0 screen should open each fragment. Fragment has access to the DataContainer.

Quering DataContext/DataContainers by JPQL or SQL isn't possible, but developer can use
Java methods to operate with data in containers. If user want to query modified data (placed
in DataContext/DataContainers), this object can't be in DB and Developer should avoid
the problem by himself.

### Flush data from DataContext to DB before query execution

RDBMS support transaction context. Can it be used to imitate business transaction by
flushing data context during transaction opening. The process is: flush data from DataContext
to DB -> query from DB -> rollback db changes.

This behaviour is implemented in CTableEdit. Create series of T004 and press Refresh button.

**Questions**

- what to do with Listeners in EntityManager, how to avoid them execution and is it needed to avoid
- when we do something with datamanager in component, we know nothing about dataContext
- if we incorporate DataContext in DataManager how to define borders of DataContext

**This way easy to use/implement, straightforward and work consistently. But it looks slightly strange**

### Using in memory DB layer based on Apache Calcite

Apache Calcite can do DB work on Application Level. It will parse SQL, create an execution plan and
evaluate it work with DB as a bunch of tables. As expected it can work slowly than execution in DB
and slightly different.

For our case we can work in a different way depending on business transaction needed:

- if business transaction needed dataManager should work through Apache Calcite
- if business transaction not needed dataManager should work directly with DB

Where is place for Apache Calcite, because Apache Calcite works only with SQL it can be
added with two ways:

- using QueryDSL and generate query SQL/JPQL depends on implementation (Apache Calcite/EclipseLink)
- DataManager -> EclipseLink -> Apache Calcite/DB 

**Questions**

- when we do something with datamanager in component, we know nothing about dataContext
- if we incorporate DataContext in DataManager how to define borders of DataContext


## Conclusion

We don't know the best way to implement business transaction context. What we can do that
add API which everyone will use to interact with Business Transactions