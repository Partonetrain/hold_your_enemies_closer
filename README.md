## Hold Your Enemies Closer
This mod adds several "opposite" enchantments that are mutually exclusive with vanilla enchantments.
The goal is to add a bit more variation to enchanting, and to experiment with the 1.21+ enchantment rework. 
As with vanilla enchantments, all enchantments from HYEC can be customized via datapack.

An optional datapack that removes the restrictions on enchantment compatibility can be found here.

The mod contains the following enchantments:

| Enchantment      | Obtainable by                         | Description                                                                                                                                                                                                     | Max Level | Mutually exclusive with |
|------------------|---------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------|-------------------------|
| Attraction       | Enchanting table, loot, trading       | Goes on swords. Applies reverse knockback to hit entities, pulling them towards the attacker. The mod is named after this one.                                                                                  | 2         | Knockback               |
| Auto-Smelt       | Treasure (Bastion Treasure)           | Goes on tools. Automatically smelts block drops.                                                                                                                                                                | 2         | Fortune, Silk Touch     |
| Curse of Frailty | Treasure, trading                     | Goes on any item with durability. Increases durability used by 1 each level.                                                                                                                                    | 3         | Unbreaking              |
| Frost            | Enchanting table, loot, trading       | Goes on bows. Adds freezing ticks (just like Powder Snow) to shot entities. Stacks, unlike Flame. Mobs like Blazes take more damage from freezing (this is a vanilla feature).                                  | 1         | Flame                   |
| Frost Protection | Enchanting table, loot, trading       | Goes on armor. Reduces freezing damage and freezing time. Having less than or equal to 50% freezing time allows you to walk on Powder Snow.                                                                     | 1         | Frost                   |
| Pull             | Enchanting table, loot, trading       | Goes on bows. Applies reverse knockback to entities hit by the arrow, pulling them in the direction opposite of the arrow's velocity. Should work with crossbows if a datapack is used to make them compatible. | 2         | Punch                   |
| Ice Aspect       | Enchanting table, loot, trading       | Goes on swords. Adds freezing ticks (just like Powder Snow) to hit entities. Stacks, unlike Fire Aspect. Mobs like Blazes take more damage from freezing (this is a vanilla feature).                           | 2         | Fire Aspect             |
| Swift Swim       | Treasure (Shipwreck, Buried Treasure) | Goes on leggings. Increases the speed at which you move in liquids. Uses a custom attribute on Fabric, `neoforge:swim_speed` on Neoforge.                                                                       | 3         | Swift Sneak             |

# Notes
- By necessity, this mod makes it possible for living entities to receive "negative knockback". As far as I know there aren't any situations in the vanilla (or modded for that matter) game where this happens outside my enchantments, but there *are* checks in the vanilla code to ensure positive values. This means there could be unintended side effects, but it's unlikely.
- The Auto-Smelt logic intentionally uses the vanilla `minecraft:smelts_loot` enchantment tag, making Fire Aspect also able to smelt block loot.
- Frost Walker also now allows entities to walk on Powder Snow.
- There is a legacy-forge project in the repo, but it is currently unused and does nothing, I left it in case I or others want to make a Forge release
