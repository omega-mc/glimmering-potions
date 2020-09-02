package draylar.glimmeringpotions.mixin;

import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.GoToWorkTask;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.dynamic.GlobalPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mixin(GoToWorkTask.class)
public class GoToWorkTaskMixin {

    /**
     * {@link GoToWorkTask#run(ServerWorld, VillagerEntity, long)}'s profession selecting logic assumes all Professions have a unique POI station.
     * Glimmering Potions' Alchemist Profession uses the brewing stand as its POI type, which is also what the Cleric uses.
     * This fix makes Villagers pick up a random Profession related to the given POI station instead of the first available one.
     *
     * @author Draylar
     */
    @Overwrite
    public void run(ServerWorld serverWorld, VillagerEntity villagerEntity, long l) {
        GlobalPos pos = villagerEntity.getBrain().getOptionalMemory(MemoryModuleType.JOB_SITE).get();
        MinecraftServer server = serverWorld.getServer();
        Optional<PointOfInterestType> foundType = server.getWorld(pos.getDimension()).getPointOfInterestStorage().getType(pos.getPos());

        // if the POI type of the position is valid
        if(foundType.isPresent()) {
            PointOfInterestType type = foundType.get();

            // find all valid professions that match the position's POI type
            List<VillagerProfession> validProfessions = new ArrayList<>();
            for (VillagerProfession profession : Registry.VILLAGER_PROFESSION) {
                if (profession.getWorkStation().equals(type)) {
                    validProfessions.add(profession);
                }
            }

            // select a random profession and assign it to the villager
            if(!validProfessions.isEmpty()) {
                VillagerProfession selectedProfession = validProfessions.get(villagerEntity.getRandom().nextInt(validProfessions.size()));
                villagerEntity.setVillagerData(villagerEntity.getVillagerData().withProfession(selectedProfession));
                villagerEntity.reinitializeBrain(serverWorld);
            }
        }
    }
}