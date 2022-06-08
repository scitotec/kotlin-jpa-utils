package com.scitotec.kotlinjpautils

import com.scitotec.kotlinjpautils.criteria.CriteriaQueryScope
import javax.persistence.EntityManager
import javax.persistence.TypedQuery
import javax.persistence.criteria.CriteriaQuery

/**
 * Create an instance of [TypedQuery] from an implicitly created (and typed) [CriteriaQuery] which can be modified.
 *
 * @return the new query instance
 */
inline fun <reified T> EntityManager.createTypedQuery(block: CriteriaQueryScope<T>.() -> Unit): TypedQuery<T> {
    return createQuery(criteriaBuilder.createQuery(T::class.java).also {
        CriteriaQueryScope(it, criteriaBuilder).apply(block)
    })
}
