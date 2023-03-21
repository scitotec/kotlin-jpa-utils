package com.scitotec.kotlinjpautils.criteria

import jakarta.persistence.criteria.*
import kotlin.reflect.KProperty1


fun <T, R>                    Path<T>.get(p: KProperty1<T, R>):       Path<R> = get(p.name)
fun <T, R, C : Collection<R>> Path<T>.get(p: KProperty1<T, C>): Expression<C> = get(p.name)
@JvmName("getMap")
fun <T, K, V, M : Map<K, V>>  Path<T>.get(p: KProperty1<T, M>): Expression<M> = get(p.name)

fun <S, T, R>    From<S, T>.join(p: KProperty1<T,             R>, joinType: JoinType = JoinType.INNER):           Join<S, R> =           join(p.name, joinType)
fun <S, T, R>    From<S, T>.join(p: KProperty1<T,       List<R>>, joinType: JoinType = JoinType.INNER):       ListJoin<S, R> =       joinList(p.name, joinType)
fun <S, T, R>    From<S, T>.join(p: KProperty1<T,        Set<R>>, joinType: JoinType = JoinType.INNER):        SetJoin<S, R> =        joinSet(p.name, joinType)
fun <S, T, R>    From<S, T>.join(p: KProperty1<T, Collection<R>>, joinType: JoinType = JoinType.INNER): CollectionJoin<S, R> = joinCollection(p.name, joinType)
fun <S, T, K, V> From<S, T>.join(p: KProperty1<T,     Map<K, V>>, joinType: JoinType = JoinType.INNER):     MapJoin<S, K, V> =        joinMap(p.name, joinType)

fun <S, T, R>    FetchParent<S, T>.fetch(p: KProperty1<T,             R>, joinType: JoinType = JoinType.INNER): Fetch<T, R> = fetch(p.name, joinType)
@JvmName("fetchCollection")
fun <S, T, R>    FetchParent<S, T>.fetch(p: KProperty1<T, Collection<R>>, joinType: JoinType = JoinType.INNER): Fetch<T, R> = fetch(p.name, joinType)
@JvmName("fetchMap")
fun <S, T, K, V> FetchParent<S, T>.fetch(p: KProperty1<T,     Map<K, V>>, joinType: JoinType = JoinType.INNER): Fetch<T, V> = fetch(p.name, joinType)
