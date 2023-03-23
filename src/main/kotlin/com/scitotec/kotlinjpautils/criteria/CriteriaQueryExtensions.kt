package com.scitotec.kotlinjpautils.criteria

import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Root

inline fun <reified R> CriteriaQuery<*>.from(): Root<R> = from(R::class.java)
