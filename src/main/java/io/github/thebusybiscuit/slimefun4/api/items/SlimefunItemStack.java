package io.github.thebusybiscuit.slimefun4.api.items;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import io.papermc.paper.inventory.ItemRarity;
import io.papermc.paper.inventory.tooltip.TooltipContext;
import io.papermc.paper.registry.set.RegistryKeySet;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import org.apache.commons.lang.Validate;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.thebusybiscuit.slimefun4.libraries.bridge.SF4Patterns;
import io.github.bakedlibs.dough.items.ItemMetaSnapshot;
import io.github.thebusybiscuit.slimefun4.api.MinecraftVersion;
import io.github.thebusybiscuit.slimefun4.api.exceptions.PrematureCodeException;
import io.github.thebusybiscuit.slimefun4.api.exceptions.WrongItemStackException;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.utils.HeadTexture;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import io.github.thebusybiscuit.slimefun4.utils.compatibility.VersionedItemFlag;

/**
 * The {@link SlimefunItemStack} functions as the base for any
 * {@link SlimefunItem}.
 * 
 * @author TheBusyBiscuit
 * @author Walshy
 *
 */
public class SlimefunItemStack extends ItemStack {

    private String id;
    private ItemMetaSnapshot itemMetaSnapshot;
    private boolean locked = false;

    private String texture = null;

    public SlimefunItemStack(@Nonnull String id, @Nonnull ItemStack item) {
        super(item);

        Validate.notNull(id, "The Item id must never be null!");
        Validate.isTrue(id.equals(id.toUpperCase(Locale.ROOT)), "Slimefun Item Ids must be uppercase! (e.g. 'MY_ITEM_ID')");

        if (Slimefun.instance() == null) {
            throw new PrematureCodeException("A SlimefunItemStack must never be be created before your Plugin was enabled.");
        }

        this.id = id;

        ItemMeta meta = super.getItemMeta();

        Slimefun.getItemDataService().setItemData(meta, id);
        Slimefun.getItemTextureService().setTexture(meta, id);

        setItemMeta(meta);
    }

    public SlimefunItemStack(@Nonnull String id, @Nonnull ItemStack item, @Nonnull Consumer<ItemMeta> consumer) {
        this(id, item);

        ItemMeta im = super.getItemMeta();
        consumer.accept(im);
        setItemMeta(im);
    }

    public SlimefunItemStack(@Nonnull String id, @Nonnull Material type, @Nonnull Consumer<ItemMeta> consumer) {
        this(id, new ItemStack(type), consumer);
    }

    public SlimefunItemStack(@Nonnull String id, @Nonnull Material type, @Nullable String name, @Nonnull Consumer<ItemMeta> consumer) {
        this(id, type, meta -> {
            if (name != null) {
                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
            }

            consumer.accept(meta);
        });
    }

    public SlimefunItemStack(@Nonnull String id, @Nonnull ItemStack item, @Nullable String name, String... lore) {
        this(id, item, im -> {
            if (name != null) {
                im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
            }

            if (lore.length > 0) {
                List<String> lines = new ArrayList<>();

                for (String line : lore) {
                    lines.add(ChatColor.translateAlternateColorCodes('&', line));
                }
                im.setLore(lines);
            }
        });
    }

    public SlimefunItemStack(@Nonnull String id, @Nonnull Material type, @Nullable String name, String... lore) {
        this(id, new ItemStack(type), name, lore);
    }

    public SlimefunItemStack(@Nonnull String id, @Nonnull Material type, @Nonnull Color color, @Nullable String name, String... lore) {
        this(id, type, im -> {
            if (name != null) {
                im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
            }

            if (lore.length > 0) {
                List<String> lines = new ArrayList<>();

                for (String line : lore) {
                    lines.add(ChatColor.translateAlternateColorCodes('&', line));
                }

                im.setLore(lines);
            }

            if (im instanceof LeatherArmorMeta leatherArmorMeta) {
                leatherArmorMeta.setColor(color);
            }

            if (im instanceof PotionMeta potionMeta) {
                potionMeta.setColor(color);
            }
        });
    }

