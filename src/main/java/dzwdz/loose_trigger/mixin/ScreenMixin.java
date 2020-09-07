package dzwdz.loose_trigger.mixin;

import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Screen.class)
public class ScreenMixin {
    @ModifyArg(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/network/ClientPlayerEntity;sendChatMessage(Ljava/lang/String;)V"
            ), method = "sendMessage(Ljava/lang/String;Z)V", index = 0
    )
    private String replaceChatMessage(String og) {
        if (og.length() > 1 && og.charAt(0) == ';') {
            String[] parts = og.substring(1).split(" ", 2);
            if (parts.length == 1) {
                return String.format("/trigger %s", parts[0]);
            } else {
                return String.format("/trigger %s set %s", parts[0], parts[1]);
            }
        }
        return og;
    }
}
