package net.kaupenjoe.tutorialmod.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.LeashKnotEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LivingTableEntity extends HostileEntity implements GeoEntity {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public LivingTableEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return AnimalEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 16.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.4f);
    }
    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.add(4, new LookAroundGoal(this));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true) {
            public boolean canStart() {
                if (this.target != null) {
                    return this.mob.distanceTo(this.target) <= 10.0D && super.canStart();
                } else {
                    return false;
                }
            }
        });
    }





    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    // Variable para rastrear si el jugador estaba cerca en el último tick


    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
        if (this.isDead()) {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("die", Animation.LoopType.PLAY_ONCE));
        } else if (this.isAttacking()) {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("attackk", Animation.LoopType.LOOP));
        } else if (tAnimationState.isMoving()) {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("caminar2", Animation.LoopType.LOOP));
        } else if (isPlayerNear()) {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("attack", Animation.LoopType.HOLD_ON_LAST_FRAME));
            wasPlayerNear = true;
        } else if (wasPlayerNear) {
            if (attackkkAnimationTime > 0) {
                tAnimationState.getController().setAnimation(RawAnimation.begin().then("attackkk", Animation.LoopType.HOLD_ON_LAST_FRAME));
                attackkkAnimationTime--;
            } else {
                wasPlayerNear = false;
                tAnimationState.getController().setAnimation(RawAnimation.begin().then("hiden", Animation.LoopType.LOOP));
            }
        } else {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("hiden", Animation.LoopType.LOOP));
        }
        return PlayState.CONTINUE;
    }

    private boolean isBiting = false;
    private int biteDuration = 0;
    private boolean isPlayerFar = false;
    private int attackkkAnimationTime = 0;
    // Variable para rastrear si el jugador estaba cerca en el último tick
    private boolean wasPlayerNear = false;

    public boolean isPlayerNear() {
        List<PlayerEntity> players = (List<PlayerEntity>) this.world.getPlayers();
        double detectionRadius = 10.0D;

        boolean playerNear = false;

        for (PlayerEntity player : players) {
            double distance = this.distanceTo(player);
            if (distance < detectionRadius) {
                playerNear = true;
                break;
            }
        }

        if (playerNear) {
            wasPlayerNear = true;
            attackkkAnimationTime = 280; // Ajusta este valor al tiempo de duración de tu animación
        }

        return playerNear;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
