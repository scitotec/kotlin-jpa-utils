package com.scitotec.kotlinjpautils.criteria

import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery

data class CriteriaQueryScope<T>(
    val query: CriteriaQuery<T>,
    val builder: CriteriaBuilder,
)