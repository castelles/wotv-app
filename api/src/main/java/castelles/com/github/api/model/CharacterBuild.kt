package castelles.com.github.api.model

data class CharacterBuildDto(
    val name: String,
    val visionCardId: Int,
    val esperId: Int,
    val characterId: Int,
    val userid: Int,
    val reactionId: Int,
    val jobsId: List<Int>,
    val equipmentIds: List<Int>,
    val supportAbilityIds: List<Int>
)

data class CharacterBuildResponse(
    val id: Int,
    val name: String,
    val visionCard: VisionCard,
    val esper: Esper,
    val character: Character,
    val equipments: List<Equipments>
)

data class Equipments(
    val id: Int,
    val name: String,
    val image: String,
    val rarity: String,
    val tmr: Boolean,
    val equipmentType: EquipmentType?,
    val equipmentCategory: EquipmentCategory?
)

data class Character(
    val id: Int,
    val name: String,
    val image: String,
    val rarity: String,
    val element: Element
)

data class Element(
    val description: String,
    val image: String
)

data class Esper(
    val id: Int,
    val name: String,
    val image: String,
    val rarity: String,
    val element: Element
)

data class VisionCard(
    val id: Int,
    val description: String,
    val image: String
)

data class JobResponse(
    val job: Job,
    val main: Boolean
)

data class Job(
    val id: Int,
    val description: String,
    val image: String
)
