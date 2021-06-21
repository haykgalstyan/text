package galstyan.hayk.core.domain.entity

import java.io.Serializable


data class Document(
    val title: String,
    val text: String,
    val dateCreated: Long,
    val dateEdited: Long
) : Serializable