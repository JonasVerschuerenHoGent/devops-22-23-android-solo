package com.example.test.screens.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test.domain.Account
import com.example.test.domain.Department
import com.example.test.domain.Role

class ListUsersViewModel : ViewModel() {

    //live data objects
    private val _listAccount = MutableLiveData<List<Account>>()
    val listUsers: LiveData<List<Account>>
        get() = _listAccount

    init {
        _listAccount.value = listOf(
            Account(
                1,
                "Test@Test.be",
                "Tester",
                Department.DIT,
                "Talen",
                "hi",
                "045897-63015",
                Role.Admin,
                "Tester@tester.gmail.com"
            ),
            Account(
                2,
                "Test@Test.be",
                "Tester",
                Department.DIT,
                "Talen",
                "hi",
                "045897-63015",
                Role.Admin,
                "Tester@tester.gmail.com"
            ),
            Account(
                3,
                "Test@Test.be",
                "Tester",
                Department.DIT,
                "Talen",
                "hi",
                "045897-63015",
                Role.Admin,
                "Tester@tester.gmail.com"
            ),
            Account(
                4,
                "Test@Test.be",
                "Tester",
                Department.DIT,
                "Talen",
                "hi",
                "045897-63015",
                Role.Admin,
                "Tester@tester.gmail.com"
            ),
            Account(
                5,
                "Test@Test.be",
                "Tester",
                Department.DIT,
                "Talen",
                "hi",
                "045897-63015",
                Role.Admin,
                "Tester@tester.gmail.com"
            )

        )
    }

}