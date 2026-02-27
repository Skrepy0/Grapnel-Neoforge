package com.grapnel.enchantments;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ModEnchantHelper {
    /**
     * 获取物品上指定附魔的等级。
     *
     * @param stack 物品堆
     * @param level 世界对象（用于访问注册表，服务端和客户端皆可）
     * @param enchantmentKey 附魔的 ResourceKey
     * @return 附魔等级，如果没有则为 0
     */
    public static int getEnchantmentLevel(ItemStack stack, Level level, ResourceKey<Enchantment> enchantmentKey) {
        // 1. 安全检查：如果物品为空或世界为空，直接返回0
        if (stack.isEmpty() || level == null) {
            return 0;
        }

        // 2. 获取物品的附魔组件
        ItemEnchantments enchantments = stack.get(DataComponents.ENCHANTMENTS);
        if (enchantments == null) {
            return 0;
        }

        // 3. 通过世界访问注册表，查找附魔的 Holder
        Holder<Enchantment> enchantmentHolder = getEnchantmentHolder(level, enchantmentKey);

        // 4. 如果找到 Holder，返回对应的等级
        if (enchantmentHolder != null) {
            return enchantments.getLevel(enchantmentHolder);
        }

        return 0;
    }

    /**
     * 获取附魔的 Holder 对象。
     * 注意：此方法依赖 Level 对象，不要在客户端专用代码中混用 Minecraft.getInstance()。
     *
     * @param level 世界对象
     * @param enchantmentKey 附魔的 ResourceKey
     * @return Holder 对象，如果未找到则为 null
     */
    public static Holder<@NotNull Enchantment> getEnchantmentHolder(Level level, ResourceKey<@NotNull Enchantment> enchantmentKey) {
        if (level == null) {
            return null;
        }
        return level.registryAccess()
                .lookupOrThrow(Registries.ENCHANTMENT)
                .get(enchantmentKey)
                .orElse(null);
    }
}
