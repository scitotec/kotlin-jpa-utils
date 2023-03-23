package com.scitotec.kotlinjpautils.criteria

import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Root

data class CriteriaQueryScope<T>(
    val query: CriteriaQuery<T>,
    val builder: CriteriaBuilder,
) {
    inline fun <reified R> from(): Root<R> = query.from()
}
