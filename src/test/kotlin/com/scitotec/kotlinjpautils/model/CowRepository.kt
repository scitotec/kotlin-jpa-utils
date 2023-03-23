package com.scitotec.kotlinjpautils.model

interface CowRepository {
    fun findByNameStartingWith(prefix: String): List<Cow>
    fun countByWeightLessThan(weightInKg: Double): Long
}