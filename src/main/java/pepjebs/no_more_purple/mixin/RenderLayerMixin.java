package pepjebs.no_more_purple.mixin;


import net.minecraft.client.render.RenderLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pepjebs.no_more_purple.client.NoMorePurpleClientMod;

@Mixin(RenderLayer.class)
public class RenderLayerMixin {



    @Inject(method="getArmorGlint",at=@At("HEAD"), cancellable = true)
    private static void noMorePurple$getArmourGlint(CallbackInfoReturnable<RenderLayer> cir) {
        if (!NoMorePurpleClientMod.CONFIG.enabled) return;
        cir.setReturnValue(NoMorePurpleClientMod.getArmorGlint());

    }

    @Inject(method="getArmorEntityGlint",at=@At("HEAD"), cancellable = true)
    private static void noMorePurple$getArmorEntityGlint(CallbackInfoReturnable<RenderLayer> cir) {
        if (!NoMorePurpleClientMod.CONFIG.enabled) return;
        cir.setReturnValue(NoMorePurpleClientMod.getArmorEntityGlint());
    }

    @Inject(method="getGlintTranslucent",at=@At("HEAD"), cancellable = true)
    private static void noMorePurple$getGlintTranslucent(CallbackInfoReturnable<RenderLayer> cir) {
        if (!NoMorePurpleClientMod.CONFIG.enabled) return;
        cir.setReturnValue(NoMorePurpleClientMod.getGlintTransluncent());
    }

    @Inject(method="getGlint",at=@At("HEAD"), cancellable = true)
    private static void noMorePurple$getGlint(CallbackInfoReturnable<RenderLayer> cir) {
        if (!NoMorePurpleClientMod.CONFIG.enabled) return;
        cir.setReturnValue(NoMorePurpleClientMod.getGlint());

    }

    @Inject(method="getDirectGlint",at=@At("HEAD"), cancellable = true)
    private static void noMorePurple$getDirectGlint(CallbackInfoReturnable<RenderLayer> cir) {
        if (!NoMorePurpleClientMod.CONFIG.enabled) return;
        cir.setReturnValue(NoMorePurpleClientMod.getGlintDirect());

    }
    @Inject(method="getEntityGlint",at=@At("HEAD"), cancellable = true)
    private static void noMorePurple$getEntityGlint(CallbackInfoReturnable<RenderLayer> cir) {
        if (!NoMorePurpleClientMod.CONFIG.enabled) return;
        cir.setReturnValue(NoMorePurpleClientMod.getEntityGlint());

    }

    @Inject(method="getDirectEntityGlint",at=@At("HEAD"), cancellable = true)
    private static void noMorePurple$getDirectEntityGlint(CallbackInfoReturnable<RenderLayer> cir) {
        if (!NoMorePurpleClientMod.CONFIG.enabled) return;
        cir.setReturnValue(NoMorePurpleClientMod.getEntityGlintDirect());

    }

}
