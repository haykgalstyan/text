package galstyan.hayk.text.domain.entity


data class Document(
    val name: String,
    val text: String,
    val dateCreated: Long,
    val dateEdited: Long
)