package com.example.test.domain

enum class Mode {
    IAAS,
    SAAS,
    PAAS
}
enum class Template {
    AI,
    MS_SQL,
    MySQL,
    PostgreSQL,
    MongoDB
}
enum class Availability{
    BUSINESS_HOURS,
    ALWAYS
}
enum class State{
    Requested,
    InProgress,
    Denied,
    Accepted,
}