    public SlimefunItemStack(@Nonnull String id, @Nonnull Color color, @Nonnull PotionEffect effect, @Nullable String name, String... lore) {
        this(id, Material.POTION, im -> {
            if (name != null) {
                im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
            }

            if (lore.length > 0) {
                List<String> lines = new ArrayList<>();

                for (String line : lore) {
                    lines.add(ChatColor.translateAlternateColorCodes('&', line));
                }

                im.setLore(lines);
            }

            if (im instanceof PotionMeta potionMeta) {
                potionMeta.setColor(color);
                potionMeta.addCustomEffect(effect, true);

                if (effect.getType().equals(PotionEffectType.SATURATION)) {
                    im.addItemFlags(VersionedItemFlag.HIDE_ADDITIONAL_TOOLTIP);
                }
            }
        });
    }

    public SlimefunItemStack(@Nonnull SlimefunItemStack item, int amount) {
        this(item.getItemId(), item.item());
        setAmount(amount);
    }

    public SlimefunItemStack(@Nonnull String id, @Nonnull String texture, @Nullable String name, String... lore) {
        this(id, getSkull(id, texture), name, lore);
        this.texture = getTexture(id, texture);
    }

    public SlimefunItemStack(@Nonnull String id, @Nonnull HeadTexture head, @Nullable String name, String... lore) {
        this(id, head.getTexture(), name, lore);
    }

    public SlimefunItemStack(@Nonnull String id, @Nonnull String texture, @Nullable String name, @Nonnull Consumer<ItemMeta> consumer) {
        this(id, getSkull(id, texture), meta -> {
            if (name != null) {
                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
            }

            consumer.accept(meta);
        });

        this.texture = getTexture(id, texture);
    }

    public SlimefunItemStack(@Nonnull String id, @Nonnull String texture, @Nonnull Consumer<ItemMeta> consumer) {
        this(id, getSkull(id, texture), consumer);
        this.texture = getTexture(id, texture);
    }

    /**
     * Returns the id that was given to this {@link SlimefunItemStack}.
     * 
     * @return The {@link SlimefunItem} id for this {@link SlimefunItemStack}
     */
    public final @Nonnull String getItemId() {
        return id;
    }

    /**
     * Gets the {@link SlimefunItem} associated for this {@link SlimefunItemStack}.
     * Null if no item is found.
     *
     * @return The {@link SlimefunItem} for this {@link SlimefunItemStack}, null if not found.
     */
    public @Nullable SlimefunItem getItem() {
        return SlimefunItem.getById(id);
    }

    /**
     * This method returns the associated {@link SlimefunItem} and casts it to the provided
     * {@link Class}.
     * 
     * If no item was found or the found {@link SlimefunItem} is not of the requested type,
     * the method will return null.
     * 
     * @param <T>
     *            The type of {@link SlimefunItem} to cast this to
     * @param type
     *            The {@link Class} of the target {@link SlimefunItem}
     * 
     * @return The {@link SlimefunItem} this {@link SlimefunItem} represents, casted to the given type
     */

    public @Nullable <T extends SlimefunItem> T getItem(@Nonnull Class<T> type) {
        SlimefunItem item = getItem();
        return type.isInstance(item) ? type.cast(item) : null;
    }

    public @Nonnull ItemMetaSnapshot getItemMetaSnapshot() {
        return itemMetaSnapshot;
    }

    @Override
    public boolean setItemMeta(ItemMeta meta) {
        validate();
        itemMetaSnapshot = new ItemMetaSnapshot(meta);

        return super.setItemMeta(meta);
    }

    @Override
    public void setType(Material type) {
        validate();
        super.setType(type);
    }

    @Override
    public void setAmount(int amount) {
        validate();
        super.setAmount(amount);
    }

