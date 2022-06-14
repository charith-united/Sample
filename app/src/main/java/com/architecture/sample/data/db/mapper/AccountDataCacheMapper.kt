package com.architecture.sample.data.db.mapper

import com.architecture.sample.data.db.entity.AccountDataCacheEntity
import com.architecture.sample.data.mapper.EntityMapper
import com.architecture.sample.data.model.AccountData
import javax.inject.Inject

class AccountDataCacheMapper @Inject constructor(): EntityMapper<AccountDataCacheEntity, AccountData> {
    override fun mapFromEntity(entity: AccountDataCacheEntity): AccountData = AccountData(
        email = entity.email,
        id = entity.id,
        name = entity.name,
        token = entity.token
    )

    override fun mapToEntity(model: AccountData): AccountDataCacheEntity = AccountDataCacheEntity(
        email = model.email,
        id = model.id,
        name = model.name,
        token = model.token
    )
}