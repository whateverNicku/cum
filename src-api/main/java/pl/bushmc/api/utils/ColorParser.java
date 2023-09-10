package pl.bushmc.api.utils;

import lombok.extern.java.Log;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

import java.util.List;
import java.util.stream.Collectors;

@Log
public class ColorParser {

    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    public ColorParser() {
        log.info("ColorParser loaded");
    }

    public Component deserialize(String string) {
        return miniMessage.deserialize(string);
    }

    public List<Component> deserializeList(List<String> strings) {
        return strings.stream()
                .map(miniMessage::deserialize)
                .collect(Collectors.toList());
    }
}
