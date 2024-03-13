package com.willfp.ecoquests.libreforge.custom

import com.willfp.libreforge.toDispatcher
import com.willfp.libreforge.triggers.Trigger
import com.willfp.libreforge.triggers.TriggerData
import com.willfp.libreforge.triggers.TriggerParameter
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.ZombieVillager
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.potion.PotionEffectType

object TriggerTherapyVillager : Trigger("therapy_villager") {
    override val parameters = setOf(
        TriggerParameter.PLAYER,
        TriggerParameter.EVENT
    )

    @EventHandler
    fun handle(event: PlayerInteractEntityEvent) {
        val clickedEntity = event.rightClicked as? ZombieVillager ?: return
        val player = event.player

        if (
            clickedEntity.type == EntityType.ZOMBIE_VILLAGER &&
            clickedEntity.hasPotionEffect(PotionEffectType.WEAKNESS) &&
            !clickedEntity.isConverting &&
            player.inventory.itemInMainHand.type == Material.GOLDEN_APPLE
        ) {
            // 检查交互手是主手，以避免重复触发
            if (event.hand == EquipmentSlot.HAND) {
                this.dispatch(
                    player.toDispatcher(),
                    TriggerData(
                        player = player,
                        event = event
                    )
                )
            }
        }
    }
}
