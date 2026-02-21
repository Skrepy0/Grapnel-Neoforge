# Grapnel Enchantment Mod for Minecraft 1.21.1

## Overview

The Grapnel Mod adds thrilling new enchantments to fishing rods, transforming them into a grappling hook-like tool that allows players to swing through the air like the vertical maneuvering equipment from popular anime. Additionally, it introduces a **Toughness** enchantment that strengthens the fishing line, enabling longer casts and reducing breakage. This mod enhances mobility and adds a fun new way to traverse your Minecraft world.

---

## Features

### Enchantment: Grapnel

- **Enchantment ID**: `grapnel:grapnel`
- **Max Level**: 1 (single-level enchantment)
- **Rarity**: Treasure
- **Applicable Items**: Fishing rods

**Effect**: When a fishing rod with the Grapnel enchantment is cast and then retrieved (right-click), the user is pulled towards the fishing bobber. This allows for Spider-Man-like swinging and vertical mobility.

---

### Enchantment: Toughness

- **Enchantment ID**: `grapnel:toughness`
- **Max Level**: 2
- **Rarity**: Common
- **Applicable Items**: Fishing rods

**Effect**: Strengthens the fishing line, allowing you to cast farther and reducing the chance of the line breaking under tension. The effective casting distance is multiplied by a factor that increases with each level, providing a smoother and more reliable fishing experience.

The formula used for distance adjustment:
- **Level 1**: ~1.2× multiplier (approximate)
- **Level 2**: ~1.5× multiplier (approximate)

(Exact values are derived from a carefully balanced mathematical function.)

---

### Core Gameplay Mechanics

1. **Grappling Hook Functionality**: Grapnel enchantment pulls the player towards the bobber.
2. **Vertical Mobility**: Allows players to swing through the air.
3. **Fall Damage Protection**: By default, users are protected from fall damage when landing after using the grapnel enchantment (first landing only).
4. **Extended Fishing Range**: Toughness enchantment increases the effective range of your fishing rod, making it easier to reach distant water spots or retrieve the bobber from farther away.

---

### Command: `/grapnel_settings`

Customize your grapnel experience with configurable settings:

```mcfunction
/grapnel_settings enableFallingBuffer <true/false>
```

- **Default**: `true` (enabled)
- **When enabled**: Players won't take fall damage on their first landing after using the grapnel
- **When disabled**: No fall damage protection - players will take normal fall damage

---

## Installation

### Requirements

- **Minecraft Version**: 1.21.1
- **NeoForge Version**: 21.1.216 or compatible
- **Java Version**: 21

### Installation Steps

1. Download and install NeoForge 21.1.216 for Minecraft 1.21.1.
2. Download the latest version of the Grapnel mod.
3. Place the mod JAR file in your Minecraft `mods` folder.
4. Launch Minecraft with the NeoForge profile.

---

## How to Obtain the Enchantments

### In-Game Methods

Both **Grapnel** and **Toughness** enchantments can be obtained through:

1. **Loot Chests**: Enchanted fishing rods may appear in:
    - Dungeon chests
    - Mineshaft chests
    - Stronghold libraries
    - Shipwreck treasure chests
    - End city chests

2. **Villager Trading**: Librarian villagers may offer enchanted fishing rods (if their trades include fishing rods).

3. **Enchanting Table** (for Toughness only): Toughness can be obtained directly from an enchanting table at a moderate cost. Grapnel remains a treasure enchantment.

### Data Pack Integration

The mod is fully compatible with data packs. You can add either enchantment to loot tables or trading recipes using the IDs:
- `grapnel:grapnel`
- `grapnel:toughness`

---

## Usage Instructions

### Basic Usage (Grapnel)

1. **Enchant a Fishing Rod** with the Grapnel enchantment.
2. **Cast the Line**: Right-click to cast the fishing line.
3. **Retrieve to Swing**: Right-click again – instead of just pulling in the line, you'll be pulled towards the bobber!

### Advanced Techniques (Grapnel)

- **Momentum Control**: Time your swings to chain movements together.
- **Directional Control**: Look in the direction you want to swing while retrieving.
- **Precision Landing**: Aim for specific blocks to land exactly where you want.

### Fishing with Toughness

1. **Enchant a Fishing Rod** with Toughness (level I or II).
2. **Cast as usual**: You'll notice the bobber travels farther before settling.
3. **Reel in**: The line is stronger, so you can fish from greater distances without the line snapping.

### Safety Tips

- The falling buffer is enabled by default, but can be disabled for more challenge.
- Practice in safe environments before attempting risky maneuvers.
- Be aware of your surroundings – you might swing into unexpected places!

---

## Configuration

### Command Details

| Setting               | Default | Description                                                            |
|-----------------------|---------|------------------------------------------------------------------------|
| `enableFallingBuffer` | `true`  | Protects players from fall damage on first landing after using grapnel |

### Permission Levels

- **Single Player**: All players can use the command.
- **Multiplayer**: Requires OP level 2 or higher to modify settings.

---

## Compatibility

### Supported Mods

- Compatible with most mods that don't heavily modify fishing rods or enchantment mechanics.

### Known Issues

- May conflict with mods that completely overhaul fishing mechanics.
- Incompatible with mods that modify the same enchantment registry keys.

---

## For Modpack Creators

### Integration Guidelines

- The mod uses standard NeoForge registration systems.
- Custom loot table integration is supported for both enchantments.

### Recommended Modpack Settings

- Include in adventure or parkour-themed modpacks.
- Pairs well with mods that add new dimensions or challenging terrain.
- Consider disabling the falling buffer for hardcore packs.

---

## Technical Details

### Mod Information

- **Mod ID**: `grapnel`
- **Source Code**: [GitHub Repository](https://github.com/Skrepy0/Grapnel-Neoforge)
- **License**: Apache License 2.0

### Dependencies

- **Required**: NeoForge 21.1.216
- **Optional**: None

---

## Support and Issues

### Reporting Bugs

If you encounter any issues:

1. Check the [GitHub Issues page](https://github.com/Skrepy0/Grapnel-Neoforge/issues)
2. Include your Minecraft version, NeoForge version, and mod version.
3. Describe what happened and steps to reproduce.

### Feature Requests

Have ideas for improving the mod? Submit feature requests on the GitHub repository!

---

## Credits

- **Mod Author**: [Skrepy2233](https://github.com/Skrepy0)
- **Special Thanks**:
    - [Mafuyu33](https://github.com/Mafuyu33)
    - [Mahiru](https://github.com/Mahirukksk)

