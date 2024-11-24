package me.norielsylvire.slimelearning;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;

public class SlimeLearning extends JavaPlugin implements SlimefunAddon {

    @Override
    public void onEnable() {
        // Read something from your config.yml
        Config cfg = new Config(this);

        if (cfg.getBoolean("options.auto-update")) {
            // You could start an Auto-Updater for example
        }

		ItemGroup learningCategory = createCategories();
		registerItems(learningCategory);
    }

    @Override
    public void onDisable() {
        // Logic for disabling the plugin...
    }

    @Override
    public String getBugTrackerURL() {
        // You can return a link to your Bug Tracker instead of null here
        return null;
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        /*
         * You will need to return a reference to your Plugin here.
         * If you are using your main class for this, simply return "this".
         */
        return this;
    }

	private ItemGroup createCategories() {
		/*
         * 1. Creating a new Category
         * This Category will use the following ItemStack
         */
        ItemStack learningCategoryItemStack = new CustomItemStack(Material.DIAMOND, "&4Learning Category", "", "&a> Click to open");

        // Give your Category a unique id.
        NamespacedKey learningGroupId = new NamespacedKey(this, "learning_category");
        return new ItemGroup(learningGroupId, learningCategoryItemStack);
	}

	private void registerItems(ItemGroup learningCategory) {
        /*
         * 2. Create a new SlimefunItemStack
         * This class has many constructors, it is very important
         * that you give each item a unique id.
         */
        SlimefunItemStack coolDiamondIS = new SlimefunItemStack("COOL_DIAMOND", Material.DIAMOND, "&6Cool Diamond", "&b+20% Coolness");

        /*
         * 3. Creating a Recipe
         * The Recipe is an ItemStack Array with a length of 9.
         * It represents a Shaped Recipe in a 3x3 crafting grid.
         * The machine in which this recipe is crafted in is specified
         * further down as the RecipeType.
         */
        ItemStack[] coolDiamondRecipe = {
			new ItemStack(Material.EMERALD), null, new ItemStack(Material.EMERALD),
			null, new ItemStack(Material.DIAMOND), null,
			new ItemStack(Material.EMERALD), null, new ItemStack(Material.EMERALD)
		};

        /*
         * 4. Registering the Item
         * Now you just have to register the item.
         * RecipeType.ENHANCED_CRAFTING_TABLE refers to the machine in
         * which this item is crafted in.
         * Recipe Types from    Slimefun itself will automatically add the recipe to that machine.
         */
        SlimefunItem coolDiamondItem = new SlimefunItem(learningCategory, coolDiamondIS, RecipeType.ENHANCED_CRAFTING_TABLE, coolDiamondRecipe);
        coolDiamondItem.register(this);
	}	
}
