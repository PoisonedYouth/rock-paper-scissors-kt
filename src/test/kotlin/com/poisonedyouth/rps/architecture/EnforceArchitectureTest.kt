package com.poisonedyouth.rps.architecture

import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.lang.ArchRule.Assertions.assertNoViolation
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import org.junit.jupiter.api.Test
import java.util.regex.Pattern

private val pattern = Pattern.compile(".*/build/classes/kotlin/main/.*")

private val allClasses = ClassFileImporter().withImportOption {
    it.matches(pattern)
}.importPackages("com.poisonedyouth.rps")

class EnforceArchitectureTest {

    @Test
    fun `domain package should not access application package`() {
        // when
        val domainPackageRule = noClasses()
            .that()
            .resideInAnyPackage("com.poisonedyouth.rps.domain.*")
            .should()
            .accessClassesThat()
            .resideInAnyPackage("com.poisonedyouth.rps.application")

        // then
        assertNoViolation(domainPackageRule.evaluate(allClasses))
    }

    @Test
    fun `failure package should not access application package`() {
        // when
        val failurePackageRule = noClasses()
            .that()
            .resideInAnyPackage("com.poisonedyouth.rps.failure")
            .should()
            .accessClassesThat()
            .resideInAnyPackage("com.poisonedyouth.rps.application")

        // then
        assertNoViolation(failurePackageRule.evaluate(allClasses))
    }

    @Test
    fun `failure package should not access domain package`() {
        // when
        val failurePackageRule = noClasses()
            .that()
            .resideInAnyPackage("com.poisonedyouth.rps.failure")
            .should()
            .accessClassesThat()
            .resideInAnyPackage("com.poisonedyouth.rps.domain.*")

        // then
        assertNoViolation(failurePackageRule.evaluate(allClasses))
    }
}
