package com.scitotec.kotlinjpautils

import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence

data class RunInTransactionScope(val em: EntityManager)

fun EntityManagerFactory.runInTransaction(block: RunInTransactionScope.() -> Unit) {
    val em = createEntityManager()
    em.transaction.begin()
    try {
        block(RunInTransactionScope(em))
    } finally {
        em.transaction.rollback()
        em.close()
    }
}

fun testEntityManagerFactory(): EntityManagerFactory =
    Persistence.createEntityManagerFactory("com.scitotec.kotlinjpautils")
