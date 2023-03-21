package com.scitotec.kotlinjpautils.criteria

import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery

data class CriteriaQueryScope<T>(
    val query: CriteriaQuery<T>,
    val builder: CriteriaBuilder,
)