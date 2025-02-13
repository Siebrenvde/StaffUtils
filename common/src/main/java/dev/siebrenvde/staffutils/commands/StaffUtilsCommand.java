package dev.siebrenvde.staffutils.commands;

import com.mojang.brigadier.arguments.*;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.siebrenvde.configlib.libs.quilt.config.api.Constraint;
import dev.siebrenvde.configlib.libs.quilt.config.api.ReflectiveConfig;
import dev.siebrenvde.configlib.libs.quilt.config.api.annotations.Comment;
import dev.siebrenvde.configlib.libs.quilt.config.api.values.*;
import dev.siebrenvde.configlib.metadata.SkipWrite;
import dev.siebrenvde.staffutils.api.command.BaseCommand;
import dev.siebrenvde.staffutils.api.command.BrigadierCommandManager;
import dev.siebrenvde.staffutils.api.command.CommandSender;
import dev.siebrenvde.staffutils.config.Config;
import dev.siebrenvde.staffutils.config.annotations.WordString;
import dev.siebrenvde.staffutils.messages.Messages;
import dev.siebrenvde.staffutils.util.Permissions;
import org.jspecify.annotations.NullMarked;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.JoinConfiguration;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;

import java.util.ArrayList;
import java.util.List;

import static dev.siebrenvde.configlib.libs.quilt.config.impl.util.SerializerUtils.getSerializedName;
import static dev.siebrenvde.configlib.utils.ConfigUtils.getDisplayName;
import static dev.siebrenvde.staffutils.util.BrigadierUtils.hasPermission;
import static dev.siebrenvde.staffutils.util.BrigadierUtils.withSender;
import static net.kyori.adventure.text.Component.*;

@NullMarked
public class StaffUtilsCommand extends BaseCommand {

    public StaffUtilsCommand() {
        super(Config.commands().staffUtils, Permissions.COMMAND_STAFFUTILS);
    }

    @Override
    public <C> LiteralCommandNode<C> brigadier(BrigadierCommandManager<C> manager) {
        return manager.literal(getName())
            .then(manager.literal("reload")
                .requires(hasPermission(Permissions.COMMAND_STAFFUTILS_RELOAD))
                .executes(withSender((ctx, sender) -> {
                    executeReload(sender);
                }))
            )
            .then(configLiteral(manager, Config.config()))
            .then(configLiteral(manager, Config.messages()))
            .then(configLiteral(manager, Config.commands()))
            .build();
    }

    @Override
    public void simple(CommandSender sender, String[] args) {
        if(!checkPermission(sender, getRootPermission())) return;
        if(args.length == 1 && args[0].equals("reload") && checkPermission(sender, Permissions.COMMAND_STAFFUTILS_RELOAD)) {
            executeReload(sender);
        } else {
            sender.sendMessage(Messages.staffUtils().usage());
        }
    }

    @Override
    public List<String> suggestions(CommandSender sender, String[] args) {
        if(args.length == 1 && "reload".startsWith(args[0].toLowerCase())) return List.of("reload");
        return List.of();
    }

    private void executeReload(CommandSender sender) {
        Config.reload();
        sender.sendMessage(Messages.staffUtils().reloadedConfigs());
    }

    private <C> LiteralArgumentBuilder<C> configLiteral(BrigadierCommandManager<C> manager, ReflectiveConfig config) {
        LiteralArgumentBuilder<C> builder = manager.literal(config.id());
        builder.requires(hasPermission(Permissions.COMMAND_STAFFUTILS + "." + config.id()));
        createCommandNodes(manager, builder, config.nodes());
        return builder;
    }

