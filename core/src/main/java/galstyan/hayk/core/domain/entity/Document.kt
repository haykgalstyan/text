package galstyan.hayk.core.domain.entity


data class Document(
    val name: String,
    val text: String,
    val dateCreated: Long,
    val dateEdited: Long
)