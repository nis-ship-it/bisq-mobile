package network.bisq.mobile.domain.data.persistance

import network.bisq.mobile.domain.data.model.BaseModel

interface PersistenceSource {
    
    suspend fun <T: BaseModel> save(item: T)
    suspend fun <T: BaseModel>saveAll(items: List<T>)
    suspend fun <T: BaseModel> get(): T?
    suspend fun <T: BaseModel> getAll(): List<T>
    suspend fun <T: BaseModel> delete(item: T)
    suspend fun deleteAll()
    suspend fun clear()
}