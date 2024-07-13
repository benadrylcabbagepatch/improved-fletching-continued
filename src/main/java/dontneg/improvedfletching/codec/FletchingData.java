package dontneg.improvedfletching.codec;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.util.math.BlockPos;

public record FletchingData(BlockPos pos) {
    public static final PacketCodec<RegistryByteBuf, FletchingData> PACKET_CODEC = PacketCodec.tuple(
            BlockPos.PACKET_CODEC, FletchingData::pos, FletchingData::new
    );
}
