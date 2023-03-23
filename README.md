# Kotlin JPA Utils

A set of helpers for writing less boilerplate code when using Kotlin with JPA.

## Installation

* Use version [0.0.1](https://mvnrepository.com/artifact/com.scitotec.kotlinjpautils/kotlin-jpa-utils/0.0.1)
  for `javax.persistence`
* Use version [0.1.0](https://mvnrepository.com/artifact/com.scitotec.kotlinjpautils/kotlin-jpa-utils/0.1.0)
  for `jakarta.persistence`

## Examples

### Use criteria extensions for typed criteria queries

```kotlin
package com.scitotec.kotlinjpautils.example

import com.scitotec.kotlinjpautils.criteria.get // ❗️
import jakarta.persistence.*

@Entity
open class Cow {
    @Id
    open var name: String = ""

    @Column(nullable = false)
    open var weightInKg: Double = 0.0
}


class CowRepository(private val em: EntityManager) {
    fun findCows(namePrefix: String, maxWeight: Double): List<Long> {
        val builder = em.criteriaBuilder
        val query = builder.createQuery(Long::class.java)
        val root = query.from(Cow::class.java)
        query.select(builder.count(root))

        query.where(builder.like(root.get(Cow::name), "$namePrefix%"))      // 🎉 typed access
        query.where(builder.lessThan(root.get(Cow::weightInKg), maxWeight)) // 🎉 typed access

        return em.createQuery(query).resultList
    }
}
```

### Use extension for less criteria query boilerplate

This helper gives you access to the `CriteriaQuery<T>` and `CriteriaBuilder` inside of the closure and returns the
created `TypedQuery<T>`.

```kotlin
package com.scitotec.kotlinjpautils.example

import com.scitotec.kotlinjpautils.createTypedQuery
import jakarta.persistence.*

@Entity
open class Cow {
    @Id
    open var name: String = ""

    @Column(nullable = false)
    open var weightInKg: Double = 0.0
}


class CowRepository(private val em: EntityManager) {

    fun findCows(): List<Cow> {
        return em.createTypedQuery<Cow> {  // 🎉
            val root = query.from(Cow::class.java)
            query.where(builder.like(/* ... */))
        }.resultList
    }
}
```