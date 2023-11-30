package net.kaupenjoe.tutorialmod.entity.custom;

import net.kaupenjoe.tutorialmod.entity.ModEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

import java.util.List;

public class GreatHunterEntity extends HostileEntity  implements GeoEntity,GeoAnimatable {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public GreatHunterEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }


    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 16.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.4f);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.2D, false));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 0.75f, 1));
        this.goalSelector.add(4, new LookAroundGoal(this));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true)); // Ataca solo a los jugadores

        // Nuevo objetivo para aplicar oscuridad y parálisis
        this.goalSelector.add(5, new DarknessAndParalysisGoal(this));
    }



    private int invisibleTicks = 0;
    private int animationTicks = 0;
    private String animationState = "idle";

    @Override
    public void tick() {
        super.tick();

        if (this.world == null || this.getBlockPos() == null) {
            return;
        }

        BlockPos downPos = this.getBlockPos().down();
        List<PlayerEntity> players = this.world.getEntitiesByClass(PlayerEntity.class, this.getBoundingBox().expand(2), player -> true);

        if (!players.isEmpty()) {
            // Hacer que la entidad se vuelva visible
            this.setInvisible(false);
            this.invisibleTicks = 0;
            this.animationTicks = 0;
            this.animationState = "idle";
        } else {
            this.invisibleTicks++;
            if (this.invisibleTicks >= 160) { // 8  segundos * 20 ticks/segundo
                // Comprobar si la entidad está en un bloque de SOUL_SAND
                if (this.world.getBlockState(downPos).isOf(Blocks.SOUL_SAND) || this.world.getBlockState(downPos).isOf(Blocks.SOUL_SOIL)) {
                    // Hacer que la entidad se vuelva invisible
                    this.setInvisible(true);

                    // Mostrar partículas
                    this.world.addParticle(ParticleTypes.SOUL, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
                } else {
                    // Hacer que la entidad se vuelva visible
                    this.setInvisible(false);
                }
            }
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
        if (this.getHealth() <= 1) {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("die", Animation.LoopType.HOLD_ON_LAST_FRAME));
        } else if (this.isAttacking()) {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("attack", Animation.LoopType.HOLD_ON_LAST_FRAME));
        } else if (tAnimationState.isMoving() && this.animationState.equals("idle")) {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
        } else if (this.invisibleTicks >= 130 && this.invisibleTicks < 160) { // 3 segundos * 20 ticks/segundo
            this.animationState = "entrar_al_suelo";
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("entrar_al_suelo", Animation.LoopType.HOLD_ON_LAST_FRAME));
        } else if (this.invisibleTicks >= 0 && this.invisibleTicks < 70) { // 3.5 segundos * 20 ticks/segundo
            this.animationState = "salir_del_suelo";
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("salir_del_suelo", Animation.LoopType.HOLD_ON_LAST_FRAME));
        } else {
            this.animationState = "idle";
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        }
        return PlayState.CONTINUE;
    }





    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    // Nueva clase para el objetivo personalizado
    private static class DarknessAndParalysisGoal extends Goal {
        private final GreatHunterEntity hunterEntity;
        private int ticks = 0;

        public DarknessAndParalysisGoal(GreatHunterEntity hunterEntity) {
            this.hunterEntity = hunterEntity;
        }

        @Override
        public boolean canStart() {
            LivingEntity target = this.hunterEntity.getTarget();
            return target != null && target.squaredDistanceTo(this.hunterEntity) <= 25;
        }



        @Override
        public void start() {
            LivingEntity target = this.hunterEntity.getTarget();
            if (target instanceof PlayerEntity) {
                // Aplicar efectos de oscuridad y parálisis
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 200)); // 10 segundos de oscuridad
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 255)); // 10 segundos de lentitud máxima
                this.ticks = 0;
            }
        }




        @Override
        public void tick() {
            super.tick();
            if (++this.ticks >= 100) { // 5 segundos * 20 ticks/segundo
                this.hunterEntity.getTarget().addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 0, 0)); // Eliminar la lentitud después de 5 segundos
            }


        }

        @Override
        public boolean shouldContinue() {
            LivingEntity target = this.hunterEntity.getTarget();
            return target != null && target.squaredDistanceTo(this.hunterEntity) <= 25 && this.ticks < 100;
        }
    }
}
