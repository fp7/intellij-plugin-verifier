package com.jetbrains.plugin.structure.teamcity.recipe

enum class RecipeRequirementType(
  val type: String,
  val isValueRequired: Boolean,
  val valueCanBeEmpty: Boolean,
) {
  EXISTS("exists", false, true),
  NOT_EXISTS("not-exists", false, true),
  EQUALS("equals", true, true),
  NOT_EQUALS("not-equals", false, true),
  MORE_THAN("more-than", true, false),
  NOT_MORE_THAN("not-more-than", true, false),
  LESS_THAN("less-than", true, false),
  NOT_LESS_THAN("not-less-than", true, false),
  STARTS_WITH("starts-with", true, false),
  CONTAINS("contains", true, false),
  DOES_NOT_CONTAIN("does-not-contain", false, true),
  ENDS_WITH("ends-with", true, false),
  MATCHES("matches", true, true),
  DOES_NOT_MATCH("does-not-match", true, true),
  VERSION_MORE_THAN("version-more-than", true, false),
  VERSION_NOT_MORE_THAN("version-not-more-than", true, false),
  VERSION_LESS_THAN("version-less-than", true, false),
  VERSION_NOT_LESS_THAN("version-not-less-than", true, false),
  ANY("any", false, true),
  ;

  companion object {
    fun from(type: String): RecipeRequirementType {
      return RecipeRequirementType
        .values()
        .find { it.type.equals(type, ignoreCase = true) }
        ?: throw IllegalArgumentException(
          "Unsupported requirement type $type. Supported values are: " + RecipeRequirementType.values().joinToString()
        )
    }
  }
}