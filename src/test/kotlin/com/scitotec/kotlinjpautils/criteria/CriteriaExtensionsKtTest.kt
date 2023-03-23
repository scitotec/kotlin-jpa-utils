package com.scitotec.kotlinjpautils.criteria

import com.scitotec.kotlinjpautils.RunInTransactionScope
import com.scitotec.kotlinjpautils.createTypedQuery
import com.scitotec.kotlinjpautils.model.Cow
import com.scitotec.kotlinjpautils.model.CowRepository
import com.scitotec.kotlinjpautils.runInTransaction
import com.scitotec.kotlinjpautils.testEntityManagerFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals


class CriteriaExtensionsKtTest {
    private lateinit var sessionFactory: EntityManagerFactory

    @BeforeTest
    fun setUp() {
        sessionFactory = testEntityManagerFactory()
    }

    class CowRepositoryImpl(private val em: EntityManager) : CowRepository {
        override fun findByNameStartingWith(prefix: String): List<Cow> {
            return em.createTypedQuery<Cow> {
                val root = query.from(Cow::class.java)
                query.where(builder.like(root.get(Cow::name), "$prefix%"))
            }.resultList
        }

        override fun countByWeightLessThan(weightInKg: Double): Long {
            return em.createTypedQuery<Long> {
                val root = query.from(Cow::class.java)
                query.select(builder.count(root))
                query.where(builder.lessThan(root.get(Cow::weightInKg), weightInKg))
            }.singleResult
        }
    }


    @Test
    fun `get allows typed access`() {
        sessionFactory.runInTransaction {
            createCows()

            val repository = CowRepositoryImpl(em)

            repository.findByNameStartingWith("Kuh").let { actual ->
                assertEquals(1, actual.size)
                assertContains(actual.map { it.name }, "Kuhnigunde")
            }

            assertEquals(1, repository.countByWeightLessThan(500.0))

            assertEquals(2, repository.countByWeightLessThan(1000.0))
        }
    }

    private fun RunInTransactionScope.createCows() {
        em.persist(Cow {
            name = "Elsa"
            weightInKg = 450.3
        })
        em.persist(Cow {
            name = "Kuhnigunde"
            weightInKg = 690.91
        })
        em.flush()
        em.clear()
    }
}