# Grapnel Enchantment Mod for Minecraft 1.21.1

## Overview
The Grapnel Mod adds a thrilling new enchantment to fishing rods, transforming them into a grappling hook-like tool that allows players to swing through the air like the vertical maneuvering equipment from popular anime. This mod enhances mobility and adds a fun new way to traverse your Minecraft world.

## Features

### Enchantment: Grapnel
- **Enchantment ID**: `grapnel:grapnel`
- **Max Level**: 1 (single-level enchantment)
- **Rarity**: Treasure
- **Applicable Items**: Fishing rods

### Core Gameplay Mechanics
1. **Grappling Hook Functionality**: When a fishing rod with the Grapnel enchantment is cast and then retrieved (right-click), the user is pulled towards the fishing bobber.
2. **Vertical Mobility**: Allows players to swing through the air, providing Spider-Man-like movement capabilities.
3. **Fall Damage Protection**: By default, users are protected from fall damage when landing after using the grapnel enchantment (first landing only).

### Command: `/grapnel_settings`
Customize your grapnel experience with configurable settings:
```mcfunction
/grapnel_settings enableFallingBuffer <true/false>
```
- **Default**: `true` (enabled)
- **When enabled**: Players won't take fall damage on their first landing after using the grapnel
- **When disabled**: No fall damage protection - players will take normal fall damage

## Installation

### Requirements
- **Minecraft Version**: 1.21.1
- **NeoForge Version**: 21.1.216 or compatible
- **Java Version**: java21

### Installation Steps
1. Download and install NeoForge 21.1.216 for Minecraft 1.21.1
2. Download the latest version of the Grapnel mod
3. Place the mod JAR file in your Minecraft `mods` folder
4. Launch Minecraft with the NeoForge profile

## How to Obtain the Grapnel Enchantment

### In-Game Methods
1. **Loot Chests**: Find enchanted fishing rods with the Grapnel enchantment in:
    - Dungeon chests
    - Mineshaft chests
    - Stronghold libraries
    - Shipwreck treasure chests
    - End city chests

2. **Villager Trading**: Some Librarian villagers might offer fishing rods with this enchantment (if they have appropriate trades)

### Data Pack Integration
The mod is fully compatible with data packs. You can add the Grapnel enchantment to loot tables or trading recipes using the enchantment ID: `grapnel:grapnel`

## Usage Instructions

### Basic Usage
1. **Enchant a Fishing Rod**: Obtain a fishing rod with the Grapnel enchantment
2. **Cast the Line**: Right-click to cast the fishing line as normal
3. **Retrieve to Swing**: Right-click again to retrieve the line - instead of just pulling in the line, you'll be pulled towards the bobber!

### Advanced Techniques
- **Momentum Control**: Time your swings to chain movements together
- **Directional Control**: Look in the direction you want to swing while retrieving
- **Precision Landing**: Aim for specific blocks to land exactly where you want

### Safety Tips
- The falling buffer is enabled by default, but can be disabled if you want more challenge
- Practice in safe environments before attempting risky maneuvers
- Be aware of your surroundings - you might swing into unexpected places!

## Configuration

### Command Details
The mod includes one configurable setting accessible via the `/grapnel_settings` command:

| Setting | Default | Description |
|---------|---------|-------------|
| `enableFallingBuffer` | `true` | Protects players from fall damage on first landing after using grapnel |

### Permission Levels
- **Single Player**: All players can use the command
- **Multiplayer**: Requires OP level 2 or higher to modify settings

## Compatibility

### Supported Mods
- Compatible with most mods that don't heavily modify fishing rods or enchantment mechanics

### Known Issues
- May conflict with mods that completely overhaul fishing mechanics
- Incompatible with mods that modify the same enchantment registry keys

## For Modpack Creators

### Integration Guidelines
1. The mod uses standard NeoForge registration systems
2. Custom loot table integration is supported

### Recommended Modpack Settings
- Include in adventure or parkour-themed modpacks
- Pairs well with mods that add new dimensions or challenging terrain
- Consider disabling the falling buffer for hardcore packs

## Technical Details

### Mod Information
- **Mod ID**: `grapnel`
- **Source Code**: Available on GitHub (if applicable)
- **License**: MIT

### Dependencies
- **Required**: NeoForge 21.1.216
- **Optional**: None

## Support and Issues

### Reporting Bugs
If you encounter any issues:
1. Check the [GitHub Issues page](https://github.com/Skrepy0/Grapnel-Neoforge/issues)
2. Include your Minecraft version, NeoForge version, and mod version
3. Describe what happened and steps to reproduce

### Feature Requests
Have ideas for improving the mod? Submit feature requests on the GitHub repository!

## Credits

- **Mod Author**: [Skrepy2233](https://github.com/Skrepy0)
- **Special Thanks**:
    - [Mafuyu33](https://github.com/Mafuyu33)
    - [Mahiru](https://github.com/Mahirukksk)
