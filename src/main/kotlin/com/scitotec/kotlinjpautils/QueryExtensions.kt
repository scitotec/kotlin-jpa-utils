package com.scitotec.kotlinjpautils

import jakarta.persistence.*


/**
 * Execute a SELECT query that returns a single result.
 * @return the result or null if no result is found
 * @throws NonUniqueResultException if more than one result
 * @throws IllegalStateException if called for a Jakarta
 *         Persistence query language UPDATE or DELETE statement
 * @throws QueryTimeoutException if the query execution exceeds
 *         the query timeout value set and only the statement is
 *         rolled back
 * @throws TransactionRequiredException if a lock mode other than
 *         <code>NONE</code> has been set and there is no transaction
 *         or the persistence context has not been joined to the
 *         transaction
 * @throws PessimisticLockException if pessimistic locking
 *         fails and the transaction is rolled back
 * @throws LockTimeoutException if pessimistic locking
 *         fails and only the statement is rolled back
 * @throws PersistenceException if the query execution exceeds
 *         the query timeout value set and the transaction
 *         is rolled back
 */
fun Query.getSingleResultOrNull(): Any? = try {
    singleResult
} catch (e: NoResultException) {
    null
}

/**
 * Execute a SELECT query that returns a single result.
 * @return the result or null if no result is found
 * @throws NonUniqueResultException if more than one result
 * @throws IllegalStateException if called for a Jakarta
 *         Persistence query language UPDATE or DELETE statement
 * @throws QueryTimeoutException if the query execution exceeds
 *         the query timeout value set and only the statement is
 *         rolled back
 * @throws TransactionRequiredException if a lock mode other than
 *         <code>NONE</code> has been set and there is no transaction
 *         or the persistence context has not been joined to the
 *         transaction
 * @throws PessimisticLockException if pessimistic locking
 *         fails and the transaction is rolled back
 * @throws LockTimeoutException if pessimistic locking
 *         fails and only the statement is rolled back
 * @throws PersistenceException if the query execution exceeds
 *         the query timeout value set and the transaction
 *         is rolled back
 */
fun <T> TypedQuery<T>.getSingleResultOrNull(): T? = try {
    singleResult
} catch (e: NoResultException) {
    null
}

