package galstyan.hayk.core.domain.entity

import java.io.Serializable


data class Document(
    val id: String? = null,
    val title: String? = null,
    val text: String? = null,
    val timeCreated: Long? = null,
    val timeEdited: Long? = null,
) : Serializable