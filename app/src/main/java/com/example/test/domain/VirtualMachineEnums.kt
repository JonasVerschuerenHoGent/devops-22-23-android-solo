package com.example.test.domain

enum class Mode(val printableName: String) {
    PAAS("Platform as a Service"),
    IAAS("Infrastructure as a Service"),
    SAAS("Software as a Service"),
    Sjabloon1("Sjabloon 1"),
    Sjabloon2("Sjabloon 2"),
    AI("AI template"),
    MS_SQL("Microsoft SQL Server template"),
    PostgreSQL("PostgreSQL template"),
    MongoDB("MongoDB template"),
    MySQL("MySQL template"),
}

enum class Availability(val printableName: String) {
    BUSINESS_HOURS("Business hours"),
    ALWAYS("24/7")
}
enum class State(val printableName: String) {
    Requested("Requested"),
    InProgress("In progress"),
    Denied("Denied"),
    Accepted("Accepted"),
}