package network.bisq.mobile.domain.data.persistance

import com.russhwolf.settings.Settings
import network.bisq.mobile.domain.data.model.BaseModel

/**
 * This implementation relies on multiplatform settings
 */
 class KeyValuePersister(settings: Settings): PersistenceSource {
    override suspend fun <T : BaseModel> save(item: T) {
        TODO("Not yet implemented")
    }

    override suspend fun <T : BaseModel> saveAll(items: List<T>) {
        TODO("Not yet implemented")
    }

    override suspend fun <T : BaseModel> get(): T? {
        TODO("Not yet implemented")
    }

    override suspend fun <T : BaseModel> getAll(): List<T> {
        TODO("Not yet implemented")
    }

    override suspend fun <T : BaseModel> delete(item: T) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll() {
        TODO("Not yet implemented")
    }

    override suspend fun clear() {
        TODO("Not yet implemented")
    }

}