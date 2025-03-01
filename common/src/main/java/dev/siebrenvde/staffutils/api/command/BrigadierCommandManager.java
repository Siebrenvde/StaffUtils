package dev.siebrenvde.staffutils.api.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.CommandNode;
import dev.siebrenvde.staffutils.messages.Messages;
import org.jspecify.annotations.NullMarked;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A command manager that uses its own {@link CommandDispatcher}
 * @param <C> The server software's command sender
 */
@NullMarked
public class BrigadierCommandManager<C> implements CommandManager<C> {

    private final CommandDispatcher<C> dispatcher;

    public BrigadierCommandManager() {
        dispatcher = new CommandDispatcher<>();
    }

    @Override
    public void register(BaseCommand<C> command) {
        dispatcher.register(command.builder());
    }

    protected void execute(C source, BaseCommand<C> command, String[] args) {
        CommandSender sender = CommandSender.of(source);

        if (command.getRootPermission() != null && !sender.hasPermission(command.getRootPermission())) {
            sender.sendMessage(Messages.commands().permissionMessage());
            return;
        }

        ParseResults<C> results = dispatcher.parse(
            (command.getName() + " " + String.join(" ", args)).trim(),
            source
        );

        try {
            dispatcher.execute(results);
        } catch (CommandSyntaxException e) {
            Map<CommandNode<C>, String> usages = dispatcher.getSmartUsage(
                results.getContext().getNodes().getLast().getNode(),
                source
            );

            if (usages.isEmpty()) {
                sender.sendMessage(Messages.commands().permissionMessage());
                return;
            }

            String nodes = results.getContext().getNodes().stream()
                .map(node -> node.getNode().getUsageText())
                .collect(Collectors.joining(" "));

            if (usages.size() == 1) {
                sender.sendMessage(Messages.commands().usage(
                    String.format("/%s %s", nodes, usages.values().iterator().next())
                ));
                return;
            }

            sender.sendMessage(Messages.commands().multilineUsage(
                usages.values().stream()
                    .map(usage -> String.format("/%s %s", nodes, usage))
                    .toList()
            ));
        }
    }

    protected List<String> suggest(C source, BaseCommand<C> command, String[] args) {
        List<String> completions = new ArrayList<>();

        dispatcher.getCompletionSuggestions(
            dispatcher.parse(command.getName() + " " + String.join(" ", args), source)
        ).thenAccept(suggestions -> {
            suggestions.getList().forEach(suggestion -> {
                completions.add(suggestion.getText());
            });
        });

        return completions;
    }

}
