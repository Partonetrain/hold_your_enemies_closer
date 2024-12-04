## Hold Your Enemies Closer
This mod adds several "opposite" enchantments.
The goal is to add a bit more variation to enchanting, and to experiment with the 1.21+ enchantment rework. 
As with vanilla enchantments, all enchantments from HYEC can be customized via datapack.

-- freeze enchant for bows, ice protection prevents freezing and lets you walk on powder snow, 

The mod contains the following enchantments:

| Enchantment      | Obtainable by                         | Description                                                                                                                                                                                                     | Max Level | Mutually exclusive with |
|------------------|---------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------|-------------------------|
| Attraction       | Enchanting table                      | Goes on swords. Applies reverse knockback to hit entities, pulling them towards the attacker. The mod is named after this one.                                                                                  | 2         | Knockback               |
| Auto-Smelt       | Treasure (Bastion Treasure)           | Goes on tools. Automatically smelts block drops.                                                                                                                                                                | 2         | Knockback               |
| Curse of Frailty | Treasure, trading                     | Goes on any item with durability. Increases durability used by 1 each level.                                                                                                                                    | 3         | Unbreaking              |
| Pull             | Enchanting table                      | Goes on bows. Applies reverse knockback to entities hit by the arrow, pulling them in the direction opposite of the arrow's velocity. Should work with crossbows if a datapack is used to make them compatible. | 2         | Punch                   |
| Ice Aspect       | Enchanting table                      | Goes on swords. Adds freezing ticks (just like Powdered Snow) to hit entities. Mobs like Blazes take more damage from freezing (this is a vanilla feature).                                                     | 2         | Fire Aspect             |
| Swift Swim       | Treasure (Shipwreck, Buried Treasure) | Goes on leggings. Increases the speed at which you move in liquids. Uses a custom attribute on Fabric, `neoforge:swim_speed` on Neoforge.                                                                       | 3         | Swift Sneak             |

# Notes
- By necessity, this mod makes it possible for living entities to receive "negative knockback". As far as I know there aren't any situations in the vanilla (or modded for that matter) game where this happens outside my enchantments, but there *are* checks in the vanilla code to ensure positive values. This means there could be unintended side effects, but it's unlikely.
- I intentionally reused the `minecraft:smelts_loot` enchantment tag for the Autosmelt logic, making Fire Aspect also able to smelt block loot.
There is a forge project in the repo, but it is currently unused and does nothing, I left it in case I want to make a Forge release