package com.willfp.ecoquests.libreforge

import com.willfp.ecoquests.api.event.PlayerQuestStartEvent
import com.willfp.libreforge.triggers.Trigger
import com.willfp.libreforge.triggers.TriggerData
import com.willfp.libreforge.triggers.TriggerParameter
import org.bukkit.event.EventHandler

object TriggerStartQuest : Trigger("start_quest") {
    override val parameters = setOf(
        TriggerParameter.PLAYER,
        TriggerParameter.EVENT
    )

    @EventHandler
    fun handle(event: PlayerQuestStartEvent) {
        val player = event.player

        this.dispatch(
            player,
            TriggerData(
                player = player,
                event = event
            )
        )
    }
}