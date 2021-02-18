package draylar.glimmeringpotions.mixin;

import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.GoToWorkTask;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.dynamic.GlobalPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.VillagerProfession;
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
    public void run(ServerWorld world, VillagerEntity villagerEntity, long l) {
        Optional<GlobalPos> optionalMemory = villagerEntity.getBrain().getOptionalMemory(MemoryModuleType.POTENTIAL_JOB_SITE);

        if(!optionalMemory.isPresent()) {
            return;
        }

        GlobalPos globalPos = optionalMemory.get();
        villagerEntity.getBrain().forget(MemoryModuleType.POTENTIAL_JOB_SITE);
        villagerEntity.getBrain().remember(MemoryModuleType.JOB_SITE, globalPos);

        if (villagerEntity.getVillagerData().getProfession() == VillagerProfession.NONE) {
            MinecraftServer minecraftServer = world.getServer();
            List<VillagerProfession> validProfessions = new ArrayList<>();

            // Filter down to valid professions for this instance
            Optional.ofNullable(minecraftServer.getWorld(globalPos.getDimension())).flatMap((worldX) -> {
                return worldX.getPointOfInterestStorage().getType(globalPos.getPos());
            }).flatMap((pointOfInterestType) -> {
                return Registry.VILLAGER_PROFESSION.stream().filter((villagerProfession) -> {
                    return villagerProfession.getWorkStation() == pointOfInterestType;
                }).findAny();
            }).ifPresent(validProfessions::add);

            // Assign random profession
            VillagerProfession selected = validProfessions.get(world.random.nextInt(validProfessions.size()));
            villagerEntity.setVillagerData(villagerEntity.getVillagerData().withProfession(selected));
            villagerEntity.reinitializeBrain(world);
        }
    }
}