package com.scitotec.kotlinjpautils.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
open class Cow {
    companion object {
        operator fun invoke(block: Cow.() -> Unit): Cow = Cow().apply(block)
    }

    @Id
    open var name: String = ""

    @Column(nullable = false)
    open var weightInKg: Double = 0.0
}