    private <C> void createCommandNodes(BrigadierCommandManager<C> manager, LiteralArgumentBuilder<C> builder, Iterable<ValueTreeNode> nodes) {
        for(ValueTreeNode node : nodes) {
            if(node.hasMetadata(SkipWrite.TYPE)) continue;

            LiteralArgumentBuilder<C> nodeLiteral = manager.literal(getSerializedName(node));

            if(node instanceof TrackedValue<?> value) {

                nodeLiteral.then(manager.literal("get")
                    .executes(withSender((ctx, sender) -> {
                        sender.sendMessage(
                            text()
                                .append(valueComponent(value))
                                .append(text(": "))
                                .append(asComponent(value.getRealValue()).color(NamedTextColor.YELLOW))
                                .build()
                        );
                    }))
                );

                nodeLiteral.then(manager.literal("reset")
                    .executes(withSender((ctx, sender) -> {
                        resetValue(value);
                    }))
                );

                if(value.getRealValue() instanceof ValueList<?> list) {

                    ArgumentType<?> type = asArgumentType(list.getType(), value);

                    nodeLiteral.then(manager.literal("add")
                        .then(manager.argument("value", type)
                            .executes(withSender((ctx, sender) -> {
                                Object input = fromArgumentType(ctx, "value", type);
                                addValueToList(list, input);
                                sender.sendMessage(asComponent(input));
                            }))
                        )
                    );

                    nodeLiteral.then(manager.literal("remove")
                        .then(manager.argument("index", IntegerArgumentType.integer(0))
                            .suggests((ctx, suggestions) -> {
                                int index = 0;
                                for(Object obj : (ValueList<?>) value.getRealValue()) {
                                    if(suggestions.getRemaining().isEmpty() || suggestions.getRemaining().startsWith(String.valueOf(index))) {
                                        suggestions.suggest(index, manager.message(asString(obj)));
                                    }
                                    index++;
                                }
                                return suggestions.buildFuture();
                            })
                            .executes(withSender((ctx, sender) -> {
                                int index = IntegerArgumentType.getInteger(ctx, "index");
                                if(index > list.size() - 1) {
                                    sender.sendMessage(
                                        text()
                                            .append(text("Out of range"))
                                            .appendNewline()
                                            .append(text("Size: " + list.size()))
                                            .build()
                                    );
                                    return;
                                }
                                sender.sendMessage(asComponent(list.get(index)));
                                list.remove(index);
                            }))
                        )
                    );

                    nodeLiteral.then(manager.literal("clear")
                        .executes(withSender((ctx, sender) -> {
                            list.clear();
                            sender.sendMessage(text("Cleared"));
                        }))
                    );

                } else if(value.getRealValue() instanceof ValueMap<?> map) {

                    // TODO: Implement

                    nodeLiteral.then(manager.literal("put"));

                    nodeLiteral.then(manager.literal("remove"));

                    nodeLiteral.then(manager.literal("clear"));

                } else {

                    ArgumentType<?> type = asArgumentType(value.getRealValue(), value);

                    nodeLiteral.then(manager.literal("set")
                        .then(manager.argument("value", type)
                            .executes(withSender((ctx, sender) -> {
                                Object input = fromArgumentType(ctx, "value", type);
                                setValue(value, input);
                                sender.sendMessage(asComponent(input));
                            }))
                        )
                    );

                }

            } else {
                createCommandNodes(manager, nodeLiteral, (ValueTreeNode.Section) node);
            }

            builder.then(nodeLiteral);
        }
    }

    private Component asComponent(Object value) {
        switch (value) {
            case ValueMap<?> map -> {
                List<Component> values = new ArrayList<>();
                map.forEach((key, mapValue) -> {
                    values.add(text(key + " = ").append(asComponent(mapValue)));
                });
                return newline().append(join(
                    JoinConfiguration.newlines(),
                    values
                ));
            }
            case ValueList<?> list -> {
                return join(
                    JoinConfiguration.arrayLike(),
                    list.stream().map(this::asComponent).toList()
                );
            }
            case ConfigSerializableObject<?> obj -> { return asComponent(obj.getRepresentation()); }
            case String string -> { return text("\"" + string + "\""); }
            case Enum<?> enumValue -> { return text("\"" + enumValue.name() + "\""); }
            case Number number -> { return text(number.toString()); }
            case Boolean bool -> { return text(bool.toString()); }
            default -> {}
        }
        return Component.empty();
    }