    /**
     * Prevents mutation of this item stack's type, amount and metadata.
     * This is used by Slimefun when an item's original template is registered.
     */
    public void lock() {
        locked = true;
    }

    private void validate() {
        if (locked) {
            throw new WrongItemStackException(id + " is not mutable.");
        }
    }

    public @Nonnull Optional<String> getSkullTexture() {
        return Optional.ofNullable(texture);
    }

    public @Nullable String getDisplayName() {
        if (itemMetaSnapshot == null) {
            // Just to be extra safe
            return null;
        }

        return itemMetaSnapshot.getDisplayName().orElse(null);
    }

    private static @Nonnull ItemStack getSkull(@Nonnull String id, @Nonnull String texture) {
        if (Slimefun.getMinecraftVersion() == MinecraftVersion.UNIT_TEST) {
            return new ItemStack(Material.PLAYER_HEAD);
        }

        return SlimefunUtils.getCustomHead(getTexture(id, texture));
    }

    private static @Nonnull String getTexture(@Nonnull String id, @Nonnull String texture) {
        Validate.notNull(id, "The id cannot be null");
        Validate.notNull(texture, "The texture cannot be null");

        if (texture.startsWith("ey")) {
            return texture;
        } else if (SF4Patterns.HEXADECIMAL.matcher(texture).matches()) {
            String value = "{\"textures\":{\"SKIN\":{\"url\":\"http://textures.minecraft.net/texture/" + texture + "\"}}}";
            return Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8));
        } else {
            throw new IllegalArgumentException("The provided texture for Item \"" + id + "\" does not seem to be a valid texture String!");
        }
    }

    @Override
    public @Nonnull SlimefunItemStack clone() {
        return new SlimefunItemStack(id, super.clone());
    }

    @Override
    public String toString() {
        return "SlimefunItemStack (" + id + (super.getAmount() > 1 ? (" x " + super.getAmount()) : "") + ')';
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean equals(Object obj) {
        // We don't want people to override this, it should use the super method
        return super.equals(obj);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int hashCode() {
        // We don't want people to override this, it should use the super method
        return super.hashCode();
    }

    /**
     * @return underlying ItemStack used
     */
    public @Nonnull ItemStack item() {
        return super.clone();
    }

    public Material getType() {
        return super.getType();
    }

    public ItemStack withType(Material type) {
        return super.withType(type);
    }

    public int getAmount() {
        return super.getAmount();
    }

    /** @deprecated */
    @Deprecated
    public MaterialData getData() {
        return super.getData();
    }

    /** @deprecated */
    @Deprecated
    public void setData(MaterialData data) {
        super.setData(data);
    }

    /** @deprecated */
    @Deprecated
    public void setDurability(short durability) {
        super.setDurability(durability);
    }

    /** @deprecated */
    @Deprecated
    public short getDurability() {
        return super.getDurability();
    }

    public int getMaxStackSize() {
        return super.getMaxStackSize();
    }

    public boolean isSimilar(ItemStack stack) {
        return super.isSimilar(stack);
    }

    public boolean containsEnchantment(Enchantment ench) {
        return super.containsEnchantment(ench);
    }

    public int getEnchantmentLevel(Enchantment ench) {
        return super.getEnchantmentLevel(ench);
    }

    public Map<Enchantment, Integer> getEnchantments() {
        return super.getEnchantments();
    }

    public void addEnchantments(Map<Enchantment, Integer> enchantments) {
        super.addEnchantments(enchantments);
    }

    public void addEnchantment(Enchantment ench, int level) {
        super.addEnchantment(ench, level);
    }

    public void addUnsafeEnchantments(Map<Enchantment, Integer> enchantments) {
        super.addUnsafeEnchantments(enchantments);
    }

    public void addUnsafeEnchantment(Enchantment ench, int level) {
        super.addUnsafeEnchantment(ench, level);
    }

    public int removeEnchantment(Enchantment ench) {
        return super.removeEnchantment(ench);
    }

    public void removeEnchantments() {
        super.removeEnchantments();
    }

    public Map<String, Object> serialize() {
        return super.serialize();
    }

    public boolean editMeta(Consumer<? super ItemMeta> consumer) {
        return super.editMeta(consumer);
    }

    public <M extends ItemMeta> boolean editMeta(Class<M> metaClass, Consumer<? super M> consumer) {
        return super.editMeta(metaClass, consumer);
    }

    public ItemMeta getItemMeta() {
        return super.getItemMeta();
    }

    public boolean hasItemMeta() {
        return super.hasItemMeta();
    }

    /** @deprecated */
    @Deprecated
    public String getTranslationKey() {
        return super.getTranslationKey();
    }

    public ItemStack enchantWithLevels(int levels, boolean allowTreasure, Random random) {
        return super.enchantWithLevels(levels, allowTreasure, random);
    }

    public ItemStack enchantWithLevels(int levels, RegistryKeySet<Enchantment> keySet, Random random) {
        return super.enchantWithLevels(levels, keySet, random);
    }

    public HoverEvent<HoverEvent.ShowItem> asHoverEvent(UnaryOperator<HoverEvent.ShowItem> op) {
        return super.asHoverEvent(op);
    }

    public Component displayName() {
        return super.displayName();
    }

    public ItemStack ensureServerConversions() {
        return super.ensureServerConversions();
    }

    public byte[] serializeAsBytes() {
        return super.serializeAsBytes();
    }

    /** @deprecated */
    public String getI18NDisplayName() {
        return super.getI18NDisplayName();
    }

    /** @deprecated */
    public int getMaxItemUseDuration() {
        return super.getMaxItemUseDuration();
    }

    public int getMaxItemUseDuration(LivingEntity entity) {
        return super.getMaxItemUseDuration(entity);
    }

    public ItemStack asOne() {
        return super.asOne();
    }

    public ItemStack asQuantity(int qty) {
        return super.asQuantity(qty);
    }

    public ItemStack add() {
        return super.add();
    }

    public ItemStack add(int qty) {
        return super.add(qty);
    }

    public ItemStack subtract() {
        return super.subtract();
    }

    public ItemStack subtract(int qty) {
        return super.subtract(qty);
    }

    @Deprecated
    public List<String> getLore() {
        return super.getLore();
    }

    public List<Component> lore() {
        return super.lore();
    }

    @Deprecated
    public void setLore(List<String> lore) {
        super.setLore(lore);
    }

    public void lore(List<? extends Component> lore) {
        super.lore(lore);
    }

    public void addItemFlags(ItemFlag... itemFlags) {
        super.addItemFlags(itemFlags);
    }

    public void removeItemFlags(ItemFlag... itemFlags) {
        super.removeItemFlags(itemFlags);
    }

    public Set<ItemFlag> getItemFlags() {
        return super.getItemFlags();
    }

    public boolean hasItemFlag(ItemFlag flag) {
        return super.hasItemFlag(flag);
    }

    public String translationKey() {
        return super.translationKey();
    }

    /** @deprecated */
    @Deprecated
    public ItemRarity getRarity() {
        return super.getRarity();
    }

    public boolean isRepairableBy(ItemStack repairMaterial) {
        return super.isRepairableBy(repairMaterial);
    }

    public boolean canRepair(ItemStack toBeRepaired) {
        return super.canRepair(toBeRepaired);
    }

    public ItemStack damage(int amount, LivingEntity livingEntity) {
        return super.damage(amount, livingEntity);
    }

    public boolean isEmpty() {
        return super.isEmpty();
    }

    public List<Component> computeTooltipLines(TooltipContext tooltipContext, Player player) {
        return super.computeTooltipLines(tooltipContext, player);
    }

    public HoverEvent<HoverEvent.ShowItem> asHoverEvent() {
        return super.asHoverEvent();
    }
}
