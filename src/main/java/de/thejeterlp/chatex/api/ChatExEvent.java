/*
 * Copyright (C) 2017 TheJeterLP
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package de.thejeterlp.chatex.api;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public final class ChatExEvent extends PlayerEvent {

    private static final HandlerList handlers = new HandlerList();
    private String format;

    public ChatExEvent(Player who, String format) {
        super(who);
        this.format = format;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public void replaceVariable(String variable, String value) {
        this.format = format.replaceAll(variable, value);
    }

    public String getFormat() {
        return this.format;
    }

    public void setFormat(String Format) {
        this.format = format;
    }

}
