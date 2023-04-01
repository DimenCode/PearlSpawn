package fr.dimenspace.pearlspawn;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class PearlSpawn extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("the plugin has started hello !");
        System.out.println("like hello, is has started");
        System.out.println("for real, it's working mf");

        // registering the event listener
        getServer().getPluginManager().registerEvents(this, this);
        // "this" because this class is a listener and also a plugin
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("The plugin has stopped, how sad");
    }

    public boolean isInArea(Location location) {

        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();

        // Spawn minimum location
        Location min = new Location(Bukkit.getWorld("world"), -250, -64, -250);
        // Spawn maximum location
        Location max = new Location(Bukkit.getWorld("world"), 250, 300, 250);

        double xMin = Math.min(min.getX(), max.getX());
        double yMin = Math.min(min.getY(), max.getY());
        double zMin = Math.min(min.getZ(), max.getZ());

        double xMax = Math.max(min.getX(), max.getX());
        double yMax = Math.max(min.getY(), max.getY());
        double zMax = Math.max(min.getZ(), max.getZ());

        // determines whether the pearl landed in Spawn or no
        return x >= xMin && x <= xMax && y >= yMin && y <= yMax && z >= zMin && z <= zMax;
}

    // creating the event listener
    @EventHandler
    public void ProjectileHitEvent(ProjectileHitEvent event) {
        // check if it's an enderpearl and check if the thrower is a player
        if (event.getEntity() instanceof EnderPearl && event.getEntity().getShooter() instanceof Player) {

            Location throwerLoc = ((Player) event.getEntity().getShooter()).getLocation();
            // checks if both the pearl thrown and the thrower are in the area
            if (isInArea(event.getEntity().getLocation()) && isInArea(throwerLoc)) {
                event.setCancelled(true);
                event.getEntity().remove();
            }
        }

    }

}
