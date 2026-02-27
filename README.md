# Grapnel

[![NeoForge](https://img.shields.io/badge/NeoForge-21.1.216+-orange?style=flat-square)](https://neoforged.net/)
[![Minecraft](https://img.shields.io/badge/Minecraft-1.21.1-brightgreen?style=flat-square)](https://minecraft.net)

[![NeoForge](https://img.shields.io/badge/NeoForge-21.11.38.beta+-orange?style=flat-square)](https://neoforged.net/)
[![Minecraft](https://img.shields.io/badge/Minecraft-1.21.11-brightgreen?style=flat-square)](https://minecraft.net)

[![NeoForge](https://img.shields.io/badge/NeoForge-21.2.1.beta+-orange?style=flat-square)](https://neoforged.net/)
[![Minecraft](https://img.shields.io/badge/Minecraft-1.21.2-brightgreen?style=flat-square)](https://minecraft.net)

[![NeoForge](https://img.shields.io/badge/NeoForge-21.0.166+-orange?style=flat-square)](https://neoforged.net/)
[![Minecraft](https://img.shields.io/badge/Minecraft-1.21-brightgreen?style=flat-square)](https://minecraft.net)

将钓鱼竿变成一把多功能抓钩工具！本模组新增三种附魔，彻底改变钓鱼竿的玩法——你可以用它来跨越地形、攀爬高处，甚至像蜘蛛侠一样摆荡。配合丰富的配置选项，让抓钩的使用更加贴合你的游戏风格。

---

## 主要特性

### 🎣 抓钩附魔
- **效果**：当鱼钩抛出的浮漂勾住方块、实体或墙面（需开启对应配置）后，再次右键收竿，玩家会被瞬间拉向浮漂的位置。
- **拉拽机制**：拉拽力度受附魔等级、浮漂与玩家的距离影响，等级越高拉得越快，距离越远拉力越强。
- **摔落缓冲**：使用抓钩后的第一次落地不会受到摔落伤害（可配置开关）。
- **耐久消耗**：每次拉拽会消耗钓鱼竿的耐久（非创造模式下），消耗概率与附魔了“耐久”的等级有关，附魔等级越高越省耐久。

### 💪 坚韧附魔
- **效果**：强化鱼线强度，显著增加抛投的有效距离。附魔等级越高，鱼线可延伸的长度越长，鱼竿收回的判定距离也相应增加。
- **实际表现**：相当于变相增加了浮漂的最大飞行距离，让你能勾到更远的方块或实体。
- **与抓钩搭配**：坚韧等级越高，抓钩的有效范围就越大，组合使用效果更佳。

### 🧗 摆荡附魔（可配置）
- **效果**：当鱼钩抓墙后，鱼线会对玩家产生持续的拉力，让你可以像荡秋千一样围绕锚点摆动。
- **物理模拟**：系统会实时计算切向速度与重力势能，自动补偿空气阻力，实现接近真实的钟摆运动。
- **碰撞处理**：撞墙时会反向弹开并损失部分动能，避免卡墙，让摆动更自然。

### 🧱 抓墙功能（可配置）
- **开启后**：浮漂击中方块时会像勾住实体一样卡在方块表面，不会掉落。
- **应用场景**：你可以瞄准墙壁，让浮漂稳稳停住，随后收竿将自己拉过去，或配合摆荡附魔开始摆动。
- **视觉效果**：浮漂会固定在击中点的坐标，并在墙上静止，直到你收竿或方块被破坏。

### ⚙️ 自定义配置
所有核心机制都可通过配置文件或游戏内命令调整，满足不同玩家的需求。新增的摆荡附魔也提供了细致的物理参数：

| 配置项 | 默认值 | 作用 |
|--------|--------|------|
| `grabWall` | `true` | 是否允许鱼钩勾住方块表面。关闭后，鱼钩只会勾住实体。 |
| `grapnelCheck` | `false` | 是否只在鱼钩确实勾住方块或实体时才触发抓钩效果。关闭时，只要收回鱼竿（即使浮漂在空中）也会拉拽玩家，可用来快速移动。 |
| `fallingBuffer` | `false` | 是否启用摔落缓冲。开启后，使用抓钩后的第一次落地不会扣血。 |
| `positionClampThreshold` | `0.2` | 位置修正阈值（摆荡附魔）。当玩家超出绳长多少格后强制拉回。数值越小绳子越“硬”，越大越“松”。 |
| `energyCompensationFactor` | `1.0` | 能量补偿系数（摆荡附魔）。补偿空气阻力造成的速度损失。设为1.0时无阻力。 |
| `lockProtectionTicks` | `5` | 锁保护时间（摆荡附魔）。锁定后不进行物理计算的缓冲时间，防止刚飞出时被瞬间弹回或卡住。 |

### 📦 钓鱼竿耐久度提升
原版钓鱼竿的耐久仅为 64，对于频繁使用的抓钩来说完全不够。本模组将钓鱼竿的基础耐久度提升至 **384**（和基岩版保持一致）。

### 🎮 游戏内命令
管理员或拥有 OP 权限的玩家可使用 `/grapnel_settings` 命令实时调整配置（服务端）：

```
/grapnel_settings enableFallingBuffer [true/false]   # 设置摔落缓冲开关
/grapnel_settings grapnelCheck [true/false]          # 设置抓钩检测开关
/grapnel_settings grabWall [true/false]              # 设置钓竿抓墙开关
```

不带参数时，命令会显示当前配置值。摆荡附魔的物理参数目前需通过模组配置修改。

---

## 附魔详情

| 附魔名称 | 最大等级 | 获取方式 | 效果简述 |
|----------|----------|----------|----------|
| 抓钩 (Grapnel) | I | 附魔台、铁砧、战利品 | 收竿时将玩家拉向浮漂 |
| 坚韧 (Toughness) | II | 附魔台、铁砧、战利品 | 增加鱼线有效长度，提升抓钩范围 |
| 摆荡 (Swing) | I | 附魔台、铁砧、战利品 | 抓墙后鱼线产生持续拉力，让玩家围绕锚点摆动 |

- **兼容性**：三种附魔可共存于同一根钓鱼竿上，且无冲突附魔。
- **附魔权重**：抓钩、坚韧和摆荡的稀有度适中，在附魔台中出现的概率与其他常见附魔相当。

---

## 兼容性与依赖

- **加载器**：NeoForge
- **游戏版本**：Minecraft 1.21.1 / 1.21.11 / 1.21.2 / 1.21
- **依赖**：无强制依赖，直接放入 `mods` 文件夹即可使用。
- **多人游戏**：服务端安装后，所有配置由服务端控制，客户端也需要安装才能进入。

---

# Overview

Turn your fishing rod into a multi-functional grappling hook tool! This mod adds three new enchantments that completely change how you use the fishing rod—cross terrains, climb heights, or even swing like Spider-Man. With rich configurable options, tailor the grappling hook experience to your playstyle.

---

## Key Features

### 🎣 Grapnel Enchantment
- **Effect**: After the bobber hooks onto a block, entity, or wall (if the corresponding config is enabled), right‑clicking again reels in and instantly pulls the player toward the bobber.
- **Pull Mechanics**: The pulling force depends on the enchantment level and the distance between the bobber and the player. Higher levels pull faster, and greater distances increase the force.
- **Fall Buffer**: The first landing after using the Grapnel does not cause fall damage (configurable).
- **Durability Consumption**: Each pull consumes durability of the fishing rod (except in Creative mode). The chance of consumption is influenced by the Unbreaking enchantment level—higher levels save more durability.

### 💪 Toughness Enchantment
- **Effect**: Strengthens the fishing line, significantly increasing the effective casting distance. The higher the enchantment level, the farther the line can extend and the greater the distance at which the rod can be retrieved.
- **In Practice**: Effectively increases the maximum flight distance of the bobber, allowing you to hook blocks or entities farther away.
- **Synergy with Grapnel**: Higher Toughness levels expand the Grapnel's effective range—using them together yields the best results.

### 🧗 Swing Enchantment (Configurable)
- **Effect**: After the hook grabs onto a wall, the fishing line exerts a continuous pulling force on the player, allowing you to swing around the anchor point like a pendulum.
- **Physics Simulation**: The system calculates tangential velocity and gravitational potential energy in real time, automatically compensating for air resistance to achieve realistic pendulum motion.
- **Collision Handling**: When hitting a wall, the player bounces back with reduced kinetic energy, preventing getting stuck and making swinging feel natural.

### 🧱 Grab Wall (Configurable)
- **When Enabled**: The bobber will stick to a block surface when it hits one, just like hooking an entity, without falling off.
- **Usage Scenarios**: Aim at walls to have the bobber firmly attach, then reel in to pull yourself over, or combine with the Swing enchantment to start swinging.
- **Visual Effect**: The bobber stays fixed at the impact point, motionless on the wall until you reel in or the block is destroyed.

### ⚙️ Customizable Configuration
All core mechanics can be adjusted via the config file or in‑game commands to suit different playstyles. The new Swing enchantment also provides detailed physics parameters:

| Config Option | Default | Description |
|---|---|---|
| `grabWall` | `true` | Whether the fishing hook can stick to block surfaces. If disabled, the hook will only attach to entities. |
| `grapnelCheck` | `false` | Whether the Grapnel effect triggers only when the hook is actually stuck in a block or entity. When disabled, reeling in (even with the bobber in mid‑air) will pull the player, allowing for quick movement. |
| `fallingBuffer` | `false` | Whether to enable the fall buffer. If enabled, the first landing after using a Grapnel rod does not cause fall damage. |
| `positionClampThreshold` | `0.2` | Position clamp threshold (Swing enchantment). How far beyond the rope length the player can go before being forcibly pulled back. Smaller values make the rope "stiffer", larger values make it "looser". |
| `energyCompensationFactor` | `1.0` | Energy compensation factor (Swing enchantment). Compensates for speed loss due to air resistance. At 1.0, there is no resistance. |
| `lockProtectionTicks` | `5` | Lock protection ticks (Swing enchantment). Buffer time after locking during which no physics calculations are performed, preventing the player from being instantly bounced back or stuck right after launch. |

### 📦 Fishing Rod Durability Buff
The vanilla fishing rod has only 64 durability, which is far too low for frequent Grapnel use. This mod increases the base durability of fishing rods to **384** (matching Bedrock Edition).

### 🎮 In‑Game Commands
Admins or players with OP privileges can use the `/grapnel_settings` command to adjust configurations in real time (server side):

```
/grapnel_settings enableFallingBuffer [true/false]   # Toggle fall buffer
/grapnel_settings grapnelCheck [true/false]          # Toggle grapnel detection
/grapnel_settings grabWall [true/false]              # Toggle grab wall
```

Running the command without arguments displays the current values. The physics parameters for the Swing enchantment currently need to be modified through the mod's configuration file.

---

## Enchantment Details

| Enchantment Name | Max Level | How to Obtain          | Effect Summary                                      |
|---|---|---|---|
| Grapnel          | I         | Enchanting Table, Anvil, Loot | Reeling in pulls the player toward the bobber      |
| Toughness        | II        | Enchanting Table, Anvil, Loot | Increases effective fishing line length, boosting Grapnel range |
| Swing            | I         | Enchanting Table, Anvil, Loot | After grabbing a wall, the line exerts continuous pull, letting the player swing around the anchor |

- **Compatibility**: All three enchantments can coexist on the same fishing rod and have no conflicts with other enchantments.
- **Enchantment Weight**: Grapnel, Toughness, and Swing have a moderate rarity, appearing in the enchanting table with a frequency comparable to common enchantments.

---

## Compatibility & Dependencies

- **Loader**: NeoForge
- **Game Version**: Minecraft 1.21.1 / 1.21.11 / 1.21.2 / 1.21
- **Dependencies**: None—just drop the mod into the `mods` folder.
- **Multiplayer**: Once installed on the server, all configurations are controlled by the server. The client also needs the mod installed to join.

---

## Credits

- **Mod Author**: [Skrepy2233](https://github.com/Skrepy0)
- **Special Thanks**:
    - [Mafuyu33](https://github.com/Mafuyu33)
    - [Mahiru](https://github.com/Mahirukksk)