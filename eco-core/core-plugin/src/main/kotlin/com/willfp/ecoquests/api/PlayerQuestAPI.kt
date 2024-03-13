package com.willfp.ecoquests.api

import com.willfp.ecoquests.quests.Quests
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

object PlayerQuestAPI {
    fun getQuestIcons(player: Player): List<ItemStack> {
        return Quests.getShownQuests(player).map {
            it.buildIcon(player)
        }
    }
}