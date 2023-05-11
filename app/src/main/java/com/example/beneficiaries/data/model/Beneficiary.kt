package com.example.beneficiaries.data.model

data class Beneficiary(
    val lastName: String,
    val firstName: String,
    val designationCode: String,
    val beneType: String,
    val socialSecurityNumber: String,
    val dateOfBirth: String,
    val middleName: String,
    val phoneNumber: String,
    val beneficiaryAddress: BeneficiaryAddress,
) {
    val designation: String?
        get() = if (designationCode == "P") "Primary" else if (designationCode == "C") "Contingent" else null
}