    private ArgumentType<?> asArgumentType(Object value, TrackedValue<?> trackedValue) {
        switch (value) {
            case ConfigSerializableObject<?> obj -> {
                return asArgumentType(obj.getRepresentation(), trackedValue);
            }
            case String ignored -> {
                return trackedValue.hasMetadata(WordString.TYPE)
                    ? StringArgumentType.word()
                    : StringArgumentType.greedyString();
            }
            case Enum<?> ignored -> {
                return StringArgumentType.word();
            }
            case Boolean ignored -> {
                return BoolArgumentType.bool();
            }
            case Number ignored -> {
                boolean hasRange = false;
                Object min = null;
                Object max = null;

                for (Constraint<?> constraint : trackedValue.constraints()) {
                    if (constraint instanceof Constraint.Range<?> range) {
                        hasRange = true;
                        min = range.min();
                        max = range.max();
                    }
                }

                switch (value) {
                    case Integer ignored1 -> {
                        if (hasRange) return IntegerArgumentType.integer((int) min, (int) max);
                        return IntegerArgumentType.integer();
                    }
                    case Double ignored1 -> {
                        if (hasRange) return DoubleArgumentType.doubleArg((double) min, (double) max);
                        return DoubleArgumentType.doubleArg();
                    }
                    case Float ignored1 -> {
                        if (hasRange) return FloatArgumentType.floatArg((float) min, (float) max);
                        return FloatArgumentType.floatArg();
                    }
                    case Long ignored1 -> {
                        if (hasRange) return LongArgumentType.longArg((long) min, (long) max);
                        return LongArgumentType.longArg();
                    }
                    default -> {}
                }
            }
            default -> {}
        }
        return StringArgumentType.word();
    }

    private <C> Object fromArgumentType(CommandContext<C> ctx, String name, ArgumentType<?> type) {
        return switch (type) {
            case StringArgumentType ignored -> StringArgumentType.getString(ctx, name);
            case BoolArgumentType ignored -> BoolArgumentType.getBool(ctx, name);
            case IntegerArgumentType ignored -> IntegerArgumentType.getInteger(ctx, name);
            case DoubleArgumentType ignored -> DoubleArgumentType.getDouble(ctx, name);
            case FloatArgumentType ignored -> FloatArgumentType.getFloat(ctx, name);
            case LongArgumentType ignored -> LongArgumentType.getLong(ctx, name);
            default -> throw new IllegalArgumentException("Unknown argument type: " + type);
        };
    }

    private String asString(Object value) {
        return switch (value) {
            case ConfigSerializableObject<?> obj -> asString(obj.getRepresentation());
            case String string -> string;
            case Enum<?> enumValue -> enumValue.name();
            case Number number -> number.toString();
            case Boolean bool -> bool.toString();
            default -> "";
        };
    }

    private Component valueComponent(ValueTreeNode node) {
        TextComponent.Builder name = text();
        name.append(text(getDisplayName(node)));

        if(node.hasMetadata(Comment.TYPE)) {
            List<Component> comments = new ArrayList<>();
            node.metadata(Comment.TYPE).forEach(comment -> comments.add(text(comment)));
            name.hoverEvent(HoverEvent.showText(join(
                JoinConfiguration.newlines(),
                comments
            )));
        }

        return name.build();
    }

    @SuppressWarnings("unchecked")
    private <T> void setValue(TrackedValue<T> tracked, Object value) {
        tracked.setValue((T) value);
    }

    @SuppressWarnings("unchecked")
    private <T> void addValueToList(ValueList<T> list, Object value) {
        list.add((T) value);
    }

    private <T> void resetValue(TrackedValue<T> value) {
        value.setValue(value.getDefaultValue());
    }

}
