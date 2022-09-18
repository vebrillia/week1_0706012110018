package Interface

interface CardListener {
    fun onCardClick(position: Int)
    fun onDeleteClick(position: Int)
    fun onEditClick(position: Int)
}