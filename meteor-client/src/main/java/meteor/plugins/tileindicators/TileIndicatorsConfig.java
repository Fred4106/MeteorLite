/*
 * Copyright (c) 2018, Tomas Slusny <slusnucky@gmail.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package meteor.plugins.tileindicators;

import meteor.config.Alpha;
import meteor.config.*;

import java.awt.*;

@ConfigGroup("tileindicators")
public interface TileIndicatorsConfig extends Config
{
	@Alpha
	@ConfigItem(
		keyName = "highlightDestinationColor",
		name = "Destination tile",
		description = "Configures the highlight color of current destination",
		position = 1
	)
	default Color highlightDestinationColor()
	{
		return Color.GRAY;
	}

	@ConfigItem(
		keyName = "highlightDestinationTile",
		name = "Highlight destination tile",
		description = "Highlights tile player is walking to",
		position = 2
	)
	default boolean highlightDestinationTile()
	{
		return true;
	}

	@ConfigItem(
		keyName = "destinationTileBorderWidth",
		name = "Destination border width",
		description = "Width of the destination tile marker border",
		position = 3
	)
	default double destinationTileBorderWidth()
	{
		return 2;
	}

	@Alpha
	@ConfigItem(
		keyName = "highlightHoveredColor",
		name = "Hovered tile",
		description = "Configures the highlight color of hovered tile",
		position = 4
	)
	default Color highlightHoveredColor()
	{
		return new Color(0, 0, 0, 0);
	}

	@ConfigItem(
		keyName = "highlightHoveredTile",
		name = "Highlight hovered tile",
		description = "Highlights tile player is hovering with mouse",
		position = 5
	)
	default boolean highlightHoveredTile()
	{
		return false;
	}

	@ConfigItem(
		keyName = "hoveredTileBorderWidth",
		name = "Hovered tile border width",
		description = "Width of the hovered tile marker border",
		position = 6
	)
	default double hoveredTileBorderWidth()
	{
		return 2;
	}

	@Alpha
	@ConfigItem(
		keyName = "highlightCurrentColor",
		name = "True tile",
		description = "Configures the highlight color of current true tile",
		position = 7
	)
	default Color highlightCurrentColor()
	{
		return Color.CYAN;
	}

	@ConfigItem(
		keyName = "highlightCurrentTile",
		name = "Highlight true tile",
		description = "Highlights true tile player is on as seen by server",
		position = 8
	)
	default boolean highlightCurrentTile()
	{
		return false;
	}

	@ConfigItem(
		keyName = "currentTileBorderWidth",
		name = "True tile border width",
		description = "Width of the true tile marker border",
		position = 9
	)
	default double currentTileBorderWidth()
	{
		return 2;
	}
}
