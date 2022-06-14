package com.architecture.sample.data.network.mapper

import com.architecture.sample.data.mapper.EntityMapper
import com.architecture.sample.data.model.AccountData
import com.architecture.sample.data.network.entity.AccountDataNetworkEntity
import javax.inject.Inject

class AccountDataNetworkMapper @Inject constructor(): EntityMapper<AccountDataNetworkEntity, AccountData> {

    override fun mapFromEntity(entity: AccountDataNetworkEntity): AccountData = AccountData(
        email = entity.email,
        id = entity.id,
        name = entity.name,
        token = entity.token
    )

    override fun mapToEntity(model: AccountData): AccountDataNetworkEntity = AccountDataNetworkEntity(
        email = model.email,
        id = model.id,
        name = model.name,
        token = model.token
    )
}