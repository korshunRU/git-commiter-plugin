package com.github.korshunru.rgsgitcommiter.dto

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class IssueDto(
    val fields: FieldsDto? = null,
    val error: String? = null
)

@JsonInclude(JsonInclude.Include.NON_NULL)
data class FieldsDto(
    val description: String? = null,
    val summary: String? = null,
    val issuelinks: List<LinkDto> = emptyList(),
    val assignee: PeopleDto? = null,
    val creator: PeopleDto? = null,
    val status: StatusDto? = null
)

@JsonInclude(JsonInclude.Include.NON_NULL)
data class LinkDto(
    val type: LinkTypeDto,
    val outwardIssue: OutwardIssueDto?
)

@JsonInclude(JsonInclude.Include.NON_NULL)
data class OutwardIssueDto(
    val id: String,
    val key: String,
    val self: String,
    val fields: OutwardFieldsDto?
)

@JsonInclude(JsonInclude.Include.NON_NULL)
data class OutwardFieldsDto(
    val summary: String
)

@JsonInclude(JsonInclude.Include.NON_NULL)
data class LinkTypeDto(
    val name: String
)

@JsonInclude(JsonInclude.Include.NON_NULL)
data class PeopleDto(
    val displayName: String
)

@JsonInclude(JsonInclude.Include.NON_NULL)
data class StatusDto(
    val name: String
)