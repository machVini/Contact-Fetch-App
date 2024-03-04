package com.br.contactfetchapp.factory

import com.br.contactfetchapp.data.model.RandomUser
import com.br.contactfetchapp.data.source.local.room.entity.RandomUserEntity

object RandomUserFactory {

    fun make(
        name: String = "John Doe",
        gender: String = "Male",
        location: String = "US",
        email: String = "john@example.com",
        cell: String = "1234-5678",
        dob: String = "18",
        nat: String = "BR",
        phone: String = "99-233-555",
        registered: String = "22/05/2015",
        picture: String = "http://test",
    ): RandomUser {
        return RandomUser(
            fullName = name,
            gender = gender,
            location = location,
            email = email,
            cell = cell,
            dob = dob,
            nat = nat,
            phone = phone,
            registered = registered,
            picture = picture,
        )
    }

    fun makeEntity(
        name: String = "John Doe",
        gender: String = "Male",
        location: String = "US",
        email: String = "john@example.com",
        cell: String = "1234-5678",
        dob: String = "18",
        nat: String = "BR",
        phone: String = "99-233-555",
        registered: String = "22/05/2015",
        picture: String = "http://test",
    ): RandomUserEntity {
        return RandomUserEntity(
            fullName = name,
            gender = gender,
            location = location,
            email = email,
            cell = cell,
            dob = dob,
            nat = nat,
            phone = phone,
            registered = registered,
            picture = picture,
        )
    }
}
